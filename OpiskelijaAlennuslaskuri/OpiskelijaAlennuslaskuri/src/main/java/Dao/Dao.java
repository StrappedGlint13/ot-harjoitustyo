package Dao;


import java.sql.SQLException;
import java.util.List;
import StudentDiscountCalculator.User;
import StudentDiscountCalculator.UserRegister;

public interface Dao <K> {
 
    K create(K k) throws SQLException;

    List<K> list() throws SQLException;

}

