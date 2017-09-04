/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.generator;

import com.er.erproject.data.PathData;
import com.er.erproject.model.BonCommande;
import com.er.erproject.model.Offre;
import com.er.erproject.model.TacheModel;
import com.er.erproject.model.Ventillation;
import com.er.erproject.model.VentillationModel;
import com.er.erproject.util.ConvertionLettre;
import com.er.erproject.util.DateUtil;
import com.er.erproject.util.NumberUtil;
import com.er.erproject.util.UtilConvert;
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
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author diary
 */
public class FactureGenerator {
    private Offre offre;
    private VentillationModel ventillation;
    private BonCommande bc;
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    
    public BonCommande getBc() {
        return bc;
    }

    public void setBc(BonCommande bc) {
        this.bc = bc;
    }

    
    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public VentillationModel getVentillation() {
        return ventillation;
    }

    public void setVentillation(VentillationModel ventillation) {
        this.ventillation = ventillation;
    }
    
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

    public FactureGenerator(Offre offre, VentillationModel ventillation,BonCommande bc,String condition) throws Exception {
        this.setOffre(offre);
        this.setVentillation(ventillation);
        this.setBc(bc);
        this.setCondition(condition);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PathData.PATH_PDF_FACTURE));
        document.open();
        addMetaData(document);
        addContent(document);
        document.close();
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Facture_generator");
        document.addSubject("Facturation");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("E.R System");
        document.addCreator("E.R System");
    }

    private void addContent(Document document) throws DocumentException, BadElementException, IOException, Exception {
        Paragraph information = new Paragraph();

        Image img = Image.getInstance("C:/Users/diary/Documents/Develeppoment/Logo/ER_LOGO _min.jpg");
        information.add(img);
        addEmptyLine(information, 1);
        document.add(information);

        information = new Paragraph();
        information.add(new Phrase(bc.getService()+"- Bâtiment Ariane 5B RDC Zone GALAXY", normalFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("101 Antananarivo MADAGASCAR", normalFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("NIF : "+bc.getNif(), boldFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("Stat : "+bc.getStats(), boldFont));
        information.setAlignment(Element.ALIGN_RIGHT);
        document.add(information);

        information = new Paragraph();
        addEmptyLine(information, 2);
        if(ventillation.getPayementName()!=null&&ventillation.getPayementName().compareToIgnoreCase("")!=0){
            information.add(new Phrase("Object : Faturation "+ventillation.getPayementName()+" "+offre.getNomProjet()+" "+offre.getTicket(),boldFont));
            
        }else{
            information.add(new Phrase("Objet : "+offre.getNomProjet()+" "+offre.getTicket(), boldFont));
        }
        addEmptyLine(information, 1);
        information.add(new Phrase("Ref : "+ventillation.getAllReference(), boldFont));
        addEmptyLine(information, 1);
        information.add(new Phrase("Suivant BC N : "+bc.getNumeroBC(), boldFont));
        addEmptyLine(information, 2);
        information.add(new Phrase("Ref : "+bc.getReferenceInterieur(), boldFont));
        addEmptyLine(information, 2);
        document.add(information);

        PdfPTable table;
        table = new PdfPTable(5);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{6, 2,3, 1, 2});

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
        c1 = new PdfPCell(new Phrase("EFFECTUER", header));
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
        List<TacheModel> taches; 
        taches = offre.getTacheinitials().getTravaux();
        for (int i = 0; i < taches.size(); i++) {
            TacheModel tache = taches.get(i);
//            TacheModel tache = this.offre.getTacheinitials().getTravaux().get(i);

            c1 = new PdfPCell(new Phrase(tache.getCatalogue().getDesignation(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(tache.getQuantite()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(tache.getEffectuer()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(tache.getCatalogue().getPrixUnitaire()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(tache.getEffectuer()*tache.getCatalogue().getPrixUnitaire()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

        }
        taches = offre.getTacheSoumission().getTravaux();
        for (int i = 0; i < taches.size(); i++) {
            TacheModel tache = taches.get(i);
//            TacheModel tache = this.offre.getTacheinitials().getTravaux().get(i);

            c1 = new PdfPCell(new Phrase(tache.getCatalogue().getDesignation(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(tache.getQuantite()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(tache.getEffectuer()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(tache.getCatalogue().getPrixUnitaire()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(tache.getEffectuer()*tache.getCatalogue().getPrixUnitaire()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

        }
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);
        
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

        c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(offre.getStatInitial().getTotalEffectuer()), smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        if(offre.getSoumission().getRemise()!=0){
            c1 = new PdfPCell();
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(5);
            c1.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);

            c1 = new PdfPCell();
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(5);
            c1.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("REMISE " + offre.getSoumission().getRemise() + " %", boldFont));
            c1.setColspan(2);
            c1.setBorder(Rectangle.NO_BORDER);
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(offre.getStatInitial().getValeurRemise()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell();
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(5);
            c1.setBorder(Rectangle.NO_BORDER);
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

            c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(offre.getStatInitial().getTotalEffectuer() - offre.getStatInitial().getValeurRemise()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);
        }
        
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);
        
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("TVA "+offre.getSoumission().getTva()+"%", boldFont));
        c1.setColspan(2);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(offre.getStatInitial().getValeurTVA()), smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        
        c1 = new PdfPCell();
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        c1.setBorder(Rectangle.NO_BORDER);
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

        c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(offre.getStatInitial().getTotalTTC()), smallFont));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        
        if(ventillation.getPourcentage()!=100){
            c1 = new PdfPCell();
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(5);
            c1.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);

            c1 = new PdfPCell();
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setPadding(5);
            c1.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("MONTANT "+ventillation.getPayementName()+" "+ventillation.getPourcentage()+"%", boldFont));
            c1.setColspan(2);
            c1.setBorder(Rectangle.NO_BORDER);
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(UtilConvert.toMoney(NumberUtil.pourcentage(offre.getStatInitial().getTotalTTC(),ventillation.getPourcentage())), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(c1);
        }

        document.add(table);
        
        information = new Paragraph(); 
        information.add(new Phrase("Arrêté la présente facture à la somme de : "+ConvertionLettre.getLettre(offre.getStatInitial().getTotalTTC())+" Ariary ",smallFontBold));
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
        information.add(new Phrase(condition,extraSmallFont)); 
        addEmptyLine(information,1);
        information.add(new Phrase("Date d'échéance :",extraSmallFont));
        if(ventillation.getPourcentage()==100){
            information.add(new Phrase(DateUtil.toLettreWithoutDay(ventillation.getDate()),extraSmallFont));
        }
        img = Image.getInstance("C:/Users/diary/Documents/Develeppoment/Logo/Capture.JPG");
        img.setAbsolutePosition(document.right()/2-50,document.bottom());
//        writer.getDirectContent().addImage(image);
        
       
        information.add(img);
        document.add(information);
        
        
        
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
