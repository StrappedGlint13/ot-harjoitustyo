package domain;

import database.DataBaseSetter;
import database.ProductDao;
import database.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import ui.CalculatorUi;

/**
 *
 * Apumetodeja UI-luokalle
 *
 *
 * @author Matias Brax
 *
 */
public class DomainService {

    private UserDao userDao;
    private ProductDao productDao;
    private DataBaseSetter dbSetter;

    public DomainService(String database) {
        this.userDao = new UserDao(database);
        this.productDao = new ProductDao(database);
        this.dbSetter = new DataBaseSetter();

        dbSetter.setDatabase(database);
    }

    /**
     * Tarkistaa onko käyttäjää tietokannassa
     *
     * @param userName käyttäjätunnus
     * @param passWord salasana
     *
     * @return true, jos käyttäjätunnus ja salasana löytyvät, muuten false
     */
    public Boolean checkIfuserExist(String userName, String passWord) {
        User user = userDao.findTheUser(userName, passWord);

        if (user.getUserName() == null || user.getPassword() == null) {
            return false;
        }
        return true;
    }

    /**
     * Palauttaa käyttäjän tuotteet kirjautumisen yhteydessä
     *
     * @param userName käyttäjätunnus
     * @param passWord salasana
     *
     * @return true, jos käyttäjätunnus ja salasana löytyvät, muuten false
     */
    public List logIn(String userName, String passWord) {
        List<Product> usersProducts = new ArrayList();
        User user = userDao.findTheUser(userName, passWord);
        usersProducts = productDao.returnProducts(user);
        return usersProducts;
    }

    /**
     * Luo käyttäjän tietokantaan
     *
     * @param User käyttäjätunnus
     *
     */
    public void createUser(User user) {
        userDao.create(user);
    }

    /**
     * Lisää uuden tuotteen tietokantaan
     *
     * @param product tuote
     *
     */
    public void addProductDB(Product product) {
        productDao.create(product);
    }

    /**
     * Tarkistaa tietokannasta onko opiskelijanumero uniikki.
     *
     * @param studentNumber opiskelijanumero
     *
     * @return true, jos käyttäjää samalla opiskelijanumerolla ei löydy, muuten
     * false
     *
     */
    public boolean iSunique(int studentNumber) {
        User user = null;
        user = userDao.read(studentNumber);

        if (user != null) {
            return false;
        }
        return true;
    }

    /**
     * Hakee tietokannasta käyttäjän opiskelijanumeron.
     *
     * @param username käyttäjänimi
     * @param password salasana
     *
     * @return studentNumber
     *
     */
    public int getStudentNumber(String username, String password) {
        User user = new User(0, "something", "something", "something");
        user = userDao.findTheUser(username, password);
        int studentNumber = user.getStudentNumber();
        return studentNumber;
    }

    /**
     * Hakee tietokannasta yhteissumman tuotteiden normaaleista hinnoista
     *
     * @param studentNumber opiskelijanumero
     *
     * @return normalPrice
     *
     */

    public double getTotalNormal(int studentNumber) {
        double normalPrice = 0.0;
        normalPrice = productDao.getNormalPrice(studentNumber);
        
        return normalPrice;
    }

    /**
     * Hakee tietokannasta yhteissumman opiskelija-alennettujen tuotteiden
     * hinnoista
     *
     * @param studentNumber opiskelijanumero
     *
     * @return studentPrice
     *
     */
    public double getTotalStudent(int studentNumber) {
        double studentPrice = 0.0;
        studentPrice = productDao.getStudentPrice(studentNumber);
        return studentPrice;
    }

    /**
     * Laskee keskiarvon tuotteiden alennusprosentista
     *
     * @param full täysihinta
     * @param part alennettu osuus
     *
     * @return studentPrice
     *
     */

    public double getAverage(double full, double part) {
        double average = 1 - part / full;
        average = average * 100;
        return average;
    }
}