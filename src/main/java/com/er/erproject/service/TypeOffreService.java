/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.dao.HibernateDao;
import com.er.erproject.model.TypeOffre;
import java.util.List;

/**
 *
 * @author diary
 */
public class TypeOffreService extends ServiceModel{  
    public List<TypeOffre> findAll() throws Exception{
        return (List<TypeOffre>) (Object) this.hibernateDao.findAll(new TypeOffre());
    }
    public void find(TypeOffre typeOffre)throws Exception{
        this.hibernateDao.findById(typeOffre);
    }
  
}
