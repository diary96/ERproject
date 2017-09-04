/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diary
 */
public class Travaux implements Cloneable {

    private List<TacheModel> tacheCatalogue;
    private List<TacheModel> tacheHorsCatalogue;

    public List<TacheModel> getTacheCatalogue() {
        return tacheCatalogue;
    }

    public void setTacheCatalogue(List<TacheModel> tacheCatalogue) {
        this.tacheCatalogue = tacheCatalogue;
    }

    public List<TacheModel> getTacheHorsCatalogue() {
        return tacheHorsCatalogue;
    }

    public void setTacheHorsCatalogue(List<TacheModel> tacheHorsCatalogue) {
        this.tacheHorsCatalogue = tacheHorsCatalogue;
    }

    public List<TacheModel> getTravaux() {
        List<TacheModel> temp = new ArrayList();
        for (int i = 0; i < this.tacheCatalogue.size(); i++) {
            temp.add(this.tacheCatalogue.get(i));
        }
        for (int i = 0; i < this.tacheHorsCatalogue.size(); i++) {
            temp.add(this.tacheHorsCatalogue.get(i));
        }

        return temp;
    }

    public List<TacheModel> getTravauxPV() {
        List<TacheModel> temp = new ArrayList();
        for (int i = 0; i < this.tacheCatalogue.size(); i++) {
            temp.add(this.tacheCatalogue.get(i));
        }
        for (int i = 0; i < this.tacheHorsCatalogue.size(); i++) {
            if (!this.tacheHorsCatalogue.get(i).getCatalogue().getIsAdmin()) {
                temp.add(this.tacheHorsCatalogue.get(i));
            }
        }
        return temp;
    }

    public Object clone() {
        Object o = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la 
            // méthode super.clone()
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons 
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return o;
    }

}
