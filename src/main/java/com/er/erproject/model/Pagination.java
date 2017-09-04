/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;
/**
 *
 * @author diary
 */
public class Pagination {
    private int page; 
    private int max;
    private boolean isMax; 
    private int nbrData;
    private int taillePage;
    private String etatGauche;
    private String etatDroite; 

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private void setIsMax() {
        this.isMax = this.nbrData != this.taillePage;
    }

    public int getTaillePage() {
        return taillePage;
    }

    public void setTaillePage(int taillePage) {
        this.taillePage = taillePage;
    }

    public int getNbrData() {
        return nbrData;
    }

    public void setNbrData(int nbrData) {
        this.nbrData = nbrData;
    }
    public String getEtatGauche(){
        if(this.page==1)return "none";
        else return "block";
    }
    public String getEtatDroite(){
        this.setIsMax();
        if(this.page==this.max)return "none";
        else return "block";
    }

    public Pagination(int page, int nbrData, int taillePage) {
        this.page = page;
        this.nbrData = nbrData;
        this.taillePage = taillePage;
    }

    public Pagination() {
        this.setTaillePage(10);
              
    }
    
}
