package org.posapp.controller.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.posapp.model.Barang;
import org.posapp.model.FixedBill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class DocFixedBill implements PrintablePDF{
    private final FixedBill data;

    public DocFixedBill(FixedBill _data) {
        data = _data;
    }
    @Override
    public void createDocument() {
        Document doc = new Document();
        try {
            // Generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Bill.pdf"));
            // Open the PDF
            doc.open();
            // Sleep for 10 seconds
//            Thread.sleep(10000);
            Thread.sleep(10);

            Paragraph title = new Paragraph("TOKO JAV (18+)", new Font(Font.FontFamily.COURIER, 20, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);

            Paragraph subTitle = new Paragraph("\nBill No: " + data.getIdFixedBill().toString(), new Font(Font.FontFamily.COURIER, 16, Font.ITALIC));
            Paragraph barangHeading = new Paragraph("\nDaftar Barang : ", new Font(Font.FontFamily.COURIER, 14, Font.NORMAL));

            doc.add(title);
            doc.add(subTitle);
            doc.add(barangHeading);

            float totalBill = 0;
            for (Map.Entry<Barang, Integer> entry : data.getMapBarang().entrySet()) {
                Barang barang = entry.getKey();
                int jumlah = entry.getValue();

                float totalHargaBarang = barang.getHargaJual() * jumlah;

                Paragraph item = new Paragraph(String.format("> %s\n\t %.2f x %d : %.2f", barang.getNama(), barang.getHargaJual(), jumlah, totalHargaBarang), new Font(Font.FontFamily.COURIER, 12, Font.NORMAL));
                totalBill += totalHargaBarang;
                item.setIndentationLeft(20);

                doc.add(item);
            }
            Paragraph totalLabel = new Paragraph(String.format("Total Bill : %.2f", totalBill), new Font(Font.FontFamily.COURIER, 14, Font.NORMAL));
            totalLabel.setAlignment(Element.ALIGN_RIGHT);
            doc.add(totalLabel);

            // Close the PDF file
            doc.close();
            // Close the writer
            writer.close();
            System.out.println("PDF created.");
        } catch (DocumentException | FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
