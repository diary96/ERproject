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
import com.er.erproject.model.Ventillation;
import com.er.erproject.model.VentillationModel;
import com.er.erproject.model.VentillationTS;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author diary
 */
public class VentillationService extends ServiceModel {

    public void save(VentillationModel vantillation) throws Exception {
        this.hibernateDao.save(vantillation);
    }

    public void save(VentillationModel vantillation, Session session) throws Exception {
        this.hibernateDao.save(vantillation, session);
    }

    public static List<VentillationModel> spliter(String data, short type) throws Exception {
        List<VentillationModel> ventillations = new ArrayList();
        String[] fistSplit = data.split(",");

        for (int i = 0; i < fistSplit.length; i++) {
            String[] temp = new String[4];
            
            temp = fistSplit[i].split("/");
            if(temp.length<4)throw new Exception("une des donnees de ventilation n'est pas complet,veuillez utiliser none pour une valeur vide ");
            VentillationModel ventillation = null;
            if (type == VentilationData.SOUMISSION) {
                ventillation = new Ventillation();
            }
            if (type == VentilationData.TS) {
                ventillation = new VentillationTS();
            }
            if (ventillation == null) {
                throw new Exception("type inconnu");
            }
            if(temp[0].compareToIgnoreCase("none")==0)throw new Exception("une des pourcentage de ventilation est vide");
            if(temp[1].compareToIgnoreCase("none")==0)throw new Exception("une des date de ventilation est vide");
            if(temp[2].compareToIgnoreCase("none")==0)throw new Exception("une des type de ventilation est vide");
            ventillation.setPourcentage(temp[0]);
            ventillation.setDate(temp[1]);
            ventillation.setTypeDescription(temp[2]);
            if(temp[3].compareToIgnoreCase("none")!=0){
                ventillation.setPayementName(temp[3]);
            }
            ventillations.add(ventillation);
        }
        double somme = 0;
        for (int i = 0; i < ventillations.size(); i++) {
            somme += ventillations.get(i).getPourcentage();
        }
        if (somme != 100) {
            throw new Exception("La somme des pourcentages doit etre égale à 100% ");
        }
        return ventillations;
    }

    public void save(String ventillationData, BonCommande bonCommande, Offre offre, short type) throws Exception {
        List<VentillationModel> ventillations = VentillationService.spliter(ventillationData, type);
        Session session = null;
        Transaction tr = null;
        try {
            session = hibernateDao.getSessionFactory().openSession();
            tr = session.beginTransaction();
            if (this.ventillationSoumissionExist(offre)) {
                throw new Exception("cette offre possede deja des ventilations");
            }
            for (int i = 0; i < ventillations.size(); i++) {
                this.save(ventillations.get(i), offre, type, session);
            }
            BonCommandeService bonCommandeService = new BonCommandeService();
            bonCommandeService.setHibernateDao(hibernateDao);
            bonCommandeService.save(bonCommande, offre, type);
            tr.commit();

        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new Exception("impossible de sauvegarder la ventillation dans la base " + e.getMessage());

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<VentillationModel> find(Offre offre, short type) throws Exception {
        Session session = null;
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            try {
                session = this.hibernateDao.getSessionFactory().openSession();
                String sql = "SELECT ventillation FROM Ventillation ventillation join ventillation.soumission soumission where soumission.id = :id";
                Query query = session.createQuery(sql);
                query.setParameter("id", offre.getSoumission().getId());
                List<VentillationModel> ventillations = query.list();
                return ventillations;
            } catch (Exception e) {
                throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
            } finally {
                if (session != null) {
                    session.close();
                    session = null;
                }
            }
        }
        if (type == VentilationData.TS) {
            if (offre.getSoumission() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            try {
                session = this.hibernateDao.getSessionFactory().openSession();
                String sql = "SELECT ventillation FROM VentillationTS ventillation join ventillation.travauxSupplementaire travauxSupplementaire where travauxSupplementaire.id = :id";

                Query query = session.createQuery(sql);
                query.setParameter("id", offre.getTravauxSupplementaire().getId());
                List<VentillationModel> ventillations = query.list();
                return ventillations; 

            } catch (Exception e) {
                throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
            } finally {
                if (session != null) {
                    session.close();
                    session=null;
                }
            }
        }
        throw new Exception("type incorrect, le type inserer n'est pas inscrit dans la base");       
    }

    public void save(VentillationModel ventillation, Offre offre, short type) throws Exception {
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            Soumission soumission = offre.getSoumission();
            Ventillation ventillationS = (Ventillation) ventillation;
            ventillationS.setSoumission(soumission);
            this.save(ventillationS);
        } else {
            if (offre.getTravauxSupplementaire() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            TravauxSupplementaire travauxSupplementaire = offre.getTravauxSupplementaire();
            VentillationTS ventillationS = (VentillationTS) ventillation;
            ventillationS.setTravauxSupplementaire(travauxSupplementaire);
            this.save(ventillationS);
        }

    }

    public void save(VentillationModel ventillation, Offre offre, short type, Session session) throws Exception {
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            Soumission soumission = offre.getSoumission();
            Ventillation ventillationS = (Ventillation) ventillation;
            ventillationS.setSoumission(soumission);
            this.save(ventillationS, session);
        } else {
            if (offre.getTravauxSupplementaire() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            TravauxSupplementaire travauxSupplementaire = offre.getTravauxSupplementaire();
            VentillationTS ventillationS = (VentillationTS) ventillation;
            ventillationS.setTravauxSupplementaire(travauxSupplementaire);
            this.save(ventillationS, session);
        }

    }

    public boolean ventillationSoumissionExist(Offre offre) throws Exception {
        if (offre.getSoumission() == null) {
            SoumissionService soumissionService = new SoumissionService();
            soumissionService.setHibernateDao(hibernateDao);
            soumissionService.find(offre);
        }
        Session session = null;
        try {
            session = this.hibernateDao.getSessionFactory().openSession();
            String sql = "SELECT ventillation FROM Ventillation ventillation join ventillation.soumission soumission where soumission.id = :id";

            Query query = session.createQuery(sql);
            query.setParameter("id", offre.getSoumission().getId());
            List<Ventillation> ventillations = query.list();

            return !ventillations.isEmpty();
        } catch (Exception e) {
            throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean ventillationTSExist(Offre offre) throws Exception {
        if (offre.getSoumission() == null) {
            TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
            travauxSupplementaireService.setHibernateDao(hibernateDao);
            travauxSupplementaireService.find(offre);
        }
        Session session = null;
        try {
            session = this.hibernateDao.getSessionFactory().openSession();
            String sql = "SELECT ventillation FROM VentillationTS ventillation join ventillation.travauxSupplementaire travauxSupplementaire where travauxSupplementaire.id = :id";

            Query query = session.createQuery(sql);
            query.setParameter("id", offre.getTravauxSupplementaire().getId());
            List<VentillationTS> ventillations = query.list();

            return !ventillations.isEmpty();
        } catch (Exception e) {
            throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public VentillationModel find(String reference)throws Exception{
        VentillationModel reponse =null; 
        try{
            ReflectService reflectService = new ReflectService(this.getHibernateDao());
            reponse = (VentillationModel)reflectService.find(reference);
        }catch(Exception e){
            throw new Exception("impossible de retrouver la ventilation correspondante");
        }
        return reponse; 
    }
    
}
