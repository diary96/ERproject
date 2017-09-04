/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.SessionReference;
import com.er.erproject.model.User;
import com.er.erproject.service.UserService;
import com.opensymphony.xwork2.Action;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class IndexAction extends ActionModel{

    private UserService userService;
    private String login;
    private String password;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }     
    }
    public String load() throws Exception { 
       
        this.setSessionUser();
        if (this.user != null) {
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    public String login(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        if(this.getLogin().compareTo("")==0||this.getPassword().compareTo("")==0){
            this.setLinkError("");
            this.setMessageError("Veuillez remplir les champs");
            return Action.ERROR;
        }
       
        try{
            user = userService.login(this.getLogin(), this.getPassword());          
            session.setAttribute(SessionReference.USER,user);
            return Action.SUCCESS;
        
        }catch(Exception e){
            this.setLinkError("");
            this.setMessageError(e.getMessage());
            e.printStackTrace();
            session.removeAttribute(SessionReference.USER);
            return Action.ERROR;
        
        }
    }
}
