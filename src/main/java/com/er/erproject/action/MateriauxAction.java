/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.data.StatuReference;
import com.er.erproject.model.Historique;
import com.er.erproject.model.Materiaux;
import com.er.erproject.model.Offre;
import com.er.erproject.model.User;
import com.er.erproject.service.MateriauxService;
import com.er.erproject.service.OffreService;
import com.er.erproject.util.UtilConvert;
import com.opensymphony.xwork2.Action;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class MateriauxAction extends ActionModel{
    private MateriauxService materiauxService;
    private OffreService offreService;
    
    private Offre offre;
    private long idOffre; 
    private String reference; 
    private String designation; 
    private String unite; 
    private int quantite; 
    private String etat; 
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   
    
    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(long idOffre) {
        this.idOffre = idOffre;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
    public MateriauxService getMateriauxService() {
        return materiauxService;
    }

    public void setMateriauxService(MateriauxService materiauxService) {
        this.materiauxService = materiauxService;
    }

    public OffreService getOffreService() {
        return offreService;
    }

    public void setOffreService(OffreService offreService) {
        this.offreService = offreService;
    }
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }    
    }
    public String load() throws Exception{
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(this.idOffre==0)return Action.NONE;
        if(this.url==null||this.url.compareToIgnoreCase("")==0)return Action.NONE;
        
        try{
            this.offre=this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        return Action.SUCCESS;
    }
    public String save() throws Exception{
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN; 
        if(this.idOffre==0)return Action.NONE;
        if(this.url==null||this.url.compareToIgnoreCase("")==0)return Action.NONE;
        if(this.idOffre==0)return Action.NONE;
        try{
            this.offre = this.offreService.find(idOffre);
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");              
            Materiaux materiaux = new Materiaux();
            materiaux.setOffre(offre);
            materiaux.setQuantite(quantite);
            materiaux.setUnite(unite);
            materiaux.setDesignation(designation);
            if(this.etat==null||this.etat.compareTo("")==0){
                materiaux.setEtat(StatuReference.NON_RECU);
            }else{
                materiaux.setEtat(StatuReference.RECU);
            }
            this.materiauxService.save(materiaux);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("ajout d'un materiel n° "+materiaux.getAllReference());
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        }catch(Exception e){     
            e.printStackTrace();
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("tentative d'ajout d'un materiel");
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = "gestionMateriel?idOffre=" + this.idOffre + "&url=" + url + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());

            return Action.ERROR;
        
        }
        this.url = url + "?idOffre=" + this.idOffre;
        return Action.SUCCESS;
        
    }
    
}
