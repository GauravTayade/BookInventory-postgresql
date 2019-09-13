/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author tayad
 */
public class User {
    
    ConnectionProperty cp = new ConnectionProperty();
    ConnectionDatabase conndb = new ConnectionDatabase(cp.getHost(), cp.getDb(), cp.getUsername(), cp.getPassword());
    
    public int login(String username, String password){
    
        int result = 0;
        ResultSet userSet = null;
        
        try{
            
            Connection sqlConn = conndb.createConnection();
            Statement statement = sqlConn.createStatement();
            userSet = statement.executeQuery("SELECT * FROM users WHERE username = '"+username+"'");
            
            if(userSet.next()){
                if(userSet.getString("password").equals(password)){
                    result = 1;
                }
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public int userUpdate(String fname,String lname,String email,String cell,String address,int status){
    
        int result = 0;
        
        try{
            
            Connection sqlconn = conndb.createConnection();
            Statement statement = sqlconn.createStatement();
            result = statement.executeUpdate("Update users set fname ='"+fname+"', lname='"+lname+"', email='"+email+"', cell='"+cell+"', address='"+address+",status="+status);
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public int userDelete(int uid){
        
        int result = 0; 
        
        try{
            Connection sqlConn = conndb.createConnection();
            Statement statement = sqlConn.createStatement();
            result = statement.executeUpdate("DELET * FROM users WHREE uid="+uid);
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public int forgotPassword(){
        
        Mail mail = new Mail();
        if(mail.sendMail()==1){
            return 1;
        }else{
            return 0;
        }    
    }
    
}
