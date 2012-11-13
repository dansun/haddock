package nu.danielsundberg.persistence.haddock;


public class HaddockEntityManager extends EntityManagerImpl {

	private HaddockEntityManagerFactory entityManagerFactory = null;
	
	public HaddockEntityManager(HaddockEntityManagerFactory haddockEntityManagerFactory) {
		entityManagerFactory = haddockEntityManagerFactory;
	}
	
	@Override
	public void close() {
		super.close();
		entityManagerFactory.close();
	}

}
