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

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;

/**
 * Abstract Haddock entity manager implementation
 */
public abstract class AbstractEntityManagerImpl implements EntityManager{


    @Resource
    private EntityTransaction transaction;

    /**
     * Clears entity manager of all entity states.
     */
	@Override
	public void clear() {
	}

    /**
     * Closes entity manager.
     */
	@Override
	public void close() {
	}

    /**
     * Indicates whether or not entity manager contains given entity
     * @param entity Entity to check in manager
     * @return true if entity manager contains given entity
     */
	@Override
	public boolean contains(Object entity) {
		return false;
	}

	@Override
	public Query createNamedQuery(String arg0) {
		return new QueryImpl();
	}

	@Override
	public Query createNativeQuery(String arg0) {
        return new QueryImpl();
	}

	@Override
	public Query createNativeQuery(String arg0, Class arg1) {
        return new QueryImpl();
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
        return new QueryImpl();
	}

	@Override
	public Query createQuery(String arg0) {
        return new QueryImpl();
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
	}

	@Override
	public Object getDelegate() {
		return null;
	}

	@Override
	public FlushModeType getFlushMode() {
		return null;
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		return null;
	}

	@Override
	public EntityTransaction getTransaction() {
		return this.transaction;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public void joinTransaction() {}

	@Override
	public void lock(Object arg0, LockModeType arg1) {
	}

	@Override
	public <T> T merge(T arg0) {
		return null;
	}

	@Override
	public void persist(Object arg0) {
	}

	@Override
	public void refresh(Object arg0) {
	}

	@Override
	public void remove(Object arg0) {
	}

	@Override
	public void setFlushMode(FlushModeType arg0) {
	}

}
