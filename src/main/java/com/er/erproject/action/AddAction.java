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
import com.er.erproject.model.TypeOffre;
import com.er.erproject.model.User;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.TypeOffreService;
import com.er.erproject.util.DateUtil;
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
    private String dateAjout;
    private long type;   

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dataAjout) {
        this.dateAjout = dataAjout;
    }

    
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
            if(!this.isValide(this.time)) this.time = "00:00";
        }
        if(!this.isValide(dateAjout))throw new Exception("Veuillez mettre une date d'entrée de l'offre");
        
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
        this.dateAjout = DateUtil.convert(Calendar.getInstance().getTime());
        this.time = "00:00";
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
            
            Date temp = DateUtil.convert(this.dateAjout);
            offre.setDateAjout(temp);
            if(this.isValide(this.deadLine)){
                Date deadLineDate = UtilConvert.convertToSQLDate(this.deadLine,this.time);
                if(temp.after(deadLineDate))throw new Exception("la deadline ne peut pas se situer avant la date d'ajout");
                offre.setDeadline(deadLineDate);
            }
            if(this.isValide(this.dateTravaux))offre.setDatetravauxprevu(UtilConvert.convertToSQLDate(this.dateTravaux));
            Offre offreTemp = this.offreService.find(ticket);
            if(offreTemp!=null){
                throw new Exception("impossible d'enregistrer cette offre, une offre possède le même ticket");
            }
            this.offreService.save(offre);
            historique = new Historique();
            historique.setUser(user);
            historique.setDescription("ajout d'une nouvelle offre");
            
            if(Calendar.getInstance().getTime().before(temp))throw new Exception("Veuillez choisir une date inferieur a celui d'aujourd'hui"); 
            historique.setDate(temp);
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
        }catch(Exception e){
            historique = new Historique();
            historique.setUser(user);
            historique.setDescription("Tentative d'ajout d'une nouvelle offre");
            historique.setDate(Calendar.getInstance().getTime());
            this.historiqueService.save(historique);
            
            this.titre = "Nouvelle Offre | Erreur";
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }

  
    
    
    
}
