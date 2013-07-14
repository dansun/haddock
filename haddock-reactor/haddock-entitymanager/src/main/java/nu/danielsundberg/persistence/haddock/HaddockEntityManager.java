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

/**
 * Haddock entity manager
 */
public class HaddockEntityManager extends EntityManagerImpl {

    /**
     * Entity manager factory
     */
	private final HaddockEntityManagerFactory entityManagerFactory;

    /**
     * Constructor configuring which factory provided entity manager.
     * @param haddockEntityManagerFactory Parent factory of entity manager.
     */
	public HaddockEntityManager(HaddockEntityManagerFactory haddockEntityManagerFactory) {
		entityManagerFactory = haddockEntityManagerFactory;
	}

    /**
     * Close entity manager.
     */
	@Override
	public void close() {
		super.close();
		entityManagerFactory.close();
	}

}
