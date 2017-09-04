/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.dao.HibernateDao;
import com.er.erproject.data.Reference;
import com.er.erproject.model.BaseModel;
import com.er.erproject.util.ReferenceUtil;

/**
 *
 * @author diary
 */
public class ReflectService extends ServiceModel{

    public ReflectService(HibernateDao hibernateDao) {
        this.hibernateDao = hibernateDao;
    }

    public ReflectService() {
    }
    
    public BaseModel find(String reference)throws Exception{
        BaseModel autoCast = (BaseModel) ReferenceUtil.toBaseModel(reference);
        try{
            hibernateDao.findById(autoCast);
        }catch(Exception e){
            throw new Exception("la reference inserer est introuvable");
        }
        return autoCast;
    }
    public void delete(String reference)throws Exception{
        BaseModel autoCast = (BaseModel) ReferenceUtil.toBaseModel(reference);
        try{
            hibernateDao.findById(autoCast);
            hibernateDao.delete(autoCast);
        }catch(Exception e){
            throw new Exception("Impossible de supprimer l'objet "+reference+" puisqu'il n'est pas vide");
        }
    }
    
   
}
