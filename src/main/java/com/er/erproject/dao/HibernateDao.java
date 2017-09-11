package com.er.erproject.dao;

import com.er.erproject.model.BaseModel;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class HibernateDao {
    private SessionFactory sessionFactory;// = HibernateSession.getSessionFactory();
    
    public HibernateDao(){}
    public HibernateDao(SessionFactory sessionFactory){
            this.setSessionFactory(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(BaseModel obj) throws Exception{
        Session session = null;
        Transaction tr=null;
        try{
            session = getSessionFactory().openSession();
            tr=session.beginTransaction();
            session.save(obj);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    public void save(BaseModel obj,Session session) throws Exception{
        try{        
            session.save(obj);
        }catch (Exception ex){         
            throw ex;
        }
    }

    public void findById(BaseModel obj) throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.load(obj,obj.getId());
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    public void findById(BaseModel obj,Session session) throws Exception{
       
        try{
            session.load(obj,obj.getId());
        }catch (Exception ex){
            throw ex;
        }
    }

    @SuppressWarnings("unchecked")
    public List<BaseModel> findAll(BaseModel obj)  throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(obj.getClass());
            criteria.addOrder(Order.asc("id"));
            return criteria.list();
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    

    public void update(BaseModel model) throws Exception {
            // TODO Auto-generated method stub
        Session session = null;
        Transaction tr=null;
        try{
            session = getSessionFactory().openSession();
            tr=session.beginTransaction();
            session.update(model);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }   
    }
    public void update(BaseModel model,Session session) throws Exception {      
        try{    
            session.update(model);         
        }catch (Exception ex){
            
            throw ex;
        }   
    }

    public void delete(BaseModel model) throws Exception {
        // TODO Auto-generated method stub
        Session session = null;
        Transaction tr=null;
        try{
            session = getSessionFactory().openSession();
            tr=session.beginTransaction();
            session.delete(model);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }   
    }
}
