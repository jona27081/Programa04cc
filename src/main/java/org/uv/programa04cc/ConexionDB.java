/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa04cc;

import java.sql.Connection;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zS20006736
 */
public class ConexionDB {

    private static ConexionDB cx = null;

    public static ConexionDB getInstance() {
        if (cx == null) {
            cx = new ConexionDB();
        }
        return cx;
    }

    private Connection con = null;

    private ConexionDB() {
    Properties props = new Properties();
    try (InputStream in = getClass().getResourceAsStream("/config.properties")) {
        props.load(in);
    } catch (IOException ex) {
        Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    String url = props.getProperty("db.url");
    String user = props.getProperty("db.username");
    String password = props.getProperty("db.password");
    try {
        con = DriverManager.getConnection(url, user, password);
    } catch (SQLException ex) {
        Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    public boolean execute() {
        return true;
    }

    public boolean execute(TransactionDB tbd) {
        return tbd.execute(con);
    }
}
