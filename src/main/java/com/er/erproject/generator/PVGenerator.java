/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.generator;

import com.er.erproject.data.PathData;
import com.er.erproject.data.StatuReference;
import com.er.erproject.model.Materiaux;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Photo;
import com.er.erproject.model.TacheModel;
import com.er.erproject.util.DateUtil;
import com.er.erproject.util.ServiceUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author diary
 */
public class PVGenerator {

    private static final Font boltTableFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private static final Font normalBoldTableFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
    private static final Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
    private static final Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static final Font header = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.YELLOW);
    private static final Font redFont = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font bold = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
    private static Font smallFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

    private static int idfacture;

    private Offre offre;

    public static Font getSmallFont() {
        return smallFont;
    }

    public static void setSmallFont(Font smallFont) {
        PVGenerator.smallFont = smallFont;
    }

    public static int getIdfacture() {
        return idfacture;
    }

    public static void setIdfacture(int idfacture) {
        PVGenerator.idfacture = idfacture;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public PVGenerator(Offre offre, Date date, String er, String telma, String lieu,HttpServletRequest servletRequest) throws Exception {
        this.offre = offre;
        Document document = new Document();
        
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(servletRequest.getSession().getServletContext().getRealPath("/")+PathData.PATH_PDF_PV));
        
        setNumberPage(writer,servletRequest);
        document.open();

        addMetaData(document);
        addContent(document, date, er, telma,writer,servletRequest);
        sautPage(document, 1);
        addLastPage(document, date, er, telma, lieu);
        document.close();
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("PDF Generator");
        document.addSubject("PV");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("E.R Systeme");
        document.addCreator("E.R Systeme");
    }

    private void addContent(Document document, Date date, String er, String telma, PdfWriter writer,HttpServletRequest servletRequest) throws Exception {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{(float) 3 / 2, 5, (float) 3 / 2});

        PdfPCell cell;

        
        Chunk underline;
        Paragraph information = new Paragraph();
        addEmptyLine(information, 1);

        underline = new Chunk("Projet : ", boldFont);
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        information.add(underline);

        information.add(new Paragraph(this.offre.getNomProjet(), boldFont));
        addEmptyLine(information, 1);

        underline = new Chunk("Ticket : ", boldFont);
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        information.add(underline);

        information.add(new Paragraph(offre.getTicket(), boldFont));
        addEmptyLine(information, 1);

        information.add(new Paragraph(DateUtil.toAllLettre(date) + " s'est réuni sur le champ à " + offre.getLocalisation() + " la commission"
                + " de réception composée de :  ", normalFont));

        addEmptyLine(information, 1);
        underline = new Chunk("Pour l'Entreprise RANDRIANANRISOA : ", boldFont);
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        information.add(underline);

        addEmptyLine(information, 2);
        information.add(new Paragraph("- " + er, normalFont));
        addEmptyLine(information, 1);

        underline = new Chunk("Pour Telma : ", boldFont);
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        information.add(underline);

        addEmptyLine(information, 2);
        information.add(new Paragraph("- " + telma, normalFont));
        addEmptyLine(information, 1);
        information.add(new Paragraph("La commission a noté avec satisfaction la conformité des Travaux de : ", normalFont));
        addEmptyLine(information, 1);
        document.add(information);

        table = new PdfPTable(5);

        table.setWidthPercentage(100);
        table.setWidths(new float[]{(float) 3 / 2, 5, 2, 2, 2});

        BaseColor myColorpan = WebColors.getRGBColor("#007119");
        PdfPCell c1 = new PdfPCell(new Phrase("ITEM", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(5);
        c1.setBackgroundColor(myColorpan);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("DESCRIPTIF DES TRAVAUX", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("UNITE", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("QUANTITE", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("REMARQUE", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(myColorpan);
        c1.setPadding(5);
        table.addCell(c1);

        //document.add(table);
        for (int i = 0; i < offre.getTacheinitials().getTravaux().size(); i++) {
            TacheModel tache = this.offre.getTacheinitials().getTravaux().get(i);
            c1 = new PdfPCell(new Phrase(Integer.toString(i + 1), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(tache.getCatalogue().getDesignation(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(10);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(tache.getCatalogue().getUnite(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(tache.getQuantite()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);
            if (tache.getQuantite() == tache.getEffectuer()) {
                c1 = new PdfPCell(new Phrase("FAIT", smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);
            } else {
                String phrase = tache.getEffectuer() + " FAIT / " + (tache.getQuantite() - tache.getEffectuer()) + " NON FAIT";
                c1 = new PdfPCell(new Phrase(phrase, smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);
            }

        }
        for (int i = 0; i < offre.getTacheSoumission().getTravaux().size(); i++) {
            TacheModel tache = this.offre.getTacheSoumission().getTravaux().get(i);
            c1 = new PdfPCell(new Phrase(Integer.toString(i + 1), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(tache.getCatalogue().getDesignation(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(10);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(tache.getCatalogue().getUnite(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(tache.getQuantite()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);
            if (tache.getQuantite() == tache.getEffectuer()) {
                c1 = new PdfPCell(new Phrase("FAIT", smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);
            } else {
                String phrase = tache.getEffectuer() + " FAIT / " + (tache.getQuantite() - tache.getEffectuer()) + " NON FAIT";
                c1 = new PdfPCell(new Phrase(phrase, smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);
            }

        }
        document.add(table);

        if (offre.getMateriaux() != null && !offre.getMateriaux().isEmpty()) {
            information = new Paragraph();
            addEmptyLine(information, 1);
            underline = new Chunk("LISTE DES MATERIELS RECUS", boldFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            information.add(underline);
            addEmptyLine(information, 2);
            document.add(information);

            table = new PdfPTable(5);

            table.setWidthPercentage(100);
            table.setWidths(new float[]{(float) 3 / 2, 5, 2, 2, 2});

            c1 = new PdfPCell(new Phrase("ITEM", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setPadding(5);
            c1.setBackgroundColor(myColorpan);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("DESCRIPTIF DES MATERIELS", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("UNITE", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("QUANTITE", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("REMARQUE", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            //document.add(table);
            for (int i = 0; i < offre.getMateriaux().size(); i++) {
                Materiaux materiaux = offre.getMateriaux().get(i);
                c1 = new PdfPCell(new Phrase(Integer.toString(i + 1), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(materiaux.getDesignation(), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                c1.setPadding(10);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(materiaux.getUnite(), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(String.valueOf(materiaux.getQuantite()), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(StatuReference.getString(materiaux.getEtat()), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

            }
            document.add(table);
        }
        if (this.offre.getTacheSupplementaire() != null && !this.offre.getTacheSupplementaire().getTravaux().isEmpty()) {
            information = new Paragraph();
            addEmptyLine(information, 1);
            underline = new Chunk("LISTE DES TRAVAUX SUPPLEMENTAIRE", boldFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            information.add(underline);
            addEmptyLine(information, 2);
            document.add(information);

            table = new PdfPTable(5);

            table.setWidthPercentage(100);
            table.setWidths(new float[]{(float) 3 / 2, 5, 2, 2, 2});

            c1 = new PdfPCell(new Phrase("ITEM", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setPadding(5);
            c1.setBackgroundColor(myColorpan);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("DESCRIPTIF DES TRAVAUX", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("UNITE", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("QUANTITE", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("REMARQUE", header));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(myColorpan);
            c1.setPadding(5);
            table.addCell(c1);

            //document.add(table);
            for (int i = 0; i < offre.getTacheSupplementaire().getTravaux().size(); i++) {
                TacheModel tache = offre.getTacheSupplementaire().getTravaux().get(i);
                c1 = new PdfPCell(new Phrase(Integer.toString(i + 1), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(tache.getCatalogue().getDesignation(), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                c1.setPadding(10);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(tache.getCatalogue().getUnite(), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(String.valueOf(tache.getQuantite()), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("FAIT", smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

            }
            document.add(table);
        }
        sautPage(document, 1);
        for (int i = 0; i < offre.getTacheinitials().getTravaux().size(); i++) {
            TacheModel tache = offre.getTacheinitials().getTravaux().get(i);
            if (tache.getPhotos() != null && !tache.getPhotos().isEmpty()) {
                float leftPage = 842-(document.getPageSize().getHeight()- writer.getVerticalPosition(false));        
                if(leftPage<200)sautPage(document,1);
                information = new Paragraph();
                information.add(new Phrase(tache.getCatalogue().getDesignation(), boldFont));
                addEmptyLine(information, 1);
                int si = 0 ;
                while ( si < tache.getPhotos().size()) {
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Photo photo = tache.getPhotos().get(si);
                    Photo photo2 = null;
                    addEmptyLine(information, 1);
                    Image img = Image.getInstance(servletRequest.getSession().getServletContext().getRealPath("/") + photo.getPathPhoto());
                    img.scaleAbsolute(220, ServiceUtil.resizeHeight(img.getWidth(), img.getHeight()));
                    information.add(new Chunk(img, 0, 0, true));
                    int limit = tache.getPhotos().size()-1;
                    if(si<limit){
                        information.add(new Chunk(glue));
                        photo2 = tache.getPhotos().get(si+1);
                        Image img2 = Image.getInstance(servletRequest.getSession().getServletContext().getRealPath("/")+ photo2.getPathPhoto());
                        img2.scaleAbsolute(220, ServiceUtil.resizeHeight(img2.getWidth(), img2.getHeight()));
                        information.add(new Chunk(img2, 0, 0, true));
                       
                    }
                    addEmptyLine(information,1);
                    information.add(new Phrase(photo.getLatitude() + "   " + photo.getLongitude(), normalFont));
                    
                    if(si<limit){
                        
                        information.add(new Chunk(glue));
                        information.add(new Phrase(photo2.getLatitude() + "   " + photo2.getLongitude(), normalFont));
                    }
                    addEmptyLine(information, 1);
                    
                    if(si<limit){
                        si++;
                    }
                    si++;
                }
                document.add(information);
            }
        }
        for (int i = 0; i < offre.getTacheSoumission().getTravaux().size(); i++) {
            TacheModel tache = offre.getTacheSoumission().getTravaux().get(i);
            if (tache.getPhotos() != null && !tache.getPhotos().isEmpty()) {
                float leftPage = 842-(document.getPageSize().getHeight()- writer.getVerticalPosition(false));        
                if(leftPage<200)sautPage(document,1);
                information = new Paragraph();
                information.add(new Phrase(tache.getCatalogue().getDesignation(), boldFont));
                addEmptyLine(information, 1);
                int si = 0 ;
                while ( si < tache.getPhotos().size()) {
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Photo photo = tache.getPhotos().get(si);
                    Photo photo2 = null;
                    addEmptyLine(information, 1);
                    Image img = Image.getInstance(servletRequest.getSession().getServletContext().getRealPath("/")+ photo.getPathPhoto());
                    img.scaleAbsolute(220,ServiceUtil.resizeHeight(img.getWidth(), img.getHeight()));
                    information.add(new Chunk(img, 0, 0, true));
                    int limit = tache.getPhotos().size()-1;
                    if(si<limit){
                        information.add(new Chunk(glue));
                        photo2 = tache.getPhotos().get(si+1);
                        Image img2 = Image.getInstance(servletRequest.getSession().getServletContext().getRealPath("/")+ photo2.getPathPhoto());
                        img2.scaleAbsolute(220, ServiceUtil.resizeHeight(img2.getWidth(), img2.getHeight()));
                        
                        information.add(new Chunk(img2, 0, 0, true));
                       
                    }
                    addEmptyLine(information,1);
                    information.add(new Phrase(photo.getLatitude() + "   " + photo.getLongitude(), normalFont));
                    
                    if(si<limit){
                        
                        information.add(new Chunk(glue));
                        information.add(new Phrase(photo2.getLatitude() + "   " + photo2.getLongitude(), normalFont));
                    }
                    addEmptyLine(information, 1);
                    
                    if(si<limit){
                        si++;
                    }
                    si++;
                }
                document.add(information);
            }
        }
        for (int i = 0; i < offre.getTacheSupplementaire().getTravaux().size(); i++) {
            TacheModel tache = offre.getTacheSupplementaire().getTravaux().get(i);
            if (tache.getPhotos() != null && !tache.getPhotos().isEmpty()) {
                float leftPage = 842-(document.getPageSize().getHeight()- writer.getVerticalPosition(false));        
                if(leftPage<200)sautPage(document,1);
                information = new Paragraph();
                information.add(new Phrase(tache.getCatalogue().getDesignation(), boldFont));
                addEmptyLine(information, 1);
                int si = 0 ;
                while ( si < tache.getPhotos().size()) {
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Photo photo = tache.getPhotos().get(si);
                    Photo photo2 = null;
                    addEmptyLine(information, 1);
                    Image img = Image.getInstance(servletRequest.getSession().getServletContext().getRealPath("/")+ photo.getPathPhoto());
                    img.scaleAbsolute(220,ServiceUtil.resizeHeight(img.getWidth(), img.getHeight()));
                    information.add(new Chunk(img, 0, 0, true));
                    int limit = tache.getPhotos().size()-1;
                    if(si<limit){
                        information.add(new Chunk(glue));
                        photo2 = tache.getPhotos().get(si+1);
                        Image img2 = Image.getInstance(servletRequest.getSession().getServletContext().getRealPath("/")+ photo2.getPathPhoto());
                        img2.scaleAbsolute(220, ServiceUtil.resizeHeight(img2.getWidth(), img2.getHeight()));
                        
                        information.add(new Chunk(img2, 0, 0, true));
                       
                    }
                    addEmptyLine(information,1);
                    information.add(new Phrase(photo.getLatitude() + "   " + photo.getLongitude(), normalFont));
                    
                    if(si<limit){
                        
                        information.add(new Chunk(glue));
                        information.add(new Phrase(photo2.getLatitude() + "   " + photo2.getLongitude(), normalFont));
                    }
                    addEmptyLine(information, 1);
                    
                    if(si<limit){
                        si++;
                    }
                    si++;
                }
                document.add(information);
            }
        }

    }

    private void addLastPage(Document document, Date date, String er, String telma, String lieu) throws DocumentException, BadElementException, IOException, Exception {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{(float) 3 / 2, 5, (float) 3 / 2});

        PdfPCell cell;

       
        Phrase phrase;
        Paragraph paragraphe;
        Chunk underline;
        Paragraph information = new Paragraph();
        addEmptyLine(information, 2);

        underline = new Chunk("Reserve(s) :", boldFont);
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        information.add(underline);
        information.add(new Phrase(" Aucun", boldFont));

        addEmptyLine(information, 1);
        information.add(new Phrase("En foi de quoi, ce procès-verbal est établi pour servir et valoir"
                + " ce que de droit", normalFont));
        addEmptyLine(information, 2);
        information.add(new Phrase("Fait en troi(3) exemplaires", normalFont));
        addEmptyLine(information, 2);

        paragraphe = new Paragraph();
        paragraphe.add(new Phrase("Fait à " + lieu + ", le " + DateUtil.toLettre(date), normalFont));
        paragraphe.setAlignment(Element.ALIGN_RIGHT);

        document.add(information);
        document.add(paragraphe);
        information = new Paragraph();

        underline = new Chunk("Ont signé :", boldFont);
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        information.add(underline);
        addEmptyLine(information, 2);
        document.add(information);

        Chunk glue = new Chunk(new VerticalPositionMark());
        Paragraph p = new Paragraph("Pour Entreprise RANDRIANARISOA", boldFont);
//        addEmptyLine(p,3);

        p.add(new Chunk(glue));
        p.add(new Phrase("Pour telma", boldFont));
        p.add(new Chunk(glue));
        addEmptyLine(p, 6);

        p.add(new Phrase(er, boldFont));
        p.add(new Chunk(glue));
        p.add(new Phrase(telma, boldFont));
        p.add(new Chunk(glue));

        document.add(p);

    }

    private static void sautPage(Document document, int saut) throws DocumentException {
        for (int i = 0; i < saut; i++) {
            document.add(Chunk.NEXTPAGE);
        }
    }

    public void setNumberPage(PdfWriter writer,final HttpServletRequest servletRequest) {
        writer.setPageEvent(new PdfPageEventHelper() {
            @Override
            public void onStartPage(PdfWriter writer, Document document) {
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                try {
                    table.setWidths(new float[]{(float) 3 / 2, 5, (float) 3 / 2});
                } catch (DocumentException ex) {
                    Logger.getLogger(PVGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }

                PdfPCell cell=null;

                try {
                    cell = new PdfPCell(Image.getInstance(servletRequest.getSession().getServletContext().getRealPath("/")+"Archive/data/Logo/telma.jpg"));
                } catch (BadElementException ex) {
                    Logger.getLogger(PVGenerator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PVGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                cell.setRowspan(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(20, "Document ", boltTableFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Version \n 1.0", normalBoldTableFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setRowspan(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("PROCES VERBAL DE RECEPETION DEFINITIVE \n \n " + offre.getTicket() + " \n", boltTableFont));
                cell.setRowspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                try {
                    document.add(table);
                } catch (DocumentException ex) {
                    Logger.getLogger(PVGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                int pageNumber = writer.getPageNumber();
                String text = "Page " + pageNumber;
                Rectangle page = document.getPageSize();
                PdfPTable structure = new PdfPTable(1);
                PdfPCell c2 = new PdfPCell(new Paragraph(text));
                c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                c2.setBorder(PdfPCell.NO_BORDER);

                structure.addCell(c2);
                structure.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
                structure.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
            }

        });
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
