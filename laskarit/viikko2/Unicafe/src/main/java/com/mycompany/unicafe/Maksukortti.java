
package com.mycompany.unicafe;

public class Maksukortti {
 
    private double saldo;
 
    public Maksukortti(double saldo) {
        this.saldo = saldo;
    }
 
    public double saldo() {
        return saldo;
    }
 
    public void lataaRahaa(double lisays) {
        this.saldo += lisays;
    }
 
    public boolean otaRahaa(double maara) {
        if (this.saldo < maara) {
            return false;
        }
 
        this.saldo = this.saldo - maara;
        return true;
    }

    @Override
    public String toString() {
        return "Kortilla on rahaa "+saldo+ " euroa";
    } 
    public boolean onnistuukoOstaminen () {
        boolean onnistuiko = true;
        
        if (onnistuiko == true) {
            return true;
        }
        return false;
    }
    
}
