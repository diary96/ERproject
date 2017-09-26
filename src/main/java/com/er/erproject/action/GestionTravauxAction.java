/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.PathData;
import com.er.erproject.data.Reference;
import com.er.erproject.data.ReferenceType;
import com.er.erproject.data.SessionReference;
import com.er.erproject.data.StatuReference;
import com.er.erproject.generator.XlsGenerator;
import com.er.erproject.model.Historique;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Photo;
import com.er.erproject.model.TacheModel;
import com.er.erproject.model.User;
import com.er.erproject.service.MateriauxService;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.PhotoService;
import com.er.erproject.service.SoumissionService;
import com.er.erproject.service.TravauxService;
import com.er.erproject.service.TravauxSupplementaireService;
import com.er.erproject.util.NumberUtil;
import com.er.erproject.util.UtilConvert;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class GestionTravauxAction extends ActionModel {

    private TravauxService travauxService;
    private OffreService offreService;
    private String type;
    private long idOffre = 0;
    private Offre offre;

    private String reference;
    private String designation;
    private String prixUnitaire;
    private String unite;
    private String quantite;
    private String url;
    private String urlStatique;
    private String admin;
    private String effectuer;

    private String tva;
    private String remise;

    private int limit;
    private int stat;

    private InputStream fileInputStream;
    private String fileName;

    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getEffectuer() {
        return effectuer;
    }

    public void setEffectuer(String effectuer) {
        this.effectuer = effectuer;
    }
   
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getRemise() {
        return remise;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    public String getUrlStatique() {
        return urlStatique;
    }

    public void setUrlStatique(String urlStatique) {
        this.urlStatique = urlStatique;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

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

    public String getType() {
        return type;
    }

    public String getUrlClassique() {
        String[] temp = url.split(Pattern.quote("?"));
        return temp[0];
    }

    public void setType(String type) {
        this.type = type;
    }

    public TravauxService getTravauxService() {
        return travauxService;
    }

    public void setTravauxService(TravauxService travauxService) {
        this.travauxService = travauxService;
    }

    public OffreService getOffreService() {
        return offreService;
    }

    public void setOffreService(OffreService offreService) {
        this.offreService = offreService;
    }

    @Override
    public void setSessionUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }
    }

    public String gestionIntial() throws Exception {
        this.setLimit(StatuReference.OFFRE);

        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        this.setType(ReferenceType.INITIAL);
        this.url = "gestionInitial";
        try {
            this.offre = this.offreService.find(idOffre);
        } catch (Exception e) {
            return Action.NONE;
        }
        this.offreService.popoluteTacheInitial(offre);
        this.setStat(this.offre.getStatu());
        return Action.SUCCESS;
    }

    public String gestionSoumission() throws Exception {
        this.setLimit(StatuReference.SOUMISSION);

        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        this.setType(ReferenceType.SOUMISSION);
        this.url = "gestionSoumission";
        try {
            this.offre = this.offreService.find(idOffre);
        } catch (Exception e) {
            return Action.NONE;
        }
        this.offreService.popoluteTacheInitial(offre);
        this.offreService.popoluteTacheSoumission(offre);

        this.setStat(this.offre.getStatu());
        return Action.SUCCESS;
    }

    public String genererDevice() throws Exception {
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
        this.offreService.popoluteTacheInitial(offre);
        this.offreService.popoluteTacheSoumission(offre);
        try {
            XlsGenerator.generateXLS(offre);
            File fileToDownload = new File(PathData.PATH_XLS_DEVIS);
            fileName = fileToDownload.getName();
            fileInputStream = new FileInputStream(fileToDownload);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("telechargement du devis");
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError("Impossible de generer le devis");
            this.url="detailOffre?idOffre="+this.idOffre;
            return Action.ERROR;

        }

        
        return Action.SUCCESS;
    }

    public String gestionSupplementaire() throws Exception {
        this.setLimit(StatuReference.TRAVAUX);

        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        this.setType(ReferenceType.TAVAUX_SUPPLEMENTAIRE);
        this.url = "gestionSupplementaire";
        try {
            this.offre = this.offreService.find(idOffre);
        } catch (Exception e) {
            return Action.NONE;
        }
        this.offreService.populateTravauxSupplementaire(offre);

        this.setStat(this.offre.getStatu());
        return Action.SUCCESS;
    }

    public String deleteTache() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.getIdOffre();
        try {
            this.offre = this.offreService.find(idOffre);
        } catch (Exception e) {
            return Action.NONE;
        }
        try {
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");               
            this.travauxService.delete(reference);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("suppression de la tache n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        } catch (Exception e) {
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("tentative de suppression de la tache n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
            e.printStackTrace();
            this.url = url + "&linkError=block&messageError=" + UtilConvert.toUrlPath("impossible de supprimer cette tache");
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }

    public String newTache() throws Exception {
        this.setSessionUser();

        if (this.user == null) {
            return Action.LOGIN;
        }

        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }
        this.setUrlStatique(this.getUrlClassique());
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.getIdOffre();
        try {
            this.offre = this.offreService.find(idOffre);
        } catch (Exception e) {
            return Action.NONE;
        }
        return Action.SUCCESS;

    }

    public void cheker() throws Exception {
        if (this.quantite.compareTo("") == 0) {
            throw new Exception("Vueiller remplir le champ de quantite");
        }
    }

    public String saveTache() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url.compareToIgnoreCase("") == 0 || this.url == null) {
            return Action.NONE;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        TacheModel tacheModel= new TacheModel();
        try {
            this.cheker();
            this.offre = this.offreService.find(idOffre);
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");               
            tacheModel =  travauxService.save(reference, designation, prixUnitaire, unite, quantite, type, this.offre.getAllReference(), this.admin);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("ajout de la tache n° "+tacheModel.getAllReference());
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
        
        } catch (Exception e) {
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("tentative d'ajout d'une nouvelle tache n° ");
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = "newTache?idOffre=" + this.idOffre + "&url=" + url + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError()) + "&type=" + this.type;
//            throw e;
            return Action.ERROR;
        }
        this.url = url + "?idOffre=" + this.idOffre;
        return Action.SUCCESS;
    }

    public String updateParameter() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }
        this.setUrlStatique(this.getUrlClassique());
        if (this.type == null || this.type.compareTo("") == 0) {
            return Action.NONE;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            if (type.compareToIgnoreCase(ReferenceType.SOUMISSION) == 0) {
                this.offreService.popoluteTacheInitial(offre);
                this.offreService.popoluteTacheSoumission(offre);
            }
            if (type.compareToIgnoreCase(ReferenceType.TAVAUX_SUPPLEMENTAIRE) == 0) {
                this.offreService.populateTravauxSupplementaire(offre);
            }
        } catch (Exception e) {
            return Action.NONE;
        }
