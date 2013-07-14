package nu.danielsundberg.persistence.haddock;

import javax.persistence.EntityManager;
import javax.persistence.spi.PersistenceUnitInfo;
import java.util.Map;
import java.util.Properties;

/**
 * Haddock specific enitity manager factory
 */
public class HaddockEntityManagerFactory extends AbstractEntityManagerFactory {

    /**
     * Properties för factory
     */
	private static Properties defaultFactoryProperties = new Properties();

    /**
     * Specific persistence unit information
     */
    private PersistenceUnitInfo persistenceUnit;

    /**
     * Static initialization
     */
    static {
		defaultFactoryProperties = new Properties();
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_CONNECTION_URL_PROPERTY.getKeyName(), "");
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_CONNECTION_DRIVER_PROPERTY.getKeyName(), "");
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_CONNECTION_DIALECT_PROPERTY.getKeyName(), "");
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_DDL_GENERATION_PROPERTY.getKeyName(), "");
	}

    /**
     *
     * @param persistenceUnit persistence unit information for factory
     * @param arguments persistence unit information for factory
     */
	public HaddockEntityManagerFactory(PersistenceUnitInfo persistenceUnit, Map<String, String> arguments) {
		this.persistenceUnit = persistenceUnit;
		defaultFactoryProperties.putAll(arguments);
	}

    /**
     * Create new entity manager instance.
     * @return new instance of entity manager
     */
	@Override
	public EntityManager createEntityManager() {
		return new HaddockEntityManager(this);
	}

    /**
     * Create new entity manager instance for provided arguments.
     * @param argumentMap provided arguments
     * @return new instance of entity manager
     */
	@Override
	@SuppressWarnings({"rawtypes" })
	public EntityManager createEntityManager(Map argumentMap) {
		Properties entityManagerProperties = new Properties();
		for(FactoryProperty property : FactoryProperty.values()) {
			if(argumentMap.containsKey(property)) {
				entityManagerProperties.setProperty(property.getKeyName(), 
						(String) argumentMap.get(property.getKeyName()));
			}
		}
		return new HaddockEntityManager(this);
	}

    /**
     * Return factory specific persistence unit information
     * @return persistence unit information
     */
	public String getPersistenceUnitName() {
		return persistenceUnit.getPersistenceUnitName();
	}

    /**
     * Haddock specific persistence unit factory properties
     */
	public enum FactoryProperty {

		HADDOCK_CONNECTION_URL_PROPERTY("haddock.connection.url"),
		HADDOCK_CONNECTION_DRIVER_PROPERTY("haddock.connection.driver_class"),
		HADDOCK_CONNECTION_DIALECT_PROPERTY("haddock.dialect"),
		HADDOCK_DDL_GENERATION_PROPERTY("haddock.ddl.generation");

        private String keyName = null;

        /**
         * Factory property construcor for provided key name
         * @param keyName
         */
		private FactoryProperty(String keyName) {
			this.keyName = keyName;
		}

        /**
         * Returns specified key name for property
         * @return
         */
		public String getKeyName() {
			return keyName;
		}
		
	}
	
}
