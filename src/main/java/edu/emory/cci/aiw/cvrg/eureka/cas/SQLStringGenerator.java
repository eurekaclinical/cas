/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emory.cci.aiw.cvrg.eureka.cas;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Miao Ai
 */
public class SQLStringGenerator {
    private String jndiName;


	public String getJndiName() {
                return jndiName;	
        }
        
        public void setJndiName(String jndiName){           
                this.jndiName  = jndiName;        
        }

        public String getUserVerficationSQLString()
        {
                String result="select a2.password from users a1 join local_users a2 on (a1.id=a2.id) where a1.username=? and a1.active=1";
                String driverName;
                Connection connection = null;
                try
                {
                        Context initContext = new InitialContext();
                        DataSource ds = (DataSource)initContext.lookup(jndiName);

                        connection = ds.getConnection();
                        driverName = connection.getMetaData().getDriverName();
                        if(driverName.toLowerCase().contains("postgresql"))
                        {
                                result="select a2.password from users a1 join local_users a2 on (a1.id=a2.id) where a1.username=? and a1.active='true'";
                        }
                }
                catch (AuthenticationException ex) {
                     Logger.getLogger("Error in Authentication Exception: " + SQLStringGenerator.class.getName()).log(Level.SEVERE, null, ex);;
                }
                catch ( NamingException ex){
                        Logger.getLogger("Error in Naming Exception: " + SQLStringGenerator.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (SQLException ex) {
                        Logger.getLogger("Error in SQL Exeception: "+SQLStringGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally {
                        if (connection != null){
                                try {
                                    connection.close();
                                } catch (SQLException ex) {
                                    Logger.getLogger("Error in SQL Exeception for closing the connection: "+SQLStringGenerator.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }
                }

                return result;          
        }
        
    
}
