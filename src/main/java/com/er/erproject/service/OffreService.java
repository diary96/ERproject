/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.data.StatuReference;
import com.er.erproject.data.VentilationData;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Catalogue;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Pagination;
import com.er.erproject.model.Soumission;
import com.er.erproject.model.Statistique;
import com.er.erproject.model.TacheModel;
import com.er.erproject.model.Travaux;
import com.er.erproject.model.TravauxSupplementaire;
import com.er.erproject.model.TypeOffre;
import com.er.erproject.model.User;
import com.er.erproject.util.UtilConvert;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

/**
 *
 * @author diary
 */
public class OffreService extends ServiceModel {

    private int getSizeRow(List<String[]> arg,Class classe,String bc,String bcTs,Session session) throws Exception {       
        try {
            Criteria criteria = session.createCriteria(classe, "offre");
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
                        if(temp[0].contains(".close")){
                            if(temp[1].compareTo("true")==0){
                                criteria.add(Restrictions.eq(temp[0],true));
                            }
                            else{
                                criteria.add(Restrictions.eq(temp[0],false));
                            }
                            
                        }
                        else{
                            criteria.add(Restrictions.ilike(temp[0], "%" + temp[1] + "%"));
                        }
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
            if(bc!=null&&bc.compareTo("")!=0){
                DetachedCriteria subCriteria = DetachedCriteria.forClass(Soumission.class, "soumission");
                subCriteria.createAlias("soumission.bonCommande", "bonCommande");
                subCriteria.add(Restrictions.eqProperty("soumission.offre.id","offre.id"));
                subCriteria.setProjection(Projections.count("soumission.bonCommande"));
                if(bc.compareToIgnoreCase("true")==0){ 
                   criteria.add(Subqueries.gt(Long.valueOf("0"),subCriteria));
                }else{
                    criteria.add(Subqueries.le(Long.valueOf("0"),subCriteria));
                }             
            }      
            if(bcTs!=null&&bcTs.compareTo("")!=0){
                DetachedCriteria subCriteria = DetachedCriteria.forClass(TravauxSupplementaire.class, "soumission");
                subCriteria.createAlias("soumission.bonCommande", "bonCommande");
                subCriteria.add(Restrictions.eqProperty("soumission.offre.id","offre.id"));
                subCriteria.setProjection(Projections.count("soumission.bonCommande"));
                if(bcTs.compareToIgnoreCase("true")==0){ 
                   criteria.add(Subqueries.gt(Long.valueOf("0"),subCriteria));
                }else{
                    criteria.add(Subqueries.le(Long.valueOf("0"),subCriteria));
                }
            }
            criteria.setProjection(Projections.rowCount());
            String value = String.valueOf((long) criteria.uniqueResult());
            int count = Integer.valueOf(value);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erreur d'extraction de catalogue");
        } 
    }
    
    public List<Offre> filtreBC(List<Offre> offres, boolean test){
        BonCommandeService bonCommandeService = new BonCommandeService();
        bonCommandeService.setHibernateDao(hibernateDao);
        List<Offre> reponse = new ArrayList();
        if(test==true){
            for(int i=0;i<offres.size();i++){
                try{
                    BonCommande bonCommande = bonCommandeService.find(offres.get(i),VentilationData.SOUMISSION );
                    
                    if(bonCommande!=null)reponse.add(offres.get(i));
                }catch(Exception e){
                    
                }
            }
        }else{
            for(int i=0;i<offres.size();i++){
                try{
                    BonCommande bonCommande = bonCommandeService.find(offres.get(i),VentilationData.SOUMISSION );
                    if(bonCommande==null)reponse.add(offres.get(i));
                }catch(Exception e){
                    reponse.add(offres.get(i));
                }
            }
        }
        return reponse;
    }
    
