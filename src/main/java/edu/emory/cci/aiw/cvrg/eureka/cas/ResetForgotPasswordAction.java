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

import edu.emory.cci.aiw.MD5PasswordEncoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.webflow.execution.RequestContext;
import javax.mail.Session;

/**
 *
 * @author miaoai
 */
public class ResetForgotPasswordAction {

        private static final Logger LOGGER = LoggerFactory.getLogger(ResetForgotPasswordAction.class);   


        private JdbcTemplate jdbcTemplate;
        private String username;
        
        public JdbcTemplate getJdbcTemplate(){
                return jdbcTemplate;
        }
        public void setJdbcTemplate(JdbcTemplate inJdbcTemplate){           
                this.jdbcTemplate  = inJdbcTemplate;        
        }          
        
        public boolean isUsernameExist(String username) {   
                boolean isExist = false;            
                String sqlSelect= "select count(username) from users where username='"+username+"'";

                int count = this.jdbcTemplate.queryForObject(sqlSelect, Integer.class);
                if(count>0){
                    isExist = true;
                }
                return isExist;
        }
 
        public String getFirstName(String username) {
                String sqlFirstName="select firstname from users where username='"+username+"'";

                return this.jdbcTemplate.queryForObject(sqlFirstName, String.class);
        }
        
        public String getLastName(String username) {
                String sqlLastName="select lastname from users where username='"+username+"'";

                return this.jdbcTemplate.queryForObject(sqlLastName, String.class);
        }       
        
        public long getResetUserId(String username) {
                String sqlSelectUserId="select a2.id from users a1 join local_users a2 on (a1.id=a2.id) where a1.username='"+username+"'";

                return this.jdbcTemplate.queryForObject(sqlSelectUserId, Long.class);
        }

        public void resetForgotPassword(String encodedRandomPassword, long id) {   
                String sqlUpdatePassword= "update local_users set password='"+encodedRandomPassword+"' where id="+id;

                this.jdbcTemplate.update(sqlUpdatePassword);
        }

        public void resetExpirationDate(long id) {
                Calendar calendar = Calendar.getInstance();
                Timestamp newExpirationDate = new Timestamp(calendar.getTime().getTime());
                String sqlUpdateExpirationDate= "update local_users set passwordexpiration='"+newExpirationDate+"' where id="+id;

                this.jdbcTemplate.update(sqlUpdateExpirationDate);

        }
        
        public String reset(RequestContext rc, Email email) throws Exception {
            String username = email.getEmailAddress();
                if(isUsernameExist(username)){

                        long userId = getResetUserId(username); 
                        String firstName = getFirstName(username);
                        String lastName = getLastName(username);
                        
                        RandomPasswordGenerator generator = new RandomPasswordGenerator();
                        String randomPassword = generator.generatePassword();

                        MD5PasswordEncoder encoder = new MD5PasswordEncoder(); 
                        String encodedRandomPassword = encoder.encode(randomPassword);
                                                   
                        resetForgotPassword(encodedRandomPassword, userId);
                        resetExpirationDate(userId);
                        
                        ApplicationProperties applicationProperties = new ApplicationProperties();
                        Properties properties = new Properties();
                        
                        Session session;
                        session = Session.getInstance(properties);
                        FreeMarkerEmailSender emailSender = new FreeMarkerEmailSender(applicationProperties, session);
                        emailSender.sendPasswordResetMessage(username, firstName, lastName, randomPassword);
                        
                        return "success"; 

                }else {
                        return "error";                    
                }
        }        
}
