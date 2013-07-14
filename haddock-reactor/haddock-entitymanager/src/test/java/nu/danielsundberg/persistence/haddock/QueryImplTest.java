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

import nu.danielsundberg.persistence.AbstractPersistenceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.Query;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Haddock implementation of Query
 */
@RunWith(MockitoJUnitRunner.class)
public class QueryImplTest extends AbstractPersistenceTest {

    public static final String NAMED_QUERY = "NAMED_QUERY";

    private Query query;

    @Test
    public void testQueryInstansiation() throws Exception {
        query = entityManager.createNamedQuery(NAMED_QUERY);
        assertThat(query instanceof QueryImpl, is(Boolean.TRUE));
    }

}
