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
import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Abstract Haddock entity manager implementation
 */
public abstract class AbstractEntityManagerImpl implements EntityManager {

    @Resource
    private EntityTransaction transaction;

    private FlushModeType flushModeType;

    private Set<Object> activeEntities = new HashSet<Object>();

    /**
     * Clears entity manager of all entity states.
     */
	@Override
	public void clear() {
        this.activeEntities = new HashSet<Object>();
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
		return this.activeEntities.contains(entity);
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
	public <T> T find(Class<T> entityClass, Object keyIdentifier) {
		T foundObject = null;
        for(Object object : this.activeEntities) {
            Object objectIdentifier = null;
            //
            // Find annotated field in active entity
            //
            for(Field field :
                    object.getClass().getDeclaredFields()) {
                //
                // Check for ID annotation
                //
                for(Annotation annotation :
                        Arrays.asList(field.getAnnotations())) {
                    //
                    // If found, find method to call for getter
                    //
                    if(annotation.annotationType().equals(Id.class)) {
                        //
                        // Check all methods of object
                        //
                        for(Method method :
                                Arrays.asList(object.getClass().getDeclaredMethods())) {
                            //
                            // If method name contains name of field and starts with "get"
                            //
                            if(method.getName().startsWith("get") &&
                                    method.getName().toUpperCase().contains(
                                            field.getName().toUpperCase())) {
                                //
                                // Invoke method
                                //
                                try {
                                    objectIdentifier = method.invoke(object, new Object[0]);
                                } catch (IllegalAccessException e) {
                                    throw new PersistenceException(e);
                                } catch (InvocationTargetException e) {
                                    throw new PersistenceException(e);
                                }
                            }
                        }
                    }
                }
            }
            if(keyIdentifier.equals(objectIdentifier)) {
                foundObject = (T) object;
            }
        }
        return foundObject;
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
	public void persist(Object entity) {
	    this.activeEntities.add(entity);
    }

	@Override
	public void refresh(Object entity) {

	}

	@Override
	public void remove(Object entity) {
        this.activeEntities.remove(entity);
	}

	@Override
	public void setFlushMode(FlushModeType arg0) {
	}

    private static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        if (type != null) {
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
        }
        return fields;
    }


}
