/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import com.er.erproject.data.Reference;
import com.er.erproject.data.StatuReference;
import com.er.erproject.util.UtilConvert;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author diary
 */
public class Offre extends BaseModel {

    private String ticket;
    private String nomProjet;
    private String localisation;
    private Date deadline;
    private Date datetravauxprevu;
    private int statu;
    private Date dateAjout;
    private User user;
    private boolean close;
    
    private TypeOffre typeOffre;
    private Travaux tacheinitials;
    private Travaux tacheSoumission;
    private Travaux tacheSupplementaire;
    private Soumission soumission;
    private TravauxSupplementaire travauxSupplementaire;
    private List<Materiaux> materiaux;
    private Statistique statInitial; 
    private Statistique statTS; 

    public Statistique getStatInitial() {
        return statInitial;
    }

    public void setStatInitial(Statistique statInitial) {
        this.statInitial = statInitial;
    }

    public Statistique getStatTS() {
        return statTS;
    }

    public void setStatTS(Statistique statTS) {
        this.statTS = statTS;
    }

    
    public List<Materiaux> getMateriaux() {
        return materiaux;
    }

    public void setMateriaux(List<Materiaux> materiaux) {
        this.materiaux = materiaux;
    }

    
    public Travaux getTacheSupplementaire() {
        return tacheSupplementaire;
    }

    public void setTacheSupplementaire(Travaux tacheSupplementaire) {
        this.tacheSupplementaire = tacheSupplementaire;
    }

    
    
    public TravauxSupplementaire getTravauxSupplementaire() {
        return travauxSupplementaire;
    }

    public void setTravauxSupplementaire(TravauxSupplementaire travauxSupplementaire) {
        this.travauxSupplementaire = travauxSupplementaire;
        if(this.tacheSupplementaire!=null)this.intDevieTravaux();
    }

    
    public Soumission getSoumission() {
        return soumission;
    }

    public void setSoumission(Soumission soumission) {
        this.soumission = soumission;
        if(this.tacheinitials!=null&&this.tacheSoumission!=null)this.intDevie();
    }

    private void intDevieTravaux() {

        List<TacheModel> allTravaux = new ArrayList();
        List<TacheModel> temp = null;
        double total = 0;
        temp = this.tacheSupplementaire.getTravaux();
        if (temp != null) {
            for (int i = 0; i < temp.size(); i++) {
                allTravaux.add(temp.get(i));
            }
            
            for (int i = 0; i < allTravaux.size(); i++) {
                TacheModel tempTache = allTravaux.get(i);
                total = total + (tempTache.getQuantite()*tempTache.getCatalogue().getPrixUnitaire());
            }
        }
        this.travauxSupplementaire.setTotal(total);
    }
    private void intDevie() {

        List<TacheModel> allTravaux = new ArrayList();
        List<TacheModel> temp = null;
        double total = 0;
        temp = this.tacheinitials.getTravaux();
        if (temp != null) {
            for (int i = 0; i < temp.size(); i++) {
                allTravaux.add(temp.get(i));
            }
            temp = this.tacheSoumission.getTravaux();
            for (int i = 0; i < temp.size(); i++) {
                allTravaux.add(temp.get(i));
            }
            for (int i = 0; i < allTravaux.size(); i++) {
                TacheModel tempTache = allTravaux.get(i);
                total = total + (tempTache.getQuantite()*tempTache.getCatalogue().getPrixUnitaire());
            }
        }
        this.soumission.setTotal(total);
    }

    public Travaux getTacheSoumission() {
        return tacheSoumission;
    }

    public void setTacheSoumission(Travaux tacheSoumission) {
        this.tacheSoumission = tacheSoumission;
    }

    public Travaux getTacheinitials() {
        return tacheinitials;
    }

    public void setTacheinitials(Travaux tacheinitials) {
        this.tacheinitials = tacheinitials;
    }

    public boolean getClose() {
        return close;
    }

    public void setClose(boolean isClose) {
        this.close = isClose;
    }

    public TypeOffre getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(TypeOffre typeOffre) {
        this.typeOffre = typeOffre;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDatetravauxprevu() {
        return datetravauxprevu;
    }

    public void setDatetravauxprevu(Date datetravauxprevu) {
        this.datetravauxprevu = datetravauxprevu;
    }

    public int getStatu() {
        return statu;
    }

    public String getStatuString() {
        return StatuReference.getString(statu);
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offre() {
        this.setReference(Reference.OFFRE);
    }

    public String getDeadlineString() {

        return UtilConvert.toStringAdvance(deadline);
    }

    public String getStringTravauxPrevu() {
        return UtilConvert.toString(this.datetravauxprevu);
    }

}
