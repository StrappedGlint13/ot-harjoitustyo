package domain;

/**
 * 
 * Järjestelmään syötettäviä tuotteita kuvaava luokka.
 * 
 * 
 * @author Matias Brax
 * 
 */

public class Product {
    private String name;
    private double normalPrice;  
    private double studentPrice;
    private double discountPercentage;
    
    public Product(String name, double normalPrice, double studentPrice, double discountPercentage) {
        this.name = name;
        this.normalPrice = normalPrice;
        this.studentPrice = studentPrice;
        this.discountPercentage = discountPercentage;
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
