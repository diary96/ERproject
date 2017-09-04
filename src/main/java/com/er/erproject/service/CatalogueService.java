/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.model.BaseModel;
import com.er.erproject.model.Catalogue;
import com.er.erproject.model.CatalogueModel;
import com.er.erproject.model.HorsCatalogue;
import com.er.erproject.model.Pagination;
import com.er.erproject.util.UtilConvert;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author diary
 */
public class CatalogueService extends ServiceModel {

    public List<CatalogueModel> findAllCatalogue() throws Exception {
        return (List<CatalogueModel>) (Object) hibernateDao.findAll(new Catalogue());
    }

    public List<CatalogueModel> findAllHorsCatalogue() throws Exception {
        return (List<CatalogueModel>) (Object) hibernateDao.findAll(new HorsCatalogue());
    }

    private int getSizeRow(List<String[]> arg,Class classe,Session session) throws Exception {       
        try {
            Criteria criteria = session.createCriteria(classe, "catalogue");
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);
                if (temp.length == 2) {
                    try {
                        criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                    } catch (Exception e) {

                        criteria.add(Restrictions.ilike(temp[0], "%" + temp[1] + "%"));
                    }
                }
                if (temp.length == 3) {
                    try {
                        criteria.add(Restrictions.between(temp[0], UtilConvert.convertToSQLDate(temp[1]), UtilConvert.convertToSQLDate(temp[2])));

                    } catch (Exception e) {
                        criteria.add(Restrictions.between(temp[0], Double.valueOf(temp[1]), Double.valueOf(temp[2])));
                    }
                }
            }
            criteria.setProjection(Projections.rowCount());
            int count = Integer.valueOf(String.valueOf((long) criteria.uniqueResult()));
            return count;
        } catch (Exception e) {
            throw new Exception("erreur d'extraction de catalogue");
        } 
    }

    public List<Catalogue> findCatalogue(List<String[]> arg, String order, Pagination pagination) throws Exception {
        Session session = null;
        try{
            session = hibernateDao.getSessionFactory().openSession();
            int page = this.getSizeRow(arg,Catalogue.class,session);
            int realTotal = 0;
            if (page != 0) {
                realTotal = page / pagination.getTaillePage();
            }
            if (page % pagination.getTaillePage() != 0) {
                realTotal += 1;
            }
            pagination.setMax(realTotal);
            Criteria criteria = session.createCriteria(Catalogue.class, "catalogue");
            if (order != null && order.compareTo("") != 0) {
                criteria.addOrder(Order.asc(order));
            }
            criteria.setFirstResult((pagination.getPage() - 1) * pagination.getTaillePage());

            criteria.setMaxResults(pagination.getTaillePage());
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);

                if (temp.length == 2) {
                    try {
                        criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                    } catch (Exception e) {

                        criteria.add(Restrictions.ilike(temp[0], "%" + temp[1] + "%"));
                    }
                }
                if (temp.length == 3) {
                    try {
                        criteria.add(Restrictions.between(temp[0], UtilConvert.convertToSQLDate(temp[1]), UtilConvert.convertToSQLDate(temp[2])));

                    } catch (Exception e) {
                        criteria.add(Restrictions.between(temp[0], Double.valueOf(temp[1]), Double.valueOf(temp[2])));

                    }
                }
            }
            List<Catalogue> list = criteria.list();
            return list;
        }catch(Exception e){
            throw new Exception("impossible d'extraire la requete");
        }finally{
            if(session!=null)session.close();
        }
    }
    
    public List<HorsCatalogue> findHorsCatalogue(List<String[]> arg, String order, Pagination pagination) throws Exception {
        Session session = null;
        try{
            session = hibernateDao.getSessionFactory().openSession();
            int page = this.getSizeRow(arg,HorsCatalogue.class,session);
            int realTotal = 0;
            if (page != 0) {
                realTotal = page / pagination.getTaillePage();
            }
            if (page % pagination.getTaillePage() != 0) {
                realTotal += 1;
            }
            pagination.setMax(realTotal);
            Criteria criteria = session.createCriteria(HorsCatalogue.class, "catalogue");
            if (order != null && order.compareTo("") != 0) {
                criteria.addOrder(Order.asc(order));
            }
            criteria.setFirstResult((pagination.getPage() - 1) * pagination.getTaillePage());

            criteria.setMaxResults(pagination.getTaillePage());
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);

                if (temp.length == 2) {
                    try {
                        criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                    } catch (Exception e) {

                        criteria.add(Restrictions.ilike(temp[0], "%" + temp[1] + "%"));
                    }
                }
                if (temp.length == 3) {
                    try {
                        criteria.add(Restrictions.between(temp[0], UtilConvert.convertToSQLDate(temp[1]), UtilConvert.convertToSQLDate(temp[2])));

                    } catch (Exception e) {
                        criteria.add(Restrictions.between(temp[0], Double.valueOf(temp[1]), Double.valueOf(temp[2])));

                    }
                }
            }
            List<HorsCatalogue> list = criteria.list();
            return list;
        }catch(Exception e){
            throw new Exception("impossible d'extraire la requete");
        }finally{
            if(session!=null)session.close();
        }
    }

    public CatalogueModel find(String reference) throws Exception {
        ReflectService reflectionService = new ReflectService();
        reflectionService.setHibernateDao(this.hibernateDao);
        BaseModel temp = reflectionService.find(reference);
        try {
            CatalogueModel value = (CatalogueModel) temp;
            return value;
        } catch (Exception e) {
            throw new Exception("La reference inserer n'est pas de type de Catalogue");
        }
    }

    public void save(Catalogue catalogue) throws Exception {
        try {
            this.hibernateDao.save(catalogue);
        } catch (Exception e) {
            throw new Exception("impossible de sauvegarder le catalogue dans la base :" + e.getMessage() + " veuillez informer l'administrateur");
        }
    }

    public void save(HorsCatalogue horsCatalogue) throws Exception {
        try {
            this.hibernateDao.save(horsCatalogue);
        } catch (Exception e) {
            throw new Exception("impossible de sauvegarder l'hors catalogue dans la base :" + e.getMessage() + "veuillez informer l'administrateur");

        }
    }

}
