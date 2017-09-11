/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.PathData;
import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.Archive;
import com.er.erproject.model.Offre;
import com.er.erproject.model.TypeFichier;
import com.er.erproject.model.User;
import com.er.erproject.service.ArchiveService;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.ReflectService;
import com.er.erproject.service.TypeFichierService;
import com.er.erproject.util.FileUtil;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class ArchiveAction extends ActionModel{
    private long idOffre; 
    private List<Archive> archives;
    private File archiveF;
    private String archiveFContentType;
    private String archiveFFileName;
    private ArchiveService archiveService; 
    private long idArchive=0;
    private String reference;
    private OffreService offreService;
    private TypeFichierService typeFichierService;
    private Offre offre;
    private List<TypeFichier> typeFichier;
    private long idType=0;
    private String referenceInterieure;

    public String getReferenceInterieure() {
        return referenceInterieure;
    }

    public void setReferenceInterieure(String referenceInterieure) {
        this.referenceInterieure = referenceInterieure;
    }
    
    
    
    public long getIdType() {
        return idType;
    }

    public void setIdType(long idType) {
        this.idType = idType;
    }

    
    public TypeFichierService getTypeFichierService() {
        return typeFichierService;
    }

    public void setTypeFichierService(TypeFichierService typeFichierService) {
        this.typeFichierService = typeFichierService;
    }

    
    
    public List<TypeFichier> getTypeFichier() {
        return typeFichier;
    }

    public void setTypeFichier(List<TypeFichier> typeFichier) {
        this.typeFichier = typeFichier;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    
    public long getIdArchive() {
        return idArchive;
    }

    public void setIdArchive(long idArchive) {
        this.idArchive = idArchive;
    }

    
    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
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

    public List<Archive> getArchives() {
        return archives;
    }

    public void setArchives(List<Archive> archives) {
        this.archives = archives;
    }

    public File getArchiveF() {
        return archiveF;
    }

    public void setArchiveF(File archiveF) {
        this.archiveF = archiveF;
    }

    public String getArchiveFContentType() {
        return archiveFContentType;
    }

    public void setArchiveFContentType(String archiveFContentType) {
        this.archiveFContentType = archiveFContentType;
    }

    public String getArchiveFFileName() {
        return archiveFFileName;
    }

    public void setArchiveFFileName(String archiveFFileName) {
        this.archiveFFileName = archiveFFileName;
    }

    

    public ArchiveService getArchiveService() {
        return archiveService;
    }

    public void setArchiveService(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }
 
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }     
    } 
    
    public String listeArchive(){
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
            this.archives = this.archiveService.find(offre);
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
            Archive archive=null;
            TypeFichier typeFichier = new TypeFichier(); 
            typeFichier.setId(idType);
            if(!this.checkerData(this.reference))throw new Exception("veuillez remplir le champ de reference");
            if(this.idType==0)throw new Exception("veuillez choisir un type de fichier");
            if(!this.checkerData(this.referenceInterieure)){         
                if(this.archiveF==null)throw new Exception("veuillez choisir un fichier");
                FileUtil.saveArchive(archiveF, FileUtil.getEx(this.archiveFFileName));
                archive = new Archive(); 
                archive.setNom(this.getReference());
                archive.setPath(PathData.PATH_ARCHIVE_SIMPLE+"/"+archiveF.getName()+"."+FileUtil.getEx(this.archiveFFileName));
                archive.setDateajout(Calendar.getInstance().getTime());
                archive.setTypeFichier(typeFichier);
                archive.setOffre(offre);
                this.archiveService.save(archive);
            }else{
                archive = null; 
                ReflectService reflectService = new ReflectService();
                reflectService.setHibernateDao(this.archiveService.getHibernateDao());
                try{
                    archive = (Archive)reflectService.find(this.getReferenceInterieure());
                }catch(Exception e){
                    throw new Exception("la reference inserer n'est pas de type archive");
                }              
                archive.setNom(this.getReference());
                archive.setTypeFichier(typeFichier);
                archive.setOffre(offre);
                if(this.archiveF!=null){
                    FileUtil.saveArchive(archiveF, FileUtil.getEx(this.archiveFFileName));                   
                    archive.setPath(PathData.PATH_ARCHIVE_SIMPLE+"/"+archiveF.getName()+"."+FileUtil.getEx(this.archiveFFileName));                
                }
                this.archiveService.update(archive);
            }
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String delete(){
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(!this.checkerData(this.reference))return Action.NONE;
        try{
            Archive archive = null; 
            try{
                ReflectService reflectService = new ReflectService();
                reflectService.setHibernateDao(this.archiveService.getHibernateDao());
                archive = (Archive)reflectService.find(reference);
            }catch(Exception e){
                throw new Exception("la reference inseré n'est pas de type archive");
            }
            this.archiveService.delete(archive);
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
       
        
        return Action.SUCCESS;
    }
    
    public String gestionArchive(){
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;    
        try{
            this.offre = this.offreService.find(idOffre);
        }catch(Exception e){
            return Action.NONE;
        }
        try{
            this.typeFichier = this.typeFichierService.find();
            if(this.checkerData(this.getReferenceInterieure())){
                Archive archive = new Archive();
                ReflectService reflectService = new ReflectService();
                reflectService.setHibernateDao(this.archiveService.getHibernateDao());
                try{
                    archive = (Archive)reflectService.find(this.getReferenceInterieure());
                }catch(Exception e){
                    throw new Exception("la reference inserer n'est pas de type archive");
                }              
                this.reference = archive.getNom();
            }
        }catch(Exception e){
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
}
