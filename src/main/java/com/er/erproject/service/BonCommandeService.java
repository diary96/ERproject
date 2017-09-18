/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.service;

import com.er.erproject.data.ReferenceType;
import com.er.erproject.data.VentilationData;
import com.er.erproject.model.Archive;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Soumission;
import com.er.erproject.model.TravauxSupplementaire;
import com.er.erproject.model.TypeFichier;
import com.er.erproject.util.FileUtil;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author diary
 */
public class BonCommandeService extends ServiceModel {

    private void save(BonCommande bonCommande) throws Exception {
        try {
            this.hibernateDao.save(bonCommande);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void save(BonCommande bonCommande, Offre offre, short type) throws Exception {
        BonCommande tempBC = null;
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                SoumissionService soumissionService = new SoumissionService();
                soumissionService.setHibernateDao(hibernateDao);
                soumissionService.find(offre);
            }
            Soumission temp = offre.getSoumission();
            tempBC = this.find(temp);
            if (tempBC != null) {
                FileUtil.deleteFile(tempBC.getPath());
                this.hibernateDao.delete(tempBC);
            }
            this.save(bonCommande);
            temp.setBonCommande(bonCommande);
            this.hibernateDao.update(temp);
        } else {
            if (offre.getTravauxSupplementaire() == null) {
                TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
                travauxSupplementaireService.setHibernateDao(hibernateDao);
                travauxSupplementaireService.find(offre);
            }
            if (offre.getTacheSupplementaire() == null) {
                OffreService offreService = new OffreService();
                offreService.setHibernateDao(hibernateDao);
                offreService.populateTravauxSupplementaire(offre);

            }
            if (offre.getTacheSupplementaire().getTravaux().isEmpty()) {
                throw new Exception("l'offre ne possède pas de travaux supplémentaire et aucun travaux supplementaire peut etre enregistré");
            }
            TravauxSupplementaire temp = offre.getTravauxSupplementaire();
            tempBC = this.find(temp);
            if (tempBC != null) {
                FileUtil.deleteFile(tempBC.getPath());
                this.hibernateDao.delete(tempBC);
            }
            this.save(bonCommande);
            temp.setBonCommande(bonCommande);
            this.hibernateDao.update(temp);
        }
        TypeFichier typeFichier = null;
        TypeFichierService typeFichierService = new TypeFichierService();
        typeFichierService.setHibernateDao(hibernateDao);
        typeFichier = typeFichierService.find("BC");
        if (typeFichier == null) {
            typeFichier = new TypeFichier();
            typeFichier.setNomType("BC");
            typeFichier.setDateajout(Calendar.getInstance().getTime());
            typeFichierService.save(typeFichier);
        }
        Archive archive = new Archive();
        archive.setNom(bonCommande.getNumeroBC() + "-" + bonCommande.getAllReference());
        archive.setOffre(offre);
        archive.setTypeFichier(typeFichier);
        archive.setPath(bonCommande.getPath());
        archive.setDateajout(Calendar.getInstance().getTime());
        ArchiveService archiveService = new ArchiveService();
        archiveService.setHibernateDao(hibernateDao);
        archiveService.save(archive);
    }

    public void update(BonCommande bonCommande, Offre offre) throws Exception {
        try {
            BonCommande temp = new BonCommande();
            temp.setId(bonCommande.getId());
            this.hibernateDao.findById(temp);
            if (temp.getPath().compareTo(bonCommande.getPath()) != 0) {
                TypeFichier typeFichier = null;
                TypeFichierService typeFichierService = new TypeFichierService();
                typeFichierService.setHibernateDao(hibernateDao);
                typeFichier = typeFichierService.find("BC");
                if (typeFichier == null) {
                    typeFichier = new TypeFichier();
                    typeFichier.setNomType("BC");
                    typeFichier.setDateajout(Calendar.getInstance().getTime());
                    typeFichierService.save(typeFichier);
                }
                Archive archive = new Archive();
                archive.setNom(bonCommande.getNumeroBC() + "-" + bonCommande.getAllReference());
                archive.setOffre(offre);
                archive.setTypeFichier(typeFichier);
                archive.setPath(bonCommande.getPath());
                archive.setDateajout(Calendar.getInstance().getTime());
                ArchiveService archiveService = new ArchiveService();
                archiveService.setHibernateDao(hibernateDao);
                archiveService.save(archive);
            }
            this.hibernateDao.update(bonCommande);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("impossible de mettre le bon de commande " + bonCommande.getNumeroBC() + " a jour");
        }
    }

