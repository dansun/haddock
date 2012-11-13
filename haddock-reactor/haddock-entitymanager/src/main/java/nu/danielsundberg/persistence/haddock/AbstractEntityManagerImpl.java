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
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;

public abstract class AbstractEntityManagerImpl implements EntityManager{

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Query createNamedQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, @SuppressWarnings("rawtypes") Class arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityTransaction getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void joinTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(Object arg0, LockModeType arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T merge(T arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFlushMode(FlushModeType arg0) {
		// TODO Auto-generated method stub
		
	}

}
