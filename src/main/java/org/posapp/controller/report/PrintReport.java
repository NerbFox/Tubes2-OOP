package org.posapp.controller.report;

import org.posapp.model.Barang;
import org.posapp.model.FixedBill;
import org.posapp.model.datastore.Datastore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PrintReport implements Runnable {
    PrintablePDF item;

    public PrintReport(PrintablePDF _item){
        item = _item;
    }

    @Override
    public void run() {
        item.createDocument();
    }

    public static void main(String[] args){
        Barang[] data = {
                new Barang(1, "Hentai", "Komik", 3, 33000F, 30000F, "url"),
                new Barang(2, "JAV", "DvD", 3, 200000F, 145000F, "url"),
                new Barang(3, "Doujin", "Komik", 3, 13000F, 11321F, "url"),
                new Barang(1, "Hentai", "Komik", 3, 33000F, 30000F, "url"),
                new Barang(2, "JAV", "DvD", 3, 200000F, 145000F, "url"),
                new Barang(3, "Doujin", "Komik", 3, 13000F, 11321F, "url"),
                new Barang(1, "Hentai", "Komik", 3, 33000F, 30000F, "url"),
                new Barang(2, "JAV", "DvD", 3, 200000F, 145000F, "url"),
                new Barang(3, "Doujin", "Komik", 3, 13000F, 11321F, "url"),
        };

        HashMap<Barang, Integer> dataBill = new HashMap<>();
        dataBill.put(data[0], 2);
        dataBill.put(data[1], 1);
        dataBill.put(data[2], 4);

        FixedBill bill = new FixedBill(dataBill, 10);
        ArrayList<FixedBill> _data = new ArrayList<>(Arrays.asList(bill, bill, bill, bill, bill, bill));

        Datastore.getInstance().setArrFixedBill(_data);

        PrintablePDF pdf = new DocLaporanKeuangan();
        Thread t = new Thread(new PrintReport(pdf));

        t.start();
    }
}
