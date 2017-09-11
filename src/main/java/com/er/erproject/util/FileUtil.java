/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.util;

import com.er.erproject.data.PathData;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author diary
 */
public class FileUtil {

    public static boolean savePdp(File image, String ext) throws Exception {
        try {

            String myfilename = image.getName() + "." + ext;
            System.out.println("Dst File name: " + myfilename);

            File destFile = new File(PathData.PATH_PHOTO, myfilename); // Null pointer exception is thrown here
            FileUtils.copyFile(image, destFile);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("impossible de copier la photo");
        }
        return true;
    }
    public static boolean saveBC(File bc, String ext) throws Exception {
        try {
            String myfilename = bc.getName() + "." + ext;
            File destFile = new File(PathData.PATH_BC, myfilename); // Null pointer exception is thrown here
            FileUtils.copyFile(bc, destFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("impossible de copier la photo");
        }
        return true;
    }
    public static boolean saveArchive(File bc,String ext)throws Exception{
        try{
            String myfilename = bc.getName() + "."+ext;
            File destFile = new File(PathData.PATH_ARCHIVE, myfilename); // Null pointer exception is thrown here
            FileUtils.copyFile(bc, destFile);
            return destFile.exists();
        }catch(Exception e){
            throw e;
        }
    }
    public static String getEx(String name)throws Exception{
        String ext=null;
        String[] data = name.split(Pattern.quote("."));
        if(data.length>0)ext=data[data.length-1];
        else throw new Exception("extension non valide");
        return ext;
    }
    public static void deleteFile(String pathPhoto) throws Exception {
        File photo = null;
        String path = PathData.PATH_PHOTO_SIMPLE + "/" + pathPhoto;
        boolean test;
        try {
            photo = new File(path);
            test = photo.exists();
            if(!test)throw new Exception();
        } catch (Exception e) {
            throw new Exception("La photo rechercher est introuvable");
        }
        try {
            Path paths = Paths.get(path);
            Files.delete(paths);
        } catch (Exception e) {
            throw new Exception("Impossible de supprimer la photo");
        }
    }
}
