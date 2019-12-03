package domain;


import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matibrax
 */
public class User {
    public String userName;
    public String email;
    public String  studentNumber;
    public String password;
    
    public User(String userName, String password, String email, String studentNumber) {
        this.userName = userName;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
    }   
    public User(String userName, String passWord) {   
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getEmail() {
        return email;
    }
   
}
