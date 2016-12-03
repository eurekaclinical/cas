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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author miaoai
 */
public class JdbcTemplateGenerator {
        /**
         * The class level logger.
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateGenerator.class);

        private String jndiName;

        public String getJndiName() {
                return jndiName;	
        }
        public void setJndiName(String jndiName){           
                this.jndiName  = jndiName;        
        }

        public JdbcTemplate getJdbcTemplateFromJndiName(){
                JdbcTemplate jdbcTemplate = null;
                Context initContext = null;

                try {
                        initContext = new InitialContext();
                        DataSource dataSource = (DataSource)initContext.lookup(jndiName);
                        jdbcTemplate = new JdbcTemplate(dataSource);

                } catch (NamingException ex) {
                        LOGGER.error("JdbcTemplateGenerator NamingException in initializing context. ",
                                        ex);
                } finally {
                        if (initContext != null) {
                                try {
                                        initContext.close();
                                } catch (NamingException ex) {
                                        LOGGER.error("JdbcTemplateGenerator NamingException in closing context. ",
                                                        ex);
                                }
                        }                     
                }   

                return jdbcTemplate;
        }    
}
