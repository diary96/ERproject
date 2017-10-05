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
import org.hibernate.Transaction;

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
    
    public void find(User user)throws Exception{
        try{
            this.hibernateDao.findById(user);
        }catch(Exception e){
            throw new Exception("impossible d'extraire l'utilisateur"); 
        
        }
    }
    
    public void find(User user,Session session)throws Exception{
        try{
            this.hibernateDao.findById(user,session);
        }catch(Exception e){
            throw new Exception("impossible d'extraire l'utilisateur"); 
        
        }
    }
    
    public User find(String matricule)throws Exception{
        Session session = null;
        try{
            session = hibernateDao.getSessionFactory().openSession();
            String sql = "from User u where u.matricule = :login";
            Query query = session.createQuery(sql);
            query.setParameter("login", matricule);
            List<User> list = query.list();
            if(list.size()>0)return list.get(0);
        }catch(Exception e){
            throw new Exception("impossible d'extraire l'user");
        }finally{
            if(session!=null)session.close();
        }  
        return null;
    }
    
    public User find(String matricule,Session session)throws Exception{
       
        String sql = "from User u where u.matricule = :login";
        Query query = session.createQuery(sql);
        query.setParameter("login", matricule);       
        List<User> list = query.list();
        if(list.size()>0)return list.get(0);
        return null;
    }
    
    public void save(User user)throws Exception{
        Session session=null; 
        Transaction tr = null;
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            tr = session.beginTransaction();
            User test = this.find(user.getMatricule(), session);
            if(test!=null)throw new Exception("cette matricule existe deja et ne peut plus etre utilisé");
            session.save(user);
            tr.commit();
        }catch(Exception e){
            tr.rollback();
            throw new Exception("erreur lors de l'enregistrement cause : "+e.getMessage());
        }finally{
            if(session!=null)session.close();
        }  
    }
    
    public List<User> find()throws Exception{
        List<User> reponse=null; 
        try{
            reponse = (List<User>)(Object)this.hibernateDao.findAll(new User());
        }catch(Exception e){
            throw new Exception("impossible d'extraire les users");
        }
        return reponse;
    }

    public User find(long id)throws Exception{
        User user = new User();
        user.setId(id);
        hibernateDao.findById(user);
        return user;
    }
    public void update(User user)throws Exception{
        try{
            User userTemp = this.find(user.getId());
            if(userTemp.getMatricule().compareTo(user.getMatricule())!=0){
                User userTest = this.find(user.getMatricule());
                if(user!=null)throw new Exception("le matricule inseré est déjà attribué à une personne");
            }
            this.hibernateDao.update(user);
        }catch(Exception e){
            throw new Exception("impossible de mettre a jour l'user "+user.getAllReference()+" cause "+e.getMessage());
        }
    }
    
}
