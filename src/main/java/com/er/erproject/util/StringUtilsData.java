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
public class StringUtilsData {
    public static boolean isNoAlpha(String data){
        boolean test =  data.matches("[a-zA-Z]+");
        return test;

    }
}
