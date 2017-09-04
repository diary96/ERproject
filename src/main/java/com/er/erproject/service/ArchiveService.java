/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.Archive;
import com.er.erproject.model.Offre;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author diary
 */
public class ArchiveService extends ServiceModel{
    public void save(Archive archive)throws Exception{
        try{
            this.hibernateDao.save(archive);
        }catch(Exception e){
            throw new Exception("impossible d'enregistrer l'archive "+archive.getNom()+" dans la base");
        }
    }
    public List<Archive> find(Offre offre)throws Exception{
        Session session = null; 
        List<Archive> reponse = null;
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            Criteria c = session.createCriteria(Archive.class, "archive");
            c.createAlias("archive.offre", "offre");
            c.add(Restrictions.eq("offre.id", offre.getId()));
            c.addOrder(Order.asc("archive.id"));
            reponse = c.list();
            return reponse;
        }catch(Exception e){
            throw new Exception("impossible d'extraire les archives de l'offre "+offre.getAllReference()+" cause "+e.getMessage());
        }finally{
            if(session!=null)session.close();
        }
    }
}
