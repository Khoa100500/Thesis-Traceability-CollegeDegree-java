package com.iupv.demo.util.component;

import com.iupv.demo.report.PdfHeadersDto;
import com.iupv.demo.score.StudentScoreDto;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.texts.PdfTextExtractOptions;
import com.spire.pdf.texts.PdfTextExtractor;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;
import org.springframework.stereotype.Component;

import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

//"src/main/java/com/iupv/demo/util/Resources/KetQuaNhapDiem (18).pdf"
@Component
public class DataComponent {


    private Integer StringToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String removeAllSpaces(String input) {
        return input.replace(" ", "");
    }

    private String removeExtraSpaces(String text) {
        return text.trim().replaceAll(" +", " ");
    }

    public Set<StudentScoreDto> extractStudentScores(PdfDocument pdfDocument) {
        //Create a PdfTableExtractor instance
        PdfTableExtractor extractor = new PdfTableExtractor(pdfDocument);
        int numberOfPages = pdfDocument.getPages().getCount();
        Set<StudentScoreDto> set = new HashSet<>();

        for (int i = 0; i < numberOfPages; i++) {

//          Extract tables from the every pages
            PdfTable[] pdfTables = extractor.extractTable(i);

            //If any tables are found
            if (pdfTables != null) {

                //Loop through the tables
                for (PdfTable pdfTable : pdfTables) {

                    //Loop through the rows in the current table
                    for (int rowNum = 1; rowNum < pdfTable.getRowCount(); rowNum++) {
                        String[] data = new String[pdfTable.getColumnCount() - 1];

                        //Loop through the columns in the current table
                        for (int colNum = 1; colNum < pdfTable.getColumnCount(); colNum++) {

                            //Extract data from the current table cell
                            String text = pdfTable.getText(rowNum, colNum);
                            if (text == null || text.isEmpty()) {
                                data[colNum - 1] = "No info";
                            } else {
                                data[colNum - 1] = text;
                            }

                        }
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        StudentScoreDto s = new StudentScoreDto(data[0], data[1], LocalDate.parse(data[2], formatter), data[3],
                                StringToInt(data[4]), StringToInt(data[5]), StringToInt(data[6]), data[7]);
                        set.add(s);
                    }
                }
            }
        }
        return set;
    }


    private PdfTextExtractOptions createExtractOptions(Rectangle2D options) {
        PdfTextExtractOptions option = new PdfTextExtractOptions();
        option.setExtractArea(options);
        return option;
    }

    private String extractAfterColon(String input) {
        String delimiter = ": ";
        int index = input.indexOf(delimiter);
        if (index != -1) {
            return input.substring(index + delimiter.length());
        }
        return "";
    }

    private String extractText(PdfPageBase page, Rectangle2D options) {
        PdfTextExtractor textExtractor = new PdfTextExtractor(page);
        String text = removeExtraSpaces(textExtractor.extract(createExtractOptions(options)));

        return extractAfterColon(text);
    }

    private String[] splitByWord(String input) {
        return input.split("nhóm");
    }


    public PdfHeadersDto extractHeaders(PdfDocument pdfDocument) {

        PdfPageBase page = pdfDocument.getPages().get(0);
        Rectangle2D[] options = {new Rectangle2D.Float(42f, 79.7f, 325.9f, 13.4f),
                new Rectangle2D.Float(42f, 94.1f, 325.9f, 13.4f),
                new Rectangle2D.Float(42f, 108.5f, 325.9f, 13.4f),
                new Rectangle2D.Float(394.2f, 94.1f, 325.9f, 13.4f),
                new Rectangle2D.Float(394.2f, 108.5f, 325.9f, 13.4f)
        };

        String courseName = extractText(page, options[0]);
        String[] courseIdGroup = splitByWord(extractText(page, options[1]));
        String lecturerName = extractText(page, options[2]);
        String lecturerId = extractText(page, options[3]);
        String hpUNIT = extractText(page, options[4]);
        String courseId = removeAllSpaces(courseIdGroup[0]);
        String groupId = removeAllSpaces( courseIdGroup[1]);

        return new PdfHeadersDto(courseId, courseName, groupId, hpUNIT, lecturerId, lecturerName);
    }
}
