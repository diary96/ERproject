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
public class Historique extends BaseModel{
    private User user;
    private String description; 
    private Date date; 
    private String referenceExterieur; 

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReferenceExterieur() {
        return referenceExterieur;
    }

    public void setReferenceExterieur(String referenceExterieur) {
        this.referenceExterieur = referenceExterieur;
    }
    
    public Historique(){
        this.setReference(Reference.HISTORIQUE);
    }

    
    
}
