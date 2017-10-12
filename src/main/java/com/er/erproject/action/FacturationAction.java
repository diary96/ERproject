/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.PathData;
import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.data.StatuReference;
import com.er.erproject.data.VentilationData;
import com.er.erproject.generator.FactureGenerator;
import com.er.erproject.generator.FactureTSGenerator;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Historique;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Pagination;
import com.er.erproject.model.User;
import com.er.erproject.model.Ventillation;
import com.er.erproject.model.VentillationModel;
import com.er.erproject.model.VentillationTS;
import com.er.erproject.service.BonCommandeService;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.UserService;
import com.er.erproject.service.VentillationService;
import com.er.erproject.util.DateUtil;
import com.er.erproject.util.FileUtil;
import com.er.erproject.util.ReferenceUtil;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class FacturationAction extends ActionModel {

    private UserService userService;
    private OffreService offreService;
    private VentillationService ventillationService;
    private BonCommandeService bonCommandeService;

    private short type;
    private String service;
    private String nif;
    private String stat;
    private String numero;
    private String referenceInterieure;
    private File bc;
    private String ventillation;
    private long idOffre;
    private Offre offre;
    private String dateToday;
    private String condition;
    private String referenceVentilation;
    private String order;
    private String responsable;
    private String fonction;

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
    
    private InputStream fileInputStream;
    private String fileName;
    
    private List<Ventillation> ventillationsData; 
    private List<VentillationTS> ventillationsTsData; 
    
    private String url;
    private String dateNow;
    private String date; 

    private String reference; 
    private String referenceOffre; 
    private String nomPaiement; 
    private String typePaiement; 
    private String datePrevu; 
    private String datePaiement; 
    private String paye; 
    private String retard;
    private String orderOld;
    private String orderPage;
    private Pagination parameter;
    private int pagination;

    public String getOrderOld() {
        return orderOld;
    }

    public void setOrderOld(String orderOld) {
        this.orderOld = orderOld;
    }

    public String getOrderPage() {
        return orderPage;
    }

    public void setOrderPage(String orderPage) {
        this.orderPage = orderPage;
    }
   
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferenceOffre() {
        return referenceOffre;
    }

    public void setReferenceOffre(String referenceOffre) {
        this.referenceOffre = referenceOffre;
    }

    public String getNomPaiement() {
        return nomPaiement;
    }

    public void setNomPaiement(String nomPaiement) {
        this.nomPaiement = nomPaiement;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public String getDatePrevu() {
        return datePrevu;
    }

    public void setDatePrevu(String datePrevu) {
        this.datePrevu = datePrevu;
    }

    public String getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(String datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getPaye() {
        return paye;
    }

    public void setPaye(String paye) {
        this.paye = paye;
    }

    public String getRetard() {
        return retard;
    }

    public void setRetard(String retard) {
        this.retard = retard;
    }

    public Pagination getParameter() {
        return parameter;
    }

    public void setParameter(Pagination parameter) {
        this.parameter = parameter;
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDateNow() {
        return dateNow;
    }

    public void setDateNow(String dateNow) {
        this.dateNow = dateNow;
    }
    
    public List<Ventillation> getVentillationsData() {
        return ventillationsData;
    }

    public void setVentillationsData(List<Ventillation> ventillationsData) {
        this.ventillationsData = ventillationsData;
    }

    public List<VentillationTS> getVentillationsTsData() {
        return ventillationsTsData;
    }

    public void setVentillationsTsData(List<VentillationTS> ventillationsTsData) {
        this.ventillationsTsData = ventillationsTsData;
    }
 
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getReferenceVentilation() {
        return referenceVentilation;
    }

    public void setReferenceVentilation(String referenceVentilation) {
        this.referenceVentilation = referenceVentilation;
    }
    
    private List<VentillationModel> ventillations;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<VentillationModel> getVentillations() {
        return ventillations;
    }

    public void setVentillations(List<VentillationModel> ventillations) {
        this.ventillations = ventillations;
    }

    public String getDateToday() {
        return dateToday;
    }

    public void setDateToday(String dateToday) {
        this.dateToday = dateToday;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

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

    public String getVentillation() {
        return ventillation;
    }

    public void setVentillation(String ventillation) {
        this.ventillation = ventillation;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public OffreService getOffreService() {
        return offreService;
    }

    public void setOffreService(OffreService offreService) {
        this.offreService = offreService;
    }

    public VentillationService getVentillationService() {
        return ventillationService;
    }

    public void setVentillationService(VentillationService ventillationService) {
        this.ventillationService = ventillationService;
    }

    public BonCommandeService getBonCommandeService() {
        return bonCommandeService;
    }

    public void setBonCommandeService(BonCommandeService bonCommandeService) {
        this.bonCommandeService = bonCommandeService;
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
    
    @Override
    public void setSessionUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }
    }
    
    public String loadVentillation() throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        if (this.type == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);       
            if(offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
        } catch (Exception e) {
            return Action.NONE;
        }
        try{
            if(this.type==VentilationData.SOUMISSION){
                if(this.ventillationService.ventillationSoumissionExist(offre)){
                    this.ventillation = VentillationService.spliter(this.ventillationService.find(offre, type));
                }
            }
            if(this.type==VentilationData.TS){
                offreService.checkerTSEmpty(offre);
                if(this.ventillationService.ventillationTSExist(offre)){
                    this.ventillation = VentillationService.spliter(this.ventillationService.find(offre, type));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        Date temp = Calendar.getInstance().getTime();
        this.dateToday = DateUtil.convert(temp);
        return Action.SUCCESS;
    }
    
    public String saveVentillation() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        if (this.type == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            if(offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
        } catch (Exception e) {
            return Action.NONE;
        }
        Date temp = Calendar.getInstance().getTime();
        this.dateToday = DateUtil.convert(temp);
        BonCommande bonCommande = null;
        FileUtil fileUtil = new FileUtil(this.servletRequest);
        try {
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié");               
            if(!this.checkerData(this.ventillation))throw new Exception("Aucun ventilation inseré");
            boolean test; 
            if(type == VentilationData.SOUMISSION){
                test = this.ventillationService.ventillationSoumissionExist(offre);
            }else{
                test = this.ventillationService.ventillationTSExist(offre);
            }
            this.ventillationService.save(this.getVentillation(),this.getOffre(), this.getType());
            this.url = "detailOffre?idOffre="+this.getIdOffre();
            
            historique =new Historique();
            historique.setUser(user);
            if(test==false){
                if(type == VentilationData.SOUMISSION) historique.setDescription("ajout des modes de paiements");
                else  historique.setDescription("ajout des modes de paiements des T.S");     
            }else {
                if(type == VentilationData.SOUMISSION) historique.setDescription("modification des modes de paiements");
                else  historique.setDescription("modification des modes de paiements des T.S");     
             };
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        }catch(Exception e){
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("tentaive d'ajout des modes de paiements");
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            if(bonCommande!=null)fileUtil.deleteFile(bonCommande.getPath());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String loadFactureInit()throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        try {
            this.titre = "Facturation des taches initiaux";
            this.offreService.populateStatistiqueInitial(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
            this.ventillations = this.ventillationService.find(offre,VentilationData.SOUMISSION);
            this.condition = VentillationModel.getCondition(ventillations);
            if(!this.checkerData(this.responsable)) this.setResponsable("RAKOTONIRINA Beby");
            if(!this.checkerData(this.fonction)) this.setFonction("Le Responsable Administratif et Financier");
        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String loadFactureTS()throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        try {
            this.offreService.populateStatistiqueTS(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
            this.ventillations = this.ventillationService.find(offre,VentilationData.TS);
            this.condition = VentillationModel.getCondition(ventillations);
            if(!this.checkerData(this.responsable)) this.setResponsable("RAKOTONIRINA Beby");
             if(!this.checkerData(this.fonction)) this.setFonction("Le Responsable Administratif et Financier");

        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String loadGestionPaiement()throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try{
           this.offre = this.offreService.find(idOffre);
           
        }catch(Exception e){
            return Action.NONE;
        }
        if(this.user.getNiveau()<4)return Action.NONE;
        try{
            this.dateNow = DateUtil.convert(Calendar.getInstance().getTime());
            this.ventillationsData = (List<Ventillation>)(Object)this.ventillationService.find(offre, VentilationData.SOUMISSION);
            this.ventillationsTsData = (List<VentillationTS>)(Object)this.ventillationService.find(offre, VentilationData.TS);
        }catch(Exception e){
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String payePaiement()throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        if(this.user.getNiveau()<4)return Action.NONE;
        try{
           this.offre = this.offreService.find(idOffre);          
        }catch(Exception e){
            return Action.NONE;
        }
        VentillationModel ventillation=null;    
        try{
            if(this.offre.getClose())throw new Exception("l'offre est clôturée et ne peut plus etre modifié"); 
               
            if(!this.checkerData(this.referenceVentilation))throw new Exception("Veuillez inserer une reference de ventilation");
            if(!this.checkerData(this.date))throw new Exception("Veuillez inserer une date de ventilation");
           
                  
            ventillation = this.ventillationService.find(this.referenceVentilation);
            Date date;
            try{
                date = DateUtil.convert(this.date);
            }catch(Exception e){
                throw new Exception("la date inserée n'est pas valide");
            }
            if(ventillation.getDatepaiement()!=null)throw new Exception("cette ventilation a déjà été payé");           
            this.ventillationService.payer(ventillation, date);       
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("paiement de facture n° "+ventillation.getAllReference());
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
        }catch(Exception e){
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("tentative de paiement de facture n° "+ventillation.getAllReference());
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            
            e.printStackTrace(); 
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String downloadFacture()throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(!this.checkerData(this.referenceVentilation))return Action.NONE;
        
        try{
            this.offre = this.offreService.find(idOffre);
           
        }catch(Exception e){
            return Action.NONE;
        }
        try {
            this.offreService.populateStatistiqueInitial(offre);
            this.offreService.popoluteTacheSoumission(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
            this.ventillations = this.ventillationService.find(offre,VentilationData.SOUMISSION);
            VentillationModel ventillationData = this.ventillationService.find(this.referenceVentilation);
            BonCommandeService bonCommandeService = new BonCommandeService();
            bonCommandeService.setHibernateDao(this.offreService.getHibernateDao());
            
            BonCommande bc = bonCommandeService.find(offre.getSoumission());
            if(bc==null)throw new Exception("aucun bon de commande enregistré, veuillez enregistrer un bon de commande pour les travaux initiaux");
            this.condition = VentillationModel.getConditionWithoutDate(ventillations);
            if(!this.checkerData(this.responsable))this.responsable = "RAKOTONIRINA Beby";
            if(!this.checkerData(this.fonction))this.fonction = "Le Responsable Administratif et Financier";
            FactureGenerator pv = new FactureGenerator(offre,ventillationData,bc,this.condition,responsable,fonction,this.servletRequest);
            File fileToDownload = new File(this.servletRequest.getSession().getServletContext().getRealPath("/")+PathData.PATH_PDF_FACTURE);
            fileName = fileToDownload.getName();
            fileInputStream = new FileInputStream(fileToDownload);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("telechargement de la facture n° "+ventillationData.getAllReference());
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String downloadFactureTs()throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(!this.checkerData(this.referenceVentilation))return Action.NONE;
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try{
            this.offre = this.offreService.find(idOffre);
            this.offreService.populateStatistiqueTS(offre);
            this.offreService.populateTravauxSupplementaire(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
        }catch(Exception e){
            return Action.NONE;
        }
        try {
            this.ventillations = this.ventillationService.find(offre,VentilationData.TS);
            VentillationModel ventillationData = this.ventillationService.find(this.referenceVentilation);
            BonCommandeService bonCommandeService = new BonCommandeService();
            bonCommandeService.setHibernateDao(this.offreService.getHibernateDao());
            
            BonCommande bc = bonCommandeService.find(offre.getTravauxSupplementaire());
            if(bc==null)throw new Exception("aucun bon de commande enregistré, veuillez enregistrer un bon de commande pour les travaux supplementaire");      
            this.condition = VentillationModel.getConditionWithoutDate(ventillations);
            if(!this.checkerData(this.responsable))this.responsable = "RAKOTONIRINA Beby";
            if(!this.checkerData(this.fonction)) this.setFonction("Le Responsable Administratif et Financier");
            FactureTSGenerator pv = new FactureTSGenerator(offre,ventillationData,bc,this.condition,responsable,fonction,this.servletRequest);
            File fileToDownload = new File(this.servletRequest.getSession().getServletContext().getRealPath("/")+PathData.PATH_PDF_FACTURE);
            fileName = fileToDownload.getName();
            fileInputStream = new FileInputStream(fileToDownload);
            
            historique =new Historique();
            historique.setUser(user);
            historique.setDescription("telechargement de la facture n° "+ventillationData.getAllReference());
            historique.setDate(Calendar.getInstance().getTime());
            historique.setReferenceExterieur(offre.getAllReference());
            this.historiqueService.save(historique);
            

        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            ex.printStackTrace();
//            throw ex;
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    private String[] initTabStrign(int size) {
        String[] temp;
        temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = new String();
        }
        return temp;

    }
    
    private List<String[]> getArgVentillation() throws Exception {
        List<String[]> reponse = new ArrayList<>();
        String[] temp = null;
        if (this.reference != null && this.reference.compareTo("") != 0) {
            temp = this.initTabStrign(2);
            Ventillation ventilation = null;
            try {
                ventilation = (Ventillation) ReferenceUtil.toBaseModel(reference);
            } catch (Exception e) {
                throw new Exception("La reference inserer n'est pas une reference de type ventilation");
            }
            temp[0] = "ventilation.id";
            temp[1] = String.valueOf(ventilation.getId());
            reponse.add(temp);
        }
        if (this.referenceOffre != null && this.referenceOffre.compareTo("") != 0) {
            temp = this.initTabStrign(2);
            Offre offre = null;
            try {
                offre = (Offre) ReferenceUtil.toBaseModel(referenceOffre);
            } catch (Exception e) {
                throw new Exception("La reference inserer n'est pas une reference de type offre");
            }
            temp[0] = "offre.id";
            temp[1] = String.valueOf(offre.getId());
            reponse.add(temp);
        }
        if (this.nomPaiement != null && this.nomPaiement.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "ventilation.payementName";
            temp[1] = this.nomPaiement;
            reponse.add(temp);
        }
        if (this.typePaiement != null && this.typePaiement.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "ventilation.typeDescription";
            temp[1] = this.typePaiement;
            reponse.add(temp);
        }
        
        return reponse;
    }
    
    private List<String[]> getArgVentillationTs() throws Exception {
        List<String[]> reponse = new ArrayList<>();
        String[] temp = null;
        if (this.reference != null && this.reference.compareTo("") != 0) {
            temp = this.initTabStrign(2);
            VentillationTS ventilation = null;
            try {
                ventilation = (VentillationTS) ReferenceUtil.toBaseModel(reference);
            } catch (Exception e) {
                throw new Exception("La reference inserer n'est pas une reference de type ventilation");
            }
            temp[0] = "ventilation.id";
            temp[1] = String.valueOf(ventilation.getId());
            reponse.add(temp);
        }
        if (this.referenceOffre != null && this.referenceOffre.compareTo("") != 0) {
            temp = this.initTabStrign(2);
            Offre offre = null;
            try {
                offre = (Offre) ReferenceUtil.toBaseModel(referenceOffre);
            } catch (Exception e) {
                throw new Exception("La reference inserer n'est pas une reference de type offre");
            }
            temp[0] = "offre.id";
            temp[1] = String.valueOf(offre.getId());
            reponse.add(temp);
        }
        if (this.nomPaiement != null && this.nomPaiement.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "ventilation.payementName";
            temp[1] = this.nomPaiement;
            reponse.add(temp);
        }
        if (this.typePaiement != null && this.typePaiement.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "ventilation.typeDescription";
            temp[1] = this.typePaiement;
            reponse.add(temp);
        }
        
        return reponse;
    }
    
    public String listeFactureInit()throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(user.getNiveau()<4)return Action.NONE;
        try{
            List<String[]> arg = new ArrayList();
            this.parameter = new Pagination();
            this.parameter.setTaillePage(10);
            if (this.pagination == 0) {
                this.parameter.setPage(1);
            } else {
                this.parameter.setPage(this.pagination);
            }
           
            if(pagination==0){
                this.setOrderPage(this.orderOld);
            }
            if(pagination>1){
                this.setOrderOld(this.orderPage);
            }
            
            this.ventillationsData = this.ventillationService.findPopulateVentillation(getArgVentillation(),paye, retard, order,orderOld, parameter);
            
            if(!this.checkerData(this.getOrderOld())&&pagination==0){
                this.setOrderOld(this.getOrder());
            }else if(this.checkerData(this.getOrderOld())==true&&this.getOrderOld().compareTo(this.getOrder())==0&&pagination==0){
                this.setOrderOld("");
            }else if(this.checkerData(this.getOrderOld())==true&&this.getOrderOld().compareTo(this.getOrder())!=0&&pagination==0){
                this.setOrderOld(this.getOrder());
            }
            this.titre = "Liste des factures initiaux";
        
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String listeFactureTs()throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(user.getNiveau()<4)return Action.NONE;
        try{
            this.parameter = new Pagination();
            this.parameter.setTaillePage(10);
            if (this.pagination == 0) {
                this.parameter.setPage(1);
            } else {
                this.parameter.setPage(this.pagination);
            }
           
            if(pagination==0){
                this.setOrderPage(this.orderOld);
            }
            if(pagination>1){
                this.setOrderOld(this.orderPage);
            }
            
            this.ventillationsTsData = this.ventillationService.findPopulateVentillationTs(getArgVentillationTs(),paye, retard, order,orderOld, parameter);
            
            if(!this.checkerData(this.getOrderOld())&&pagination==0){
                this.setOrderOld(this.getOrder());
            }else if(this.checkerData(this.getOrderOld())==true&&this.getOrderOld().compareTo(this.getOrder())==0&&pagination==0){
                this.setOrderOld("");
            }else if(this.checkerData(this.getOrderOld())==true&&this.getOrderOld().compareTo(this.getOrder())!=0&&pagination==0){
                this.setOrderOld(this.getOrder());
            }
            this.titre = "Liste des factures des travaux supplémentaires";
        
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
}
