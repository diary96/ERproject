/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.data;

/**
 *
 * @author diary
 */
public class StatuReference {    
    public static final int OFFRE = 0;
    public static final int SOUMISSION = 1;
    public static final int TRAVAUX = 2;
    public static final int PV = 3;
    public static final int FACTURATION = 4;
    
    public static final int RECU = -1;
    public static final int NON_RECU = -2;
    
    public static final String SOFFRE = "offre";
    public static final String SSOUMISSION = "soumission";
    public static final String STRAVAUX = "travaux";
    public static final String SPV = "pv";
    public static final String SFACTURATION = "facturation";
    
//    public static final int CLOSE = 5;
    public static String getString(int statu){
        if(statu==0)return "Appel d'Offre";  
        if(statu==1)return "Soumission";
        if(statu==2)return "Travaux";
        if(statu==3)return "P.V";
        if(statu==4)return "Facturation";
        if(statu==StatuReference.RECU)return "recu";
        if(statu==StatuReference.NON_RECU)return "non recu";
//        if(statu==5)return "cloture";
        else return "L'etape n'est pas dans la base";       
    }
    public static int getStatu(String statut)throws Exception{
        if(StatuReference.SOFFRE.compareToIgnoreCase(statut)==0) return StatuReference.OFFRE;
        if(StatuReference.SSOUMISSION.compareToIgnoreCase(statut)==0) return StatuReference.SOUMISSION;
        if(StatuReference.STRAVAUX.compareToIgnoreCase(statut)==0) return StatuReference.TRAVAUX;
        if(StatuReference.SPV.compareToIgnoreCase(statut)==0) return StatuReference.PV;
        if(StatuReference.SFACTURATION.compareToIgnoreCase(statut)==0) return StatuReference.FACTURATION;
        throw new Exception("Le statu rechercher est introuvable");
    }
    
}
