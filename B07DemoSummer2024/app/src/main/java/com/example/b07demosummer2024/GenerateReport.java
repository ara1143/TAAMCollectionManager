package com.example.b07demosummer2024;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenerateReport {

    public void generateReportByLotNumber(String lotNumber, List<Collection> collections) {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File pdfFile = new File(pdfPath, "report_by_lot_number.pdf");

        try {
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Report for Lot Number: " + lotNumber));
            document.add(new Paragraph(" "));

            for (Collection collection : collections) {
                if (collection.lotNumber.equals(lotNumber)) {
                    document.add(new Paragraph("Name: " + collection.name));
                    document.add(new Paragraph("Lot Number: " + collection.lotNumber));
                    document.add(new Paragraph("Category: " + collection.category));
                    document.add(new Paragraph("Period: " + collection.period));
                    document.add(new Paragraph("Description: " + collection.description));
                    document.add(new Paragraph("Media URL: " + collection.mediaUrl));
                    document.add(new Paragraph(" "));
                }
            }

            document.close();
            Log.d("GenerateReport", "PDF generated successfully");

        } catch (IOException e) {
            Log.e("GenerateReport", "Error generating PDF", e);
        }
    }
}

