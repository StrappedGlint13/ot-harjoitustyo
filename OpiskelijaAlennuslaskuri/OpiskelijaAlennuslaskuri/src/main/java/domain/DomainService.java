package domain;

import database.DBcoordinator;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * Apumetodeja UI-luokalle
 *
 *
 * @author Matias Brax
 *
 */
public class DomainService {

    private DBcoordinator databaseCoordinator;
    private User user;

    public DomainService(DBcoordinator databaseCoordinator) {
        this.databaseCoordinator = databaseCoordinator;
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
        User user = databaseCoordinator.findTheUser(userName, passWord);

        if (user.getUserName() == null || user.getPassword() == null) {
            return false;
        }
        return true;
    }
    
    public ObservableList logIn (User user) {
        ObservableList<Product> userProducts;
        userProducts = databaseCoordinator.returnProducts(user);
        return userProducts;
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
        User user = databaseCoordinator.findTheUser(userName, passWord);
        return user;
    }
    
    /**
     * Lisää uuden tuotteen tietokantaan
     *
     * @param product tuote
     * 
     */

    public void addProductDB(Product product) {
        databaseCoordinator.createProduct(product);  
    }
    

}
