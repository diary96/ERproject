/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.data;

import com.er.erproject.model.Catalogue;
import com.er.erproject.model.HorsCatalogue;
import com.er.erproject.model.TacheInitialCatalogue;
import com.er.erproject.model.TacheInitialHorsCatalogue;
import com.er.erproject.model.TacheSoumissionCatalogue;
import com.er.erproject.model.TacheSoumissionHorsCatalogue;
import com.er.erproject.model.TacheTSCatalogue;
import com.er.erproject.model.TacheTSHorsCatalogue;

/**
 *
 * @author diary
 */
public class ReflectData {
    public static Class findClass(String referenceCatalogue,String type) throws Exception{
        Class catalogueClass = Reference.getClass(referenceCatalogue);
        if(catalogueClass.getSimpleName().compareToIgnoreCase(Catalogue.class.getSimpleName())==0&&type.compareToIgnoreCase(ReferenceType.INITIAL)==0){
            return TacheInitialCatalogue.class;
        }
        if(catalogueClass.getSimpleName().compareToIgnoreCase(HorsCatalogue.class.getSimpleName())==0&&type.compareToIgnoreCase(ReferenceType.INITIAL)==0){
            return TacheInitialHorsCatalogue.class;
        }
        if(catalogueClass.getSimpleName().compareToIgnoreCase(Catalogue.class.getSimpleName())==0&&type.compareToIgnoreCase(ReferenceType.SOUMISSION)==0){
            return TacheSoumissionCatalogue.class;
        }
        if(catalogueClass.getSimpleName().compareToIgnoreCase(HorsCatalogue.class.getSimpleName())==0&&type.compareToIgnoreCase(ReferenceType.SOUMISSION)==0){
            return TacheSoumissionHorsCatalogue.class;
        }
        if(catalogueClass.getSimpleName().compareToIgnoreCase(Catalogue.class.getSimpleName())==0&&type.compareToIgnoreCase(ReferenceType.TAVAUX_SUPPLEMENTAIRE)==0){
            return TacheTSCatalogue.class;
        }
        if(catalogueClass.getSimpleName().compareToIgnoreCase(HorsCatalogue.class.getSimpleName())==0&&type.compareToIgnoreCase(ReferenceType.TAVAUX_SUPPLEMENTAIRE)==0){
            return TacheTSHorsCatalogue.class;
        }
        throw new Exception("La classe rechercher est introuvable dans la base");
    }
}
