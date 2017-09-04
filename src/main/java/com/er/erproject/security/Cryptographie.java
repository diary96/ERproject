/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author diary
 */
public class Cryptographie {
    public static String crypterHashage(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        //convert the byte to hex format method 2
//        StringBuffer hexString = new StringBuffer();
//    	for (int i=0;i<byteData.length;i++) {
//    		String hex=Integer.toHexString(0xff & byteData[i]);
//   	     	if(hex.length()==1) hexString.append('0');
//   	     	hexString.append(hex);
//    	}
        return sb.toString();
    }
    public static void main(String[] arg){
        try{
            System.out.print(Cryptographie.crypterHashage("Diary"));
        }catch(Exception e){
        
        }
    }
    
}
