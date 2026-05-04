package services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;

import models.User;

public class PDFExporter {
    public void exportUsers(List<User> users, File file) throws IOException {

        try (PdfDocument pdf = new PdfDocument(new PdfWriter(file));
             Document doc = new Document(pdf, PageSize.LETTER.rotate())) {

            doc.add(new Paragraph("Users Report")
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));

            float[] cols = {1, 3, 3, 2, 3};
            Table table = new Table(UnitValue.createPercentArray(cols))
                    .useAllAvailableWidth();

            String[] headers = {"#", "Name", "Email"};

            for (String h : headers) {
                table.addHeaderCell(new Cell()
                        .add(new Paragraph(h))
                        .setBackgroundColor(new DeviceGray(0.8f))
                        .setTextAlignment(TextAlignment.CENTER));
            }

            int i = 1;
            for (User u : users) {
                table.addCell(String.valueOf(i++));
                table.addCell(u.getName());
                table.addCell(u.getEmail());
            }

            doc.add(table);
        }
    }
}