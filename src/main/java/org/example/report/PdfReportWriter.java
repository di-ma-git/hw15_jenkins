package org.example.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.model.Citizen;

import java.io.FileOutputStream;
import java.util.List;

public class PdfReportWriter implements ReportWriter {

    private final String filePath;

    public PdfReportWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeReport(List<String> lines) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfPTable table = new PdfPTable(1);
            for (String line : lines) {
                table.addCell(line);
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
