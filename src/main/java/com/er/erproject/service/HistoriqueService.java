/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.Historique;
import com.er.erproject.model.Offre;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author diary
 */
public class HistoriqueService extends ServiceModel {
    public void save(Historique historique, Offre offre)throws Exception{
        if(offre.getId()==0)throw new Exception("l'offre n'est pas initialisé");
        try{
            historique.setReference(offre.getAllReference());
            this.hibernateDao.save(historique);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'enegistrer l'historique dans la base");
        }
    }
    public void save(Historique historique)throws Exception{ 
        try{
            this.hibernateDao.save(historique);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'enegistrer l'historique dans la base");
        }
    }
    public void find(Historique historique)throws Exception{ 
        try{
            this.hibernateDao.findById(historique);
        }catch(Exception e){
            throw new Exception("impossible d'extraire l'historique");
        }
    }
    public List<Historique> find(Offre offre)throws Exception{
        List<Historique> reponse = null ; 
        Session session = null;
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Historique.class,"historique");
            criteria.add(Restrictions.like("historique.referenceExterieur", offre.getAllReference()));
            reponse = criteria.list();
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire les historiques de l'offre "+offre.getAllReference());
        }finally{
            if(session!=null)session.close();
        }
        return reponse;
    }
}
