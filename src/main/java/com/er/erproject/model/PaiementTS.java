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
public class PaiementTS extends PaiementModel {
    private VentillationTS ventillation; 

    public VentillationTS getVentillation() {
        return ventillation;
    }

    public void setVentillation(VentillationTS ventillation) {
        this.ventillation = ventillation;
    }
    
}
