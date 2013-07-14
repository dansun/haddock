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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Map;

/**
 * Abstract Haddock entity manager factory
 */
public abstract class AbstractEntityManagerFactory implements EntityManagerFactory {

    /**
     * Indication whether factory is open or not.
     */
	private boolean isOpen = false;

    /**
     * Closes this factory.
     */
	@Override
	public void close() {
        isOpen = false;
    }

    /**
     * Creates entity manager.
     * @return Entity manager.
     */
	@Override
	public abstract EntityManager createEntityManager();


    /**
     * Creates entity manager with given properties.
     * @param properties Given properties set to entity manager.
     * @return Entity manager with given properties.
     */
	@Override
	@SuppressWarnings("rawtypes")
	public abstract EntityManager createEntityManager(Map properties);

    /**
     * Indicates whether the factory is open or not.
     * @return true if factory is open.
     */
	@Override
	public boolean isOpen() {
		return isOpen;
	}
	
}
