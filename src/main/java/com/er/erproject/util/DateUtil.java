/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author diary
 */
public class DateUtil {
    public static String convert(Date date)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String reponse; 
        try{
            reponse = sdf.format(date);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("La date inserer n'est pas valide");
        }
        return reponse; 
    }
    public static String toLettre(Date date)throws Exception{
       
        SimpleDateFormat dateFormat;

        dateFormat = new SimpleDateFormat("EEEE dd MMMMM yyyy");
        return dateFormat.format(date);
    }
    public static String toLettreWithoutDay(Date date){
       
        SimpleDateFormat dateFormat;

        dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        return dateFormat.format(date);
    }
    public static String toAllLettre(Date date)throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM");
        String mois = dateFormat.format(date);
        String annee = ConvertionLettre.getLettre(date.getYear()+1900);
        String jour = ConvertionLettre.getLettre(date.getDate());
        
        String reponse = "L'an "+annee+", le "+jour+""+mois; 
        return reponse;
    }
   
    public static Date convert(String date)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date reponse ;
        try{
            reponse = sdf.parse(date);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Impossible d'extraire la date");
        }
        return reponse; 
    }
}
