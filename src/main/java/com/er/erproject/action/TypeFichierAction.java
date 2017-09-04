/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.TypeFichier;
import com.er.erproject.model.User;
import com.er.erproject.service.TypeFichierService;
import com.opensymphony.xwork2.Action;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class TypeFichierAction extends ActionModel{
    private TypeFichierService typeFichierService; 
    private TypeFichier typeFichier; 
    private String nomType; 
    private List<TypeFichier> typeFichiers;
    private long idType=0;

    public long getIdType() {
        return idType;
    }

    public void setIdType(long idType) {
        this.idType = idType;
    }
    
    

    public List<TypeFichier> getTypeFichiers() {
        return typeFichiers;
    }

    public void setTypeFichiers(List<TypeFichier> typeFichiers) {
        this.typeFichiers = typeFichiers;
    }

    
    
    public TypeFichierService getTypeFichierService() {
        return typeFichierService;
    }

    public void setTypeFichierService(TypeFichierService typeFichierService) {
        this.typeFichierService = typeFichierService;
    }

    public TypeFichier getTypeFichier() {
        return typeFichier;
    }

    public void setTypeFichier(TypeFichier typeFichier) {
        this.typeFichier = typeFichier;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
    
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }     
    } 
    
    public String listeType(){
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        try{
            this.typeFichiers = this.typeFichierService.find();
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.INVISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    public String loadNewType(){
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;       
        try{
            if(this.idType!=0){
                TypeFichier typeFichier = new TypeFichier();
                typeFichier.setId(idType);
                this.typeFichierService.find(typeFichier);
                this.nomType = typeFichier.getNomType();
            }
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    public String save()throws Exception{
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;       
        try{
            TypeFichier typeFichier ;
            if(this.idType!=0){
                typeFichier = new TypeFichier();
                typeFichier.setId(idType);
                this.typeFichierService.find(typeFichier);              
                typeFichier.setNomType(nomType);
                this.typeFichierService.update(typeFichier);
            }else{
                typeFichier = new TypeFichier(); 
                typeFichier.setNomType(nomType);
                typeFichier.setDateajout(Calendar.getInstance().getTime());
                
                this.typeFichierService.save(typeFichier);
            }
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.INVISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
}
