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
import com.er.erproject.service.ReflectService;
import com.er.erproject.service.TravauxService;
import com.er.erproject.util.FileUtil;
import com.er.erproject.util.StringUtilsData;
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
    
    private String url;

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
            photoService.delete(referencePhoto);      
            
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
        try{
            FileUtil.savePdp(photoFile, "jpeg");
            this.photo = new Photo();
            if(StringUtilsData.isNoAlpha(this.latitude))throw new Exception("La latitude n'est pas un coordonnee valable");
            if(StringUtilsData.isNoAlpha(this.longitude))throw new Exception("La longitude n'est pas un coordonnee valable");
            this.latitude+=" "+this.latitudeM;
            this.longitude+=" "+this.longitudeM;
            
            photo.setLatitude(latitude);
            photo.setLongitude(longitude);
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
