/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.generator;

import com.er.erproject.data.PathData;
import com.er.erproject.data.StatuReference;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Travaux;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author diary
 */
public class XlsGenerator {

    public static void main(String[] args)
            throws BiffException, IOException, WriteException {
        WritableWorkbook wworkbook;
        wworkbook = Workbook.createWorkbook(new File("E:/University/xlsExport/output.xls"));
        WritableSheet wsheet = wworkbook.createSheet("Devise", 0);

        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
        cellFont.setBoldStyle(WritableFont.BOLD);

        WritableCellFormat titleFormat = new WritableCellFormat(cellFont);
        setAllBorder(titleFormat);
        titleFormat.setAlignment(Alignment.CENTRE);

        WritableCellFormat normalBoltFormat = new WritableCellFormat(cellFont);
        normalBoltFormat.setAlignment(Alignment.LEFT);

        WritableCellFormat normalFormat = new WritableCellFormat();
        normalFormat.setAlignment(Alignment.LEFT);

        WritableCellFormat extraFormat = new WritableCellFormat(cellFont);
//        setAllBorder(extraFormat);
        extraFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat extraBorderFormat = new WritableCellFormat(cellFont);
        setAllBorder(extraBorderFormat);
        extraBorderFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat normal = new WritableCellFormat();
        setAllBorder(normal);
        normal.setAlignment(Alignment.LEFT);

        WritableCellFormat argentFormat = new WritableCellFormat();
        setAllBorder(argentFormat);
        argentFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat centerFormat = new WritableCellFormat();
        setAllBorder(centerFormat);
        centerFormat.setAlignment(Alignment.CENTRE);
        
        File imageFile = new File("C:/Users/diary/Documents/Develeppoment/Logo/ER_LOGO.jpg");
        BufferedImage input = ImageIO.read(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(input, "PNG", baos);
        wsheet.addImage(new WritableImage(1, 0, input.getWidth() / 250,
                input.getHeight() / 70, baos.toByteArray()));
        
        List<Label> labels = new ArrayList();
        labels.add(new Label(1, 5, "LALAS CYBER AMBATOROKA", normalBoltFormat));
        labels.add(new Label(1, 6, "REC-20160906-0306-fo", normalFormat));
        labels.add(new Label(1, 7, "SOUM05", normal));
        labels.add(new Label(0, 8, "N°", titleFormat));
        labels.add(new Label(1, 8, "DENOMINATION", titleFormat));
        labels.add(new Label(2, 8, "U", titleFormat));
        labels.add(new Label(3, 8, "QTE", titleFormat));
        labels.add(new Label(4, 8, "PU", titleFormat));
        labels.add(new Label(5, 8, "MONTANT", titleFormat));

        List<Label> denominations = new ArrayList();
        denominations.add(new Label(1, 9, "Ouverture d'une chambre soudee", normal));
        denominations.add(new Label(1, 10, "Soudure d'une chambre", normal));
        denominations.add(new Label(1, 11, "Total HT", extraFormat));
        denominations.add(new Label(1, 12, "TVA", extraFormat));
        denominations.add(new Label(1, 13, "TOTAL TTC", extraFormat));

        List<Number> num = new ArrayList();
        num.add(new Number(0, 9, 1, centerFormat));
        num.add(new Number(0, 10, 2, centerFormat));
        
        List<Label> unites = new ArrayList();
        unites.add(new Label(2, 9, "U", centerFormat));
        unites.add(new Label(2, 10, "U", centerFormat));

        List<Number> qte = new ArrayList();
        qte.add(new Number(3, 9, 3, centerFormat));
        qte.add(new Number(3, 10, 1, centerFormat));

        List<Number> pu = new ArrayList();
        pu.add(new Number(4, 9, 8000, argentFormat));
        pu.add(new Number(4, 10, 70000, argentFormat));

        List<Number> montant = new ArrayList();
        montant.add(new Number(5, 9, 24000, argentFormat));
        montant.add(new Number(5, 10, 70000, argentFormat));
        montant.add(new Number(5, 11, 94000, extraBorderFormat));
        montant.add(new Number(5, 12, 62000, extraBorderFormat));
        montant.add(new Number(5, 13, 156000, extraBorderFormat));
        
        labels.add(new Label(2,15,"Antananarivo le, 12 Septembre 2016"));
        
        File imageFileLogo = new File("C:/Users/diary/Documents/Develeppoment/Logo/signature.gif");
        BufferedImage inputLogo = ImageIO.read(imageFileLogo);
        ByteArrayOutputStream baosLogo = new ByteArrayOutputStream();
        ImageIO.write(inputLogo, "PNG", baosLogo);
        wsheet.addImage(new WritableImage(4, 17, inputLogo.getWidth() / 100,
                inputLogo.getHeight() / 30, baosLogo.toByteArray()));
        
        for (int i = 0; i < num.size(); i++) {
            wsheet.addCell(num.get(i));

        }
        for (int i = 0; i < labels.size(); i++) {
            wsheet.addCell(labels.get(i));

        }
        for (int i = 0; i < denominations.size(); i++) {
            wsheet.addCell(denominations.get(i));
        }
        for (int i = 0; i < unites.size(); i++) {
            wsheet.addCell(unites.get(i));
        }
        for (int i = 0; i < qte.size(); i++) {
            wsheet.addCell(qte.get(i));
        }

        for (int i = 0; i < pu.size(); i++) {
            wsheet.addCell(pu.get(i));
        }
        for (int i = 0; i < montant.size(); i++) {
            wsheet.addCell(montant.get(i));
        }
        sheetAutoFitColumns(wsheet);
        wworkbook.write();
        wworkbook.close();

        Workbook workbook = Workbook.getWorkbook(new File("E:/University/xlsExport/output.xls"));
//      Sheet sheet = workbook.getSheet(0);
//      Cell cell1 = sheet.getCell(0, 2);
//     
//      Cell cell2 = sheet.getCell(3, 4);

        workbook.close();

    }
    public static void  generateXLS(Offre offre)throws Exception{
        if(offre.getTacheinitials()==null)throw new Exception("Offre non initialiser");
        if(offre.getSoumission()==null)throw new Exception("Offre non initialiser");
        
        Travaux initial = offre.getTacheinitials(); 
        Travaux soumission = offre.getTacheSoumission();
                
        
        WritableWorkbook wworkbook;
        wworkbook = Workbook.createWorkbook(new File(PathData.PATH_XLS_DEVIS));
        WritableSheet wsheet = wworkbook.createSheet("Devise", 0);

        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
        cellFont.setBoldStyle(WritableFont.BOLD);

        WritableCellFormat titleFormat = new WritableCellFormat(cellFont);
        setAllBorder(titleFormat);
        titleFormat.setAlignment(Alignment.CENTRE);

        WritableCellFormat normalBoltFormat = new WritableCellFormat(cellFont);
        normalBoltFormat.setAlignment(Alignment.LEFT);

        WritableCellFormat normalFormat = new WritableCellFormat();
        normalFormat.setAlignment(Alignment.LEFT);

        WritableCellFormat extraFormat = new WritableCellFormat(cellFont);
//        setAllBorder(extraFormat);
        extraFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat extraBorderFormat = new WritableCellFormat(cellFont);
        setAllBorder(extraBorderFormat);
        extraBorderFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat normal = new WritableCellFormat();
        setAllBorder(normal);
        normal.setAlignment(Alignment.LEFT);

        WritableCellFormat argentFormat = new WritableCellFormat();
        setAllBorder(argentFormat);
        argentFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat centerFormat = new WritableCellFormat();
        setAllBorder(centerFormat);
        centerFormat.setAlignment(Alignment.CENTRE);
        
        File imageFile = new File("C:/Users/diary/Documents/Develeppoment/Logo/ER_LOGO.jpg");
        BufferedImage input = ImageIO.read(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(input, "PNG", baos);
        wsheet.addImage(new WritableImage(1, 0, input.getWidth() / 250,
                input.getHeight() / 70, baos.toByteArray()));
        
        List<Label> labels = new ArrayList();
        labels.add(new Label(1, 5, offre.getNomProjet(), normalBoltFormat));
        labels.add(new Label(1, 6, offre.getTicket(), normalFormat));
        labels.add(new Label(1, 7, offre.getAllReference(), normal));
        labels.add(new Label(0, 8, "N°", titleFormat));
        labels.add(new Label(1, 8, "DENOMINATION", titleFormat));
        labels.add(new Label(2, 8, "U", titleFormat));
        labels.add(new Label(3, 8, "QTE", titleFormat));
        labels.add(new Label(4, 8, "PU", titleFormat));
        labels.add(new Label(5, 8, "MONTANT", titleFormat));

        List<Label> denominations = new ArrayList();
        int depart = 9;
        for(int i=0;i<initial.getTravaux().size();i++){
            denominations.add(new Label(1, depart,initial.getTravaux().get(i).getCatalogue().getDesignation(), normal));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            denominations.add(new Label(1, depart,soumission.getTravaux().get(i).getCatalogue().getDesignation(), normal));
            depart++;
        }  
        denominations.add(new Label(1, depart, "Total HT", extraFormat));
        depart++;
        denominations.add(new Label(1, depart, "Remise de "+offre.getSoumission().getRemise()+"%", extraFormat));
        depart++;
        denominations.add(new Label(1, depart, "TVA de "+offre.getSoumission().getTva()+"%", extraFormat));
        depart++;
        
        denominations.add(new Label(1, depart, "TOTAL TTC", extraFormat));
        
        depart=9;

        List<Number> num = new ArrayList();
        int totalLine = initial.getTravaux().size()+soumission.getTravaux().size();
        for(int i=1;i<=totalLine;i++){
            num.add(new Number(0,depart, i, centerFormat));
            depart++;
        }
        depart=9;

        List<Label> unites = new ArrayList();
        
        for(int i=0;i<initial.getTravaux().size();i++){
            unites.add(new Label(2, depart,initial.getTravaux().get(i).getCatalogue().getUnite(), centerFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
             unites.add(new Label(2, depart,soumission.getTravaux().get(i).getCatalogue().getUnite(), centerFormat));
           
            depart++;
        }
        depart=9;

        List<Number> qte = new ArrayList();
        for(int i=0;i<initial.getTravaux().size();i++){
            qte.add(new Number(3, depart, initial.getTravaux().get(i).getQuantite(), centerFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            qte.add(new Number(3, depart, soumission.getTravaux().get(i).getQuantite(), centerFormat));
            depart++;
        }
        depart=9;       

        List<Number> pu = new ArrayList();
        for(int i=0;i<initial.getTravaux().size();i++){
            pu.add(new Number(4, depart, initial.getTravaux().get(i).getCatalogue().getPrixUnitaire(), argentFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            pu.add(new Number(4, depart, soumission.getTravaux().get(i).getCatalogue().getPrixUnitaire(), argentFormat));
            depart++;
        }
        depart=9;
        
        List<Number> montant = new ArrayList();
        for(int i=0;i<initial.getTravaux().size();i++){
            montant.add(new Number(5, depart, initial.getTravaux().get(i).getCatalogue().getPrixUnitaire()*initial.getTravaux().get(i).getQuantite(), argentFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            montant.add(new Number(5, depart, soumission.getTravaux().get(i).getCatalogue().getPrixUnitaire()*soumission.getTravaux().get(i).getQuantite(), argentFormat));          
            depart++;
        }
        montant.add(new Number(5, depart, offre.getSoumission().getTotal(), extraBorderFormat));
        depart++;  
        montant.add(new Number(5, depart, offre.getSoumission().getValeurRemise(), extraBorderFormat));
        depart++;
        montant.add(new Number(5, depart, offre.getSoumission().getValeurTVA(), extraBorderFormat));
        depart++;
        montant.add(new Number(5, depart, offre.getSoumission().getTotalTTC(), extraBorderFormat));
        depart++;
        depart++;
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        SimpleDateFormat dateFormat;

        dateFormat = new SimpleDateFormat("EEEE dd MMMMM yyyy");
        labels.add(new Label(2,depart,"Antananarivo le, "+dateFormat.format(date)));
        depart++;
        File imageFileLogo = new File("C:/Users/diary/Documents/Develeppoment/Logo/signature.gif");
        BufferedImage inputLogo = ImageIO.read(imageFileLogo);
        ByteArrayOutputStream baosLogo = new ByteArrayOutputStream();
        ImageIO.write(inputLogo, "PNG", baosLogo);
        wsheet.addImage(new WritableImage(3, depart+2, inputLogo.getWidth() / 100,
                inputLogo.getHeight() / 30, baosLogo.toByteArray()));
        
        for (int i = 0; i < num.size(); i++) {
            wsheet.addCell(num.get(i));

        }
        for (int i = 0; i < labels.size(); i++) {
            wsheet.addCell(labels.get(i));

        }
        for (int i = 0; i < denominations.size(); i++) {
            wsheet.addCell(denominations.get(i));
        }
        for (int i = 0; i < unites.size(); i++) {
            wsheet.addCell(unites.get(i));
        }
        for (int i = 0; i < qte.size(); i++) {
            wsheet.addCell(qte.get(i));
        }

        for (int i = 0; i < pu.size(); i++) {
            wsheet.addCell(pu.get(i));
        }
        for (int i = 0; i < montant.size(); i++) {
            wsheet.addCell(montant.get(i));
        }
        sheetAutoFitColumns(wsheet);
        wworkbook.write();
        wworkbook.close();

        Workbook workbook = Workbook.getWorkbook(new File(PathData.PATH_XLS_DEVIS));
        workbook.close();
    }
    public static void  generateFactureXLS(Offre offre)throws Exception{
        if(offre.getTacheinitials()==null)throw new Exception("Offre non initialiser");
        if(offre.getSoumission()==null)throw new Exception("Offre non initialiser");
        if(offre.getStatu()<StatuReference.FACTURATION)throw new Exception("L'offre ne peut pas etre facturisé");
        
        Travaux initial = offre.getTacheinitials(); 
        Travaux soumission = offre.getTacheSoumission();
                
        
        WritableWorkbook wworkbook;
        wworkbook = Workbook.createWorkbook(new File(PathData.PATH_XLS_FACTURE));
        WritableSheet wsheet = wworkbook.createSheet("Devise", 0);

        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
        cellFont.setBoldStyle(WritableFont.BOLD);

        WritableCellFormat titleFormat = new WritableCellFormat(cellFont);
        setAllBorder(titleFormat);
        titleFormat.setAlignment(Alignment.CENTRE);

        WritableCellFormat normalBoltFormat = new WritableCellFormat(cellFont);
        normalBoltFormat.setAlignment(Alignment.LEFT);

        WritableCellFormat normalFormat = new WritableCellFormat();
        normalFormat.setAlignment(Alignment.LEFT);

        WritableCellFormat extraFormat = new WritableCellFormat(cellFont);
//        setAllBorder(extraFormat);
        extraFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat extraBorderFormat = new WritableCellFormat(cellFont);
        setAllBorder(extraBorderFormat);
        extraBorderFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat normal = new WritableCellFormat();
        setAllBorder(normal);
        normal.setAlignment(Alignment.LEFT);

        WritableCellFormat argentFormat = new WritableCellFormat();
        setAllBorder(argentFormat);
        argentFormat.setAlignment(Alignment.RIGHT);

        WritableCellFormat centerFormat = new WritableCellFormat();
        setAllBorder(centerFormat);
        centerFormat.setAlignment(Alignment.CENTRE);
        
        File imageFile = new File("C:/Users/diary/Documents/Develeppoment/Logo/ER_LOGO.jpg");
        BufferedImage input = ImageIO.read(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(input, "PNG", baos);
        wsheet.addImage(new WritableImage(1, 0, input.getWidth() / 250,
                input.getHeight() / 70, baos.toByteArray()));
        
        List<Label> labels = new ArrayList();
        labels.add(new Label(1, 5, offre.getNomProjet(), normalBoltFormat));
        labels.add(new Label(1, 6, offre.getTicket(), normalFormat));
        labels.add(new Label(1, 7, offre.getAllReference(), normal));
        labels.add(new Label(0, 8, "N°", titleFormat));
        labels.add(new Label(1, 8, "DENOMINATION", titleFormat));
        labels.add(new Label(2, 8, "U", titleFormat));
        labels.add(new Label(3, 8, "QTE", titleFormat));
        labels.add(new Label(4, 8, "EFFECTUER", titleFormat));
        labels.add(new Label(5, 8, "PU", titleFormat));
        labels.add(new Label(6, 8, "MONTANT", titleFormat));

        List<Label> denominations = new ArrayList();
        int depart = 9;
        for(int i=0;i<initial.getTravaux().size();i++){
            denominations.add(new Label(1, depart,initial.getTravaux().get(i).getCatalogue().getDesignation(), normal));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            denominations.add(new Label(1, depart,soumission.getTravaux().get(i).getCatalogue().getDesignation(), normal));
            depart++;
        }  
        denominations.add(new Label(1, depart, "Total HT", extraFormat));
        depart++;
        denominations.add(new Label(1, depart, "Remise de "+offre.getSoumission().getRemise()+"%", extraFormat));
        depart++;
        denominations.add(new Label(1, depart, "TVA de "+offre.getSoumission().getTva()+"%", extraFormat));
        depart++;
        
        denominations.add(new Label(1, depart, "TOTAL TTC", extraFormat));
        
        depart=9;

        List<Number> num = new ArrayList();
        int totalLine = initial.getTravaux().size()+soumission.getTravaux().size();
        for(int i=1;i<=totalLine;i++){
            num.add(new Number(0,depart, i, centerFormat));
            depart++;
        }
        depart=9;

        List<Label> unites = new ArrayList();
        
        for(int i=0;i<initial.getTravaux().size();i++){
            unites.add(new Label(2, depart,initial.getTravaux().get(i).getCatalogue().getUnite(), centerFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
             unites.add(new Label(2, depart,soumission.getTravaux().get(i).getCatalogue().getUnite(), centerFormat));
           
            depart++;
        }
        depart=9;

        List<Number> qte = new ArrayList();
        for(int i=0;i<initial.getTravaux().size();i++){
            qte.add(new Number(3, depart, initial.getTravaux().get(i).getQuantite(), centerFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            qte.add(new Number(3, depart, soumission.getTravaux().get(i).getQuantite(), centerFormat));
            depart++;
        }
        depart=9;       
        List<Number> effectuer = new ArrayList();
        for(int i=0;i<initial.getTravaux().size();i++){
            effectuer.add(new Number(4, depart, initial.getTravaux().get(i).getEffectuer(), centerFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            effectuer.add(new Number(4, depart, soumission.getTravaux().get(i).getEffectuer(), centerFormat));
            depart++;
        }
        depart=9;       

        List<Number> pu = new ArrayList();
        for(int i=0;i<initial.getTravaux().size();i++){
            pu.add(new Number(5, depart, initial.getTravaux().get(i).getCatalogue().getPrixUnitaire(), argentFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            pu.add(new Number(5, depart, soumission.getTravaux().get(i).getCatalogue().getPrixUnitaire(), argentFormat));
            depart++;
        }
        depart=9;
        
        List<Number> montant = new ArrayList();
        for(int i=0;i<initial.getTravaux().size();i++){
            montant.add(new Number(6, depart, initial.getTravaux().get(i).getCatalogue().getPrixUnitaire()*initial.getTravaux().get(i).getEffectuer(), argentFormat));
            depart++;
        }
        for(int i=0;i<soumission.getTravaux().size();i++){
            montant.add(new Number(6, depart, soumission.getTravaux().get(i).getCatalogue().getPrixUnitaire()*soumission.getTravaux().get(i).getEffectuer(), argentFormat));          
            depart++;
        }
        montant.add(new Number(6, depart, offre.getStatInitial().getTotalEffectuer(), extraBorderFormat));
        depart++;  
        montant.add(new Number(6, depart, offre.getStatInitial().getValeurRemise(), extraBorderFormat));
        depart++;
        montant.add(new Number(6, depart, offre.getStatInitial().getValeurTVA(), extraBorderFormat));
        depart++;
        montant.add(new Number(6, depart, offre.getStatInitial().getTotalTTC(), extraBorderFormat));
        depart++;
        depart++;
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        SimpleDateFormat dateFormat;

        dateFormat = new SimpleDateFormat("EEEE dd MMMMM yyyy");
        labels.add(new Label(3,depart,"Antananarivo le, "+dateFormat.format(date)));
        depart++;
        File imageFileLogo = new File("C:/Users/diary/Documents/Develeppoment/Logo/signature.gif");
        BufferedImage inputLogo = ImageIO.read(imageFileLogo);
        ByteArrayOutputStream baosLogo = new ByteArrayOutputStream();
        ImageIO.write(inputLogo, "PNG", baosLogo);
        wsheet.addImage(new WritableImage(4, depart+2, inputLogo.getWidth() / 100,
                inputLogo.getHeight() / 30, baosLogo.toByteArray()));
        
        for (int i = 0; i < num.size(); i++) {
            wsheet.addCell(num.get(i));

        }
        for (int i = 0; i < labels.size(); i++) {
            wsheet.addCell(labels.get(i));

        }
        for (int i = 0; i < denominations.size(); i++) {
            wsheet.addCell(denominations.get(i));
        }
        for (int i = 0; i < unites.size(); i++) {
            wsheet.addCell(unites.get(i));
        }
        for (int i = 0; i < qte.size(); i++) {
            wsheet.addCell(qte.get(i));
        }
        for (int i = 0; i < effectuer.size(); i++) {
            wsheet.addCell(effectuer.get(i));
        }

        for (int i = 0; i < pu.size(); i++) {
            wsheet.addCell(pu.get(i));
        }
        for (int i = 0; i < montant.size(); i++) {
            wsheet.addCell(montant.get(i));
        }
        sheetAutoFitColumns(wsheet);
        wworkbook.write();
        wworkbook.close();

        Workbook workbook = Workbook.getWorkbook(new File(PathData.PATH_XLS_FACTURE));
        workbook.close();
    }

    private static void setAllBorder(WritableCellFormat centerFormat) throws WriteException {
        centerFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

    }
    private static int plus(int dep,int value){
        dep=dep+value;
        return dep;
    }
    private static void sheetAutoFitColumns(WritableSheet sheet) {
        for (int i = 0; i < sheet.getColumns(); i++) {
            Cell[] cells = sheet.getColumn(i);
            int longestStrLen = -1;

            if (cells.length == 0) {
                continue;
            }
            /* Find the widest cell in the column. */
            for (int j = 0; j < cells.length-3; j++) {
                if (cells[j].getContents().length() > longestStrLen) {
                    String str = cells[j].getContents();
                    if (str == null || str.isEmpty()) {
                        continue;
                    }
                    longestStrLen = str.trim().length();
                }
            }

            /* If not found, skip the column. */
            if (longestStrLen == -1) {
                continue;
            }

            /* If wider than the max width, crop width */
            if (longestStrLen > 255) {
                longestStrLen = 255;
            }

            CellView cv = sheet.getColumnView(i);
            cv.setSize(longestStrLen * 256 + 1000);
            /* Every character is 256 units wide, so scale it. */
            sheet.setColumnView(i, cv);
        }
    }
}
