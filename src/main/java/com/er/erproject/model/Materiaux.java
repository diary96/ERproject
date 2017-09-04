/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import com.er.erproject.data.Reference;
import com.er.erproject.data.StatuReference;

/**
 *
 * @author diary
 */
public class Materiaux extends BaseModel {
    private String designation;
    private String unite; 
    private int quantite; 
    private int etat;
    private Offre offre;

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation)throws Exception {
        if(designation==null||designation.compareToIgnoreCase("")==0)throw new Exception("Le champs de designation est vide");
        this.designation = designation;
    }

    
    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) throws Exception {
        if(unite==null||unite.compareToIgnoreCase("")==0)throw new Exception("Le champs de l'unite est vide");
        this.unite = unite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws Exception {
        if(quantite<0)throw new Exception("La quantite ne peut pas etre inferieure a 0");
        this.quantite = quantite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    public String getEtatString(){
        return StatuReference.getString(this.etat);
    }
    
    
    public Materiaux(){
        this.setReference(Reference.MATERIAUX);
    }
}
