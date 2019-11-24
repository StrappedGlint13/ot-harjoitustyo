/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Ui.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matibrax
 */
public class DBcoordinator {
    
    private String DB;
    
    public DBcoordinator (String DBname) {
    
    this.DB = DBname;
}
    
    public void setDatabase() {
        try {
            Connection connection = connect();
            PreparedStatement setDatabase = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Users(id INTEGER AUTO_INCREMENT PRIMARY KEY, username VARCHAR(200), email VARCHAR(20), studentNumber INTEGER);");
            setDatabase.execute();
            setDatabase.close();
            
            PreparedStatement setDatabase1 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Products(id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(200), discountPercentage INTEGER, discount INTEGER);");
            setDatabase1.execute();
            setDatabase1.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:" + DB;
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    
}
