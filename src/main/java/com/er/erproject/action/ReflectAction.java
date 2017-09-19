/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.Historique;
import com.er.erproject.model.Offre;
import com.er.erproject.model.User;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.ReflectService;
import com.er.erproject.util.UtilConvert;
import com.opensymphony.xwork2.Action;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class ReflectAction extends ActionModel {
    private String reference;
    private ReflectService reflectService;
    private String url;
    private long idOffre; 

    public long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(long idOffre) {
        this.idOffre = idOffre;
    }
    
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ReflectService getReflectService() {
        return reflectService;
    }

    public void setReflectService(ReflectService reflectService) {
        this.reflectService = reflectService;
    }
    
    
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }     
    }
    public String delete() throws Exception{
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(this.reference==null||this.reference.compareToIgnoreCase("")==0) return Action.NONE;
        if(this.url==null||this.url.compareToIgnoreCase("")==0) return Action.NONE;
        Offre offre;
            
        try{
            OffreService offreService = new OffreService();
            offreService.setHibernateDao(this.reflectService.getHibernateDao());
            if(idOffre!=0){
                offre = offreService.find(idOffre);
                if(offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié"); 
           
            }
             this.url=url+"?idOffre="+this.idOffre;
          
            this.reflectService.delete(reference);
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("suppression de l'objet n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            if(idOffre==0)historique.setReferenceExterieur(reference);
            else historique.setReferenceExterieur(Reference.OFFRE+idOffre);
            this.historiqueService.save(historique);
           
        }catch(Exception e){
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("tentative de suppression de l'objet n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            if(idOffre==0)historique.setReferenceExterieur(reference);
            else historique.setReferenceExterieur(Reference.OFFRE+idOffre);
            this.historiqueService.save(historique);
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = url+"&linkError="+this.getLinkError()+"&messageError="+UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
}
