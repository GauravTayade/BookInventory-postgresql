/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/**
 *
 * @author tayad
 */
public class DB {
    
    ConnectionProperty connProps = new ConnectionProperty();
    ConnectionDatabase conn = new ConnectionDatabase(connProps.getHost(), connProps.getDb(), connProps.getUsername(), connProps.getPassword());
    
    public ResultSet select(String query){
    
        ResultSet selectSet = null;
        
        try{
            
            Connection sqlConn = conn.createConnection();
            Statement statement = sqlConn.createStatement();
            selectSet = statement.executeQuery(query);
            sqlConn.close(); 
        }catch(SQLException ex){
            
            ex.printStackTrace();
            
        }
        return selectSet;
    }
    
    public int insert(String query){
        
        int result  = 0;
        
        try{
            Connection sqlconn = conn.createConnection();
            Statement statement = sqlconn.createStatement();
            result  =  statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public int edit(String query){
        
        int result  = 0;
        
        try{
            Connection sqlconn = conn.createConnection();
            Statement statement = sqlconn.createStatement();
            result  =  statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return result;
    
    }
    
    public int save(String query){
        
        int result  = 0;
        
        try{
            Connection sqlconn = conn.createConnection();
            Statement statement = sqlconn.createStatement();
            result  =  statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return result;
    
    }
    
    public int delete(String query){
        
        int result  = 0;
        
        try{
            Connection sqlconn = conn.createConnection();
            Statement statement = sqlconn.createStatement();
            result  =  statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return result;
    
    }
    
}
