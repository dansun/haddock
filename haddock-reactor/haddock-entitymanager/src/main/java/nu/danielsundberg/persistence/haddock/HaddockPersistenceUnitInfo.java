package nu.danielsundberg.persistence.haddock;

import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Implementation of Haddock Persistence unit information
 */
public class HaddockPersistenceUnitInfo implements PersistenceUnitInfo {

    /**
     * Name of created persistence unit.
     */
    private final String persistenceUnitName;

    /**
     * Type of transaction for persistence unit.
     */
    private final PersistenceUnitTransactionType transactionType;

    /**
     * Constructor for instance o persistence unit.
     * @param peristenceUnitName name of unit
     * @param transactionType type of transactions for unit
     */
    public HaddockPersistenceUnitInfo(String peristenceUnitName, 
                                      PersistenceUnitTransactionType transactionType) {
        this.persistenceUnitName = peristenceUnitName;
        this.transactionType = transactionType;
    }

    /**
     * Get persistence unit name
     * @return name of persistence unit
     */
    @Override
    public String getPersistenceUnitName() {
        return this.persistenceUnitName;
    }

    /**
     * Get class name of persistence provider for unit
     * @return name of persistence provider
     */
    @Override
    public String getPersistenceProviderClassName() {
        return "HaddockPersistence";
    }

    /**
     * Get type of transactions for persistence unit
     * @return type of transactions
     */
    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return this.transactionType;
    }

    /**
     * Get transaction handled data source for persistence unit.
     * @return data source for persistence unit
     */
    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    /**
     * Get non transaction handled data source for persistence unit
     * @return non handled data source
     */
    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    /**
     * Get mapping file names for persistence unit
     * @return mapping file names
     */
    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    /**
     * Get configured jar file urls
     * @return
     */
    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    /**
     * Get persistence unit root url
     * @return root url
     */
    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    /**
     * Get managed classnames
     * @return list of managed class names
     */
    @Override
    public List<String> getManagedClassNames() {
        return null;
    }

    /**
     * Get exclude unlisted classes
     * @return indication of exclusion of unlisted classes
     */
    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    /**
     * Get configured properties for persistence unit
     * @return configured properties
     */
    @Override
    public Properties getProperties() {
        return null;
    }

    /**
     * Get classloader for persistence unit
     * @return classloader
     */
    @Override
    public ClassLoader getClassLoader() {
        return this.getClassLoader();
    }

    /**
     * Add new transformer
     * @param transformer adds new class transformer
     */
    @Override
    public void addTransformer(ClassTransformer transformer) {

    }

    /**
     * Get new temp class loader
     * @return temp class loader
     */
    @Override
    public ClassLoader getNewTempClassLoader() {
        return ClassLoader.getSystemClassLoader();
    }

}
