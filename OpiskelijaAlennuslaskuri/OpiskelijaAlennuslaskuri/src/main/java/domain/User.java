package domain;


/**
 * 
 * Järjestelmän käyttäjää kuvaava luokka.
 * 
 * 
 * @author Matias Brax
 * 
 */

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return userName.equals(other.userName);
    }
   
}
