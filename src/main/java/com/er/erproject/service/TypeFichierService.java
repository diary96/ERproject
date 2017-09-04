/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.TypeFichier;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class TypeFichierService extends ServiceModel {
    public void save(TypeFichier typeFichier) throws Exception{
        try{
            this.hibernateDao.save(typeFichier);
        }catch(Exception e){
            throw new Exception("Impossible de sauvegarder "+typeFichier.getNomType()+" cause "+e.getMessage());
        }
    }
    
    public void save(TypeFichier typeFichier, Session session)throws Exception{
        try{
            session.save(typeFichier);
        }catch(Exception e){
            throw new Exception("Impossible de sauvegarder "+typeFichier.getNomType()+" cause "+e.getMessage());
        }
    }
    
    public List<TypeFichier> find() throws Exception {
        List<TypeFichier> reponse = null; 
        try{
            reponse = (List<TypeFichier>)(Object)this.hibernateDao.findAll(new TypeFichier());
            return reponse;
        }catch(Exception e){
            throw new Exception("impossible d'extraire la liste de tout les type de fichier cause "+e.getMessage());
        }
    }
    
    public void find(TypeFichier typeFichier)throws Exception{
        if(typeFichier.getId()==0)throw new Exception("le type de fichier que vous essayer d'initialiser n'existe pas");
        try{
            this.hibernateDao.findById(typeFichier);    
        }catch(Exception e){
            throw new Exception("impossibe d'initialiser le type de fichier cause "+e.getMessage());
        } 
    }
    
    public void update(TypeFichier typeFichier)throws Exception{
        try{
            this.hibernateDao.update(typeFichier);
        }catch(Exception e){
            throw new Exception("impossible de modifier cause "+e.getMessage());
        }
    }
    
    public void delete(TypeFichier typeFichier)throws Exception{
        try{
            this.hibernateDao.delete(typeFichier);
        }catch(Exception e){
            throw new Exception("impossible de supprimer "+typeFichier.getNomType()+" dans la base cause "+e.getMessage());
        }
    }
}
