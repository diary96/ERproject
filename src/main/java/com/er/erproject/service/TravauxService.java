/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.dao.HibernateDao;
import com.er.erproject.data.Reference;
import com.er.erproject.data.ReferenceType;
import com.er.erproject.data.ReflectData;
import com.er.erproject.model.BaseModel;
import com.er.erproject.model.Catalogue;
import com.er.erproject.model.CatalogueModel;
import com.er.erproject.model.HorsCatalogue;
import com.er.erproject.model.Offre;
import com.er.erproject.model.TacheInitialCatalogue;
import com.er.erproject.model.TacheInitialHorsCatalogue;
import com.er.erproject.model.TacheModel;
import com.er.erproject.model.TacheSoumissionCatalogue;
import com.er.erproject.model.TacheSoumissionHorsCatalogue;
import com.er.erproject.model.TacheTSCatalogue;
import com.er.erproject.model.TacheTSHorsCatalogue;
import com.er.erproject.model.Travaux;
import com.er.erproject.util.ReferenceUtil;
import com.er.erproject.util.UtilConvert;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author diary
 */
public class TravauxService extends ServiceModel {

    public void populatePhoto(TacheModel tacheModel) throws Exception {
        PhotoService photoService = new PhotoService();
        photoService.setHibernateDao(hibernateDao);

        tacheModel.setPhotos(photoService.find(tacheModel.getAllReference()));

    }

    public void populatePhoto(Offre offre) throws Exception {
        TacheModel temp;
        if (offre.getTacheinitials() != null) {
            for (int i = 0; i < offre.getTacheinitials().getTravaux().size(); i++) {
                temp = offre.getTacheinitials().getTravaux().get(i);
                this.populatePhoto(temp);
            }
        }
        if (offre.getTacheSoumission() != null) {
            for (int i = 0; i < offre.getTacheSoumission().getTravaux().size(); i++) {
                temp = offre.getTacheSoumission().getTravaux().get(i);
                this.populatePhoto(temp);
            }
        }
        if (offre.getTacheSupplementaire() != null) {
            for (int i = 0; i < offre.getTacheSupplementaire().getTravaux().size(); i++) {
                temp = offre.getTacheSupplementaire().getTravaux().get(i);
                this.populatePhoto(temp);
            }
        }
    }

