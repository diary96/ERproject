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
                if(temp.getTypeFichier()==null){
                   TypeFichierService.find(temp, session);
                }
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