    public List<Offre> filtreBCTS(List<Offre> offres, boolean test){
        BonCommandeService bonCommandeService = new BonCommandeService();
        bonCommandeService.setHibernateDao(hibernateDao);
        List<Offre> reponse = new ArrayList();
        if(test==true){
            for(int i=0;i<offres.size();i++){
                try{
                    BonCommande bonCommande = bonCommandeService.find(offres.get(i),VentilationData.TS );
                    
                    if(bonCommande!=null)reponse.add(offres.get(i));
                }catch(Exception e){
                    
                }
            }
        }else{
            for(int i=0;i<offres.size();i++){
                try{
                    BonCommande bonCommande = bonCommandeService.find(offres.get(i),VentilationData.TS );
                    if(bonCommande==null)reponse.add(offres.get(i));
                }catch(Exception e){
                    reponse.add(offres.get(i));
                }
            }
        }
        return reponse;
    }
    
    public List<Offre> find(List<String[]> arg, String order,String orderOld, Pagination pagination,String bc,String bcTs) throws Exception {
        Session session = null;
        try{
            session = hibernateDao.getSessionFactory().openSession();
            int page = this.getSizeRow(arg,Offre.class,bc,bcTs,session);
            int realTotal = 0;
            if (page != 0) {
                realTotal = page / pagination.getTaillePage();
            }
            if (page % pagination.getTaillePage() != 0) {
                realTotal += 1;
            }
            pagination.setMax(realTotal);
            Criteria criteria = session.createCriteria(Offre.class, "offre");
            criteria.createAlias("offre.typeOffre", "type");
            
            if (order != null && order.compareTo("") != 0) {
                if(orderOld.compareTo(order)==0){
                    criteria.addOrder(Order.desc(order));
                }else{
                    criteria.addOrder(Order.asc(order));
                }
                
            }else{
                criteria.addOrder(Order.desc("offre.id"));
            }
            criteria.setFirstResult((pagination.getPage() - 1) * pagination.getTaillePage());

            criteria.setMaxResults(pagination.getTaillePage());
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
                        if(temp[0].contains(".close")){
                            if(temp[1].compareTo("true")==0){
                                criteria.add(Restrictions.eq(temp[0],true));
                            }
                            else{
                                criteria.add(Restrictions.eq(temp[0],false));
                            }
                            
                        }
                        else{
                            criteria.add(Restrictions.ilike(temp[0], "%" + temp[1] + "%"));
                        }
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
            
            
            if(bc!=null&&bc.compareTo("")!=0){
                DetachedCriteria subCriteria = DetachedCriteria.forClass(Soumission.class, "soumission");
                subCriteria.createAlias("soumission.bonCommande", "bonCommande");
                subCriteria.add(Restrictions.eqProperty("soumission.offre.id","offre.id"));
                subCriteria.setProjection(Projections.count("soumission.bonCommande"));
                if(bc.compareToIgnoreCase("true")==0){ 
                   criteria.add(Subqueries.gt(Long.valueOf("0"),subCriteria));
                }else{
                    criteria.add(Subqueries.le(Long.valueOf("0"),subCriteria));
                }             
            }      
            if(bcTs!=null&&bcTs.compareTo("")!=0){
                DetachedCriteria subCriteria = DetachedCriteria.forClass(TravauxSupplementaire.class, "soumission");
                subCriteria.createAlias("soumission.bonCommande", "bonCommande");
                subCriteria.add(Restrictions.eqProperty("soumission.offre.id","offre.id"));
                subCriteria.setProjection(Projections.count("soumission.bonCommande"));
                if(bcTs.compareToIgnoreCase("true")==0){ 
                   criteria.add(Subqueries.gt(Long.valueOf("0"),subCriteria));
                }else{
                    criteria.add(Subqueries.le(Long.valueOf("0"),subCriteria));
                }
            }
            List<Offre> list = criteria.list();
            return list;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire la requete");
        }finally{
            if(session!=null)session.close();
        }
    }
    
    public List<Offre> findAll() throws Exception {
        return (List<Offre>) (Object) hibernateDao.findAll(new Offre());
    }

