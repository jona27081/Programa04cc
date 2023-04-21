/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa04cc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zS20006736
 */
public class ConexionDB {
    
    private static ConexionDB cx=null;
    public static ConexionDB getInstance(){
        if(cx==null)
            cx= new ConexionDB();
        return cx;
    }
    
    private Connection con=null;
    private ConexionDB(){
        try {
            String url="jdbc:postgresql://localhost:5433/ejemplo";
            con =DriverManager.getConnection(url,"postgres","123456789");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean execute(String sql){
        return true;
    }
    
    public boolean execute(TransactionDB  tbd){
        return tbd.execute(con);
    }
}
