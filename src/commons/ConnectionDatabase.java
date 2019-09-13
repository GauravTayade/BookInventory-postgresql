/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author tayad
 */
public class ConnectionDatabase {
    
    private final String url;
    private final Properties properties;
    
    public ConnectionDatabase(String host, String dbname,String username, String password){
        this.url = "jdbc:postgresql://"+host+"/"+dbname;
        this.properties = new Properties();
        this.properties.setProperty("user",username);
        this.properties.setProperty("password", password);
    }
    
     public java.sql.Connection createConnection() throws SQLException{
    
        return DriverManager.getConnection(this.url,this.properties);
        
    }
    
}
