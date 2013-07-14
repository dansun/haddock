package nu.danielsundberg.persistence.haddock.ejb;

/*
 *   ___ ___    _____  ________  ________   ________  _________  ____  __.
 *  /   |   \  /  _  \ \______ \ \______ \  \_____  \ \_   ___ \|    |/ _|
 * /    ~    \/  /_\  \ |    |  \ |    |  \  /   |   \/    \  \/|      <  
 * \    Y    /    |    \|    `   \|    `   \/    |    \     \___|    |  \ 
 *  \___|_  /\____|__  /_______  /_______  /\_______  /\______  /____|__ \
 *        \/         \/        \/        \/         \/        \/        \/
 * 
 * Copyright 2012 Daniel Sundberg
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import nu.danielsundberg.persistence.haddock.HaddockConstants;
import nu.danielsundberg.persistence.haddock.HaddockEntityManagerFactory;
import nu.danielsundberg.persistence.haddock.HaddockPersistenceUnitInfo;
import nu.danielsundberg.persistence.haddock.exception.HaddockPersitenceException;
import org.reflections.Reflections;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.MappedSuperclass;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Haddock persistence provider implementation
 */
public class HaddockPersistence implements PersistenceProvider {

    /**
     * Logger for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(HaddockPersistence.class.getSimpleName());

    /**
     * Initialization message containing public version.
     */
    private static final String HADDOCK_PERSISTENCE_INFO    = "Initializing Haddock Persistence " + HaddockConstants.getPublicVersion() + ".\n" +
                                                              "-------------------------------------------------------------------------\n"+
                                                              "   ___ ___    _____  ________  ________   ________  _________  ____  __.\n" +
                                                              "  /   |   \\  /  _  \\ \\______ \\ \\______ \\  \\_____  \\ \\_   ___ \\|    |/ _|\n" +
                                                              " /    ~    \\/  /_\\  \\ |    |  \\ |    |  \\  /   |   \\/    \\  \\/|      <  \n" +
                                                              " \\    Y    /    |    \\|    `   \\|    `   \\/    |    \\     \\___|    |  \\ \n" +
                                                              "  \\___|_  /\\____|__  /_______  /_______  /\\_______  /\\______  /____|__ \\\n" +
                                                              "        \\/         \\/        \\/        \\/         \\/        \\/        \\/\n"+
                                                              "-------------------------------------------------------------------------";
    /**
     * Warning when no entities was found in classpath.
     */
    private static final String NO_ENTITIES_WARNING         = "No entities found in classpath.";

    /**
     * Warning when no mapped superclasses was found in classpath.
     */
    private static final String NO_SUPERCLASSES_WARNING     = "No mapped superclasses found in classpath.";

    /**
     * Warning when no embeddable classes was found in classpath.
     */
    private static final String NO_EMBEDDABLES_WARNING      = "No embeddable classes found in classpath.";

    /**
     * Location of persistence.xml files to search for.
     */
    private static final String PERSISTENCE_XML_NAME = "META-INF/persistence.xml";

    /**
     * Managed persistenceunits.
     */
	private static Set<PersistenceUnitInfo> persistenceUnits = new HashSet<PersistenceUnitInfo>();

    /**
     * Discovered entity classes
     */
    private static Set<Class<?>> discoveredEntityClasses = new HashSet<Class<?>>();

    /**
     * Discovered embeddable classes
     */
    private static Set<Class<?>> discoveredEmbeddableClasses = new HashSet<Class<?>>();

    /**
     * Discovered mapped superclasses
     */
    private static Set<Class<?>> discoveredMappedSuperclasses = new HashSet<Class<?>>();

    /**
     * Static initialization of HaddockPersistence
     */
    static {
        LOGGER.log(Level.INFO, HADDOCK_PERSISTENCE_INFO);
        //
        // Scan for classes on classpath
        //
        initialScanForClasses();
        //
        // Parse resources for persistence.xml
        //
        initialConfigurationFileScan();

    }

    /**
     * Scans classpath for persistence.xml files for persistence unit setup.
     */
    private static void initialConfigurationFileScan() {

        File persistenceUnitFile = new File(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResource(PERSISTENCE_XML_NAME)
                        .getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(persistenceUnitFile);
            doc.getDocumentElement().normalize();
            NodeList persistenceUnitNodes = doc.getElementsByTagName("persistence-unit");
            for (int i = 0; i < persistenceUnitNodes.getLength(); i++) {
                Node node = persistenceUnitNodes.item(i);
                persistenceUnits.add(new HaddockPersistenceUnitInfo(
                        node.getAttributes().getNamedItem("name").getTextContent(),
                        PersistenceUnitTransactionType.valueOf(
                                node.getAttributes().getNamedItem("transaction-type").getTextContent())));
            }
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, "Parser-fail.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO-fail.");
        } catch (SAXException e){
            LOGGER.log(Level.SEVERE, "SAX-fail.");
        }
    }

    /**
     * Scan for classes with persistence annotations
     */
    private static void initialScanForClasses() {
        //
        // Get reflections for classpath operations
        //
        Reflections reflections = new Reflections();
        //
        // Discover all Entity annotated classes.
        //
        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
        if(entityClasses.isEmpty()) {
            //
            // If no classes is annotated with MappedSuperclass
            //
            LOGGER.log(Level.WARNING, NO_ENTITIES_WARNING);
        } else {
            //
            // Link discovered classes to persistence
            //
            discoveredEntityClasses = entityClasses;
        }
        //
        // Discover all MappedSuperclass annotated classes
        //
        Set<Class<?>> mappedSuperclasses = reflections.getTypesAnnotatedWith(MappedSuperclass.class);
        if(mappedSuperclasses.isEmpty()) {
            //
            // If no classes is annotated with Entity
            //
            LOGGER.log(Level.WARNING, NO_SUPERCLASSES_WARNING);
        } else {
            //
            // Link discovered classes to persistence
            //
            discoveredMappedSuperclasses = mappedSuperclasses;
        }
        //
        // Discover all Embeddable annotated classes
        //
        Set<Class<?>> embeddableClasses = reflections.getTypesAnnotatedWith(Embeddable.class);
        if(embeddableClasses.isEmpty()) {
            //
            // If no classes is annotated with Embeddable
            //
            LOGGER.log(Level.WARNING, NO_EMBEDDABLES_WARNING);
        } else {
            //
            // Link discovered classes to persistence
            //
            discoveredEmbeddableClasses = embeddableClasses;
        }
    }

    /**
     * Create factory for producing entity managers for provided persistence unit information with arguments.
     * @param persistenceUnitInfo Information about a persistence unit.
     * @param properties arguments passed to provider.
     * @return Factory that provides entity managers with provided arguments.
     */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo persistenceUnitInfo, Map properties) {
        properties.putAll(persistenceUnitInfo.getProperties());
		return new HaddockEntityManagerFactory(persistenceUnitInfo, properties);
	}

    /**
     * Create factory for producing entity manager for named persistence unit name with provided properties.
     * @param persistenceUnitName Name of persistence unit.
     * @param properties Properties of given entity manager.
     * @return Factory that provides entity managers for named persistence unit.
     */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EntityManagerFactory createEntityManagerFactory(String persistenceUnitName, Map properties) {
		for(PersistenceUnitInfo persistenceUnit : persistenceUnits) {
			if(persistenceUnit.getPersistenceUnitName().equals(persistenceUnitName)) {
				return new HaddockEntityManagerFactory(persistenceUnit, properties);
			}
		}
		throw new HaddockPersitenceException("No persistenceunit with name "+persistenceUnitName+" was found.");
	}

}
