/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.Photo;
import com.er.erproject.model.TacheModel;
import com.er.erproject.model.User;
import com.er.erproject.service.PhotoService;
import com.er.erproject.service.TravauxService;
import com.er.erproject.util.FileUtil;
import com.er.erproject.util.UtilConvert;
import com.opensymphony.xwork2.Action;
import java.io.File;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class PhotoAction extends ActionModel {
    private TravauxService travauxService;
    private TacheModel travaux; 
    private String referenceTravaux; 
    private Photo photo; 
    private String latitude; 
    private String latitudeM; 
    private String longitude; 
    private String longitudeM; 
    private File photoFile; 
    private String referencePhoto;
    
    private String latitude_degres=""; 
    private String latitude_minutes=""; 
    private String latitude_secondes=""; 
    
    private String longitude_degres=""; 
    private String longitude_minutes=""; 
    private String longitude_secondes="";
    
    private String url;

    public String getLatitude_degres() {
        return latitude_degres;
    }

    public void setLatitude_degres(String latitude_degres) {
        this.latitude_degres = latitude_degres;
    }

    public String getLatitude_minutes() {
        return latitude_minutes;
    }

    public void setLatitude_minutes(String latitude_minutes) {
        this.latitude_minutes = latitude_minutes;
    }

    public String getLatitude_secondes() {
        return latitude_secondes;
    }

    public void setLatitude_secondes(String latitude_secondes) {
        this.latitude_secondes = latitude_secondes;
    }

    public String getLongitude_degres() {
        return longitude_degres;
    }

    public void setLongitude_degres(String longitude_degres) {
        this.longitude_degres = longitude_degres;
    }

    public String getLongitude_minutes() {
        return longitude_minutes;
    }

    public void setLongitude_minutes(String longitude_minutes) {
        this.longitude_minutes = longitude_minutes;
    }

    public String getLongitude_secondes() {
        return longitude_secondes;
    }

    public void setLongitude_secondes(String longitude_secondes) {
        this.longitude_secondes = longitude_secondes;
    }

    
    public String getReferencePhoto() {
        return referencePhoto;
    }

    public void setReferencePhoto(String referencePhoto) {
        this.referencePhoto = referencePhoto;
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public TravauxService getTravauxService() {
        return travauxService;
    }

    public void setTravauxService(TravauxService travauxService) {
        this.travauxService = travauxService;
    }

    public TacheModel getTravaux() {
        return travaux;
    }

    public void setTravaux(TacheModel travaux) {
        this.travaux = travaux;
    }

    public String getReferenceTravaux() {
        return referenceTravaux;
    }

    public void setReferenceTravaux(String referenceTravaux) {
        this.referenceTravaux = referenceTravaux;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitudeM() {
        return latitudeM;
    }

    public void setLatitudeM(String latitudeM) {
        this.latitudeM = latitudeM;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitudeM() {
        return longitudeM;
    }

    public void setLongitudeM(String longitudeM) {
        this.longitudeM = longitudeM;
    }

    public File getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }
 
    private boolean checker(String data){
        if(data==null||data.compareToIgnoreCase("")==0)return false; 
        return true;
    }
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }     
    }
    public String load(){
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(!this.checker(this.referenceTravaux))return Action.NONE;
        try{
            this.travaux = this.travauxService.find(referenceTravaux);
            if(travaux==null)return Action.NONE;
            travauxService.populatCatalogue(travaux);
        }catch(Exception e){
            return Action.NONE;
        }
       
        return Action.SUCCESS;
    } 
    public String delete(){
        PhotoService photoService = new PhotoService();
        photoService.setHibernateDao(this.travauxService.getHibernateDao());
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(this.referencePhoto==null||this.referencePhoto.compareToIgnoreCase("")==0) return Action.NONE;
        if(this.referenceTravaux==null||this.referenceTravaux.compareToIgnoreCase("")==0) return Action.NONE;
        
       
        try{
            photoService.delete(referencePhoto,this.servletRequest);      
            
        }catch(Exception e){
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = "gestionPhoto?reference="+this.referenceTravaux+"&linkError="+this.getLinkError()+"&messageError="+UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;
        }
         this.url = "gestionPhoto?reference="+this.referenceTravaux;
           
        return Action.SUCCESS;
    }
    public String save(){
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(!this.checker(this.referenceTravaux))return Action.NONE;
        try{
            this.travaux = this.travauxService.find(referenceTravaux);
            if(travaux==null)return Action.NONE;
            travauxService.populatCatalogue(travaux);
        }catch(Exception e){
           
            return Action.NONE;
        }
        FileUtil fileUtil = new FileUtil(this.servletRequest);
        try{
            if(this.photoFile==null)throw new Exception("Veuillez inserer une photo");
            fileUtil.savePdp(photoFile, "jpeg");
            this.photo = new Photo();
           if(!this.checkerData(this.latitude_degres)&&!this.checkerData(this.latitude_minutes)&&!this.checkerData(this.latitude_secondes)){
                this.setLatitude("");
            }else{
                String latitudeTemp = this.latitude_degres+"\u00b0"+this.latitude_minutes+"'"+this.latitude_secondes+"\"";
                this.setLatitude(latitudeTemp);
            }
            if(!this.checkerData(this.longitude_degres)&&!this.checkerData(this.longitude_minutes)&&!this.checkerData(this.longitude_secondes)){
                this.setLongitude("");
            }else{
                String latitudeTemp = this.longitude_degres+"\u00b0"+this.longitude_minutes+"'"+this.longitude_secondes+"\"";
                this.setLongitude(latitudeTemp);
            }
            if(!this.checker(this.latitude)||!this.checker(this.longitude)){
                photo.setLatitude("");
                photo.setLongitude("");
            }
            else{
                this.latitude = this.latitudeM+" "+this.latitude;
                this.longitude = this.longitudeM+" "+this.longitude;
                photo.setLatitude(latitude);
                photo.setLongitude(longitude);
            } 
            photo.setPathPhoto("Archive/data/photo/"+photoFile.getName()+".jpeg");
            photo.setReferenceTravaux(referenceTravaux);
            
            PhotoService photoService = new PhotoService();
            photoService.setHibernateDao(this.travauxService.getHibernateDao());
            
            photoService.save(photo);
            this.url = "gestionPhoto?reference="+this.getReferenceTravaux();
        }catch(Exception e){
            e.printStackTrace();
             this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
            
        }
        
        
        return Action.SUCCESS;
    }
}
