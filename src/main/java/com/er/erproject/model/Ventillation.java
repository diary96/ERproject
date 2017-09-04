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
public class Ventillation extends VentillationModel{
    private Soumission soumission; 

    public Soumission getSoumission() {
        return soumission;
    }

    public void setSoumission(Soumission soumission) {
        this.soumission = soumission;
    }
    public Ventillation(){
        this.setReference(Reference.VENTILATION);
    }
    
}
