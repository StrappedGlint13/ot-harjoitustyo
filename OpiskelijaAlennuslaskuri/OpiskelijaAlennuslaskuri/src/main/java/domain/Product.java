package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matibrax
 */
public class Product {
    private double normalPrice;

    public double getStudentPrice() {
        return studentPrice;
    }

    public void setStudentPrice(double studentPrice) {
        this.studentPrice = studentPrice;
    }
    private double studentPrice;
    private double discountPercentage;
    private double dicount;
    
    public Product(double normalPrice, double studentPrice, double discountPercentage, double discount) {
        this.normalPrice = normalPrice;
        this.discountPercentage = discountPercentage;
        this.dicount = discount;
    }
}
