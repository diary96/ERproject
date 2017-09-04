/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.util;

import com.er.erproject.data.Reference;
import com.er.erproject.model.BaseModel;
import com.er.erproject.model.TacheModel;

/**
 *
 * @author diary
 */
public class ReferenceUtil {
    public static BaseModel toBaseModel(String reference)throws Exception{
        String ref = reference.substring(0, 3);
        String id = reference.substring(3);
        BaseModel tache;
        try {
            tache =  (BaseModel)Reference.getClass(ref).newInstance();
            Long idTemp;
            try{
              idTemp= Long.valueOf(id);
            }catch(NumberFormatException e){
                throw new Exception("L'identifier donner n'est pas un chiffre valide");
            }
            tache.setId(idTemp);
        } catch (Exception e) {
            throw e;
        }
        return tache;
    }
    public static TacheModel toTacheModel(String reference) throws Exception {
        String ref = reference.substring(0, 4);
        String id = reference.substring(4);
        TacheModel tache;
        try {
            tache =  (TacheModel)Reference.getClass(ref).newInstance();
            Long idTemp;
            try{
              idTemp= Long.valueOf(id);
            }catch(NumberFormatException e){
                throw new Exception("L'identifier donner n'est pas un chiffre valide");
            }
            tache.setId(idTemp);
        } catch (Exception e) {
            throw e;
        }
        return tache;
    }
}
