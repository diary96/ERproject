/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author diary
 */
public class ActionModel extends ActionSupport{
    public String titre;
    private String linkError ="none";
    private String messageError;
    protected User user;

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
}