    public List<Offre> findAllWithDepart() throws Exception {
        List<Offre> offres = (List<Offre>) (Object) hibernateDao.findAll(new Offre());
        for (int i = 0; i < offres.size(); i++) {
            Offre tempOffre = offres.get(i);
            tempOffre.setTypeOffre(this.findTypeOffre(tempOffre));
//            this.hibernateDao.findById(temp);
        }
        return offres;
    }
    
    public void checkerTSEmpty(Offre offre)throws Exception{     
        
        if (offre.getTravauxSupplementaire() == null) {
            this.populateTS(offre);
            if (offre.getTravauxSupplementaire() == null) {
                throw new Exception("le travaux supplementaire n'est pas initialiser");
            }
        }
        if (offre.getTacheSupplementaire() == null) {
            this.populateTravauxSupplementaire(offre);
        }
        if (offre.getTacheSupplementaire().getTravaux().isEmpty()) {
            throw new Exception("aucun tache n'est presente dans les travaux supplementaire");
        }
    }
    
    public Offre find(long idOffre) throws Exception {
        Offre offre = new Offre();
        offre.setId(idOffre);
        hibernateDao.findById(offre);
        return offre;
    }

    public void popoluteTacheInitial(Offre offre) throws Exception {
        if (offre.getId() == 0) {
            throw new Exception("L'offre que vous essayer de remplir n'est pas instancier ou n'existe pas");
        }
        TravauxService travauxService = new TravauxService();
        travauxService.setHibernateDao(hibernateDao);
        travauxService.findInitial(offre);
    }

    public void populateStatistiqueInitial(Offre offre) throws Exception {
        if (offre.getId() == 0) {
            throw new Exception("L'offre que vous essayer de remplir n'est pas instancier ou n'existe pas");
        }
        if (offre.getTacheinitials() == null) {
            this.popoluteTacheInitial(offre);
        }
        if (offre.getSoumission() == null) {
            this.popoluteTacheSoumission(offre);
        }
        Statistique statistique = new Statistique(offre.getTacheinitials().getTravaux(), offre.getTacheSoumission().getTravaux(), offre.getSoumission().getTva(), offre.getSoumission().getRemise());
        offre.setStatInitial(statistique);
    }

    public void populateStatistiqueTS(Offre offre) throws Exception {
        if (offre.getId() == 0) {
            throw new Exception("L'offre que vous essayer de remplir n'est pas instancier ou n'existe pas");
        }
        if (offre.getTacheSupplementaire() == null) {
            this.populateTravauxSupplementaire(offre);
        }

        Statistique statistique = new Statistique(offre.getTacheSupplementaire().getTravaux(), offre.getTravauxSupplementaire().getTva(), offre.getTravauxSupplementaire().getRemise());
        offre.setStatTS(statistique);
    }

    public void populatePhoto(Offre offre) throws Exception {
        if (offre.getId() == 0) {
            throw new Exception("L'offre que vous essayer de remplir n'est pas instancier ou n'existe pas");
        }
        TravauxService travauxService = new TravauxService();
        travauxService.setHibernateDao(hibernateDao);
        travauxService.populatePhoto(offre);

    }

    public void popoluteTacheSoumission(Offre offre) throws Exception {
        if (offre.getId() == 0) {
            throw new Exception("L'offre que vous essayer de remplir n'est pas instancier ou n'existe pas");
        }
        TravauxService travauxService = new TravauxService();
        travauxService.setHibernateDao(hibernateDao);
        travauxService.findSoumission(offre);

    }

    public void populateSoumission(Offre offre)throws Exception{
        SoumissionService soumissionService = new SoumissionService();
        soumissionService.setHibernateDao(hibernateDao); 
        try{
            soumissionService.find(offre);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire la soumission de l'offre "+offre.getAllReference());
        }
    }
    
