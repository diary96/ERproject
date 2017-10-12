/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diary
 */
public class Statistique {

    private double remise;
    private double tva;
    private List<TacheModel> tacheModel;

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public List<TacheModel> getTacheModel() {
        return tacheModel;
    }

    public void setTacheModel(List<TacheModel> tacheModel) {
        this.tacheModel = tacheModel;
    }

    public double getTotalEffectuer() {
        double reponse = 0;
        for (int i = 0; i < tacheModel.size(); i++) {
            TacheModel temp = tacheModel.get(i);
            reponse += temp.getEffectuer() * temp.getCatalogue().getPrixUnitaire();
        }
        return reponse;
    }

    public double getValeurTVA() {
        return this.getTotalEffectuer() * this.tva / 100;
    }

    public double getValeurRemise() {
        return (this.getValeurTVA() + this.getTotalEffectuer()) * this.remise / 100;
    }

    public double getTotalTTC() {
        return this.getTotalEffectuer() + this.getValeurTVA() - this.getValeurRemise();
    }

    public Statistique(List<TacheModel> tache, double tva, double remise) {
        this.setTacheModel(tache);
        this.setTva(tva);
        this.setRemise(remise);
    }
    
    public String getTotalEffectuerString(){
        return this.getValeurString(getTotalEffectuer());
    }
    public String getValeurRemiseString(){
         return this.getValeurString(getValeurRemise());
    }
    public String getValeurApresRemiseString(){
         return this.getValeurString(getTotalEffectuer()-getValeurRemise());
    }
    public String getValeurTVAString(){
        return this.getValeurString(this.getValeurTVA());
    }
    public String getTotalTTCString(){
        return this.getValeurString(this.getTotalTTC());
    }
    private String getValeurString(double data){
         return new BigDecimal(data).toPlainString();
    }
    public Statistique(List<TacheModel> tache, List<TacheModel> tache1, double tva, double remise) {
        List<TacheModel> newList = new ArrayList();
        newList.addAll(tache);
        newList.addAll(tache1);
        this.setTacheModel(newList);
        this.setTva(tva);
        this.setRemise(remise);
    }
}
