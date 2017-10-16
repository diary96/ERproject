/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.data.VentilationData;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Pagination;
import com.er.erproject.model.Soumission;
import com.er.erproject.model.TravauxSupplementaire;
import com.er.erproject.model.Ventillation;
import com.er.erproject.model.VentillationModel;
import com.er.erproject.model.VentillationTS;
import com.er.erproject.util.DateUtil;
import com.er.erproject.util.UtilConvert;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author diary
 */
public class VentillationService extends ServiceModel {

    public void save(VentillationModel vantillation) throws Exception {
        this.hibernateDao.save(vantillation);
    }

    public void save(VentillationModel vantillation, Session session) throws Exception {
        this.hibernateDao.save(vantillation, session);
    }
    
    public void payer(VentillationModel ventilation)throws Exception{
        if(ventilation.getDatepaiement()!=null)throw new Exception(ventilation.getAllReference()+" a déjà été");
        ventilation.setDatepaiement(Calendar.getInstance().getTime());
        try{
            this.hibernateDao.update(ventilation);
        }catch(Exception e){
            throw new Exception("impossible de mettre à jour "+ventilation.getReference());
        }
    }
    
    public void payer(VentillationModel ventilation,Date date) throws Exception{
        if(ventilation.getDatepaiement()!=null)throw new Exception(ventilation.getAllReference()+" a déjà été");
        ventilation.setDatepaiement(date);
        try{
            this.hibernateDao.update(ventilation);
        }catch(Exception e){
            throw new Exception("impossible de mettre à jour "+ventilation.getReference());
        }    
    }
    
