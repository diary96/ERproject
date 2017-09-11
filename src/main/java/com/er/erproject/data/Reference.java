/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.data;

import com.er.erproject.model.Archive;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Catalogue;
import com.er.erproject.model.HorsCatalogue;
import com.er.erproject.model.Materiaux;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Photo;
import com.er.erproject.model.TacheInitialCatalogue;
import com.er.erproject.model.TacheInitialHorsCatalogue;
import com.er.erproject.model.TacheSoumissionCatalogue;
import com.er.erproject.model.TacheSoumissionHorsCatalogue;
import com.er.erproject.model.TacheTSCatalogue;
import com.er.erproject.model.TacheTSHorsCatalogue;
import com.er.erproject.model.TypeFichier;
import com.er.erproject.model.Ventillation;
import com.er.erproject.model.VentillationTS;
import java.lang.reflect.Type;

/**
 *
 * @author diary
 */
public class Reference {
    public static final String CATALOGUE = "CAT";
    public static final String HORSCATALOGUE = "HOC";
    public static final String TRAVAUX_INITIAL_CATALOGUE ="TIC";
    public static final String TRAVAUX_INITIAL_HORSCATALOGUE = "TIH";
    public static final String TRAVAUX_SOUMISSION_CATALOGUE = "TSC";
    public static final String TRAVAUX_SOUSMISSION_HORSCATALOGUE = "TSH";
    public static final String TRAVAUX_SUPLLEMENTAIRE_CATALOGUE="TUC";
    public static final String TRAVAUX_SUPLLEMENTAIRE_HORSCATALOGUE="TUS";
    public static final String OFFRE = "OFF";
    public static final String PHOTO = "PHO";
    public static final String VISIBIBLE = "block";
    public static final String INVISIBIBLE = "none";
    public static final String MATERIAUX = "MAT";
    public static final String BC = "BDC";
    public static final String VENTILATION = "VTL";
    public static final String VENTILATION_TS = "VTS";
    public static final String TYPE_FICHIER = "TYF";
    public static final String ARCHIVE = "ARC";
    
    public static Class getClass(String reference)throws Exception{
        if(reference.compareToIgnoreCase(Reference.CATALOGUE)==0)return Catalogue.class;
        if(reference.compareToIgnoreCase(Reference.HORSCATALOGUE)==0)return HorsCatalogue.class;
        if(reference.compareToIgnoreCase(Reference.TRAVAUX_INITIAL_CATALOGUE)==0)return TacheInitialCatalogue.class;
        if(reference.compareToIgnoreCase(Reference.TRAVAUX_INITIAL_HORSCATALOGUE)==0)return TacheInitialHorsCatalogue.class;
        if(reference.compareToIgnoreCase(Reference.TRAVAUX_SOUMISSION_CATALOGUE)==0)return TacheSoumissionCatalogue.class;
        if(reference.compareToIgnoreCase(Reference.TRAVAUX_SOUSMISSION_HORSCATALOGUE)==0)return TacheSoumissionHorsCatalogue.class;
        if(reference.compareToIgnoreCase(Reference.TRAVAUX_SUPLLEMENTAIRE_CATALOGUE)==0)return TacheTSCatalogue.class;
        if(reference.compareToIgnoreCase(Reference.TRAVAUX_SUPLLEMENTAIRE_HORSCATALOGUE)==0)return TacheTSHorsCatalogue.class;
        if(reference.compareToIgnoreCase(Reference.OFFRE)==0)return Offre.class;
        if(reference.compareToIgnoreCase(Reference.MATERIAUX)==0)return Materiaux.class;
        if(reference.compareToIgnoreCase(Reference.PHOTO)==0)return Photo.class;
        if(reference.compareToIgnoreCase(Reference.BC)==0)return BonCommande.class;
        if(reference.compareToIgnoreCase(Reference.VENTILATION)==0)return Ventillation.class;
        if(reference.compareToIgnoreCase(Reference.VENTILATION_TS)==0)return VentillationTS.class;
        if(reference.compareToIgnoreCase(Reference.TYPE_FICHIER)==0)return TypeFichier.class;
        if(reference.compareToIgnoreCase(Reference.ARCHIVE)==0)return Archive.class;
        
        else throw new Exception("Desole, la reference n'est pas dans la base");
        
    }
}
