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
public class TacheTSHorsCatalogue extends TacheModel {
    private TravauxSupplementaire travauxSupplementaire;

    public TravauxSupplementaire getTravauxSupplementaire() {
        return travauxSupplementaire;
    }

    public void setTravauxSupplementaire(TravauxSupplementaire travauxSupplementaire) {
        this.travauxSupplementaire = travauxSupplementaire;
    }
    public TacheTSHorsCatalogue() {
        this.setReference(Reference.TRAVAUX_SUPLLEMENTAIRE_HORSCATALOGUE);
    }
    
    
}
