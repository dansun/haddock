package nu.danielsundberg.persistence.haddock;

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

import nu.danielsundberg.persistence.AbstractPersistenceJPATest;
import org.junit.Test;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Basic testing for Haddock entities.
 */
public class EntityTest extends AbstractPersistenceJPATest {
	
	@Test
	public void testEntity() {
		new TestEntity();
	}
	
	@Entity
	@Table(name="TEST")
	public class TestEntity implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@SequenceGenerator(name="testGenerator", sequenceName="TESTSEQ")
		@GeneratedValue(generator="testGenerator")
		@Column(name="TESTID")
		private long id;

		public void setId(long id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}
	}
	
}
