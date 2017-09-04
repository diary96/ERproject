/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.Offre;
import com.er.erproject.model.TypeOffre;
import com.er.erproject.model.User;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.TypeOffreService;
import com.er.erproject.util.UtilConvert;
import com.opensymphony.xwork2.Action;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class AddAction extends ActionModel{
    private OffreService offreService;
    private TypeOffreService typeOffreService;
    private List<TypeOffre> typeOffres;

    private String ticket;
    private String projet;
    private String localisation;
    private String deadLine;
    private String dateTravaux;
    private String time;
    private long type;   

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
    public OffreService getOffreService() {
        return offreService;
    }

    public void setOffreService(OffreService offreService) {
        this.offreService = offreService;
    }
    
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getDateTravaux() {
        return dateTravaux;
    }

    public void setDateTravaux(String dateTravaux) {
        this.dateTravaux = dateTravaux;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }
    
    public List<TypeOffre> getTypeOffres() {
        return typeOffres;
    }

    public void setTypeOffres(List<TypeOffre> typeOffres) {
        this.typeOffres = typeOffres;
    }
    
    public TypeOffreService getTypeOffreService() {
        return typeOffreService;
    }

    public void setTypeOffreService(TypeOffreService typeOffreService) {
        this.typeOffreService = typeOffreService;
    }
   
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }     
    }
    private boolean isValide(String data){
       return !(data==null||data.compareTo("")==0);
    }
    private void chekerData()throws Exception{
        if(!this.isValide(this.getTicket())) throw new Exception("Veuillez remplir le champs du ticket");
        if(!this.isValide(this.getProjet())) throw new Exception("Veuillez remplir le champs du nom de projet");
        if(this.isValide(this.deadLine)){
            if(!this.isValide(this.time)) throw new Exception("Vueillez remplir le champs de l'heure du deadline");
        }
    }
    private boolean checkerUser(){
        return this.user != null;
    }
    private void populate() throws Exception{
        this.typeOffres = this.typeOffreService.findAll();
    }
    public String newLoadOffre() throws Exception{
        this.titre = "Nouvelle Offre";
        this.setSessionUser();
        if(!this.checkerUser()) return Action.NONE;
        this.populate();
        return Action.SUCCESS;
    }
    public String saveOffre() throws Exception{
        this.setLinkError(Reference.INVISIBIBLE);
        this.populate();
        this.setSessionUser();
        if(!this.checkerUser()) return Action.NONE;
        try{
            this.chekerData();
            Offre offre = new Offre();
            offre.setDateAjout(new Date(Calendar.getInstance().getTime().getTime()));
            offre.setTicket(ticket);
            offre.setNomProjet(projet);
            offre.setStatu(0);
            TypeOffre typeOffre = new TypeOffre();
            typeOffre.setId(type);
            offre.setTypeOffre(typeOffre);
            offre.setUser(user);
            offre.setLocalisation(localisation);
            if(this.isValide(this.deadLine)){
                if(this.time==null)time="00:00";
                offre.setDeadline(UtilConvert.convertToSQLDate(this.deadLine,this.time));
            }
            if(this.isValide(this.dateTravaux))offre.setDatetravauxprevu(UtilConvert.convertToSQLDate(this.dateTravaux));
            
            this.offreService.save(offre);
            
           
        }catch(Exception e){      
            this.titre = "Nouvelle Offre | Erreur";
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }

  
    
    
    
}
