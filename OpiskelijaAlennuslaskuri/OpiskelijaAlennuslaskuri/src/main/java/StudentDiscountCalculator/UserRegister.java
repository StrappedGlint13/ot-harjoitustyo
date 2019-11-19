package StudentDiscountCalculator;


import Dao.Dao;
import Dao.UserDao;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matibrax
 */
public class UserRegister {
    private List<User> users;
    private UserDao UserDao;
    
    public UserRegister () {
        this.users = new ArrayList<>();
        this.UserDao = UserDao;
    }
    public void add (User user) {
        user.getUserName().toLowerCase();
        user.getEmail().toLowerCase();
        
        if (!this.users.equals(user)) {
            this.users.add(user);
        } else {
            System.out.println("The user already exists.");
        }
    }
    public String searchWithUsername (String username) {
        username.toLowerCase().trim();
        int i = 0;
        
        while (i < users.size() - 1) {
            if  (username.equals(users.get(i))) {
                return "The user already exists";
            }
            i++;
        }
        return "This username is OK";
    }
     public boolean createUser(String userName, String email, int studentNumber) {
        User user = new User(userName, email, studentNumber);
        try {   
            UserDao.create(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
}
