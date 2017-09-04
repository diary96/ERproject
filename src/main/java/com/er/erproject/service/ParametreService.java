/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.Parametre;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author diary
 */
public class ParametreService extends ServiceModel {
    public void save(Parametre parametre)throws Exception{
        this.hibernateDao.save(parametre);
    }
    public Parametre getLast()throws Exception{
        Parametre reponse=null; 
        Session session = null; 
        try{
            session = hibernateDao.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Parametre.class, "parametre");
            criteria.addOrder(Order.desc("dateajout"));
            List<Parametre> listes = criteria.list();
            if(!listes.isEmpty())reponse = listes.get(0);
        }catch(Exception e){
            throw new Exception("Impossible d'extraire les paramatres ");
        }finally{
            if(session!=null)session.close();
        }
        return reponse; 
    }
}
