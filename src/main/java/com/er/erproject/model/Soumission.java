/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

/**
 *
 * @author diary
 */
public class Soumission extends BaseModel {
    private int statu; 
    private double tva=20;
    private double remise=0;
    private double total=0;
    
    private Offre offre;
    private User user;
    private BonCommande bonCommande; 

    public BonCommande getBonCommande() {
        return bonCommande;
    }

    public void setBonCommande(BonCommande bonCommande) {
        this.bonCommande = bonCommande;
    }
    
    
    public double getValeurTVA(){
        return (this.total-this.getValeurRemise() )*this.tva/100;
    }
    public double getValeurRemise(){
        return this.total*this.remise/100;
    }
    public double getTotalTTC(){
        return this.total+this.getValeurTVA()-this.getValeurRemise();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    
    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva)throws Exception {
        if(tva>100)throw new Exception("le TVA ne peut pas depasser les 100%");
        if(tva<0)throw new Exception("le TVA ne peut pas etre inferieur a 0%");
        this.tva = tva;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) throws Exception {
        if(remise>100)throw new Exception("la remise ne peut pas depasser les 100%");
        if(remise<0)throw new Exception("la remise ne peut pas etre inferieur a 0%");
        this.remise = remise;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
