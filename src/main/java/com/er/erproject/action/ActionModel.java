/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.model.Historique;
import com.er.erproject.model.User;
import com.er.erproject.service.HistoriqueService;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author diary
 */
public class ActionModel extends ActionSupport implements ServletRequestAware{
    protected HistoriqueService historiqueService;
    protected Historique historique;
    public String titre;
    private String linkError ="none";
    private String messageError;
    protected User user;
    protected HttpServletRequest servletRequest;
    public Historique getHistorique() {
        return historique;
        
    }

    public void setHistorique(Historique historique) {
        this.historique = historique;
    }

    
    
    public HistoriqueService getHistoriqueService() {
        return historiqueService;
    }

    public void setHistoriqueService(HistoriqueService historiqueService) {
        this.historiqueService = historiqueService;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    public String getLinkError() {
        return linkError;
    }

    public void setLinkError(String linkError) {
        this.linkError = linkError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    protected boolean checkerData(String data){
        if(data==null||data.compareToIgnoreCase("")==0)return false; 
        return true; 
    
    }
    
    public void setSessionUser()throws Exception{
      
    }   

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;
    }
    
    
}
