/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import com.er.erproject.util.DateUtil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author diary
 */
public class VentillationModel extends BaseModel {

    protected Date date;
    protected double pourcentage;
    protected String typeDescription;
    protected String payementName;
    protected Date datepaiement;

    public boolean isPayer(){
        if(this.datepaiement==null)return false; 
        return true;
    }
    public Date getDatepaiement() {
        return datepaiement;
    }

    public void setDatepaiement(Date datepaiement) {
        this.datepaiement = datepaiement;
    }
    
    

    public String getPayementName() {
        return payementName;
    }

    public void setPayementName(String payementName) {
        this.payementName = payementName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) throws Exception {
        try {
            this.setDate(DateUtil.convert(date));
        } catch (Exception e) {
            throw new Exception("Erreur de format de date utiliser le format YYYY-MM-JJ");
        }
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) throws Exception {
        if (pourcentage <= 0) {
            throw new Exception("Impossible de mettre une ventillation egale a 0%");
        }
        if (pourcentage > 100) {
            throw new Exception("Impossible de mettre une ventillation superieure a 100%");
        }
        this.pourcentage = pourcentage;
    }

    public void setPourcentage(String pourcentage) throws Exception {
        try {
            double temp = Double.valueOf(pourcentage);
            this.setPourcentage(temp);
        } catch (Exception e) {
            throw new Exception("le pourcentage inseré n'est pas un nombre valide");
        }
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public static String getCondition(List<VentillationModel> ventilations) throws Exception {
//        if(ventilations.isEmpty())throw new Exception("Ventilations vide");
        String reponse = "";
        for (int i = 0; i < ventilations.size(); i++) {
            VentillationModel temp = ventilations.get(i);
            if (i == ventilations.size() - 1) {
                if (temp.getPayementName() != null && temp.getPayementName().compareTo("") != 0) {
                    reponse += temp.getPayementName() + " " + temp.getPourcentage() + "% par " + temp.getTypeDescription() + " le " + DateUtil.toLettreWithoutDay(temp.getDate());
                } else {
                    reponse += temp.getPourcentage() + "% par " + temp.getTypeDescription() + " le " + DateUtil.toLettreWithoutDay(temp.getDate());
                }
            } else {
                if (temp.getPayementName() != null && temp.getPayementName().compareTo("") != 0) {
                    reponse += temp.getPayementName() + " " + temp.getPourcentage() + "% par " + temp.getTypeDescription() + " le " + DateUtil.toLettreWithoutDay(temp.getDate()) + ", ";
                } else {
                    reponse += temp.getPourcentage() + "% par " + temp.getTypeDescription() + " le " + DateUtil.toLettreWithoutDay(temp.getDate()) + ", ";

                }
            }
        }
        return reponse;
    }
    public static String getConditionWithoutDate(List<VentillationModel> ventilations) throws Exception {
//        if(ventilations.isEmpty())throw new Exception("Ventilations vide");
        String reponse = "";
        for (int i = 0; i < ventilations.size(); i++) {
            VentillationModel temp = ventilations.get(i);
            if (i == ventilations.size() - 1) {
                if (temp.getPayementName() != null && temp.getPayementName().compareTo("") != 0) {
                    reponse += temp.getPayementName() + " " + temp.getPourcentage() + "% par " + temp.getTypeDescription();
                } else {
                    reponse += temp.getPourcentage() + "% par " + temp.getTypeDescription();
                }
            } else {
                if (temp.getPayementName() != null && temp.getPayementName().compareTo("") != 0) {
                    reponse += temp.getPayementName() + " " + temp.getPourcentage() + "% par " + temp.getTypeDescription()  + ", ";
                } else {
                    reponse += temp.getPourcentage() + "% par " + temp.getTypeDescription() + ", ";

                }
            }
        }
        return reponse;
    }

}
