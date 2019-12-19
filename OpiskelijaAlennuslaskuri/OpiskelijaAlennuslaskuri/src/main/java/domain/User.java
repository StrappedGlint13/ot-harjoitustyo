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
    
    public User(int studentNumber, String userName, String password, String email) {
        this.userName = userName;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
    }   

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
    public String userName;
    public String email;
    public int  studentNumber;
    public String password;
   
    
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

    public int getStudentNumber() {
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
    @Override
    public String toString() {
        return this.userName;
    }
}
