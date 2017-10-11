/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.Reference;
import com.er.erproject.data.SessionReference;
import com.er.erproject.model.User;
import com.er.erproject.security.Cryptographie;
import com.er.erproject.service.UserService;
import com.er.erproject.util.DateUtil;
import com.opensymphony.xwork2.Action;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class UserAction extends ActionModel{
    private UserService userService;
    private List<User> users;
    private long idUser=0;
    private String nom; 
    private String prenom ;
    private String CIN;
    private String dateNaissance; 
    private String dateEmbauche;
    private int niveau; 
    private String password; 
    private String confirmer;
    private String matricule;
    private String information;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }
    
    

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
    
    @Override
    public void setSessionUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object != null) {
            this.setUser((User) object);
        }     
    } 
    
    public String loadList() throws Exception{
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(this.user.getNiveau()<5)return Action.NONE;
        try{
            this.users = this.userService.find();
        }catch(Exception e){
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
    public String newUser() throws Exception{
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(user.getId()!=this.getIdUser()){
            if(this.user.getNiveau()<5){
                return Action.NONE;
            }
        }
        try{
            this.titre = "Gestion d'utilisateur";
            this.information = "Nouveau utilisateur";
            if(this.idUser!=0){
                User userTemp = this.userService.find(idUser);
                if(userTemp==null)throw new Exception(); 
                this.nom = userTemp.getNom(); 
                this.prenom = userTemp.getPrenom(); 
                this.CIN = userTemp.getCIN(); 
                this.dateEmbauche = DateUtil.convert(userTemp.getDateEmbauche());
                this.dateNaissance = DateUtil.convert(userTemp.getDateNaissance());
                this.matricule = userTemp.getMatricule();
                this.niveau = userTemp.getNiveau();
                this.information = "Modificiation des informations de "+userTemp.getPrenom()+" "+userTemp.getNom();
            }
        }catch(Exception e){
            return Action.NONE;
        }
        return Action.SUCCESS;
    }
    
    public String saveUser() throws Exception{
        this.setSessionUser();
        if(this.user==null)return Action.LOGIN;
        if(user.getId()!=this.getIdUser()){
            if(this.user.getNiveau()<5){
                return Action.NONE;
            }
        }
        User userTemp;
        try{
            if(!this.checkerData(this.nom))throw new Exception("Veuillez remplir le champ du nom");
            if(!this.checkerData(this.prenom))throw new Exception("Veuillez remplir le champ du prenom");
            if(!this.checkerData(this.CIN))throw new Exception("Veuillez remplir le champ du CIN");
            if(!this.checkerData(this.dateNaissance))throw new Exception("Veuillez remplir le champ de la date de naissance");
            if(!this.checkerData(this.dateEmbauche))throw new Exception("Veuillez remplir le champ de la date d'embauche");
            if(!this.checkerData(this.matricule))throw new Exception("Veuillez remplir le champ de la matricule");
            if(idUser!=0){
                userTemp = this.userService.find(idUser);
                if(userTemp==null)return Action.NONE;
                userTemp.setNom(nom);
                userTemp.setPrenom(prenom);
                userTemp.setCIN(CIN);
                userTemp.setDateNaissance(DateUtil.convert(dateNaissance));
                userTemp.setDateEmbauche(DateUtil.convert(dateEmbauche));
                userTemp.setMatricule(matricule);
                userTemp.setNiveau(niveau);
                
                if(this.checkerData(this.password)){
                    if(password.compareTo(confirmer)!=0)throw new Exception("Les mots de passe sont différents");
                    userTemp.setPassword(Cryptographie.crypterHashage(password));
                }
                this.userService.update(userTemp);
            }else{
                userTemp = new User();
                userTemp.setNom(nom);
                userTemp.setPrenom(prenom);
                userTemp.setCIN(CIN);
                userTemp.setDateNaissance(DateUtil.convert(dateNaissance));
                userTemp.setDateEmbauche(DateUtil.convert(dateEmbauche));
                userTemp.setMatricule(matricule);
                userTemp.setNiveau(niveau);
                if(password.compareTo(confirmer)!=0)throw new Exception("Les mots de passe sont différents");
                userTemp.setPassword(Cryptographie.crypterHashage(password));           
                this.userService.save(userTemp);
            }
        }catch(Exception e){
            this.setLinkError(Reference.VISIBIBLE);
            this.setMessageError(e.getMessage());
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
    
}
