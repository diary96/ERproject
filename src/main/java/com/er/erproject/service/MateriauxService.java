/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.dao.HibernateDao;
import com.er.erproject.data.StatuReference;
import com.er.erproject.model.Materiaux;
import com.er.erproject.model.Offre;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class MateriauxService extends ServiceModel {
    public void save(Materiaux materiaux)throws Exception{
        try{
            this.hibernateDao.save(materiaux);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Impossible de sauvegarder le materiel"); 
        }
    }
    public void find(Materiaux materiaux)throws Exception{
        try{
            this.hibernateDao.findById(materiaux);
        }catch(Exception e){
            throw new Exception("Impossible d'initialiser le materiel cause : "+e.getMessage());
        }
    }
    public List<Materiaux> find()throws Exception{
        try{
            return (List<Materiaux>)(Object)this.hibernateDao.findAll(new Materiaux());
        }catch(Exception e){
            throw new Exception("Impossible d'initialiser le materiel cause : "+e.getMessage());
        }
    }
    public void changeStatu(String reference) throws Exception{
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        Materiaux materiaux = (Materiaux) reflectService.find(reference);
        if(materiaux.getEtat()==StatuReference.RECU) materiaux.setEtat(StatuReference.NON_RECU);
        else materiaux.setEtat(StatuReference.RECU);
        try{
            this.hibernateDao.update(materiaux);
        }catch(Exception e){
            throw new Exception(reference+" mise a jour impossible");
        }
    }
    public List<Materiaux> find(Offre offre)throws Exception{
        Session session = null;
        try{
            session = hibernateDao.getSessionFactory().openSession();
            String sql = "select m from Materiaux m join m.offre o where o.id = :id";        
            Query query = session.createQuery(sql);
            query.setParameter("id", offre.getId());
            List<Materiaux> temp = query.list();
           
            offre.setMateriaux(temp);
            return temp;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Impossible d'initialiser les materiaux de l'offre "+offre.getAllReference());
        }finally{
            if(session!=null)session.close();
        }     
    }

    public MateriauxService() {
    }
    public MateriauxService(HibernateDao hibernateDao) {
        this.setHibernateDao(hibernateDao);
    }
    
}
