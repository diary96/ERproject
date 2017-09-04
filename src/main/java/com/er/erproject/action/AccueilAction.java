/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.SessionReference;
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
public class AccueilAction extends ActionModel{
    private OffreService offreService; 
    private List<Offre> offres;


    public OffreService getOffreService() {
        return offreService;
    }

    public void setOffreService(OffreService offreService) {
        this.offreService = offreService;
    }

    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
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
        if (this.user == null) {
            return Action.ERROR;
        }
        this.titre = "Accueil";
        this.offres = this.offreService.findAllWithDepart();
        return Action.SUCCESS;
    }  
}
