package database;

import domain.Product;
import domain.User;
import ui.CalculatorUi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * Tietokannassa käyttäjänhallintaan tarkoitettu luokka
 *
 *
 * @author Matias Brax
 *
 */
public class UserDao implements Dao<User, Integer> {

    private String dataBase;

    public UserDao(String dataBase) {

        this.dataBase = dataBase;
    }

    /**
     * Luo uuden käyttäjän tietokantaan
     *
     * @param user käyttäjä
     *
     */
    
    @Override
    public void create(User user) {
        Connection connection = connect();

        try {
            PreparedStatement newUser = connection.prepareStatement("INSERT INTO Users(studentNumber, userName, passWord, email)"
                    + " VALUES (?, ?, ?, ?);");

            newUser.setInt(1, user.getStudentNumber());
            newUser.setString(2, user.getUserName());
            newUser.setString(3, user.getPassword());
            newUser.setString(4, user.getEmail());

            newUser.executeUpdate();
            newUser.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
     * Hakee käyttäjän tietokannasta
     *
     * @param userName käyttäjätunnus
     * @param passWord salasana
     *
     * @return User
     */
    
    public User findTheUser(String userName, String passWord) {
        int dbStudentNumber = 0;
        String dbUsername = null;
        String dbPassword = null;
        String dbEmail = null;
        User searchUser = new User(dbStudentNumber, dbUsername, dbPassword, dbEmail);

        try {
            Connection connection = connect();
            PreparedStatement findUser = connection.prepareStatement("SELECT studentNumber, userName, passWord, email FROM Users WHERE userName = ? AND passWord = ?;");
            findUser.setString(1, userName);
            findUser.setString(2, passWord);
            ResultSet rs = findUser.executeQuery();

            while (rs.next()) {
                String searchUN = rs.getString("userName");
                String searchPW = rs.getString("passWord");
                int searchSN = rs.getInt("studentNumber");
                String searchemail = rs.getString("email");

                if (searchUN.equals(userName) && searchPW.equals(passWord)) {
                    searchUser.setUserName(userName);
                    searchUser.setPassword(passWord);
                    searchUser.setStudentNumber(searchSN);
                    searchUser.setEmail(searchemail);
                }
            }
            rs.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchUser;
    }
    
     /**
     * Hakee käyttäjän tietokannasta
     *
     * @param key avain
     *
     * @return User
     */
    
    public User read(Integer key) {
        try {
            Connection connection = connect();
            PreparedStatement readUser = connection.prepareStatement("SELECT * FROM Users WHERE studentNumber = ?;");
            readUser.setInt(1, key);
            ResultSet rs = readUser.executeQuery();

            if (!rs.next()) {
                return null;
            }

            User user = new User(rs.getInt("studentNumber"), rs.getString("userName"), rs.getString("passWord"), rs.getString("email"));

            readUser.close();
            rs.close();
            connection.close();

            return user;

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     /**
     * Luo yhteyden tietokantaan
     *
     *
     * @return connection
     */

    private Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:" + dataBase;
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
