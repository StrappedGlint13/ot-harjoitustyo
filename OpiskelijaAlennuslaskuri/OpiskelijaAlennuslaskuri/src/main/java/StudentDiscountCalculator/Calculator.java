package StudentDiscountCalculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matibrax
 */
public class Calculator {
    private double totalNormalPrice;
    private double totalDiscountPercentage;
    private double totalDicount;
    
    public Calculator() {
        totalNormalPrice = 0.0;
        totalDiscountPercentage = 0.0;
        totalDicount = 0.0;
    }

    public double getTotalNormalPrice() {
        return totalNormalPrice;
    }

    public void setTotalNormalPrice(double totalNormalPrice) {
        this.totalNormalPrice = totalNormalPrice;
    }

    public double getTotalDiscountPercentage() {
        return totalDiscountPercentage;
    }

    public void setTotalDiscountPercentage(double totalDiscountPercentage) {
        this.totalDiscountPercentage = totalDiscountPercentage;
    }

    public double getTotalDicount() {
        return totalDicount;
    }

    public void setTotalDicount(double totalDicount) {
        this.totalDicount = totalDicount;
    }
    
    
}
