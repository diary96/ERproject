/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.util;

/**
 *
 * @author diary
 */
public class NumberUtil {
    public static double pourcentage(double value,double pourcentage){
        return pourcentage*value/100;
    }
    public static int toInt(String value)throws Exception{
        int reponse;
        try{
            reponse = Integer.valueOf(value);
            return reponse;
        } catch(Exception e){
            throw new Exception("ce n'est pas un chiffre");
        }
    }
        
    
}
