/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.data.VentilationData;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Soumission;
import com.er.erproject.model.TravauxSupplementaire;
import com.er.erproject.util.FileUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class BonCommandeService extends ServiceModel {

    private void save(BonCommande bonCommande) throws Exception {
        try{
        this.hibernateDao.save(bonCommande);
        }catch(Exception e){
            e.printStackTrace();
            throw e; 
        }
    }

    public void save(BonCommande bonCommande, Offre offre, short type) throws Exception {
        BonCommande tempBC = null; 
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            Soumission temp = offre.getSoumission();
            tempBC = this.find(temp); 
            if(tempBC!=null){
                FileUtil.deleteFile(tempBC.getPath());
                this.hibernateDao.delete(tempBC);
            }
            this.save(bonCommande);
            temp.setBonCommande(bonCommande);          
            this.hibernateDao.update(temp);
        } else {
            if (offre.getTravauxSupplementaire() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            TravauxSupplementaire temp = offre.getTravauxSupplementaire();
            tempBC = this.find(temp); 
            if(tempBC!=null){
                FileUtil.deleteFile(tempBC.getPath());
                this.hibernateDao.delete(tempBC);
            }
            this.save(bonCommande);
            temp.setBonCommande(bonCommande);
            this.hibernateDao.update(temp);
        }
    }

    public BonCommande find(Soumission soumission) throws Exception {
        BonCommande reponse = null;
        Session session = null;
        try {
            session = hibernateDao.getSessionFactory().openSession();
            String sql = "select bc from Soumission s join s.bonCommande bc where s.id = :id";

            Query query = session.createQuery(sql);
            query.setParameter("id", soumission.getId());
            List<BonCommande> catalogues = query.list();
            if (!catalogues.isEmpty()) {
                soumission.setBonCommande(catalogues.get(0));
                return soumission.getBonCommande();
            }
            return reponse;
        } catch (Exception e) {
            throw new Exception("Impossible d'extraire le bon de commande : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public BonCommande find(Soumission soumission, Session session) {
        BonCommande reponse = null;
        String sql = "select bc from Soumission s join s.bonCommande bc where s.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", soumission.getId());
        List<BonCommande> catalogues = query.list();

        if (!catalogues.isEmpty()) {
            soumission.setBonCommande(catalogues.get(0));
            return soumission.getBonCommande();
        }
        return reponse;
    }

    public BonCommande find(TravauxSupplementaire travauxSupplementaire) throws Exception {
        BonCommande reponse = null;
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select bc from TravauxSupplementaire s join s.bonCommande bc where s.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", travauxSupplementaire.getId());
        List<BonCommande> catalogues = query.list();
        session.close();
        if (!catalogues.isEmpty()) {
            travauxSupplementaire.setBonCommande(catalogues.get(0));
            return travauxSupplementaire.getBonCommande();
        }
        return reponse;
    }

    public void populateBC(Offre offre) throws Exception {
        if (offre.getSoumission() == null) {
            SoumissionService soumissionService = new SoumissionService();
            soumissionService.setHibernateDao(hibernateDao);
            soumissionService.find(offre);
        }
        if (offre.getTravauxSupplementaire() == null) {
            TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
            travauxSupplementaireService.setHibernateDao(hibernateDao);
            travauxSupplementaireService.find(offre);
        }
        this.find(offre.getSoumission());
        this.find(offre.getTravauxSupplementaire());

    }

}
