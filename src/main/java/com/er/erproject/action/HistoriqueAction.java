/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.ReferenceType;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.Historique;
import com.er.erproject.model.Offre;
import com.er.erproject.model.User;
import com.er.erproject.service.OffreService;
import com.opensymphony.xwork2.Action;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class HistoriqueAction extends ActionModel {
    private OffreService offreService;
    private long idOffre; 
    private Offre offre; 
    private String reference;
    private List<Historique> historiques;

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }
    
    

    public OffreService getOffreService() {
        return offreService;
    }

    public void setOffreService(OffreService offreService) {
        this.offreService = offreService;
    }

    public long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(long idOffre) {
        this.idOffre = idOffre;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    @Override
    public void setSessionUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }
    }
    public String loadHistorique(){
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
        } catch (Exception e) {
            return Action.NONE;
        }
        try{
            if(user.getNiveau()<3)return Action.NONE;
            this.historiques = this.historiqueService.find(offre);
            this.titre = "Historique de l'objet "+offre.getAllReference();
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
}
