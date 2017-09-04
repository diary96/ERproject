/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.data.StatuReference;
import com.er.erproject.generator.PVGenerator;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Parametre;
import com.er.erproject.model.User;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.ParametreService;
import com.er.erproject.service.TravauxService;
import com.er.erproject.service.UserService;
import com.er.erproject.service.VentillationService;
import com.er.erproject.util.DateUtil;
import com.er.erproject.util.UtilConvert;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class OffreAction extends ActionModel {

    private TravauxService travauxService;
    private UserService userService;
    private OffreService offreService;
    private Offre offre;
    private long idOffre = 0;

    private String url;
    private String nextLevel;
    private Offre temp;

    private String date;
    private String er;
    private String telma;
    private String lieu;

    private InputStream fileInputStream;
    private String fileName;
    private boolean initiaux; 
    private boolean ts; 

    public boolean getInitiaux() {
        return initiaux;
    }

    public void setInitiaux(boolean initiaux) {
        this.initiaux = initiaux;
    }

    public boolean getTs() {
        return ts;
    }

    public void setTs(boolean ts) {
        this.ts = ts;
    }
    
    
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public String getTelma() {
        return telma;
    }

    public void setTelma(String telma) {
        this.telma = telma;
    }

    public Offre getTemp() {
        return this.temp;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TravauxService getTravauxService() {
        return travauxService;
    }

    public void setTravauxService(TravauxService travauxService) {
        this.travauxService = travauxService;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(long idOffre) {
        this.idOffre = idOffre;
    }

    public OffreService getOffreService() {
        return offreService;
    }

    public void setOffreService(OffreService offreService) {
        this.offreService = offreService;
    }

    public Offre getOffres() {
        return offre;
    }

    public void setOffres(Offre offres) {
        this.offre = offres;
    }

    @Override
    public void setSessionUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }
    }

    public String loadPv() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            this.date = DateUtil.convert(Calendar.getInstance().getTime());
            ParametreService parametreService = new ParametreService();
            parametreService.setHibernateDao(this.travauxService.getHibernateDao());
            Parametre tempParametre = parametreService.getLast();
            if (tempParametre != null) {
                this.er = tempParametre.getEr();
                this.telma = tempParametre.getTelma();
            }
        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            return Action.NONE;
        }
        return Action.SUCCESS;

    }

    public String genererPV() throws Exception {
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
        this.offreService.populateTravauxSupplementaire(offre);
        this.offreService.populateMateriaux(offre);
        this.offreService.populatePhoto(offre);
        this.offreService.findTypeOffre(offre);
        OffreService.removeAdministratif(offre.getTacheSoumission());
        OffreService.removeAdministratif(offre.getTacheinitials());
        OffreService.removeAdministratif(offre.getTacheSupplementaire());

        try {
            if(!this.checkerData(date))throw new Exception("Vueillez remplir le champ de date");
            if(!this.checkerData(lieu))throw new Exception("Vueillez remplir le champ du lieu");
            if(!this.checkerData(er))throw new Exception("Vueillez remplir le champ du responsable de l'entreprise");
            if(!this.checkerData(telma))throw new Exception("Vueillez remplir le champ du responsable de telma");
            PVGenerator pv = new PVGenerator(offre, DateUtil.convert(date), er, telma, lieu);
            File fileToDownload = new File("E:/Stage/ER/ERproject/src/main/webapp/Archive/data/PDF/pv_generate.pdf");
            fileName = fileToDownload.getName();
            fileInputStream = new FileInputStream(fileToDownload);
            
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = "loadPv?idOffre=" + this.idOffre + "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;

        }

        return Action.SUCCESS;
    }
    public String saveParametre()throws Exception{
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
        try {
            if(!this.checkerData(url))return Action.NONE;
            url = url+"?idOffre="+this.getIdOffre();
            if(!this.checkerData(er))throw new Exception("Vueillez remplir le champ du responsable de l'entreprise");
            if(!this.checkerData(telma))throw new Exception("Vueillez remplir le champ du responsable de telma");
            Parametre parametre = new Parametre(); 
            parametre.setEr(er);
            parametre.setTelma(telma);
            parametre.setDateajout(Calendar.getInstance().getTime());
            ParametreService parametreService = new ParametreService(); 
            parametreService.setHibernateDao(this.offreService.getHibernateDao());
            parametreService.save(parametre);
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            this.url = url+ "&linkError=" + this.getLinkError() + "&messageError=" + UtilConvert.toUrlPath(this.getMessageError());
            return Action.ERROR;

        }
        return Action.SUCCESS;
    }
    public String valider() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        if (this.nextLevel == null || this.nextLevel.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }
        if (this.url == null || this.url.compareToIgnoreCase("") == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            this.offreService.popoluteTacheInitial(offre);
        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            return Action.NONE;
        }
        this.url = url + "?idOffre=" + this.idOffre;
        try {
            this.offreService.valider(offre, user, nextLevel);
 
        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());

            this.url = url + "&linkError=block&messageError=" + UtilConvert.toUrlPath(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }

    public String loadFiche() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if (this.idOffre == 0) {
            return Action.NONE;
        }
        try {
            this.offre = this.offreService.find(idOffre);
            this.temp = this.offre;
            this.offreService.popoluteTacheInitial(offre);
            this.offreService.popoluteTacheSoumission(offre);
            this.offreService.populateTravauxSupplementaire(offre);
            this.offreService.populateMateriaux(offre);
            
            VentillationService ventillationService = new VentillationService(); 
            ventillationService.setHibernateDao(this.travauxService.getHibernateDao());
            if(offre.getStatu()>=StatuReference.FACTURATION){
                this.initiaux = ventillationService.ventillationSoumissionExist(offre); 
                this.ts = ventillationService.ventillationTSExist(offre);
            } 

        } catch (Exception ex) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(ex.getMessage());
            ex.printStackTrace();
//            throw ex;
            return Action.NONE;
        }

        return Action.SUCCESS;
    }
    
    public String loadFacturation()throws Exception{
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
            this.offreService.populateStatistiqueTS(offre);

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
