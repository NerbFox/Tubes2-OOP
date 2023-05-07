package org.posapp.controller.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.posapp.model.Barang;
import org.posapp.model.FixedBill;
import org.posapp.model.datastore.Datastore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class DocLaporanKeuangan implements PrintablePDF{
    private final List<FixedBill> data;

    public DocLaporanKeuangan() {
        data = Datastore.getInstance().getArrFixedBill();
    }
    @Override
    public void createDocument() {
        Document doc = new Document();
        try {
            // Generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Sample.pdf"));
            // Open the PDF
            doc.open();
            // Sleep for 10 seconds
            Thread.sleep(10);

            Paragraph title = new Paragraph("LAPORAN KEUANGAN TOKO JAV (18+)", new Font(Font.FontFamily.COURIER, 20, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);

            Paragraph subTitle = new Paragraph("\nDaftar Barang : ", new Font(Font.FontFamily.COURIER, 14, Font.NORMAL));
            doc.add(title);
            doc.add(subTitle);

            float totalReceive = 0;
            float totalCost = 0;
            float totalProfit = 0;

            for (FixedBill item : data) {
                Paragraph billID = new Paragraph("Bill ID : " + item.getIdFixedBill(), new Font(Font.FontFamily.COURIER, 14, Font.NORMAL));
                billID.setIndentationLeft(10);
                doc.add(billID);

                float totalItemReceive = 0;
                float totalItemCost = 0;
                float totalItemProfit = 0;
                for (Map.Entry<Barang, Integer> entry : item.getMapBarang().entrySet()) {
                    Barang barang = entry.getKey();
                    int jumlah = entry.getValue();

                    float totalJualBarang = barang.getHargaJual() * jumlah;
                    float totalBeliBarang = barang.getHargaBeli() * jumlah;
                    float totalProfitBarang = totalJualBarang - totalBeliBarang;

                    Paragraph barangLabel = new Paragraph(String.format("> %s\n %.2f x %d : %.2f", barang.getNama(), barang.getHargaJual(), jumlah, totalJualBarang), new Font(Font.FontFamily.COURIER, 12, Font.NORMAL));
                    barangLabel.setIndentationLeft(20);

                    totalItemReceive += totalJualBarang;
                    totalItemCost += totalBeliBarang;
                    totalItemProfit += totalProfitBarang;

                    doc.add(barangLabel);
                }
                totalReceive += totalItemReceive;
                totalCost += totalItemCost;
                totalProfit += totalItemProfit;

                Paragraph totalBillLabel = new Paragraph(String.format("Total Pendapatan : %.2f\nTotal Pengeluaran : %.2f\nTotal Profit : %.2f", totalItemReceive, totalItemCost, totalItemProfit), new Font(Font.FontFamily.COURIER, 12, Font.NORMAL));
                totalBillLabel.setAlignment(Element.ALIGN_RIGHT);
                doc.add(totalBillLabel);
            }
            float percent = 100 * totalProfit/totalCost;

            Paragraph totalLabel = new Paragraph(String.format("\n\nTotal Pemasukan : %.2f\nTotal Pengeluaran : %.2f\nTotal Profit : %.2f (%.2f %%)", totalReceive, totalCost, totalProfit, percent), new Font(Font.FontFamily.COURIER, 14, Font.BOLDITALIC));
            totalLabel.setAlignment(Element.ALIGN_LEFT);
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
