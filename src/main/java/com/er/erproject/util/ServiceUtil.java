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
public class ServiceUtil {
    public static float  resizeHeight(float x,float y)throws Exception{
        float reponseY; 
        reponseY =(y*(22000/x)/100);
        
        return reponseY;          
    }
}
