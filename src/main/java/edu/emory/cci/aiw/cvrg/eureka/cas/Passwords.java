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

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miaoai
 */
public class Passwords implements Serializable{
        /**
         * The class level logger.
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(Passwords.class);

        private static final long serialVersionUID = 1L;        
        private String oldPassword;
        private String newPassword;
        private String verifiedNewPassword;

        public String getOldPassword() {
                return oldPassword;	
        }

        public void setOldPassword(String oldPassword){           
                this.oldPassword  = oldPassword;        
        }

        public String getNewPassword() {
                return newPassword;	
        }

        public void setNewPassword(String newPassword){           
                this.newPassword  = newPassword;        
        }    

        public String getVerifiedNewPassword() {
                return verifiedNewPassword;	
        }

        public void setVerifiedNewPassword(String verifiedNewPassword){           
                this.verifiedNewPassword  = verifiedNewPassword;        
        }         
}
