/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

/**
 *
 * @author Hobiana
 */
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import fonction.Fonction;


public class FacturePDF {
        Fonction f=new Fonction();
        private  String FILE = "C:/Users/Hobiana/Desktop/facture/facture_E_varotra.pdf";
        private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
        private static Font normalFont = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        private static Font header = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL,GrayColor.GRAYWHITE);
        private static Font redFont = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL, BaseColor.RED);
        private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
        private static Font bold = new Font(Font.FontFamily.COURIER, 12,Font.BOLD);
        private static Panier panier;
        private static Client client;
        private static int idfacture;

    public FacturePDF(Panier p,Client c,int idFacture) throws Exception {
        panier=p;
        client=c;
        this.idfacture=idFacture;
        
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

        private static void addTitlePage(Document document)
                        throws DocumentException {
                Paragraph preface = new Paragraph();
                // We add one empty line
                addEmptyLine(preface, 1);
                // Lets write a big header
                preface.add(new Paragraph("Facture  E-Varotra :", catFont));

                addEmptyLine(preface, 1);
               
                

                document.add(preface);
                // Start a new page
               // document.newPage();
               
               
        }

        private static void addContent(Document document) throws DocumentException {
                Paragraph preface = new Paragraph();
                // We add one empty line
                addEmptyLine(preface, 1);
                // Lets write a big header
                preface.add(new Paragraph("Date : "+Fonction.getDateDuJour(),normalFont));
                preface.setAlignment(Element.ALIGN_RIGHT);
                preface.add(new Paragraph("Facture  E-Varotra", catFont));
                
                addEmptyLine(preface, 1);
                
                preface.add(new Paragraph("Client : "+client.getNom(),normalFont));
                preface.add(new Paragraph("Reference : F"+String.format("%05d", idfacture),normalFont));
                
                addEmptyLine(preface, 1);
                document.add(preface);
                
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                table.setWidths(new float[] { (float)1/2,3,2,(float)3/2,2 });
                
                PdfPCell c1 = new PdfPCell(new Phrase("#",header));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.GRAY);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase("Product",header));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.GRAY);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Price(Ariary)",header));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.GRAY);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Quantity",header));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.GRAY);
                table.addCell(c1);
                table.setHeaderRows(1);
                
                c1 = new PdfPCell(new Phrase("Total(Ariary)",header));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.GRAY);
                table.addCell(c1);
                table.setHeaderRows(1);
                
            // donnee tableau
            int i=1;
            for (PanierArticle panier_art : panier.getList_art()) {
                c1 = new PdfPCell(new Phrase(Integer.toString(i),normalFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase(panier_art.getArt().getNomArticle(),normalFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(panier_art.getArt().getPrixString(),normalFont));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(panier_art.getQuantiteString(),normalFont));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(panier_art.getMontantString(),normalFont));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(c1);
                i++;
            }
                //TVA
                c1 = new PdfPCell(new Phrase("TVA",bold));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                c1.setColspan(4);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase(panier.getTVAString(),bold));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(c1);
                
                //remise
                c1 = new PdfPCell(new Phrase("DISCOUNT",bold));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                c1.setColspan(4);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase(panier.getRemiseString(),bold));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(c1);
                
                // total
                c1 = new PdfPCell(new Phrase("TOTAL",bold));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                c1.setColspan(4);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase(panier.getTotalRemiseTVA(),bold));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(c1);

                document.add(table);
                
                Paragraph p2 = new Paragraph(" ");
                addEmptyLine(p2, 1);
                document.add(p2);
               
                // pour le petit paragraphe net à payer
                PdfPTable table2 = new PdfPTable(1);
                table2.setWidthPercentage(100);
                
                PdfPCell c2 = new PdfPCell(new Paragraph("Net to pay : "+panier.getTotalRemiseTVA()+" Ar",redFont));
                c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                c2.setBorder(PdfPCell.NO_BORDER);
                table2.addCell(c2);
                
                document.add(table2);
                

        }

        private static void createTable(Section subCatPart)
                        throws BadElementException {
                PdfPTable table = new PdfPTable(3);

                PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Table Header 2"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Table Header 3"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                table.setHeaderRows(1);

                table.addCell("1.0");
                table.addCell("1.1");
                table.addCell("1.2");
                table.addCell("2.1");
                table.addCell("2.2");
                table.addCell("2.3");

                subCatPart.add(table);

        }

        private static void createList(Section subCatPart) {
                List list = new List(true, false, 10);
                list.add(new ListItem("First point"));
                list.add(new ListItem("Second point"));
                list.add(new ListItem("Third point"));
                subCatPart.add(list);
        }

        private static void addEmptyLine(Paragraph paragraph, int number) {
                for (int i = 0; i < number; i++) {
                        paragraph.add(new Paragraph(" "));
                }
        }
}