    public void populateTS(Offre offre)throws Exception{
        TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService(); 
        travauxSupplementaireService.setHibernateDao(hibernateDao);
        try{
        travauxSupplementaireService.find(offre);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("impossible d'extraire les travaux supplementaire de l'offre "+offre.getAllReference());
        }
    }
    
    public void populateTravauxSupplementaire(Offre offre) throws Exception {
        if (offre.getId() == 0) {
            throw new Exception("L'offre que vous essayer de remplir n'est pas instancier ou n'existe pas");
        }
        TravauxService travauxService = new TravauxService();
        travauxService.setHibernateDao(hibernateDao);
        travauxService.findTravauxSupplementaire(offre);
    }

    public static void removeAdministratif(Travaux travaux) throws Exception {
        List<TacheModel> temp = new ArrayList();
        for (int i = 0; i < travaux.getTacheHorsCatalogue().size(); i++) {
            if (!travaux.getTacheHorsCatalogue().get(i).getCatalogue().getIsAdmin()) {
                temp.add(travaux.getTacheHorsCatalogue().get(i));
            }
        }
        travaux.setTacheHorsCatalogue(temp);
    }

    public void populateMateriaux(Offre offre) throws Exception {
        if (offre.getId() == 0) {
            throw new Exception("L'offre que vous essayer de remplir n'est pas instancier ou n'existe pas");
        }
        MateriauxService materiauxService = new MateriauxService(this.hibernateDao);
        materiauxService.find(offre);
    }

    public TypeOffre findTypeOffre(Offre offre) throws Exception {
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select t from Offre o join o.typeOffre t where  o.id = :id";
        Query query = session.createQuery(sql);
        query.setParameter("id", offre.getId());

        List<TypeOffre> list = query.list();
        session.close();
        if (!list.isEmpty()) {
            TypeOffre reponse = list.get(list.size() - 1);
            offre.setTypeOffre(reponse);
            return reponse;
        } else {
            return null;
        }

    }

    public void save(Offre offre) throws Exception {
        try {
            offre.setClose(false);
            if(offre.getDeadline()!=null&&offre.getDatetravauxprevu()!=null){
                if(offre.getDatetravauxprevu().before(offre.getDeadline())){
                    throw new Exception("la date prevu des travaux doit se situer apres la date de deadline");
                }
            }
            this.hibernateDao.save(offre);

        } catch (Exception e) {
            throw new Exception("Impossible de sauvegarder l'offre, cause : " + e.getMessage());
        }
    }
    
