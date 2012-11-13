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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TemporalType;

public abstract class AbstractQueryImpl implements Query {

	@Override
	public int executeUpdate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getResultList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSingleResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFirstResult(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFlushMode(FlushModeType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setHint(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setMaxResults(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(String arg0, Date arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(String arg0, Calendar arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(int arg0, Date arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(int arg0, Calendar arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
