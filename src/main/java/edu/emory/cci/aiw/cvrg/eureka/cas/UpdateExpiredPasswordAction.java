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
import java.util.Calendar;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.webflow.execution.RequestContext;

/**
 *
 * @author miaoai
 */
public class UpdateExpiredPasswordAction {
        /**
         * The class level logger.
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(UpdateExpiredPasswordAction.class);


        private JdbcTemplate jdbcTemplate;


        public JdbcTemplate getJdbcTemplate(){
                return jdbcTemplate;
        }

        public void setJdbcTemplate(JdbcTemplate inJdbcTemplate){           
                this.jdbcTemplate  = inJdbcTemplate;        
        } 


        public long getUpdateUserId(String username) {
                String sqlSelect="select a2.id from users a1 join local_users a2 on (a1.id=a2.id) where a1.username='"+username+"'";

                return this.jdbcTemplate.queryForObject(sqlSelect, Long.class);
        }

        public void updateExpiredPassword(String encodedNewPassword, long id) {   
                String sqlUpdate= "update local_users set password='"+encodedNewPassword+"' where id="+id;

                this.jdbcTemplate.update(sqlUpdate);
        }

        public void updateExpirationDate(long id) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 90);
                
                String sqlUpdate= "update local_users set passwordexpiration=? where id=?";
                
                Object[] param ={
                        new java.sql.Timestamp(calendar.getTime().getTime()),
                        id
                };
                
                this.jdbcTemplate.update(sqlUpdate, param);

        }

        public boolean update(RequestContext rc, Passwords passwords, UsernamePasswordCredentials inCredentials) throws Exception {  

                String cUsername = inCredentials.getUsername();
                String cPassword = inCredentials.getPassword();

                String oldPassword = passwords.getOldPassword();
                String newPassword = passwords.getNewPassword();
                String verifiedNewPassword = passwords.getVerifiedNewPassword();

                long userId = getUpdateUserId(cUsername);

                MD5PasswordEncoder encoder = new MD5PasswordEncoder(); 
                String encodedNewPassword = encoder.encode(newPassword);

                
                if(oldPassword!=null && newPassword!=null && verifiedNewPassword!=null ){
                        if(oldPassword.equals(cPassword) && newPassword.equals(verifiedNewPassword)){
                                updateExpiredPassword(encodedNewPassword, userId);
                                updateExpirationDate(userId);
                                UsernamePasswordCredentials outCredentials = new UsernamePasswordCredentials();
                                outCredentials.setUsername(cUsername);                        
                                outCredentials.setPassword(newPassword);

                                rc.getFlowScope().put("credentials", outCredentials);
                                
                                return true; 
                        }else if(!oldPassword.equals(cPassword)) {
                                rc.getMessageContext().addMessage(new MessageBuilder().error().source("oldPassword")
                                        .code("cas.oldPassword.invalid")
                                        .defaultText("Old password is incorrect.")
                                        .build());
                                            
                                return false; 
                        }else if(!newPassword.equals(verifiedNewPassword)){
                                rc.getMessageContext().addMessage(new MessageBuilder().error().source("newPassword")
                                        .code("cas.newPassword.invalid")
                                        .defaultText("New passwords do not match.")
                                        .build());  
                                
                                return false;
                        }else{
                                return false;
                        }
                }else{
                        return false;                     
                }
        }
}

