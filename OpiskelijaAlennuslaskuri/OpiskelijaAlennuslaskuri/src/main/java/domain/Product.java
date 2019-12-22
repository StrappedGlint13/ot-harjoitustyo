package domain;

import java.util.Objects;

/**
 * 
 * Järjestelmään syötettäviä tuotteita kuvaava luokka.
 * 
 * 
 * @author Matias Brax
 * 
 */

public class Product {
    private int studentNumberID;
    private String name;
    private double normalPrice;  
    private double studentPrice;
    private double discountPercentage;
    
    public Product(int studentNumberID, String name, double normalPrice, double studentPrice, double discountPercentage) {
        this.studentNumberID = studentNumberID;
        this.name = name;
        this.normalPrice = normalPrice;
        this.studentPrice = studentPrice;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.studentNumberID != other.studentNumberID) {
            return false;
        }
        if (Double.doubleToLongBits(this.normalPrice) != Double.doubleToLongBits(other.normalPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.studentPrice) != Double.doubleToLongBits(other.studentPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.discountPercentage) != Double.doubleToLongBits(other.discountPercentage)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public int getStudentNumberID() {
        return studentNumberID;
    }

    public void setStudentNumberID(int studentNumberID) {
        this.studentNumberID = studentNumberID;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStudentPrice() {
        return studentPrice;
    }

    public void setStudentPrice(double studentPrice) {
        this.studentPrice = studentPrice;
    } 
}
