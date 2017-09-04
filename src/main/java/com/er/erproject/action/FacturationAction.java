/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.data.StatuReference;
import com.er.erproject.data.VentilationData;
import com.er.erproject.generator.FactureGenerator;
import com.er.erproject.generator.FactureTSGenerator;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Offre;
import com.er.erproject.model.User;
import com.er.erproject.model.VentillationModel;
import com.er.erproject.service.BonCommandeService;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.UserService;
import com.er.erproject.service.VentillationService;
import com.er.erproject.util.DateUtil;
import com.er.erproject.util.FileUtil;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
    
    private InputStream fileInputStream;
    private String fileName;
    
    private String url;

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
        } catch (Exception e) {
            return Action.NONE;
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
        } catch (Exception e) {
            return Action.NONE;
        }
        Date temp = Calendar.getInstance().getTime();
        this.dateToday = DateUtil.convert(temp);
        BonCommande bonCommande = null;
        try {
            if (!this.checkerData(this.service))throw new Exception("Veuillez remplir le champ de service");
            if(!this.checkerData(this.nif))throw new Exception("Veuillez remplir le champ de nif");
            if(!this.checkerData(this.stat))throw new Exception("Veuillez remplir le champ de stats"); 
            if(!this.checkerData(this.numero))throw new Exception("Veuillez remplir le champ du numero de bon de commande"); 
            if(!this.checkerData(this.referenceInterieure))throw new Exception("Veuillez remplir le champ du reference interieur");
            if(!this.checkerData(this.ventillation))throw new Exception("Aucun ventilation inseré");
            if(this.bc==null)throw new Exception("Aucun bon de commande inserer");
            FileUtil.saveBC(bc,"pdf");
            bonCommande = new BonCommande(); 
            bonCommande.setService(this.getService()); 
            bonCommande.setNif(this.getNif());
            bonCommande.setStats(this.getStat());
            bonCommande.setNumeroBC(this.getNumero());
            bonCommande.setReferenceInterieur(this.getReferenceInterieure());
            bonCommande.setDateajout(Calendar.getInstance().getTime());
            bonCommande.setPath("Archive/data/bc/"+bc.getName()+".pdf");
            
            this.ventillationService.save(this.getVentillation(), bonCommande, this.getOffre(), this.getType());
            this.url = "detailOffre?idOffre="+this.getIdOffre();
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            if(bonCommande!=null)FileUtil.deleteFile(bonCommande.getPath());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String loadFactureInit()throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            this.offreService.populateStatistiqueInitial(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
            this.ventillations = this.ventillationService.find(offre,VentilationData.SOUMISSION);
            this.condition = VentillationModel.getCondition(ventillations);
            

        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            ex.printStackTrace();
//            throw ex;
            return Action.NONE;
        }
        return Action.SUCCESS;
    }
    
    public String loadFactureTS()throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            this.offreService.populateStatistiqueTS(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
            this.ventillations = this.ventillationService.find(offre,VentilationData.TS);
            this.condition = VentillationModel.getCondition(ventillations);
            

        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            ex.printStackTrace();
//            throw ex;
            return Action.NONE;
        }
        return Action.SUCCESS;
    }
    
    public String downloadFacture()throws Exception{
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(!this.checkerData(this.referenceVentilation))return Action.NONE;
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            this.offreService.populateStatistiqueInitial(offre);
            this.offreService.popoluteTacheSoumission(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
            this.ventillations = this.ventillationService.find(offre,VentilationData.SOUMISSION);
            VentillationModel ventillationData = this.ventillationService.find(this.referenceVentilation);
            BonCommandeService bonCommandeService = new BonCommandeService();
            bonCommandeService.setHibernateDao(this.offreService.getHibernateDao());
            
            BonCommande bc = bonCommandeService.find(offre.getSoumission());
            this.condition = VentillationModel.getConditionWithoutDate(ventillations);
            
            FactureGenerator pv = new FactureGenerator(offre,ventillationData,bc,this.condition);
            File fileToDownload = new File("E:/Stage/ER/ERproject/src/main/webapp/Archive/data/PDF/facture_generate.pdf");
            fileName = fileToDownload.getName();
            fileInputStream = new FileInputStream(fileToDownload);
            
            
            

        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            ex.printStackTrace();
//            throw ex;
            return Action.NONE;
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
        try {
            this.offre = this.offreService.find(idOffre);
            this.offreService.populateStatistiqueInitial(offre);
            this.offreService.popoluteTacheSoumission(offre);
            if(this.offre.getStatu()<StatuReference.FACTURATION)return Action.NONE;
            this.ventillations = this.ventillationService.find(offre,VentilationData.SOUMISSION);
            VentillationModel ventillationData = this.ventillationService.find(this.referenceVentilation);
            BonCommandeService bonCommandeService = new BonCommandeService();
            bonCommandeService.setHibernateDao(this.offreService.getHibernateDao());
            
            BonCommande bc = bonCommandeService.find(offre.getSoumission());
            this.condition = VentillationModel.getConditionWithoutDate(ventillations);
            
            FactureTSGenerator pv = new FactureTSGenerator(offre,ventillationData,bc,this.condition);
            File fileToDownload = new File("E:/Stage/ER/ERproject/src/main/webapp/Archive/data/PDF/facture_generate.pdf");
            fileName = fileToDownload.getName();
            fileInputStream = new FileInputStream(fileToDownload);
            
            
            

        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            ex.printStackTrace();
//            throw ex;
            return Action.NONE;
        }
        return Action.SUCCESS;
    }
}
