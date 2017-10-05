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
public class Photo extends BaseModel{
    private TacheModel travaux;
    private String pathPhoto;
    private String coordonnee; 
    private String description; 
    private String referenceTravaux; 
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude)throws Exception {
        if(longitude==null)throw new Exception("le champ de la longitude est vide, veuillez le remplir");
        this.longitude = longitude;
    }

    public String getLatitude() {
       
        return latitude;
    }

    public void setLatitude(String latitude)throws Exception {
        if(latitude==null)throw new Exception("le champ de la longitude est vide, veuillez le remplir");
        this.latitude = latitude;
    }

    
    public TacheModel getTravaux() {
        return travaux;
    }

    public void setTravaux(TacheModel travaux) {
        this.travaux = travaux;
    }

    
    public String getPathPhoto() {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto)throws Exception {
        if(pathPhoto==null||pathPhoto.compareToIgnoreCase("")==0)throw new Exception("la photo ne peut pas etre vide");
        this.pathPhoto = pathPhoto;
    }

    public String getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(String coordonnee) {
        this.coordonnee = coordonnee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)throws Exception {
        
        this.description = description;
    }

    public String getReferenceTravaux() {
        return referenceTravaux;
    }
    
    public void populateCoordonnee() throws Exception{
        if(this.latitude==null)throw new Exception("Veuillez remplir la latitude de la photo "+this.getAllReference());
        if(this.longitude==null)throw new Exception("Veuillez remplir la longitude "+this.getAllReference());
        if(this.latitude.compareTo("")==0||this.longitude.compareTo("")==0){
            this.coordonnee="";
        }else{
            this.coordonnee=this.latitude+"-"+this.longitude;
        }
        
    }
    public void populateLL()throws Exception{
        if(this.coordonnee==null)throw new Exception("Les coordonnees de la photo "+this.getAllReference()+" n'est pas instancier");
        String[] temp = this.coordonnee.split("-"); 
        if(temp.length!=2){
            this.latitude="";
            this.longitude="";
        }else{
            this.latitude = temp[0];
            this.longitude = temp[1];
        }
       
    }
    
    public void setReferenceTravaux(String referenceTravaux) throws Exception {
        if(referenceTravaux==null||referenceTravaux.compareToIgnoreCase("")==0)throw new Exception("Veuillez inserer une reference");
        this.referenceTravaux = referenceTravaux;
    }

    public Photo() {
        this.setReference(Reference.PHOTO);
    }
    
    
    
}
