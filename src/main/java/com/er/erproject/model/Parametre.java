/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import java.util.Date;

/**
 *
 * @author diary
 */
public class Parametre extends BaseModel{
    private String er; 
    private String telma; 
    private Date dateajout;

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

    public Date getDateajout() {
        return dateajout;
    }

    public void setDateajout(Date dateajout) {
        this.dateajout = dateajout;
    }
    
    
}
