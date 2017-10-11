/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.dao.HibernateDao;
import com.er.erproject.model.Photo;
import com.er.erproject.model.TacheModel;
import com.er.erproject.util.FileUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class PhotoService extends ServiceModel {

    public PhotoService(HibernateDao hibernateDao) {
        this.setHibernateDao(hibernateDao);
    }

    public PhotoService() {
    }
    
    public void save(Photo photo)throws Exception{
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        CatalogueService catalogueService = new CatalogueService();
        catalogueService.setHibernateDao(hibernateDao);
        TacheModel tache = null;
        try{
            tache = (TacheModel)reflectService.find(photo.getReferenceTravaux());
            
        }catch(Exception e){
            throw new Exception("La reference n'est pas une tache"); 
        }
//        catalogueService.find(tache.getCatalogue().getAllReference());
//        if(tache.getCatalogue().getIsAdmin())throw new Exception("Impossible d'enregistrer une photo pour les dossier de type administratif");
        try{
            photo.populateCoordonnee();
            this.hibernateDao.save(photo);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Impossible de sauvegarder la photo");
        }
    }
    public void delete(String reference,HttpServletRequest servletRequest)throws Exception{
        FileUtil fileUtil = new FileUtil(servletRequest);
        Photo photo = null;
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        try{
            photo = (Photo)reflectService.find(reference); 
        }catch(Exception e){
            throw new Exception("La reference n'est pas une photo");
        }
        try{
            this.hibernateDao.delete(photo);
        }catch(Exception e){
            throw new Exception("Impossible de supprimer la photo");
        }
        try{
           fileUtil.deleteFile(photo.getPathPhoto());
        }catch(Exception e){
            throw new Exception("Impossible de supprimer la photo dans la base, supprimer manuellement");
        }
        
        
    }
    public List<Photo> find(String reference) throws Exception{
        List<Photo> photo = new ArrayList();
        Session session = null;
        try{
            session = hibernateDao.getSessionFactory().openSession();
            String sql = "select p from Photo p where p.referenceTravaux = :reference"; 

            Query query = session.createQuery(sql); 
            query.setParameter("reference", reference);
            photo = query.list();
            for(int i=0;i<photo.size();i++){
                photo.get(i).populateLL();
            }
            
            return photo;
        }catch(Exception e){
            throw new Exception("impossible d'extraire les donnees");
        }finally{
            if(session!=null) session.close();
        }
    }
    
    public static List<Photo> find(String reference,Session session) throws Exception{
        List<Photo> photo = new ArrayList();
        if(session==null)throw new Exception("la connexion n'existe pas");
        try{
            String sql = "select p from Photo p where p.referenceTravaux = :reference"; 

            Query query = session.createQuery(sql); 
            query.setParameter("reference", reference);
            photo = query.list();
            for(int i=0;i<photo.size();i++){
                photo.get(i).populateLL();
            }
            
            return photo;
        }catch(Exception e){
            throw new Exception("impossible d'extraire les donnees");
        }
    }
    public void update(Photo photo)throws Exception{
        try{
            this.hibernateDao.save(photo);                
        }catch(Exception e){
            throw new Exception("Impossible de mettre a jour la photo");
        }
    
    }
}