    public void save(String catalogueReference, String designation, String prixUnitaire, String Unite, String quantite, String type, String refRelation, String admin) throws Exception {
        if (type == null || type.compareToIgnoreCase("") == 0) {
            throw new Exception("Pas de type");
        }
        if (catalogueReference.compareToIgnoreCase("") != 0) {
            BaseModel base = ReferenceUtil.toBaseModel(catalogueReference);
            Class test = Reference.getClass(base.getReference());
            CatalogueModel catalogue;
            CatalogueService catalogueService = new CatalogueService();
            ReflectService reflectService = new ReflectService();
            reflectService.setHibernateDao(hibernateDao);
            catalogueService.setHibernateDao(hibernateDao);
            catalogue = catalogueService.find(catalogueReference);
            if (test.getSimpleName().compareToIgnoreCase(Catalogue.class.getSimpleName()) == 0) {
                if (type.compareToIgnoreCase(ReferenceType.INITIAL) == 0) {
                    TacheInitialCatalogue tic = new TacheInitialCatalogue();
                    tic.setCatalogue(catalogue);
                    tic.setOffre((Offre) reflectService.find(refRelation));
                    tic.setQuantite(UtilConvert.toEntier(quantite));
                    tic.setEffectuer(0);
                    this.save(tic);
                }
                if (type.compareToIgnoreCase(ReferenceType.SOUMISSION) == 0) {
                    TacheSoumissionCatalogue tic = new TacheSoumissionCatalogue();
                    tic.setCatalogue(catalogue);
                    SoumissionService soumissionService = new SoumissionService();
                    soumissionService.setHibernateDao(hibernateDao);
                    tic.setSoumission(soumissionService.find((Offre) reflectService.find(refRelation)));
                    tic.setQuantite(UtilConvert.toEntier(quantite));
                    tic.setEffectuer(0);
                    this.save(tic);
                }
                if (type.compareToIgnoreCase(ReferenceType.TAVAUX_SUPPLEMENTAIRE) == 0) {
                    TacheTSCatalogue tuc = new TacheTSCatalogue();
                    tuc.setCatalogue(catalogue);
                    TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                    travauxSupplementaireService.setHibernateDao(hibernateDao);
                    tuc.setTravauxSupplementaire(travauxSupplementaireService.find((Offre) reflectService.find(refRelation)));
                    tuc.setQuantite(UtilConvert.toEntier(quantite));
                    tuc.setEffectuer(0);
                    this.save(tuc);
                }
            }
            if (test.getSimpleName().compareToIgnoreCase(HorsCatalogue.class.getSimpleName()) == 0) {
                if (type.compareToIgnoreCase(ReferenceType.INITIAL) == 0) {
                    TacheInitialHorsCatalogue tic = new TacheInitialHorsCatalogue();
                    tic.setCatalogue(catalogue);
                    tic.setOffre((Offre) reflectService.find(refRelation));
                    tic.setQuantite(UtilConvert.toEntier(quantite));
                    if (catalogue.getIsAdmin() == false) {
                        tic.setEffectuer(tic.getQuantite());
                    } else {
                        tic.setEffectuer(0);
                    }
                    this.save(tic);
                }
                if (type.compareToIgnoreCase(ReferenceType.SOUMISSION) == 0) {
                    TacheSoumissionHorsCatalogue tic = new TacheSoumissionHorsCatalogue();
                    tic.setCatalogue(catalogue);
                    SoumissionService soumissionService = new SoumissionService();
                    soumissionService.setHibernateDao(hibernateDao);
                    tic.setSoumission(soumissionService.find((Offre) reflectService.find(refRelation)));
                    tic.setQuantite(UtilConvert.toEntier(quantite));
                    if (catalogue.getIsAdmin() == false) {
                        tic.setEffectuer(tic.getQuantite());
                    } else {
                        tic.setEffectuer(0);
                    }
                    this.save(tic);
                }
                if (type.compareToIgnoreCase(ReferenceType.TAVAUX_SUPPLEMENTAIRE) == 0) {
                    TacheTSHorsCatalogue tuc = new TacheTSHorsCatalogue();
                    tuc.setCatalogue(catalogue);
                    TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                    travauxSupplementaireService.setHibernateDao(hibernateDao);
                    tuc.setTravauxSupplementaire(travauxSupplementaireService.find((Offre) reflectService.find(refRelation)));
                    tuc.setQuantite(UtilConvert.toEntier(quantite));
                    if (catalogue.getIsAdmin() == false) {
                        tuc.setEffectuer(tuc.getQuantite());
                    } else {
                        tuc.setEffectuer(0);
                    }
                    this.save(tuc);
                }
            }
        } else {
            if (designation == null || designation.compareTo("") == 0) {
                throw new Exception("Veuillez remplir le champs de designation");
            }
            if (prixUnitaire == null || prixUnitaire.compareTo("") == 0) {
                throw new Exception("Veuiller remplir le champs du prix unitaire");
            }
            if (Unite == null || Unite.compareTo("") == 0) {
                throw new Exception("Veuillez remplir le champs de l'unite");
            }
            HorsCatalogue horsCatalogue = new HorsCatalogue();
            horsCatalogue.setDesignation(designation);
            horsCatalogue.setPrixUnitaire(UtilConvert.toDoubleEntier(prixUnitaire));
            horsCatalogue.setUnite(Unite);
            if (admin == null || admin.compareTo("") == 0) {
                horsCatalogue.setIsAdmin(false);
            } else {
                horsCatalogue.setIsAdmin(true);
            }
            this.hibernateDao.save(horsCatalogue);
            if (horsCatalogue.getId() > 0) {
                this.save(horsCatalogue.getAllReference(), designation, prixUnitaire, Unite, quantite, type, refRelation, admin);
            } else {
                throw new Exception("Impossible de sauvegarder les donnees du catalogue");
            }
        }
    }

    public void save(TacheModel travaux) throws Exception {

        try {
            this.hibernateDao.save(travaux);
        } catch (Exception e) {
            throw new Exception("Impossible de sauvegarder le travaux, cause : " + e.getMessage());
        }
    }

