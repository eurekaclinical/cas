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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.sql.Timestamp;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 *
 * @author miaoai
 */
public class ExpirationCheckAction extends AbstractAction{
        /**
         * The class level logger.
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(ExpirationCheckAction.class);


        private JdbcTemplate jdbcTemplate;


        public JdbcTemplate getJdbcTemplate(){
                return jdbcTemplate;
        }

        public void setJdbcTemplate(JdbcTemplate inJdbcTemplate){           
                this.jdbcTemplate  = inJdbcTemplate;        
        } 

        public Timestamp getExpirationTimestamp(String username) {
                String sql="select a2.passwordexpiration from users a1 join local_users a2 on (a1.id=a2.id) where a1.username='"+username+"'";

                return this.jdbcTemplate.queryForObject(sql, Timestamp.class);
        }

        @Override
        protected Event doExecute(RequestContext rc) throws Exception {

                UsernamePasswordCredentials credentials =(UsernamePasswordCredentials) rc.getFlowScope().get("credentials");
                String username = credentials.getUsername();

                Timestamp expirationTimestamp = getExpirationTimestamp(username);

                Date cuurentDate = new Date();
                Timestamp currentTimestamp = new Timestamp(cuurentDate.getTime());                

                if(expirationTimestamp !=null && currentTimestamp.after(expirationTimestamp)){
                        return error();                    
                }else {
                        return success();                      
                }
        }
        
}
