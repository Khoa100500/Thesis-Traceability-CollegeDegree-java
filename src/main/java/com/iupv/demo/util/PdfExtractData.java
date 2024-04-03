package com.iupv.demo.util;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.texts.PdfTextExtractOptions;
import com.spire.pdf.texts.PdfTextExtractor;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

import java.awt.geom.Rectangle2D;


public class PdfExtractData {

    public static void main(String[] args) {

        //Load a sample PDF document
        PdfDocument pdf = new PdfDocument("src/main/java/com/iupv/demo/util/Resources/KetQuaNhapDiem (18).pdf");

        //Create a PdfTableExtractor instance
        PdfTableExtractor extractor = new PdfTableExtractor(pdf);

        //Extract tables from the first page
        PdfTable[] pdfTables  = extractor.extractTable(0);


        //If any tables are found
        if (pdfTables != null && pdfTables.length > 0) {

            //Loop through the tables
            for (int tableNum = 0; tableNum < pdfTables.length; tableNum++) {
                //Loop through the rows in the current table
                for (int rowNum = 0; rowNum < pdfTables[tableNum].getRowCount(); rowNum++) {
                    //Loop through the columns in the current table
                    for (int colNum = 0; colNum < pdfTables[tableNum].getColumnCount(); colNum++) {

                        //Extract data from the current table cell
                        String text = pdfTables[tableNum].getText(rowNum, colNum);
                        System.out.print(text + "   ");
                    }
                    System.out.println();
                }
            }
        }
        PdfPageBase page = pdf.getPages().get(0);
        PdfTextExtractor textExtractor = new PdfTextExtractor(page);
        PdfTextExtractOptions extractOptions1 = new PdfTextExtractOptions();
        PdfTextExtractOptions extractOptions2 = new PdfTextExtractOptions();
        PdfTextExtractOptions extractOptions3 = new PdfTextExtractOptions();
        PdfTextExtractOptions extractOptions4 = new PdfTextExtractOptions();
        PdfTextExtractOptions extractOptions5 = new PdfTextExtractOptions();
        extractOptions1.setExtractArea(new Rectangle2D.Float(42f, 79.7f, 325.9f, 13.4f));
        extractOptions2.setExtractArea(new Rectangle2D.Float(42f, 94.1f, 325.9f, 13.4f));
        extractOptions3.setExtractArea(new Rectangle2D.Float(42f, 108.5f, 325.9f, 13.4f));
        extractOptions4.setExtractArea(new Rectangle2D.Float(394.2f, 94.1f, 325.9f, 13.4f));
        extractOptions5.setExtractArea(new Rectangle2D.Float(394.2f, 108.5f, 325.9f, 13.4f));
        System.out.println(textExtractor.extract(extractOptions1));
        System.out.println(textExtractor.extract(extractOptions2));
        System.out.println(textExtractor.extract(extractOptions3));
        System.out.println(textExtractor.extract(extractOptions4));
        System.out.println(textExtractor.extract(extractOptions5));



    }
}
