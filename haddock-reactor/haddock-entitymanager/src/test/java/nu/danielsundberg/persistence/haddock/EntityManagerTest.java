package nu.danielsundberg.persistence.haddock;

import nu.danielsundberg.persistence.AbstractPersistenceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.spi.PersistenceUnitInfo;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

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

/**
 * Tests for default Haddock entity manager.
 */
@RunWith(MockitoJUnitRunner.class)
public class EntityManagerTest extends AbstractPersistenceTest {

    private static final Long ENTITY_ID = 1l;

    @Mock private PersistenceUnitInfo persitenceUnit;

    private EntityManager entityManager;
    private Map<String, String> properties;
	
	@Before
	public void setup() {
		properties = new HashMap<String, String>();
		entityManager = new HaddockEntityManagerFactory(persitenceUnit, properties)
                .createEntityManager();
	}
	
	@Test
	public void testClear() {
        EntityManagerTestEntity entity = new EntityManagerTestEntity(ENTITY_ID);
        assertThat(entityManager.contains(entity), is(Boolean.FALSE));
        entityManager.persist(entity);
        assertThat(entityManager.contains(entity), is(Boolean.TRUE));
		entityManager.clear();
        assertThat(entityManager.contains(entity), is(Boolean.FALSE));
	}

    @Test
    public void testContains() {
        EntityManagerTestEntity entity = new EntityManagerTestEntity(ENTITY_ID);
        assertThat(entityManager.contains(entity), is(Boolean.FALSE));
        entityManager.persist(entity);
        assertThat(entityManager.contains(entity), is(Boolean.TRUE));
    }

    @Test
    public void testPersist() {
        EntityManagerTestEntity entity = new EntityManagerTestEntity(ENTITY_ID);
        assertThat(entityManager.contains(entity), is(Boolean.FALSE));
        entityManager.persist(entity);
        assertThat(entityManager.contains(entity), is(Boolean.TRUE));
    }

    @Test
    public void testRemove() {
        EntityManagerTestEntity entity = new EntityManagerTestEntity(ENTITY_ID);
        assertThat(entityManager.contains(entity), is(Boolean.FALSE));
        entityManager.persist(entity);
        assertThat(entityManager.contains(entity), is(Boolean.TRUE));
        entityManager.remove(entity);
        assertThat(entityManager.contains(entity), is(Boolean.FALSE));
    }

    @Test
    public void testFind() {
        EntityManagerTestEntity entity = new EntityManagerTestEntity(ENTITY_ID);
        assertThat(entityManager.contains(entity), is(Boolean.FALSE));
        entityManager.persist(entity);
        assertThat(entityManager.find(EntityManagerTestEntity.class, ENTITY_ID),
                is(equalTo(entity)));

    }

    @Entity
    public class EntityManagerTestEntity {

        @Id
        private long id;

        EntityManagerTestEntity(long id) {
            this.id = id;
        }

        long getId() {
            return this.id;
        }

    }
	
}
