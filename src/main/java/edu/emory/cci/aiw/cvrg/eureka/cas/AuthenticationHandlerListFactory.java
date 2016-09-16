package edu.emory.cci.aiw.cvrg.eureka.cas;

/*-
 * #%L
 * CAS Server
 * %%
 * Copyright (C) 2012 - 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.FactoryBean;

/**
 *
 * @author Andrew Post
 */
public class AuthenticationHandlerListFactory implements FactoryBean {

	private List list = new ArrayList();

	public AuthenticationHandlerListFactory(List pre, List middle, List post) {
		list.addAll(pre);
		list.addAll(middle);
		list.addAll(post);
	}

	@Override
	public Object getObject() throws Exception {
		return list;
	}

	@Override
	public Class getObjectType() {
		return list.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