    public BonCommande find(Offre offre, short type) throws Exception {
        if (type == VentilationData.SOUMISSION) {
            if (offre.getSoumission() == null) {
                OffreService offreService = new OffreService();
                offreService.setHibernateDao(hibernateDao);
                offreService.populateSoumission(offre);
                if (offre.getSoumission() == null) {
                    throw new Exception("la soumission n'est pas encore initialisé");
                }
            }
            return this.find(offre.getSoumission());
        }
        if (type == VentilationData.TS) {
            OffreService offreService = new OffreService();
            offreService.setHibernateDao(hibernateDao);
            if (offre.getTravauxSupplementaire() == null) {
                offreService.populateTS(offre);
                if (offre.getTravauxSupplementaire() == null) {
                    throw new Exception("le travaux supplementaire n'est pas initialiser");
                }
            }
            if (offre.getTacheSupplementaire() == null) {
                offreService.populateTravauxSupplementaire(offre);
            }
            if (offre.getTacheSupplementaire().getTravaux().isEmpty()) {
                throw new Exception("aucun tache n'est presente dans les travaux supplementaire et aucun bon de commande ne peut etre gestionné");
            }
            return this.find(offre.getTravauxSupplementaire());
        }
        throw new Exception("vueillez ne pas modifier les types de l'url manuellement");
    }

    public BonCommande find(long id) throws Exception {
        BonCommande reponse = new BonCommande();
        reponse.setId(id);
        try {
            this.hibernateDao.findById(reponse);
        } catch (Exception e) {
            throw new Exception("l'identifiant inserer n'est pas dans la base");
        }
        return reponse;
    }

    public BonCommande find(Soumission soumission) throws Exception {
        BonCommande reponse = null;
        Session session = null;
        try {
            session = hibernateDao.getSessionFactory().openSession();
            String sql = "select bc from Soumission s join s.bonCommande bc where s.id = :id";

            Query query = session.createQuery(sql);
            query.setParameter("id", soumission.getId());
            List<BonCommande> catalogues = query.list();
            if (!catalogues.isEmpty()) {
                soumission.setBonCommande(catalogues.get(0));
                return soumission.getBonCommande();
            }
            return reponse;
        } catch (Exception e) {
            throw new Exception("Impossible d'extraire le bon de commande : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public BonCommande find(Soumission soumission, Session session) {
        BonCommande reponse = null;
        String sql = "select bc from Soumission s join s.bonCommande bc where s.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", soumission.getId());
        List<BonCommande> catalogues = query.list();

        if (!catalogues.isEmpty()) {
            soumission.setBonCommande(catalogues.get(0));
            return soumission.getBonCommande();
        }
        return reponse;
    }

    public BonCommande find(TravauxSupplementaire travauxSupplementaire) throws Exception {
        BonCommande reponse = null;
        Session session = hibernateDao.getSessionFactory().openSession();
        String sql = "select bc from TravauxSupplementaire s join s.bonCommande bc where s.id = :id";

        Query query = session.createQuery(sql);
        query.setParameter("id", travauxSupplementaire.getId());
        List<BonCommande> catalogues = query.list();
        session.close();
        if (!catalogues.isEmpty()) {
            travauxSupplementaire.setBonCommande(catalogues.get(0));
            return travauxSupplementaire.getBonCommande();
        }
        return reponse;
    }

    public void populateBC(Offre offre) throws Exception {
        if (offre.getSoumission() == null) {
            SoumissionService soumissionService = new SoumissionService();
            soumissionService.setHibernateDao(hibernateDao);
            soumissionService.find(offre);
        }
        if (offre.getTravauxSupplementaire() == null) {
            TravauxSupplementaireService travauxSupplementaireService = new TravauxSupplementaireService();
            travauxSupplementaireService.setHibernateDao(hibernateDao);
            travauxSupplementaireService.find(offre);
        }
        this.find(offre.getSoumission());
        this.find(offre.getTravauxSupplementaire());

    }

}