    public void populatCatalogue(TacheModel tache) throws Exception {
        Session session = hibernateDao.getSessionFactory().openSession();

        String sql = "select c from " + tache.getClass().getSimpleName() + " t join t.catalogue c where t.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", tache.getId());
        List<CatalogueModel> catalogues = query.list();
        if (!catalogues.isEmpty()) {
            tache.setCatalogue(catalogues.get(0));
        }
        session.close();
    }

    public TacheModel find(String reference) throws Exception {
        TacheModel tache = (TacheModel) ReferenceUtil.toBaseModel(reference);
        this.find(tache);
        return tache;
    }

    public void delete(String reference) throws Exception {
//        TacheModel tache = (TacheModel)ReferenceUtil.toBaseModel(reference);
        TacheModel tache = this.find(reference);

        this.delete(tache);
    }

    public void delete(TacheModel tache) throws Exception {
        this.hibernateDao.delete(tache);
    }

    private void find(TacheModel tache) throws Exception {
        this.hibernateDao.findById(tache);
    }

    public void findInitial(Offre offre) throws Exception {
        List<TacheModel> initialCatalogue;
        List<TacheModel> initialHorsCatalogue;

        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select t from TacheInitialCatalogue t join t.offre o where o.id = :id ORDER BY t.id";
        String sql1 = "select t from TacheInitialHorsCatalogue t join t.offre o where o.id = :id ORDER BY t.id";

        Query query = session.createQuery(sql);
        query.setParameter("id", offre.getId());
        initialCatalogue = query.list();

        query = session.createQuery(sql1);
        query.setParameter("id", offre.getId());
        initialHorsCatalogue = query.list();

        session.close();

        Travaux reponse = new Travaux();
        reponse.setTacheCatalogue(initialCatalogue);
        reponse.setTacheHorsCatalogue(initialHorsCatalogue);
        offre.setTacheinitials(reponse);
        for (int i = 0; i < initialCatalogue.size(); i++) {
            this.populatCatalogue(initialCatalogue.get(i));
        }
        for (int i = 0; i < initialHorsCatalogue.size(); i++) {
            this.populatCatalogue(initialHorsCatalogue.get(i));
        }
    }

    public void findInitial(Offre offre, Session session) throws Exception {
        List<TacheModel> initialCatalogue;
        List<TacheModel> initialHorsCatalogue;
        try {
            String sql = "select t from TacheInitialCatalogue t join t.offre o where o.id = :id ORDER BY t.id";
            String sql1 = "select t from TacheInitialHorsCatalogue t join t.offre o where o.id = :id ORDER BY t.id";

            Query query = session.createQuery(sql);
            query.setParameter("id", offre.getId());
            initialCatalogue = query.list();

            query = session.createQuery(sql1);
            query.setParameter("id", offre.getId());
            initialHorsCatalogue = query.list();

            Travaux reponse = new Travaux();
            reponse.setTacheCatalogue(initialCatalogue);
            reponse.setTacheHorsCatalogue(initialHorsCatalogue);
            offre.setTacheinitials(reponse);
            for (int i = 0; i < initialCatalogue.size(); i++) {
                this.populatCatalogue(initialCatalogue.get(i));
            }
            for (int i = 0; i < initialHorsCatalogue.size(); i++) {
                this.populatCatalogue(initialHorsCatalogue.get(i));
            }
        }catch(Exception e){
            throw new Exception("Impossible d'extraire les taches initials "+e.getMessage());
        }
    }

    public void findSoumission(Offre offre) throws Exception {
        List<TacheModel> initialSoumissionCatalogue;
        List<TacheModel> initialSoumissionHorsCatalogue;

        Session session = hibernateDao.getSessionFactory().openSession();

        Criteria c = session.createCriteria(TacheSoumissionCatalogue.class, "tsc");
        c.createAlias("tsc.soumission", "soumission"); // inner join by default
        c.createAlias("soumission.offre", "offre");
        c.add(Restrictions.eq("offre.id", offre.getId()));
        c.addOrder(Order.asc("tsc.id"));

        Criteria horsCatalogue = session.createCriteria(TacheSoumissionHorsCatalogue.class, "tsc");
        horsCatalogue.createAlias("tsc.soumission", "soumission"); // inner join by default
        horsCatalogue.createAlias("soumission.offre", "offre");
        horsCatalogue.add(Restrictions.eq("offre.id", offre.getId()));
        horsCatalogue.addOrder(Order.asc("tsc.id"));

        initialSoumissionCatalogue = c.list();
        initialSoumissionHorsCatalogue = horsCatalogue.list();

        Travaux reponse = new Travaux();
        reponse.setTacheCatalogue(initialSoumissionCatalogue);
        reponse.setTacheHorsCatalogue(initialSoumissionHorsCatalogue);
        offre.setTacheSoumission(reponse);
        for (int i = 0; i < initialSoumissionCatalogue.size(); i++) {
            this.populatCatalogue(initialSoumissionCatalogue.get(i));
        }
        for (int i = 0; i < initialSoumissionHorsCatalogue.size(); i++) {
            this.populatCatalogue(initialSoumissionHorsCatalogue.get(i));
        }
        SoumissionService soumissionService = new SoumissionService();
        soumissionService.setHibernateDao(hibernateDao);
        soumissionService.find(offre);
    }
    
    public void findSoumission(Offre offre,Session session) throws Exception {
        List<TacheModel> initialSoumissionCatalogue;
        List<TacheModel> initialSoumissionHorsCatalogue;
        try{

            Criteria c = session.createCriteria(TacheSoumissionCatalogue.class, "tsc");
            c.createAlias("tsc.soumission", "soumission"); // inner join by default
            c.createAlias("soumission.offre", "offre");
            c.add(Restrictions.eq("offre.id", offre.getId()));
            c.addOrder(Order.asc("tsc.id"));
            c.addOrder(Order.asc("tsc.id"));

            Criteria horsCatalogue = session.createCriteria(TacheSoumissionHorsCatalogue.class, "tsc");
            horsCatalogue.createAlias("tsc.soumission", "soumission"); // inner join by default
            horsCatalogue.createAlias("soumission.offre", "offre");
            horsCatalogue.add(Restrictions.eq("offre.id", offre.getId()));
            horsCatalogue.addOrder(Order.asc("tsc.id"));
            horsCatalogue.addOrder(Order.asc("tsc.id"));

            initialSoumissionCatalogue = c.list();
            initialSoumissionHorsCatalogue = horsCatalogue.list();

            Travaux reponse = new Travaux();
            reponse.setTacheCatalogue(initialSoumissionCatalogue);
            reponse.setTacheHorsCatalogue(initialSoumissionHorsCatalogue);
            offre.setTacheSoumission(reponse);
            for (int i = 0; i < initialSoumissionCatalogue.size(); i++) {
                this.populatCatalogue(initialSoumissionCatalogue.get(i));
            }
            for (int i = 0; i < initialSoumissionHorsCatalogue.size(); i++) {
                this.populatCatalogue(initialSoumissionHorsCatalogue.get(i));
            }
            SoumissionService soumissionService = new SoumissionService();
            soumissionService.setHibernateDao(hibernateDao);
            soumissionService.find(offre);
        }catch(Exception e){
            throw  new Exception("impossible d'extraire les soumission "+e.getMessage());
        }
    }

    public void findTravauxSupplementaire(Offre offre) throws Exception {
        List<TacheModel> initialSoumissionCatalogue;
        List<TacheModel> initialSoumissionHorsCatalogue;

        Session session = hibernateDao.getSessionFactory().openSession();

        Criteria c = session.createCriteria(TacheTSCatalogue.class, "tsc");
        c.createAlias("tsc.travauxSupplementaire", "soumission"); // inner join by default
        c.createAlias("soumission.offre", "offre");
        c.add(Restrictions.eq("offre.id", offre.getId()));
        c.addOrder(Order.asc("tsc.id"));

        Criteria horsCatalogue = session.createCriteria(TacheTSHorsCatalogue.class, "tsc");
        horsCatalogue.createAlias("tsc.travauxSupplementaire", "soumission"); // inner join by default
        horsCatalogue.createAlias("soumission.offre", "offre");
        horsCatalogue.add(Restrictions.eq("offre.id", offre.getId()));
        horsCatalogue.addOrder(Order.asc("tsc.id"));

        initialSoumissionCatalogue = c.list();
        initialSoumissionHorsCatalogue = horsCatalogue.list();

        session.close();

        Travaux reponse = new Travaux();
        reponse.setTacheCatalogue(initialSoumissionCatalogue);
        reponse.setTacheHorsCatalogue(initialSoumissionHorsCatalogue);
        offre.setTacheSupplementaire(reponse);
        for (int i = 0; i < initialSoumissionCatalogue.size(); i++) {
            this.populatCatalogue(initialSoumissionCatalogue.get(i));
        }
        for (int i = 0; i < initialSoumissionHorsCatalogue.size(); i++) {
            this.populatCatalogue(initialSoumissionHorsCatalogue.get(i));
        }
        TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
        travauxSupplementaireService.setHibernateDao(hibernateDao);
        travauxSupplementaireService.find(offre);
    }
    
    public void findTravauxSupplementaire(Offre offre,Session session) throws Exception {
        List<TacheModel> initialSoumissionCatalogue;
        List<TacheModel> initialSoumissionHorsCatalogue;       
        try{

            Criteria c = session.createCriteria(TacheTSCatalogue.class, "tsc");
            c.createAlias("tsc.travauxSupplementaire", "soumission"); // inner join by default
            c.createAlias("soumission.offre", "offre");
            c.add(Restrictions.eq("offre.id", offre.getId()));
            c.addOrder(Order.asc("tsc.id"));

            Criteria horsCatalogue = session.createCriteria(TacheTSHorsCatalogue.class, "tsc");
            horsCatalogue.createAlias("tsc.travauxSupplementaire", "soumission"); // inner join by default
            horsCatalogue.createAlias("soumission.offre", "offre");
            horsCatalogue.add(Restrictions.eq("offre.id", offre.getId()));
            horsCatalogue.addOrder(Order.asc("tsc.id"));

            initialSoumissionCatalogue = c.list();
            initialSoumissionHorsCatalogue = horsCatalogue.list();

            session.close();

            Travaux reponse = new Travaux();
            reponse.setTacheCatalogue(initialSoumissionCatalogue);
            reponse.setTacheHorsCatalogue(initialSoumissionHorsCatalogue);
            offre.setTacheSupplementaire(reponse);
            for (int i = 0; i < initialSoumissionCatalogue.size(); i++) {
                this.populatCatalogue(initialSoumissionCatalogue.get(i));
            }
            for (int i = 0; i < initialSoumissionHorsCatalogue.size(); i++) {
                this.populatCatalogue(initialSoumissionHorsCatalogue.get(i));
            }
            TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
            travauxSupplementaireService.setHibernateDao(hibernateDao);
            travauxSupplementaireService.find(offre);
        }catch(Exception e){
            throw new Exception("impossible d'extraire les travaux supplementaire "+e.getMessage());
        }
    }

    public void save(List<TacheModel> travaux) throws Exception {
        String erreur = "";
        for (int i = 0; i < travaux.size(); i++) {
            try {
                this.save(travaux.get(i));
            } catch (Exception e) {
                erreur += i + " cause : " + e.getMessage() + "\n";
            }
        }
        if (erreur.compareTo("") != 0) {
            erreur = "Erreur(s) rencontre : 0 " + erreur;
            throw new Exception(erreur);
        }

    }

    public void plusDone(String reference) throws Exception {
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        if (reference == null || reference.compareTo("") == 0) {
            throw new Exception("La reference est vide");
        }
        TacheModel tache = null;
        try {
            tache = (TacheModel) reflectService.find(reference);

        } catch (Exception e) {
            throw new Exception("La tache que vous recherchiez est introuvable");
        }
        int quantite = tache.getQuantite();
        int effectuer = tache.getEffectuer();
        try {
            if (effectuer < quantite) {
                effectuer++;
                tache.setEffectuer(effectuer);
                this.hibernateDao.update(tache);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Impossible de mettre a jour la tache");
        }
    }

    public void minusDone(String reference) throws Exception {
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        if (reference == null || reference.compareTo("") == 0) {
            throw new Exception("La reference est vide");
        }
        TacheModel tache = null;
        try {
            tache = (TacheModel) reflectService.find(reference);

        } catch (Exception e) {
            throw new Exception("La tache que vous recherchiez est introuvable");
        }
        int quantite = tache.getQuantite();
        int effectuer = tache.getEffectuer();
        try {
            if (effectuer > 0) {
                effectuer--;
                tache.setEffectuer(effectuer);
                this.hibernateDao.update(tache);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Impossible de mettre a jour la tache");
        }
    }
    
    public void manualDone(String reference,int effectuer)throws Exception{
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        if (reference == null || reference.compareTo("") == 0) {
            throw new Exception("La reference est vide");
        }
        TacheModel tache = null;
        try {
            tache = (TacheModel) reflectService.find(reference);

        } catch (Exception e) {
            throw new Exception("La tache que vous recherchiez est introuvable");
        }
        int quantite = tache.getQuantite();  
        if(effectuer<0)effectuer=0;
        if(effectuer>quantite)effectuer=quantite;
        try {            
            tache.setEffectuer(effectuer);
            this.hibernateDao.update(tache);           
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Impossible de mettre a jour la tache");
        }
    }
    
    public void allDone(String reference) throws Exception {
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        if (reference == null || reference.compareTo("") == 0) {
            throw new Exception("La reference est vide");
        }
        TacheModel tache = null;
        try {
            tache = (TacheModel) reflectService.find(reference);

        } catch (Exception e) {
            throw new Exception("La tache que vous recherchiez est introuvable");
        }
        int quantite = tache.getQuantite();

        try {

            tache.setEffectuer(quantite);
            this.hibernateDao.update(tache);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Impossible de mettre a jour la tache");
        }
    }

    public void noneDone(String reference) throws Exception {
        ReflectService reflectService = new ReflectService(this.hibernateDao);
        if (reference == null || reference.compareTo("") == 0) {
            throw new Exception("La reference est vide");
        }
        TacheModel tache = null;
        try {
            tache = (TacheModel) reflectService.find(reference);
        } catch (Exception e) {
            throw new Exception("La tache que vous recherchiez est introuvable");
        }
        try {
            tache.setEffectuer(0);
            this.hibernateDao.update(tache);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Impossible de mettre a jour la tache");
        }
    }

    public boolean existPhoto(TacheModel tacheModel) throws Exception {
        PhotoService photoService = new PhotoService();
        photoService.setHibernateDao(hibernateDao);
        if (tacheModel.getId() == 0) {
            throw new Exception("la tache que vous essaye d'utiliser n'existe pas");
        }
        if (tacheModel.getCatalogue() == null) {
            this.populatCatalogue(tacheModel);
        }
        if (tacheModel.getCatalogue().getClass() == HorsCatalogue.class) {
            HorsCatalogue horsCatalogue = (HorsCatalogue) tacheModel.getCatalogue();
            if (horsCatalogue.getIsAdmin()) {
                return true;
            }
        }
        return !photoService.find(tacheModel.getAllReference()).isEmpty();
    }

    public boolean existPhoto(TacheModel tacheModel, Session session) throws Exception {

        if (tacheModel.getId() == 0) {
            throw new Exception("la tache que vous essaye d'utiliser n'existe pas");
        }
        if (tacheModel.getCatalogue() == null) {
            this.populatCatalogue(tacheModel);
        }
        if (tacheModel.getCatalogue().getClass() == HorsCatalogue.class) {
            HorsCatalogue horsCatalogue = (HorsCatalogue) tacheModel.getCatalogue();
            if (horsCatalogue.getIsAdmin()) {
                return true;
            }
        }
        return !PhotoService.find(tacheModel.getAllReference(), session).isEmpty();
    }

    public boolean existPhoto(Offre offre) throws Exception {
        Session session = null;
        try {
            session = this.hibernateDao.getSessionFactory().openSession();
            if (offre.getTacheinitials() == null)this.findInitial(offre,session);
            if (offre.getTacheSoumission() == null)this.findSoumission(offre,session);
            if (offre.getTacheSupplementaire() == null)this.findTravauxSupplementaire(offre,session);
            for (int i = 0; i < offre.getTacheinitials().getTravaux().size(); i++) {
                TacheModel tache = offre.getTacheinitials().getTravaux().get(i);
                if (!this.existPhoto(tache, session))return false;     
            }
            for (int i = 0; i < offre.getTacheSoumission().getTravaux().size(); i++) {
                TacheModel tache = offre.getTacheSoumission().getTravaux().get(i);
                if (!this.existPhoto(tache, session))return false;                
            }
            for (int i = 0; i < offre.getTacheSoumission().getTravaux().size(); i++) {
                TacheModel tache = offre.getTacheSoumission().getTravaux().get(i);
                if (!this.existPhoto(tache, session))return false;      
            }
            return true;
        } catch (Exception e) {                 
            throw e;
        } finally {
            if (session != null)session.close();
        }
    }
}
