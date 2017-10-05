/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Pagination;
import com.er.erproject.model.TypeOffre;
import com.er.erproject.model.User;
import com.er.erproject.service.OffreService;
import com.er.erproject.service.TypeOffreService;
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
public class AccueilAction extends ActionModel{
    private OffreService offreService;
    private TypeOffreService typeOffreService;
    private List<TypeOffre> typeOffres;
    private Pagination parameter;
    private String reference; 
    private String nomProjet;
    private String nomType;
    private String ticket;
    private String statu;
    private String close; 
    private String orderOld;
    private String orderPage;
    private int pagination;
    private String order;
    private List<Offre> offres;
    private User userTemp;
    private String bcTest;
    private String bcTsTest;

    public String getBcTest() {
        return bcTest;
    }

    public void setBcTest(String bcTest) {
        this.bcTest = bcTest;
    }

    public String getBcTsTest() {
        return bcTsTest;
    }

    public void setBcTsTest(String bcTsTest) {
        this.bcTsTest = bcTsTest;
    }

    
    public User getUserTemp() {
        return userTemp;
    }

    public void setUserTemp(User userTemp) {
        this.userTemp = userTemp;
    }

    
    public String getOrderPage() {
        return orderPage;
    }

    public void setOrderPage(String orderPage) {
        this.orderPage = orderPage;
    }

    
    public String getOrderOld() {
        return orderOld;
    }

    public void setOrderOld(String type) {
        this.orderOld = type;
    }

    
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public TypeOffreService getTypeOffreService() {
        return typeOffreService;
    }

    public void setTypeOffreService(TypeOffreService typeOffreService) {
        this.typeOffreService = typeOffreService;
    }
 
    public List<TypeOffre> getTypeOffres() {
        return typeOffres;
    }

    public void setTypeOffres(List<TypeOffre> typeOffres) {
        this.typeOffres = typeOffres;
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
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
            Offre offre = null;
            try {
                offre = (Offre) ReferenceUtil.toBaseModel(reference);
            } catch (Exception e) {
                throw new Exception("La reference inserer n'est pas une reference de type offre");
            }
            temp[0] = "offre.id";
            temp[1] = String.valueOf(offre.getId());
            reponse.add(temp);
        }
        if (this.nomProjet != null && this.nomProjet.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "offre.nomProjet";
            temp[1] = this.nomProjet;
            reponse.add(temp);
        }
        if (this.nomType != null && this.nomType.compareToIgnoreCase("") != 0&& this.nomType.compareToIgnoreCase("none") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "offre.typeOffre.id";
            temp[1] = this.getNomType();
            reponse.add(temp);
        }
        if (this.ticket != null && this.ticket.compareToIgnoreCase("") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "offre.ticket";
            temp[1] = this.getTicket();
            reponse.add(temp);
        }
        if (this.statu != null && this.statu.compareToIgnoreCase("") != 0&& this.statu.compareToIgnoreCase("none") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "offre.statu";
            temp[1] = this.getStatu();
            reponse.add(temp);
        }
        if (this.close != null && this.close.compareToIgnoreCase("") != 0 && this.close.compareToIgnoreCase("none") != 0) {
            temp = this.initTabStrign(2);
            temp[0] = "offre.close";
            temp[1] = this.getClose();
            reponse.add(temp);
        }
        return reponse;
    }
    
    public Pagination getParameter() {
        return parameter;
    }
    
    public void setParameter(Pagination parametre) {
        this.parameter = parametre;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String isClose) {
        this.close = isClose;
    }
    
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
            return Action.LOGIN;
        }
        this.setUserTemp(user);
        List<String[]> arg = this.getArg();
        this.titre = "Accueil";
        try{
            this.typeOffres = this.typeOffreService.findAll();
            this.parameter = new Pagination();
            this.parameter.setTaillePage(20);
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
            this.offres = this.offreService.find(arg,order,orderOld, parameter,bcTest,bcTsTest);
            if(!this.checkerData(this.getOrderOld())&&pagination==0){
                this.setOrderOld(this.getOrder());
            }else if(this.checkerData(this.getOrderOld())==true&&this.getOrderOld().compareTo(this.getOrder())==0&&pagination==0){
                this.setOrderOld("");
            }else if(this.checkerData(this.getOrderOld())==true&&this.getOrderOld().compareTo(this.getOrder())!=0&&pagination==0){
                this.setOrderOld(this.getOrder());
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
