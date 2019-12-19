/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.CalculatorUi;

/**
 *
 * @author matibrax
 */
public class DataBaseSetter {
   
    
    public DataBaseSetter() {
        
    }
    /**
     * 
     *
     * Alustaa tietokannan
     *
     * 
     */

    public void setDatabase(String database) {
        try {
            Connection connection = connect(database);
            PreparedStatement setDatabase = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Users(studentNumber INTEGER, userName VARCHAR(200), passWord VARCHAR (200), email VARCHAR (200), PRIMARY KEY(studentNumber));");
            setDatabase.execute();
            setDatabase.close();

            PreparedStatement setDatabase1 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Products(id INTEGER AUTO_INCREMENT PRIMARY KEY, studentNumber_id INTEGER, name VARCHAR(200), normalPrice DOUBLE, studentPrice DOUBLE, discountPercentage DOUBLE,"
                            + "FOREIGN KEY (studentNumber_id) REFERENCES Users(studentNumber));");
            setDatabase1.execute();
            setDatabase1.close();
            
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(CalculatorUi.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    private Connection connect(String database) {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:" + database;
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