//        if (this.type.compareToIgnoreCase(StatuReference.SSOUMISSION) == 0) {

        try {
            if (type.compareToIgnoreCase(ReferenceType.SOUMISSION) == 0) {
                this.offre.getSoumission().setTva(UtilConvert.toDoubleEntier(tva));
                this.offre.getSoumission().setRemise(UtilConvert.toDoubleEntier(remise));
                SoumissionService service = new SoumissionService();
                service.setHibernateDao(this.travauxService.getHibernateDao());
                service.update(this.offre.getSoumission());
            }
            if (type.compareToIgnoreCase(ReferenceType.TAVAUX_SUPPLEMENTAIRE) == 0) {
                this.offre.getTravauxSupplementaire().setTva(UtilConvert.toDoubleEntier(tva));
                this.offre.getTravauxSupplementaire().setRemise(UtilConvert.toDoubleEntier(remise));
                TravauxSupplementaireService service = new TravauxSupplementaireService();
                service.setHibernateDao(this.travauxService.getHibernateDao());
                service.update(this.offre.getTravauxSupplementaire());
            }

        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = "parametre?idOffre=" + this.idOffre + "&url=" + this.getUrlStatique() + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError()) + "&type=" + this.type;
            return Action.ERROR;
        }

//        }
        this.url = url + "?idOffre=" + this.idOffre;
        return Action.SUCCESS;
    }

    public String gestionParametre() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }
        this.setUrlStatique(this.getUrlClassique());
        if (this.type == null || this.type.compareTo("") == 0) {
            return Action.NONE;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            if (this.type.compareToIgnoreCase(ReferenceType.SOUMISSION) == 0) {
                this.gestionParametreSoumission();
            }
            if (this.type.compareToIgnoreCase(ReferenceType.TAVAUX_SUPPLEMENTAIRE) == 0) {
                this.gestionParametreSupplementaire();
            }
        } catch (Exception e) {
            return Action.NONE;
        }
        return Action.SUCCESS;
    }

    public String gestionParametreSoumission() throws Exception {
        this.offreService.popoluteTacheInitial(offre);
        this.offreService.popoluteTacheSoumission(offre);

        this.setTva(String.valueOf(this.offre.getSoumission().getTva()));
        this.setRemise(String.valueOf(this.offre.getSoumission().getRemise()));
        return Action.SUCCESS;
    }

    public String gestionParametreSupplementaire() throws Exception {

        this.offreService.populateTravauxSupplementaire(offre);

        this.setTva(String.valueOf(this.offre.getTravauxSupplementaire().getTva()));
        this.setRemise(String.valueOf(this.offre.getTravauxSupplementaire().getRemise()));
        return Action.SUCCESS;
    }

    public String plus() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }

        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.idOffre;
        try {
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");               
            this.travauxService.plusDone(reference);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("augmentation de total effectuer  de la tache n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = url + "&url=" + this.getUrlStatique() + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }

    public String minus() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }

        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.idOffre;
        try {
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");              
            this.travauxService.minusDone(reference);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("diminution de total effectuer  de la tache n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = url + "&url=" + this.getUrlStatique() + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }

    public String changeManual() throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }

        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try{
            this.offre = this.offreService.find(idOffre);          
        }catch(Exception e){
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.idOffre;
        try {
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");             
            if(this.effectuer==null)return Action.NONE;
            this.travauxService.manualDone(reference,NumberUtil.toInt(effectuer));
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("modificaton manuel de total effectuer de la tache n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = url + "&url=" + this.getUrlStatique() + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }
    
    public String all() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }

        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.idOffre;
        try {
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");              
            this.travauxService.allDone(reference);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("tout effectuer les tache du travaux n° "+reference);
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = url + "&url=" + this.getUrlStatique() + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }

    public String change() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }

        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
        } catch (Exception e) {
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.idOffre;

        try {
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié"); 
               
            if (this.offre.getStatu() == StatuReference.PV) {
                MateriauxService materiauxService = new MateriauxService(this.travauxService.getHibernateDao());
                materiauxService.changeStatu(reference);
                
                historique =new Historique();
                historique.setUser(user);
                historique.setDescription("changement de l'etat du materiel recu n° "+reference);
                historique.setDate(Calendar.getInstance().getTime());
                historique.setReferenceExterieur(offre.getAllReference());
                this.historiqueService.save(historique);
                
            }
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = url + "&url=" + this.getUrlStatique() + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;
        }

        return Action.SUCCESS;
    }

    public String gestionPhoto() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }

        if (this.reference == null || this.reference.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }
        try {
            PhotoService photoService = new PhotoService(this.travauxService.getHibernateDao());
            this.photos = photoService.find(reference);
        } catch (Exception e) {
            return Action.NONE;

        }
        return Action.SUCCESS;
    }
}
