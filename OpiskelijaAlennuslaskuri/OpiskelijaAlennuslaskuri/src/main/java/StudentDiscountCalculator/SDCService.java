package StudentDiscountCalculator;


import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matibrax
 */
public class SDCService {
    private List<User> users;
    
    public SDCService () {
        this.users = new ArrayList<>();
    }
    public void addUser (User user) {
        user.getUserName().toLowerCase();
        user.getEmail().toLowerCase();
        
    }
    /*public String searchWithUsername (String username) {
        username.toLowerCase().trim();
        int i = 0;
        
        while (i < users.size() - 1) {
            if  (username.equals(users.get(i))) {
                return "The user already exists";
            }
            i++;
        }
        return "This username is OK";
    }*/
    
}
