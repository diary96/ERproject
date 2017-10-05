/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.Offre;
import com.er.erproject.model.TravauxSupplementaire;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class TravauxSupplementaireService extends ServiceModel{
    public void save(TravauxSupplementaire travauxSupplementaire)throws Exception{
        try{
          hibernateDao.save(travauxSupplementaire);
        }catch(Exception e){
            e.printStackTrace();          
            throw new Exception("Erreur lors de la creation de le TS cause : "+e.getMessage());
        }
    }
    public Offre find(TravauxSupplementaire travauxSupplementaire)throws Exception{
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select o from TravauxSupplementaire s join s.offre o where s.id = :id"; 
        
        Query query = session.createQuery(sql); 
        query.setParameter("id", travauxSupplementaire.getId());
        List<Offre> offres = query.list();
        session.close();
        if (!offres.isEmpty()) {
            return offres.get(0);
        }
        return null;
    }
    public static Offre find(TravauxSupplementaire travauxSupplementaire, Session session)throws Exception{
        Offre offre = null;
        try{
            String sql = "select o from TravauxSupplementaire s join s.offre o where s.id = :id"; 
        
            Query query = session.createQuery(sql); 
            query.setParameter("id", travauxSupplementaire.getId());
            if(!query.list().isEmpty())offre = (Offre) query.list().get(0);
        }catch(Exception e){
           throw new Exception("impossible d'extraire l'offre du travaux supplementaire "+travauxSupplementaire.getAllReference());
        }
        return offre;
    }
    
    public TravauxSupplementaire find(Offre offre)throws Exception{
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select s from TravauxSupplementaire s join s.offre o where o.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", offre.getId());
        List<TravauxSupplementaire> catalogues = query.list();
        session.close();
        if( !catalogues.isEmpty()){
            offre.setTravauxSupplementaire(catalogues.get(0));
            return offre.getTravauxSupplementaire();
        }
        return null;
    }
    
    public void update(TravauxSupplementaire travauxSupplementaire)throws Exception{
        try{
            this.hibernateDao.update(travauxSupplementaire);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Impossible de mettre a jour la soumission");
        }
    }
    public boolean exist(Offre offre)throws Exception{
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select s from TravauxSupplementaire s join s.offre o where o.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", offre.getId());
        List<TravauxSupplementaire> catalogues = query.list();
        session.close();
        return !catalogues.isEmpty();
    }
}
