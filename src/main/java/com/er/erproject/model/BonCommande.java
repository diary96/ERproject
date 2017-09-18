/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import com.er.erproject.data.Reference;
import java.util.Date;

/**
 *
 * @author diary
 */
public class BonCommande extends BaseModel{
    private String service; 
    private String nif; 
    private String stats; 
    private String numeroBC; 
    private String referenceInterieur; 
    private Date dateajout;
    private String path; 

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getNumeroBC() {
        return numeroBC;
    }

    public void setNumeroBC(String numeroBC) {
        this.numeroBC = numeroBC;
    }

    public String getReferenceInterieur() {
        return referenceInterieur;
    }

    public void setReferenceInterieur(String referenceInterieur) {
        this.referenceInterieur = referenceInterieur;
    }

    public Date getDateajout() {
        return dateajout;
    }

    public void setDateajout(Date dateajout) {
        this.dateajout = dateajout;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public BonCommande(){
        this.setReference(Reference.BC);
    }   
}
