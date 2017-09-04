/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.dao.HibernateDao;
import com.er.erproject.model.User;
import com.er.erproject.security.Cryptographie;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class UserService extends ServiceModel {
    public User login(String matricule, String password)throws Exception{
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "from User u where u.matricule = :login AND u.password = :password";
        Query query = session.createQuery(sql);
        query.setParameter("login", matricule);
        String crypt = Cryptographie.crypterHashage(password);
        query.setParameter("password",Cryptographie.crypterHashage(password));
        List<User> list = query.list();
        session.close();
        if(list.size()>0)return list.get(0);
        else throw new Exception("L'email ou le mot de passe est incorrect");
    }
    
    
}
