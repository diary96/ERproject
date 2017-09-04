/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import java.util.List;

/**
 *
 * @author diary
 */
public class TacheModel extends BaseModel{
    protected CatalogueModel catalogue;
    
    private int quantite; 
    private int effectuer;
    private String remarque;
    private List<Photo> photos;
    public double getResultEffectuer(){
        return effectuer*this.catalogue.getPrixUnitaire();
    }
    public double getResult(){
        return quantite*this.catalogue.getPrixUnitaire();
    }
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
    

    public CatalogueModel getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(CatalogueModel catalogue) {
        this.catalogue = catalogue;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getEffectuer() {
        return effectuer;
    }

    public void setEffectuer(int effectuer) {
        this.effectuer = effectuer;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }   
}
