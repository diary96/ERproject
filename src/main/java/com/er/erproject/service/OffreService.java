/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.data.StatuReference;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Soumission;
import com.er.erproject.model.Statistique;
import com.er.erproject.model.TacheModel;
import com.er.erproject.model.Travaux;
import com.er.erproject.model.TravauxSupplementaire;
import com.er.erproject.model.TypeOffre;
import com.er.erproject.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class OffreService extends ServiceModel {

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

        Statistique statistique = new Statistique(offre.getTacheSupplementaire().getTravaux(), offre.getSoumission().getTva(), offre.getSoumission().getRemise());
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
}
