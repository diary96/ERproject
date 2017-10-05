/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.Catalogue;
import com.er.erproject.model.CatalogueModel;
import com.er.erproject.model.Pagination;
import com.er.erproject.model.User;
import com.er.erproject.service.CatalogueService;
import com.er.erproject.service.UserService;
import com.er.erproject.util.ReferenceUtil;
import com.opensymphony.xwork2.Action;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class CatalogueAction extends ActionModel {

    private UserService userService;
    private CatalogueService catalogueService;
    private List<Catalogue> catalogues;

    private String reference;
    private String designation;
    private String unite;
    private double prixUnitaire = 0;
    private double finPrixUnitaire = 0;
    private Pagination parameter;
    private int pagination = 0;
    private String order;
    private String url;
    private String retour; 
    private String retourUrl;
    private String idOffre; 
    private String type;
  
    public String getRetourUrl() {
        return retourUrl;
    }

    public void setRetourUrl(String retourUrl) {
        this.retourUrl = retourUrl;
    }

    
    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    public double getFinPrixUnitaire() {
        return finPrixUnitaire;
    }

    public void setFinPrixUnitaire(double finPrixUnitaire) {
        this.finPrixUnitaire = finPrixUnitaire;
    }

    public Pagination getParameter() {
        return parameter;
    }

    public void setParameter(Pagination paramatre) {
        this.parameter = paramatre;
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    private String[] initTabStrign(int size) {
        String[] temp;
        temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = new String();
        }
        return temp;

    }

    private List<String[]> getArg() throws Exception {
        List<String[]> reponse = new ArrayList<>();
        String[] temp = null;
        if (this.reference != null && this.reference.compareTo("") != 0) {
            temp = this.initTabStrign(2);
            CatalogueModel catalogue = null;
            try {
                catalogue = (CatalogueModel) ReferenceUtil.toBaseModel(reference);
            } catch (Exception e) {
                throw new Exception("La reference inserer n'est pas une reference de type catalogue");
            }
            temp[0] = "catalogue.id";
            temp[1] = String.valueOf(catalogue.getId());
            reponse.add(temp);
        }
        if (this.designation != null && this.designation.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "catalogue.designation";
            temp[1] = this.designation;
            reponse.add(temp);
        }
        if (this.unite != null && this.unite.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "catalogue.unite";
            temp[1] = this.getUnite();
            reponse.add(temp);
        }

        if (this.prixUnitaire > 0 && this.finPrixUnitaire > 0) {
            temp = this.initTabStrign(3);
            temp[0] = "catalogue.prixUnitaire";
            temp[1] = String.valueOf(this.prixUnitaire);
            temp[2] = String.valueOf(this.finPrixUnitaire);
            reponse.add(temp);
        }
        return reponse;
    }

    @Override
    public void setSessionUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }
    }

    public String listeCatalogue() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        try {
            this.parameter = new Pagination();
            this.parameter.setTaillePage(10);
            if (this.pagination == 0) {
                this.parameter.setPage(1);
            } else {
                this.parameter.setPage(this.pagination);
            }
            this.catalogues = (List<Catalogue>) (Object) this.catalogueService.findCatalogue(this.getArg(),order, parameter);
            
            if(this.checkerData(retour)&&this.checkerData(idOffre)&&this.checkerData(url)&&this.checkerData(type)){
                this.retourUrl = retour+"?idOffre="+this.getIdOffre()+"&url="+this.getUrl()+"&type="+this.getType();
            }else{
                this.retourUrl="#?";
            }
            return Action.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
    }

    public String listeHorsCatalogue() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        try {
            this.parameter = new Pagination();
            this.parameter.setTaillePage(10);
            if (this.pagination == 0) {
                this.parameter.setPage(1);
            } else {
                this.parameter.setPage(this.pagination);
            }
            this.catalogues = (List<Catalogue>) (Object) this.catalogueService.findHorsCatalogue(this.getArg(),order, parameter);
            if(this.checkerData(retour)&&this.checkerData(idOffre)&&this.checkerData(url)&&this.checkerData(type)){
                this.retourUrl = retour+"?idOffre="+this.getIdOffre()+"&url="+this.getUrl()+"&type="+this.getType();
            }else{
                this.retourUrl="#?";
            }
            return Action.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
    }

    public String loadSave() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        if(user.getNiveau()<2)return Action.NONE;
        try{
            if(this.checkerData(reference)){
                Catalogue catalogue =  (Catalogue)this.catalogueService.find(reference);
                this.setDesignation(catalogue.getDesignation());
                this.setReference(catalogue.getAllReference());
                this.setUnite(catalogue.getUnite());
                this.setPrixUnitaire(catalogue.getPrixUnitaire());
            }
        }catch(Exception e){
            return Action.NONE;
        }
        return Action.SUCCESS;
    }

    public String saveCatalogue() throws Exception {
        this.setSessionUser();
        if (this.user == null) {
            return Action.LOGIN;
        }
        try {
            if(user.getNiveau()<2)return Action.NONE;
            if (!this.checkerData(this.designation)) {
                throw new Exception("Veuillez remplir le champ de designation");
            }
            if (!this.checkerData(this.unite)) {
                throw new Exception("Veuillez remplir le champ d'unité");
            }
            if (this.prixUnitaire == 0) {
                throw new Exception("Veuillez remplir le champ du prix unitaire");
            }
            if (!this.checkerData(this.reference)) {
                Catalogue catalogue = new Catalogue();
                catalogue.setDesignation(designation);
                catalogue.setUnite(unite);
                catalogue.setPrixUnitaire(prixUnitaire);

                this.catalogueService.save(catalogue);
            }else{
                Catalogue catalogue = (Catalogue)this.catalogueService.find(reference);
                catalogue.setDesignation(designation);
                catalogue.setUnite(unite);
                catalogue.setPrixUnitaire(prixUnitaire);
                
                this.catalogueService.update(catalogue);
            }

        } catch (Exception e) {
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }

}
