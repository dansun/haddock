package nu.danielsundberg.persistence.haddock;

import nu.danielsundberg.persistence.AbstractTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class HaddockPersistenceUnitInfoTest extends AbstractTest {

    private final static String PERSISTENCE_UNIT_NAME = "UNIT_NAME";
    private final static PersistenceUnitTransactionType PERSISTENCE_UNIT_TRANSACTION_TYPE =
            PersistenceUnitTransactionType.RESOURCE_LOCAL;
    private PersistenceUnitInfo persistenceUnitInfo;


    @Test
    public void testHaddockPersistenceUnitName() throws Exception {
        persistenceUnitInfo = new HaddockPersistenceUnitInfo(
                PERSISTENCE_UNIT_NAME,
                PERSISTENCE_UNIT_TRANSACTION_TYPE);
        assertThat(persistenceUnitInfo.getPersistenceUnitName(), is(PERSISTENCE_UNIT_NAME));
    }

    @Test
    public void testHaddockPersistenceUnitTransactionTypeResourceLocal() throws Exception {
        persistenceUnitInfo = new HaddockPersistenceUnitInfo(
                PERSISTENCE_UNIT_NAME,
                PersistenceUnitTransactionType.RESOURCE_LOCAL);
        assertThat(persistenceUnitInfo.getTransactionType(), is(PersistenceUnitTransactionType.RESOURCE_LOCAL));
    }

    @Test
    public void testHaddockPersistenceUnitTransactionTypeJTA() throws Exception {
        persistenceUnitInfo = new HaddockPersistenceUnitInfo(
                PERSISTENCE_UNIT_NAME,
                PersistenceUnitTransactionType.JTA);
        assertThat(persistenceUnitInfo.getTransactionType(), is(PersistenceUnitTransactionType.JTA));
    }

}