    public static Soumission find(Ventillation ventilation,Session session)throws Exception{
        Soumission soumission = null; 
        try{
            String sql = "SELECT soumission FROM Ventillation ventillation join ventillation.soumission soumission where ventillation.id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", ventilation.getId());
            if(!query.list().isEmpty()){
                soumission = (Soumission) query.list().get(0);
            }
           
        }catch(Exception e){
            throw new Exception("impossibe d'extraire la soumission de la ventilation "+ventilation.getAllReference());
        }
        return soumission;
    }
    
    public static TravauxSupplementaire find(VentillationTS ventilation,Session session)throws Exception{
        TravauxSupplementaire travauxSupplementaire = null; 
        try{
            String sql = "SELECT travauxSupplementaire FROM VentillationTS ventillation join ventillation.travauxSupplementaire travauxSupplementaire where ventillation.id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", ventilation.getId());
            if(!query.list().isEmpty()){
                travauxSupplementaire = (TravauxSupplementaire) query.list().get(0);
            }
           
        }catch(Exception e){
            throw new Exception("impossibe d'extraire le travaux supplementaire de la ventilation "+ventilation.getAllReference());
        }
        return travauxSupplementaire;
    }
    
    private int getSizeRowVentillation(List<String[]> arg,Session session,String payement,String retard) throws Exception {       
        try {
            Criteria criteria = session.createCriteria(Ventillation.class, "ventilation");
            criteria.createAlias("ventilation.soumission", "soumission"); 
            criteria.createAlias("soumission.offre", "offre");
            if(payement!=null&&payement.compareTo("")!=0){
                if(payement.compareToIgnoreCase("true")==0){
                    criteria.add(Restrictions.isNotNull("ventilation.datepaiement"));
                }else{
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                }            
            }
            if(retard!=null&&retard.compareTo("")!=0){
                if(payement==null||payement.compareTo("")==0){              
                    if(retard.compareToIgnoreCase("true")==0){
                        criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                    }else{
                        criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                    }
                    
                }else{
                    if(payement.compareToIgnoreCase("true")==0){
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                        }else{
                            criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                        }
                        
                    }else{
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        }else{
                            criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        }
                    }
                }
            }
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);
                if (temp.length == 2) {   
                    try{
                        if(temp[0].contains(".id")){
                            criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                        }else{
                            criteria.add(Restrictions.eq(temp[0], Integer.valueOf(temp[1])));
                        }
                    }catch(Exception e){
                       
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
            String value = String.valueOf((long) criteria.uniqueResult());
            int count = Integer.valueOf(value);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erreur d'extraction de ventillation");
        } 
    }
    
    private int getSizeRowVentillationTs(List<String[]> arg,Session session,String payement,String retard) throws Exception {       
        try {
            Criteria criteria = session.createCriteria(VentillationTS.class, "ventilation");
            criteria.createAlias("ventilation.travauxSupplementaire", "travauxSupplementaire"); 
            criteria.createAlias("travauxSupplementaire.offre", "offre");
            if(payement!=null&&payement.compareTo("")!=0){
                if(payement.compareToIgnoreCase("true")==0){
                    criteria.add(Restrictions.isNotNull("ventilation.datepaiement"));
                }else{
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                }            
            }
            if(retard!=null&&retard.compareTo("")!=0){
                if(payement==null||payement.compareTo("")==0){              
                    if(retard.compareToIgnoreCase("true")==0){
                        criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                    }else{
                        criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                    }
                    
                }else{
                    if(payement.compareToIgnoreCase("true")==0){
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                        }else{
                            criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                        }
                        
                    }else{
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        }else{
                            criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        }
                    }
                }
            }
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);
                if (temp.length == 2) {   
                    try{
                        if(temp[0].contains(".id")){
                            criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                        }else{
                            criteria.add(Restrictions.eq(temp[0], Integer.valueOf(temp[1])));
                        }
                    }catch(Exception e){
                       
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
            String value = String.valueOf((long) criteria.uniqueResult());
            int count = Integer.valueOf(value);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erreur d'extraction de ventillation");
        } 
    }
    
    public List<Ventillation> findPopulateVentillation(List<String[]> arg,String payement,String retard,String order,String orderOld,Pagination pagination)throws Exception{
        List<Ventillation> ventillations = null;
        Session session = null; 
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            int page = this.getSizeRowVentillation(arg, session, payement, retard);
            int realTotal = 0;
            if (page != 0) {
                realTotal = page / pagination.getTaillePage();
            }
            if (page % pagination.getTaillePage() != 0) {
                realTotal += 1;
            }
            pagination.setMax(realTotal);
            Criteria criteria = session.createCriteria(Ventillation.class, "ventilation");
            criteria.createAlias("ventilation.soumission", "soumission"); 
            criteria.createAlias("soumission.offre", "offre");
            
            if (order != null && order.compareTo("") != 0) {
                if(orderOld.compareTo(order)==0){
                    criteria.addOrder(Order.desc(order));
                }else{
                    criteria.addOrder(Order.asc(order));
                }
                
            }
            criteria.setFirstResult((pagination.getPage() - 1) * pagination.getTaillePage());
            criteria.setMaxResults(pagination.getTaillePage());
            
            if(payement!=null&&payement.compareTo("")!=0){
                if(payement.compareToIgnoreCase("true")==0){
                    criteria.add(Restrictions.isNotNull("ventilation.datepaiement"));
                }else{
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                }            
            }
            if(retard!=null&&retard.compareTo("")!=0){
                if(payement==null||payement.compareTo("")==0){   
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                    if(retard.compareToIgnoreCase("true")==0){
                        criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                       
                    }else{
                        criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        
                    }
                    
                }else{
                    if(payement.compareToIgnoreCase("true")==0){
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                        }else{
                            criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                        }
                        
                    }else{
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        }else{
                            criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        }
                    }
                }
            }
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);
                if (temp.length == 2) {   
                    try{
                        if(temp[0].contains(".id")){
                            criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                        }else{
                            criteria.add(Restrictions.eq(temp[0], Integer.valueOf(temp[1])));
                        }
                    }catch(Exception e){
                       
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
            ventillations = criteria.list();
            for(int index=0;index<ventillations.size();index++){
                Ventillation ventillation = ventillations.get(index);
                ventillation.setSoumission(VentillationService.find(ventillation, session));
                ventillation.getSoumission().setOffre(SoumissionService.find(ventillation.getSoumission(), session));
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire les factures cause "+e.getMessage());
                    
        }finally{
            if(session!=null)session.close();
        }
        return ventillations;
    }
    
    public List<Ventillation> findPopulateVentillation(List<String[]> arg,String payement,String retard,String order,String orderOld)throws Exception{
        List<Ventillation> ventillations = null;
        Session session = null; 
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            
            Criteria criteria = session.createCriteria(Ventillation.class, "ventilation");
            criteria.createAlias("ventilation.soumission", "soumission"); 
            criteria.createAlias("soumission.offre", "offre");
            
            if (order != null && order.compareTo("") != 0) {
                if(orderOld.compareTo(order)==0){
                    criteria.addOrder(Order.desc(order));
                }else{
                    criteria.addOrder(Order.asc(order));
                }
                
            }
            if(payement!=null&&payement.compareTo("")!=0){
                if(payement.compareToIgnoreCase("true")==0){
                    criteria.add(Restrictions.isNotNull("ventilation.datepaiement"));
                }else{
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                }            
            }
            if(retard!=null&&retard.compareTo("")!=0){
                if(payement==null||payement.compareTo("")==0){   
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                    if(retard.compareToIgnoreCase("true")==0){
                        criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                       
                    }else{
                        criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        
                    }
                    
                }else{
                    if(payement.compareToIgnoreCase("true")==0){
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                        }else{
                            criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                        }
                        
                    }else{
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        }else{
                            criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        }
                    }
                }
            }
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);
                if (temp.length == 2) {   
                    try{
                        if(temp[0].contains(".id")){
                            criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                        }else{
                            criteria.add(Restrictions.eq(temp[0], Integer.valueOf(temp[1])));
                        }
                    }catch(Exception e){
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
            ventillations = criteria.list();
            for(int index=0;index<ventillations.size();index++){
                Ventillation ventillation = ventillations.get(index);
                ventillation.setSoumission(VentillationService.find(ventillation, session));
                ventillation.getSoumission().setOffre(SoumissionService.find(ventillation.getSoumission(), session));
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire les factures cause "+e.getMessage());
                    
        }finally{
            if(session!=null)session.close();
        }
        return ventillations;
    }
    
    public List<VentillationTS> findPopulateVentillationTs(List<String[]> arg,String payement,String retard,String order,String orderOld,Pagination pagination)throws Exception{
        List<VentillationTS> ventillations = null;
        Session session = null; 
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            int page = this.getSizeRowVentillationTs(arg, session, payement, retard);
            int realTotal = 0;
            if (page != 0) {
                realTotal = page / pagination.getTaillePage();
            }
            if (page % pagination.getTaillePage() != 0) {
                realTotal += 1;
            }
            pagination.setMax(realTotal);
            Criteria criteria = session.createCriteria(VentillationTS.class, "ventilation");
            criteria.createAlias("ventilation.travauxSupplementaire", "travauxSupplementaire"); 
            criteria.createAlias("travauxSupplementaire.offre", "offre");
            
            if (order != null && order.compareTo("") != 0) {
                if(orderOld.compareTo(order)==0){
                    criteria.addOrder(Order.desc(order));
                }else{
                    criteria.addOrder(Order.asc(order));
                }
                
            }
            criteria.setFirstResult((pagination.getPage() - 1) * pagination.getTaillePage());
            criteria.setMaxResults(pagination.getTaillePage());
            
            if(payement!=null&&payement.compareTo("")!=0){
                if(payement.compareToIgnoreCase("true")==0){
                    criteria.add(Restrictions.isNotNull("ventilation.datepaiement"));
                }else{
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                }            
            }
            if(retard!=null&&retard.compareTo("")!=0){
                if(payement==null||payement.compareTo("")==0){   
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                    if(retard.compareToIgnoreCase("true")==0){
                        criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                       
                    }else{
                        criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        
                    }
                    
                }else{
                    if(payement.compareToIgnoreCase("true")==0){
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                        }else{
                            criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                        }
                        
                    }else{
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        }else{
                            criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        }
                    }
                }
            }
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);
                if (temp.length == 2) {   
                    try{
                        if(temp[0].contains(".id")){
                            criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                        }else{
                            criteria.add(Restrictions.eq(temp[0], Integer.valueOf(temp[1])));
                        }
                    }catch(Exception e){
                       
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
            ventillations = criteria.list();
            for(int index=0;index<ventillations.size();index++){
                VentillationTS ventillation = ventillations.get(index);
                ventillation.setTravauxSupplementaire(VentillationService.find(ventillation, session));
                ventillation.getTravauxSupplementaire().setOffre(TravauxSupplementaireService.find(ventillation.getTravauxSupplementaire(), session));
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire les factures cause "+e.getMessage());
                    
        }finally{
            if(session!=null)session.close();
        }
        return ventillations;
    }
    
    public List<VentillationTS> findPopulateVentillationTs(List<String[]> arg,String payement,String retard,String order,String orderOld)throws Exception{
        List<VentillationTS> ventillations = null;
        Session session = null; 
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            
            Criteria criteria = session.createCriteria(VentillationTS.class, "ventilation");
            criteria.createAlias("ventilation.travauxSupplementaire", "travauxSupplementaire"); 
            criteria.createAlias("travauxSupplementaire.offre", "offre");
            
            if (order != null && order.compareTo("") != 0) {
                if(orderOld.compareTo(order)==0){
                    criteria.addOrder(Order.desc(order));
                }else{
                    criteria.addOrder(Order.asc(order));
                }
                
            }
            
            if(payement!=null&&payement.compareTo("")!=0){
                if(payement.compareToIgnoreCase("true")==0){
                    criteria.add(Restrictions.isNotNull("ventilation.datepaiement"));
                }else{
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                }            
            }
            if(retard!=null&&retard.compareTo("")!=0){
                if(payement==null||payement.compareTo("")==0){   
                    criteria.add(Restrictions.isNull("ventilation.datepaiement"));
                    if(retard.compareToIgnoreCase("true")==0){
                        criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                       
                    }else{
                        criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        
                    }
                    
                }else{
                    if(payement.compareToIgnoreCase("true")==0){
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.ltProperty("ventilation.date","ventilation.datepaiement"));
                        }else{
                            criteria.add(Restrictions.geProperty("ventilation.date","ventilation.datepaiement"));
                        }
                        
                    }else{
                        if(retard.compareToIgnoreCase("true")==0){
                            criteria.add(Restrictions.lt("ventilation.date",Calendar.getInstance().getTime()));
                        }else{
                            criteria.add(Restrictions.ge("ventilation.date",Calendar.getInstance().getTime()));
                        }
                    }
                }
            }
            for (int i = 0; i < arg.size(); i++) {
                String[] temp = arg.get(i);
                if (temp.length == 2) {   
                    try{
                        if(temp[0].contains(".id")){
                            criteria.add(Restrictions.eq(temp[0], Long.valueOf(temp[1])));
                        }else{
                            criteria.add(Restrictions.eq(temp[0], Integer.valueOf(temp[1])));
                        }
                    }catch(Exception e){
                       
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
            ventillations = criteria.list();
            for(int index=0;index<ventillations.size();index++){
                VentillationTS ventillation = ventillations.get(index);
                ventillation.setTravauxSupplementaire(VentillationService.find(ventillation, session));
                ventillation.getTravauxSupplementaire().setOffre(TravauxSupplementaireService.find(ventillation.getTravauxSupplementaire(), session));
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire les factures cause "+e.getMessage());
                    
        }finally{
            if(session!=null)session.close();
        }
        return ventillations;
    }
    
    public static String spliter(List<VentillationModel> data)throws Exception{
        String reponse=""; 
        if(data==null)throw new Exception("les conditions ne sont pas initialisé");
        for(int i=0;i<data.size();i++){
            VentillationModel ventillationTemp = data.get(i);
            String tempData; 
            String payementName; 
            if(ventillationTemp.getPayementName()==null)payementName ="none";
            else payementName = ventillationTemp.getPayementName();
            tempData = String.valueOf(ventillationTemp.getPourcentage())+"/"+DateUtil.convert(ventillationTemp.getDate())+"/"+ventillationTemp.getTypeDescription()+"/"+payementName+",";
            reponse+=tempData;
        }
        return reponse;
    }
    
    public static List<VentillationModel> spliter(String data, short type) throws Exception {
        List<VentillationModel> ventillations = new ArrayList();
        String[] fistSplit = data.split(",");

        for (int i = 0; i < fistSplit.length; i++) {
            String[] temp = new String[4];
            
            temp = fistSplit[i].split("/");
            if(temp.length<4)throw new Exception("une des donnees de ventilation n'est pas complet,veuillez utiliser none pour une valeur vide ");
            VentillationModel ventillation = null;
            if (type == VentilationData.SOUMISSION) {
                ventillation = new Ventillation();
            }
            if (type == VentilationData.TS) {
                ventillation = new VentillationTS();
            }
            if (ventillation == null) {
                throw new Exception("type inconnu");
            }
            if(temp[0].compareToIgnoreCase("none")==0)throw new Exception("une des pourcentage de ventilation est vide");
            if(temp[1].compareToIgnoreCase("none")==0)throw new Exception("une des date de ventilation est vide");
            if(temp[2].compareToIgnoreCase("none")==0)throw new Exception("une des type de ventilation est vide");
            ventillation.setPourcentage(temp[0]);
            ventillation.setDate(temp[1]);
            ventillation.setTypeDescription(temp[2]);
            if(temp[3].compareToIgnoreCase("none")!=0){
                ventillation.setPayementName(temp[3]);
            }
            ventillations.add(ventillation);
        }
        double somme = 0;
        for (int i = 0; i < ventillations.size(); i++) {
            somme += ventillations.get(i).getPourcentage();
        }
        if (somme != 100) {
            throw new Exception("La somme des pourcentages doit etre égale à 100% ");
        }
        return ventillations;
    }
    
    public void save(String ventillationData, Offre offre, short type) throws Exception {
        List<VentillationModel> ventillations = VentillationService.spliter(ventillationData, type);
        Session session = null;
        Transaction tr = null;
        try {           
            session = hibernateDao.getSessionFactory().openSession();
            tr = session.beginTransaction();
            if(type==VentilationData.SOUMISSION){
                if (this.ventillationSoumissionExist(offre)) {
                    this.delete(offre, type, session); 
                }
            }
            else{
                if(this.ventillationTSExist(offre)){
                    this.delete(offre, type, session); 
                }
            }
            for (int i = 0; i < ventillations.size(); i++) {
                this.save(ventillations.get(i), offre, type, session);
            }           
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new Exception("impossible de sauvegarder la ventillation dans la base " + e.getMessage());

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
       
    public List<VentillationModel> find(Offre offre, short type) throws Exception {
        Session session = null;
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            try {
                session = this.hibernateDao.getSessionFactory().openSession();
                String sql = "SELECT ventillation FROM Ventillation ventillation join ventillation.soumission soumission where soumission.id = :id";
                Query query = session.createQuery(sql);
                query.setParameter("id", offre.getSoumission().getId());
                List<VentillationModel> ventillations = query.list();
                return ventillations;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
            } finally {
                if (session != null) {
                    session.close();
                    session = null;
                }
            }
        }
        if (type == VentilationData.TS) {
            if (offre.getTravauxSupplementaire() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            try {
                session = this.hibernateDao.getSessionFactory().openSession();
                String sql = "SELECT ventillation FROM VentillationTS ventillation join ventillation.travauxSupplementaire travauxSupplementaire where travauxSupplementaire.id = :id";

                Query query = session.createQuery(sql);
                query.setParameter("id", offre.getTravauxSupplementaire().getId());
                List<VentillationModel> ventillations = query.list();
                return ventillations; 

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
            } finally {
                if (session != null) {
                    session.close();
                    session=null;
                }
            }
        }
        throw new Exception("type incorrect, le type inserer n'est pas inscrit dans la base");       
    }

    public void save(VentillationModel ventillation, Offre offre, short type) throws Exception {
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            Soumission soumission = offre.getSoumission();
            Ventillation ventillationS = (Ventillation) ventillation;
            ventillationS.setSoumission(soumission);
            this.save(ventillationS);
        } else {
            if (offre.getTravauxSupplementaire() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            TravauxSupplementaire travauxSupplementaire = offre.getTravauxSupplementaire();
            VentillationTS ventillationS = (VentillationTS) ventillation;
            ventillationS.setTravauxSupplementaire(travauxSupplementaire);
            this.save(ventillationS);
        }

    }

    public void save(VentillationModel ventillation, Offre offre, short type, Session session) throws Exception {
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            Soumission soumission = offre.getSoumission();
            Ventillation ventillationS = (Ventillation) ventillation;
            ventillationS.setSoumission(soumission);
            this.save(ventillationS, session);
        } else {
            if (offre.getTravauxSupplementaire() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            TravauxSupplementaire travauxSupplementaire = offre.getTravauxSupplementaire();
            VentillationTS ventillationS = (VentillationTS) ventillation;
            ventillationS.setTravauxSupplementaire(travauxSupplementaire);
            this.save(ventillationS, session);
        }

    }

    private void delete(VentillationModel ventillation)throws Exception{
        try{
            this.hibernateDao.delete(ventillation);
        }catch(Exception e){
            throw new Exception("impossible de supprimer la ventilation");
        }
    }
    
    private static void delete(VentillationModel ventillation, Session session)throws Exception{
        try{
            session.delete(ventillation);
        }catch(Exception e){
            throw new Exception("impossible de supprimer la ventilation");
        }
    }
    
    public void delete(Offre offre, short type)throws Exception{
        List<VentillationModel> ventillation = this.find(offre, type);
        if(ventillation.isEmpty())throw new Exception("la ventilation est vide");
        Session session = null; 
        Transaction tr=null;
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            tr = session.beginTransaction(); 
            for(int i=0;i<ventillation.size();i++){
                VentillationService.delete(ventillation.get(i), session);
            }
            tr.commit();
        }catch(Exception e){ 
            if(tr!=null)tr.rollback();
            throw e;
        }finally{
            if(session!=null)session.close();
        }
    }
    
    public void delete(Offre offre, short type,Session session)throws Exception{
        List<VentillationModel> ventillation = this.find(offre, type);
        if(ventillation.isEmpty())throw new Exception("la ventilation est vide");    
        try{
           
            for(int i=0;i<ventillation.size();i++){
                VentillationService.delete(ventillation.get(i), session);
            }
            
        }catch(Exception e){
            throw e;
        }
    }
    
    public boolean ventillationSoumissionExist(Offre offre) throws Exception {
        if (offre.getSoumission() == null) {
            SoumissionService soumissionService = new SoumissionService();
            soumissionService.setHibernateDao(hibernateDao);
            soumissionService.find(offre);
        }
        Session session = null;
        try {
            session = this.hibernateDao.getSessionFactory().openSession();
            String sql = "SELECT ventillation FROM Ventillation ventillation join ventillation.soumission soumission where soumission.id = :id";

            Query query = session.createQuery(sql);
            query.setParameter("id", offre.getSoumission().getId());
            List<Ventillation> ventillations = query.list();

            return !ventillations.isEmpty();
        } catch (Exception e) {
            throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean ventillationTSExist(Offre offre) throws Exception {
        if (offre.getSoumission() == null) {
            TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
            travauxSupplementaireService.setHibernateDao(hibernateDao);
            travauxSupplementaireService.find(offre);
        }
        Session session = null;
        try {
            session = this.hibernateDao.getSessionFactory().openSession();
            String sql = "SELECT ventillation FROM VentillationTS ventillation join ventillation.travauxSupplementaire travauxSupplementaire where travauxSupplementaire.id = :id";

            Query query = session.createQuery(sql);
            query.setParameter("id", offre.getTravauxSupplementaire().getId());
            List<VentillationTS> ventillations = query.list();

            return !ventillations.isEmpty();
        } catch (Exception e) {
            throw new Exception("Impossible de tester l'existance de la ventillation de l'offre :" + offre.getAllReference());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public VentillationModel find(String reference)throws Exception{
        VentillationModel reponse =null; 
        try{
            ReflectService reflectService = new ReflectService(this.getHibernateDao());
            reponse = (VentillationModel)reflectService.find(reference);
        }catch(Exception e){
            throw new Exception("impossible de retrouver la ventilation correspondante");
        }
        return reponse; 
    }
    
}
