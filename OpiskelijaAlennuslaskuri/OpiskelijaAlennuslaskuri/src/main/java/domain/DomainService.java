package domain;


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

    public DomainService(UserDao userDao, ProductDao productDao) {
        this.userDao = userDao;
        this.productDao = productDao;
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
    
    public ObservableList logIn (User user) {
        ObservableList<Product> userProducts;
        userProducts = userDao.returnProducts(user);
        return userProducts;
    }
    
    public void createUser(User user) {
        userDao.create(user);
    }
    
    /**
     * Hakee käyttäjän tietokannasta
     *
     * @param userName käyttäjätunnus
     * @param passWord salasana
     *
     * @return palautaa käyttäjän, mikäli käyttäjä löytyy
     */

    public User getUser(String userName, String passWord) {
        User user = userDao.findTheUser(userName, passWord);
        return user;
    }
    
    /**
     * Lisää uuden tuotteen tietokantaan
     *
     * @param product tuote
     * 
     */

    public void addProductDB(Product product) {
        productDao.createProduct(product);  
    }
     /**
     * 
     *
     * Alustaa tietokannan
     *
     * 
     */

}
