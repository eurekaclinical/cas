/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emory.cci.aiw.cvrg.eureka.cas;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Miao Ai
 */
public class SQLStringGenerator {
	/**
	 * The class level logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(
			SQLStringGenerator.class);
        
        private String jndiName;


	public String getJndiName() {
                return jndiName;	
        }
        
        public void setJndiName(String jndiName){           
                this.jndiName  = jndiName;        
        }

        public String getUserVerficationSQLString(){
                String result="select a2.password from users a1 join local_users a2 on (a1.id=a2.id) where a1.username=? and a1.active=1";
                String driverName;
                Connection connection = null;
                Context initContext = null;
                
                try {
                        initContext = new InitialContext();
                        DataSource ds = (DataSource)initContext.lookup(jndiName);

                        connection = ds.getConnection();
                        driverName = connection.getMetaData().getDriverName();
                        if(driverName.toLowerCase().contains("postgresql")) {
                                result="select a2.password from users a1 join local_users a2 on (a1.id=a2.id) where a1.username=? and a1.active='true'";
                        }
                } catch (AuthenticationException ex) {
                        LOGGER.error("Error authenticating user: "
                                        +"user credentials is invalid or naming/directory service failed. ",
                                        ex);
                } catch ( NamingException ex) {
                        LOGGER.error("NamingException in initializing context. ",
                                        ex);
                } catch (SQLException ex) {
                        LOGGER.error("SQLException in getting data source connetion or driver name. ",
                                        ex);
                } finally {
                        if (initContext != null) {
                                try {
                                    initContext.close();
                                } catch (NamingException ex) {
                                    LOGGER.error("NamingException in closing context. ",
                                                    ex);
                                }
                        }  
                        if (connection != null) {
                                try {
                                    connection.close();
                                } catch (SQLException ex) {
                                    LOGGER.error("SQLException in closing connection. ",
                                                    ex);
                                }
                        }
                }

                return result;          
        }
        
    
}