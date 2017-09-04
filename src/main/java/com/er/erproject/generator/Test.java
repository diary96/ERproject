/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.generator;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author diary
 */
public class Test {

    private String FILE = "E:/University/Test pdf/facture_E_varotra.pdf";
    private static final Font boltTableFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private static final Font normalBoldTableFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
    private static final Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
    private static final Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

    private static Font header = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, GrayColor.BLACK);
    private static Font redFont = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font bold = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

    private static Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
    private static Font extraSmallFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
    private static Font smallFontBold = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);

    public static void main(String[] arg) throws Exception {
        Test test = new Test();
    }

    public Test() throws Exception {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        addMetaData(document);
        addContent(document);
        document.close();
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void addContent(Document document) throws DocumentException, BadElementException, IOException {
        Paragraph information = new Paragraph();

        Image img = Image.getInstance("C:/Users/diary/Documents/Develeppoment/Logo/ER_LOGO _min.jpg");
        information.add(img);
        addEmptyLine(information, 1);
        document.add(information);

        information = new Paragraph();
        information.add(new Phrase("Telma SA - Bâtiment Ariane 5B RDC Zone GALAXY", normalFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("101 Antananarivo MADAGASCAR", normalFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("NIF : 3000003944", boldFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("Stat : 61101 11 1995 0 00180", boldFont));
        information.setAlignment(Element.ALIGN_RIGHT);
        document.add(information);

        information = new Paragraph();
        addEmptyLine(information, 2);
        information.add(new Phrase("Objet : Villa de l'imerina TXF AMBODIVONA ACT-20161207-01203 (LOT N 1)", boldFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("Ref : ER-1704-FACT-193", boldFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("Suivant BC N : CF F00 1700018834", boldFont));
        addEmptyLine(information, 2);
        information.add(new Phrase("Ref : EN TF DTG 005814", boldFont));
        addEmptyLine(information, 2);
        document.add(information);

        PdfPTable table;
        table = new PdfPTable(4);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{7, 2, 1, 2});

        PdfPCell c1;

//        BaseColor myColorpan = WebColors.getRGBColor("#BDBDBD");
        c1 = new PdfPCell(new Phrase("DENOMINATION", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("QUANTITE", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);    
//        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("P.U", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("MONTANT", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        for (int i = 0; i < 5; i++) {
//            TacheModel tache = this.offre.getTacheinitials().getTravaux().get(i);

            c1 = new PdfPCell(new Phrase("Ouverture chambre soudee", smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(12), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(1000), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("8 000", smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

        }
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("TOTAL HT", boldFont));
        c1.setColspan(2);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1 768 000", smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("REMISE 15 %", boldFont));
        c1.setColspan(2);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("265 200", smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("TOTAL APRES REMISE", boldFont));
        c1.setColspan(2);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1 502 800", smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("TVA 20%", boldFont));
        c1.setColspan(2);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("300 560", smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("TOTAL TTC", boldFont));
        c1.setColspan(2);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1 803 360", smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        document.add(table);
        
        information = new Paragraph(); 
        information.add(new Phrase("Arrêté la présente facture à la somme de : un million huit cent trois mille trois cent soixante Ariary ",smallFontBold));
        document.add(information);
        
        information = new Paragraph();
        information.add(new Phrase("Antananarivo le,",smallFont));
        addEmptyLine(information,1);
        information.add(new Phrase("Le Responsable Administratif et Financier",smallFont));
        addEmptyLine(information,6);
        information.add(new Phrase("RAKOTONIRINA Beby",smallFontBold));
        information.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(information,1);
        document.add(information);
        
        information = new Paragraph(); 
        information.add(new Phrase("Condition de paiement :",extraSmallFont));
        information.add(new Phrase("  100% par Mvola",extraSmallFont)); 
        addEmptyLine(information,1);
        information.add(new Phrase("Date d'échéance :",extraSmallFont));
        information.add(new Phrase("  05 mai 2017",extraSmallFont));
        document.add(information);
        
        
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
