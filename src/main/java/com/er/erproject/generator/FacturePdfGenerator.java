/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.generator;
import com.er.erproject.data.PathData;
import com.er.erproject.model.Offre;
import com.er.erproject.model.Ventillation;
import com.er.erproject.model.VentillationModel;
import com.er.erproject.util.DateUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author diary
 */
public class FacturePdfGenerator {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author diary
 */

    private String FILE = PathData.PATH_PDF_FACTURE_LISTE;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
    private static Font smallFont = new Font(Font.FontFamily.COURIER, 9, Font.NORMAL);
    private static Font header = new Font(Font.FontFamily.COURIER, 9, Font.NORMAL, GrayColor.GRAYWHITE);
    private static Font redFont = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font bold = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

    private List<Ventillation> ventillations;

    public String getFILE() {
        return FILE;
    }

    public void setFILE(String FILE) {
        this.FILE = FILE;
    }

    /* public AppelClientPdf(Client client, List<Appel> appels) {
        this.client = client;
        this.appels = appels;
    }*/
    public FacturePdfGenerator() {
    }

    public List<Ventillation> getVentillations() {
        return ventillations;
    }

    public void setVentillations(List<Ventillation> ventillations) {
        this.ventillations = ventillations;
    }

    private static void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Liste des factures : ", catFont));
        addEmptyLine(preface, 1);
        document.add(preface);

    }

    private void createTable(Document document) throws Exception {
        Paragraph information = new Paragraph();
        information.add(new Paragraph("Date de creation : " + DateUtil.convertAllNormal(Calendar.getInstance().getTime()), normalFont));
        addEmptyLine(information, 2);
        
        information.add(new Paragraph("Liste des factures", catFont));
        addEmptyLine(information, 1);
        document.add(information);
        
        PdfPTable table = new PdfPTable(9);

        table.setWidthPercentage(100);
        table.setWidths(new float[]{(float) 1 / 2, 2, 2, 2, 2, 2, 2,2,2});

        PdfPCell c1 = new PdfPCell(new Phrase("#", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Reference du facture", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ticket", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nom du projet", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nom du paiement", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Mode de paiment", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Pourcentage de paiement", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Date prévu du paiement", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Date du paiement", header));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);

       
        //document.add(table);

        for (int i = 0; i < ventillations.size(); i++) {
            Ventillation ventilation = ventillations.get(i);
            
            c1 = new PdfPCell(new Phrase(Integer.toString(i+1), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(ventilation.getAllReference()), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

          
            c1 = new PdfPCell(new Phrase(ventilation.getSoumission().getOffre().getTicket(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(ventilation.getSoumission().getOffre().getNomProjet(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(ventilation.getPayementName(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(ventilation.getTypeDescription(), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(String.valueOf(ventilation.getPourcentage()+"%"), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(String.valueOf(DateUtil.convertNormal(ventilation.getDate())), smallFont));
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c1);
            
            if(ventilation.getDatepaiement()!=null){
                c1 = new PdfPCell(new Phrase(DateUtil.convertNormal(ventilation.getDatepaiement()), smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }else{
                c1 = new PdfPCell(new Phrase("Non-payé", smallFont));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        }

        document.add(table);
    }

    private static void addEmptyLine(Paragraph paragraphe, int taille) {
        for (int i = 0; i < taille; i++) {
            paragraphe.add(new Phrase("\n"));
        }

    }

    private static void addMetaData(Document document) {
        document.addTitle("Liste des factures PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("E.R System");
        document.addCreator("E.R System");
    }
    
    public void setNumberPage(PdfWriter writer){
        writer.setPageEvent(new PdfPageEventHelper() {
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
   
    public FacturePdfGenerator(List<Ventillation> ventillation,HttpServletRequest requestServlet) throws Exception {
        this.setVentillations(ventillation);

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(requestServlet.getSession().getServletContext().getRealPath("/")+FILE));
        setNumberPage(writer);

        document.open();
        addMetaData(document);
        this.createTable(document);
        document.close();
    }

    private static void sautPage(Document document, int saut) throws DocumentException {
        for (int i = 0; i < saut; i++) {
            document.add(Chunk.NEXTPAGE);
        }
    }


}