    public Offre find(String ticket)throws Exception{
        Offre offre = null;
        Session session = null; 
        try{
            session = this.hibernateDao.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Offre.class,"offre");
            criteria.add(Restrictions.like("offre.ticket", ticket));
            criteria.addOrder(Order.asc("offre.id"));
            if(!criteria.list().isEmpty()){
                offre = (Offre) criteria.list().get(0);
            }
        }catch(Exception e){
            throw new Exception("impossible d'extraire l'offre "+ticket); 
        }finally{
            if(session!=null)session.close();
        }
        return offre;
    }
    
    private void toSoumission(Offre offre, User user) throws Exception {
        if (offre.getTacheinitials().getTravaux().isEmpty()) {
            throw new Exception("L'offre n'a pas de tache initialiser et ne peut pas etre enregistrer");
        }
        SoumissionService soumissionService = new SoumissionService();
        soumissionService.setHibernateDao(hibernateDao);
        Soumission soumission = new Soumission();
        soumission.setOffre(offre);
        soumission.setUser(user);
        offre.setStatu(StatuReference.SOUMISSION);
        try {
            this.hibernateDao.update(offre);
            if (!soumissionService.exist(offre)) {
                soumissionService.save(soumission);
            }

        } catch (Exception e) {
            e.printStackTrace();
            offre.setStatu(StatuReference.OFFRE);
            this.hibernateDao.update(offre);
            throw new Exception("Impossible d'initialiser la soumission cause : " + e.getMessage());
        }
    }

    private void toTravaux(Offre offre) throws Exception {
        if (offre.getTacheinitials().getTravaux().isEmpty()) {
            throw new Exception("L'offre n'a pas de tache initialiser et ne peut pas etre enregistrer");
        }
        TravauxSupplementaireService travauxSuppplementaireService = new TravauxSupplementaireService();
        travauxSuppplementaireService.setHibernateDao(hibernateDao);
        TravauxSupplementaire travauxSupplementaire = new TravauxSupplementaire();
        travauxSupplementaire.setOffre(offre);

        offre.setStatu(StatuReference.TRAVAUX);
        try {
            this.hibernateDao.update(offre);
            if (!travauxSuppplementaireService.exist(offre)) {
                travauxSuppplementaireService.save(travauxSupplementaire);
            }

        } catch (Exception e) {
            e.printStackTrace();
            offre.setStatu(StatuReference.SOUMISSION);
            this.hibernateDao.update(offre);
            throw new Exception("Impossible d'initialiser du travaux cause " + e.getMessage());
        }
    }

    private void toPV(Offre offre) throws Exception {
        if (offre.getTacheinitials().getTravaux().isEmpty()) {
            throw new Exception("L'offre n'a pas de tache initialiser et ne peut pas etre enregistrer");
        }

        offre.setStatu(StatuReference.PV);
        try {
            this.hibernateDao.update(offre);
        } catch (Exception e) {
            e.printStackTrace();
            offre.setStatu(StatuReference.TRAVAUX);
            this.hibernateDao.update(offre);
            throw new Exception("Impossible d'initialiser du travaux cause " + e.getMessage());
        }
    }

    private void toFacturation(Offre offre) throws Exception {
        offre.setStatu(StatuReference.FACTURATION);
        try {
            this.hibernateDao.update(offre);
        } catch (Exception e) {
            e.printStackTrace();
            offre.setStatu(StatuReference.PV);
            this.hibernateDao.update(offre);
            throw new Exception("Impossible d'initialiser du travaux cause " + e.getMessage());
        }
    }

    public void valider(Offre offre, User user, String nextLevel) throws Exception {
        if (offre.getClose() == true) {
            throw new Exception("Cette offre est fermé, vous ne pouvez plus modifier");
        }

        int level = StatuReference.getStatu(nextLevel);
        if (offre.getStatu() >= level) {
            throw new Exception("Vous ne pouvez pas retrograder de niveau");
        }
        if (level == StatuReference.SOUMISSION) {
            this.toSoumission(offre, user);
        }
        if (level == StatuReference.TRAVAUX) {
            this.toTravaux(offre);
        }
        if (level == StatuReference.PV) {
            this.toPV(offre);
        }
        if (level == StatuReference.FACTURATION) {
            this.toFacturation(offre);
        }
    }

    public void close(Offre offre)throws Exception{
        if(offre.getClose()==true)throw new Exception("l'offre est déjà fermé");
        offre.setClose(true);
        try{
            this.hibernateDao.update(offre);
        }catch(Exception e){
            throw new Exception("impossible de cloturé l'offre "+e.getMessage());
        }
    }
    
    public void open(Offre offre)throws Exception{
        if(offre.getClose()==false)throw new Exception("l'offre est déjà ouvert");
        offre.setClose(false);
        try{
            this.hibernateDao.update(offre);
        }catch(Exception e){
            throw new Exception("la réouverture de  l'offre "+e.getMessage()+" est impossible");
        }
    }
    
    public void downgrade(Offre offre, int etat)throws Exception{
        if(etat>4||etat<0)throw new Exception("l'etape inseré n'existe pas"); 
        if(offre.getStatu()<etat)throw new Exception("Veuillez choisir une etape inferieure de celle de l'etape actuel");
        offre.setStatu(etat);
        try{
            this.hibernateDao.update(offre);
        }catch(Exception e){
            throw new Exception("impossible de changer l'etape de l'offre cause "+e.getMessage());
        }
    }
}
