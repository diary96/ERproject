/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.Archive;
import com.er.erproject.model.Offre;
import com.er.erproject.util.FileUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            e.printStackTrace();
            throw new Exception("impossible d'enregistrer l'archive "+archive.getNom()+" dans la base");
        }
    }
    public void update(Archive archive)throws Exception{
        Session session=null;
        Transaction tr = null;
        if(archive.getId()==0)throw new Exception("archive non initialise");
        try{
            session = this.hibernateDao.getSessionFactory().openSession(); 
            tr = session.beginTransaction();
            Archive temp =null;
            temp = this.find(archive.getId(), session);
            if(temp.getPath().compareTo(archive.getPath())!=0){
                FileUtil.deleteFile(temp.getPath());
            }
            this.hibernateDao.update(temp, session);
            tr.commit();           
        }catch(Exception e){            
            if(tr!=null)tr.rollback();
            e.printStackTrace();
            throw new Exception("impossible de mettre a jour l'archive "+archive.getAllReference()+" cause "+e.getMessage());
        }finally{
            if(session!=null)session.close();
        }
    }
    public Archive find(long idArchive,Session session)throws Exception{
        Archive reponse= new Archive(); 
        try{
            reponse.setId(idArchive);
            this.hibernateDao.findById(reponse,session);
        }catch(Exception e){
            throw new Exception("archive introuvable"); 
        }
        return reponse;
    }
    public void find(Archive archive)throws Exception{
        try{
            this.hibernateDao.findById(archive);
        }catch(Exception e){
            throw new Exception("impossible de trouver l'archive dans la base");
        }
    }
    public void delete(Archive archive)throws Exception{
        try{
            try{
            FileUtil.deleteFile(archive.getPath());
            }catch(Exception e){
                throw new Exception("impossible de supprimer le fichier de l'archive");
            }
            try{
                this.hibernateDao.delete(archive);
            }catch(Exception e){
                throw new Exception("impossible de supprimer l'archive dans la base");
            }
        }catch(Exception e){
            throw new Exception("la suppression n'a pas ete enregistrer cause "+e.getMessage());
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
            Archive temp;
            for(int i=0;i<reponse.size();i++){
                temp = reponse.get(i);
                temp.setOffre(offre);              
                TypeFichierService.find(temp, session);               
            }
            return reponse;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire les archives de l'offre "+offre.getAllReference()+" cause "+e.getMessage());
        }finally{
            if(session!=null)session.close();
        }
    }
}
