/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Offre;
import com.er.erproject.model.User;
import com.er.erproject.service.BonCommandeService;
import com.er.erproject.service.OffreService;
import com.er.erproject.util.FileUtil;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class BonCommandeAction extends ActionModel{
    private BonCommandeService bonCommandeService;    
    private OffreService offreService;
    
    private String service;
    private String nif;
    private String stat;
    private String numero;
    private String referenceInterieure;
    private File bc;
    private String bcFileName;
    private Offre offre;
    private long idBonCommande;
    private long idOffre; 
    private short type; 

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferenceInterieure() {
        return referenceInterieure;
    }

    public void setReferenceInterieure(String referenceInterieure) {
        this.referenceInterieure = referenceInterieure;
    }

    public File getBc() {
        return bc;
    }

    public void setBc(File bc) {
        this.bc = bc;
    }

    public String getBcFileName() {
        return bcFileName;
    }

    public void setBcFileName(String bcFileName) {
        this.bcFileName = bcFileName;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public long getIdBonCommande() {
        return idBonCommande;
    }

    public void setIdBonCommande(long idBonCommande) {
        this.idBonCommande = idBonCommande;
    }

    public long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(long idOffre) {
        this.idOffre = idOffre;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }
 
    public BonCommandeService getBonCommandeService() {
        return bonCommandeService;
    }
    
    public void setBonCommandeService(BonCommandeService bonCommandeService) {
        this.bonCommandeService = bonCommandeService;
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
    
    public String gestionBonCommande(){
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(this.idOffre==0)return Action.NONE;
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        try{                       
            BonCommande bonCommande = null; 
            bonCommande = this.bonCommandeService.find(offre, type);
            if(bonCommande!=null){
                this.setService(bonCommande.getService());
                this.setNif(bonCommande.getNif());
                this.setStat(bonCommande.getStats());
                this.setNumero(bonCommande.getNumeroBC());
                this.setReferenceInterieure(bonCommande.getReferenceInterieur());
                this.setIdBonCommande(bonCommande.getId());
            }          
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS; 
    }
    public String save(){
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(this.idOffre==0)return Action.NONE;
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        
        try{
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");              
            if (!this.checkerData(this.service))throw new Exception("Veuillez remplir le champ de service");
            if(!this.checkerData(this.nif))throw new Exception("Veuillez remplir le champ de nif");
            if(!this.checkerData(this.stat))throw new Exception("Veuillez remplir le champ de stats"); 
            if(!this.checkerData(this.numero))throw new Exception("Veuillez remplir le champ du numero de bon de commande"); 
            if(!this.checkerData(this.referenceInterieure))throw new Exception("Veuillez remplir le champ du reference interieur");           
            BonCommande bonCommande;
            if(idBonCommande==0){
                if(this.bc==null)throw new Exception("Aucun bon de commande inserer");
                FileUtil.saveBC(bc,FileUtil.getEx(this.bcFileName));
                
                bonCommande = new BonCommande(); 
                bonCommande.setService(this.getService()); 
                bonCommande.setNif(this.getNif());
                bonCommande.setStats(this.getStat());
                bonCommande.setNumeroBC(this.getNumero());
                bonCommande.setReferenceInterieur(this.getReferenceInterieure());
                bonCommande.setDateajout(Calendar.getInstance().getTime());
                bonCommande.setPath("Archive/data/bc/"+bc.getName()+"."+FileUtil.getEx(this.bcFileName));
                this.bonCommandeService.save(bonCommande, offre, type);
            }else{
                bonCommande = this.bonCommandeService.find(offre, type);
                bonCommande.setService(this.getService()); 
                bonCommande.setNif(this.getNif());
                bonCommande.setStats(this.getStat());
                bonCommande.setNumeroBC(this.getNumero());
                bonCommande.setReferenceInterieur(this.getReferenceInterieure());
                if(this.bc!=null){
                    FileUtil.saveBC(bc,FileUtil.getEx(this.bcFileName));                   
                    bonCommande.setPath("Archive/data/bc/"+bc.getName()+"."+FileUtil.getEx(this.bcFileName));                   
                }
                bonCommandeService.update(bonCommande,offre);
            }
        }catch(Exception e){
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
}
