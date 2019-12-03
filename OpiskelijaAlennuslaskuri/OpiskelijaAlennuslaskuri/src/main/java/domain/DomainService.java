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
    private User user;

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
}
