/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import com.er.erproject.data.Reference;

/**
 *
 * @author diary
 */
public class TacheInitialCatalogue extends TacheModel{   
   
    private Offre offre;
    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
    
    public TacheInitialCatalogue() {
        this.setReference(Reference.TRAVAUX_INITIAL_CATALOGUE);
    } 
    
}
