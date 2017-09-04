package com.er.erproject.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author diary
 */
public class UtilConvert {
    public static String toMoney(double data){
        return  String.format("%,d", (int)data);
    }
    public static double toSeconde(String minute, String seconde) throws Exception {
        int minuteTemp = 0;
        int secondeTemp = 0;
        double reponse = 0;
        try {
            minuteTemp = Integer.valueOf(minute);
            secondeTemp = Integer.valueOf(seconde);

            reponse = secondeTemp;
            reponse += minuteTemp * 60;

        } catch (Exception e) {
            throw e;
            // throw new Exception("L'une des entrees n'est pas un chiffre ");
        }
        return reponse;
    }

    public static Date convertToUtilDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            throw new Exception("La date inserer " + date + " n'est pas une date valide, essayer avec le format 'jj-mm-aaaa'");
        }

    }

    public static int toEntier(String data) throws Exception {
        try {
            int test = Integer.valueOf(data);
            if (test < 0) {
                throw new Exception("le nombre de doit pas etre inferieure a 0");
            }
            return test;
        } catch (Exception e) {
            throw new Exception(data + " n'est pas un nombre valide");
        }
    }

    public static double toDoubleEntier(String data) throws Exception {
        try {
            double test = Double.valueOf(data.replaceAll(",", "."));
            if (test < 0) {
                throw new Exception("le nombre de doit pas etre inferieure a 0");
            }
            return test;
        } catch (Exception e) {
            throw new Exception(data + " n'est pas un nombre valide");
        }
    }

    public static Date convertToSQLDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            throw new Exception("La date inserer " + date + " n'est pas une date valide, essayer avec le format 'yyyy-mm-dd'");
        }

    }

    public static Date convertToSQLDate(String date, String time) throws Exception {
        if (time.compareTo("") == 0) {
            throw new Exception("Veuillez rempliz l'heure");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.parse(date + " " + time);
        } catch (Exception e) {
            throw new Exception("La date inserer " + date + " " + time + " n'est pas une date valide, essayer avec le format 'yyyy-mm-dd HH:mm'");
        }

    }

    public static String toUrlPath(String data) {
        if (data != null) {
            String reponse = data.replaceAll("'", "%27");
            reponse = reponse.replaceAll(" ", "%20");
            return reponse;
        }
        return data;
    }

    public static String convertToString(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            throw new Exception("La date : " + date.toString() + " ne peut pas etre converti en format 'jj-MM-aaaa'");
        }
    }

    public static int convertStringToInt(String data) throws Exception {
        int reponse = -1;
        try {
            reponse = Integer.valueOf(data);
        } catch (Exception e) {
            throw new Exception("Erreur de convertion de texte en nombre");
        }
        return reponse;
    }

    public static String convertIntToString(int data) throws Exception {
        String reponse = "";
        try {
            reponse = String.valueOf(data);
        } catch (Exception e) {
            throw new Exception("Erreur de convertion de nombre en texte");
        }
        return reponse;
    }

    public static String convertCharToString(char data) {
        String reponse = "";
        reponse = String.valueOf(data);
        return reponse;
    }

    public static int getYears(Date d) {
        Calendar curr = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(d);
        int yeardiff = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        curr.add(Calendar.YEAR, -yeardiff);
        if (birth.after(curr)) {
            yeardiff = yeardiff - 1;
        }
        return yeardiff;
    }

    public static String convertToSQLDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static double toSeconde(int minute, int seconde) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String toStringAdvance(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm");
        return sdf.format(date);
    }

    public static String toString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        return sdf.format(date);
    }
}
