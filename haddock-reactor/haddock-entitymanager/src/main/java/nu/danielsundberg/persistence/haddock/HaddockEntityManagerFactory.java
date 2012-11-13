package nu.danielsundberg.persistence.haddock;

import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.spi.PersistenceUnitInfo;

public class HaddockEntityManagerFactory extends AbstractEntityManagerFactory {

	private static Properties defaultFactoryProperties = new Properties();
	private PersistenceUnitInfo persistenceUnit;
	static {
		defaultFactoryProperties = new Properties();
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_CONNECTION_URL_PROPERTY.getKeyName(), "");
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_CONNECTION_DRIVER_PROPERTY.getKeyName(), "");
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_CONNECTION_DIALECT_PROPERTY.getKeyName(), "");
		defaultFactoryProperties.setProperty(FactoryProperty.HADDOCK_DDL_GENERATION_PROPERTY.getKeyName(), "");
	}
	
	public HaddockEntityManagerFactory(PersistenceUnitInfo persistenceUnit, Map<String, String> arguments) {
		this.persistenceUnit = persistenceUnit;
		defaultFactoryProperties.putAll(arguments);
	}
	
	@Override
	public void close() {
		isOpen = false;
	}

	@Override
	public EntityManager createEntityManager() {
		return new HaddockEntityManager(this);
	}

	
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

	public String getPersistenceUnitName() {
		return persistenceUnit.getPersistenceUnitName();
	}
	
	public enum FactoryProperty {
		HADDOCK_CONNECTION_URL_PROPERTY("haddock.connection.url"),
		HADDOCK_CONNECTION_DRIVER_PROPERTY("haddock.connection.driver_class"),
		HADDOCK_CONNECTION_DIALECT_PROPERTY("haddock.dialect"),
		HADDOCK_DDL_GENERATION_PROPERTY("haddock.ddl.generation");
		private String keyName = null;
		
		private FactoryProperty(String keyName) {
			this.keyName = keyName;
		}
		
		public String getKeyName() {
			return keyName;
		}
		
	}
	
}
