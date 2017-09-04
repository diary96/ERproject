/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.Offre;
import com.er.erproject.model.Soumission;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class SoumissionService extends ServiceModel{
    public void save(Soumission soumission)throws Exception{
        try{
          hibernateDao.save(soumission);
        }catch(Exception e){
            e.printStackTrace();          
            throw new Exception("Erreur lors de la creation de la soumission cause : "+e.getMessage());
        }
    }
    public Offre find(Soumission soumission)throws Exception{
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select o from Soumission s join s.offre o where s.id = :id"; 
        
        Query query = session.createQuery(sql); 
        query.setParameter("id", soumission.getId());
        List<Offre> offres = query.list();
        session.close();
        if (!offres.isEmpty()) {
            return offres.get(0);
        }
        return null;
    }
    public Soumission find(Offre offre)throws Exception{
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select s from Soumission s join s.offre o where o.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", offre.getId());
        List<Soumission> catalogues = query.list();
        session.close();
        if( !catalogues.isEmpty()){
            offre.setSoumission(catalogues.get(0));
            return offre.getSoumission();
        }
        return null;
    }
    public void update(Soumission soumission)throws Exception{
        try{
            this.hibernateDao.update(soumission);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Impossible de mettre a jour la soumission");
        }
    }
    public boolean exist(Offre offre)throws Exception{
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select s from Soumission s join s.offre o where o.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", offre.getId());
        List<Soumission> catalogues = query.list();
        session.close();
        return !catalogues.isEmpty();
    }
}
