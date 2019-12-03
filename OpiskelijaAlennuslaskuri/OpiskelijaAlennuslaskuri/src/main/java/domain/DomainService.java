/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import database.DBcoordinator;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author matibrax
 */
public class DomainService {

    private DBcoordinator databaseCoordinator;

    public DomainService(DBcoordinator databaseCoordinator) {
        this.databaseCoordinator = databaseCoordinator;
    }

    public Boolean checkIfuserExist(String userName, String passWord) {
        User user = databaseCoordinator.findTheUser(userName, passWord);

        if (user.getUserName() == null || user.getPassword() == null) {
            return false;
        }
        return true;
    }

    public void addProductToCalculator(Product product) {
        databaseCoordinator.createProduct(product);
    }
    /*
    public Node TableView() {
        
        TableView tableView = new TableView();
        
        TableColumn<String, Product> column1 = new TableColumn<>("Product name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Product> column2 = new TableColumn<>("Normal price");
        column2.setCellValueFactory(new PropertyValueFactory<>("normalPrice"));

        TableColumn<String, Product> column3 = new TableColumn<>("Student price");
        column3.setCellValueFactory(new PropertyValueFactory<>("studentPrice"));

        TableColumn<String, Product> column4 = new TableColumn<>("Discount percentage");
        column4.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        tableView.getColumns().addAll(column1, column2, column3, column4);

        
        return tableView;
    }
     */
}
