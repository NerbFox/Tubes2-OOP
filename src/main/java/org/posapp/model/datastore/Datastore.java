package org.posapp.model.datastore;

import lombok.Data;
import org.posapp.model.*;

import java.io.IOException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.FileWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;

@Data
public class Datastore {
    private static Datastore instance = new Datastore();
    private ArrayList<Barang> arrBarang;
    private ArrayList<FixedBill> arrFixedBill;
    private ArrayList<Customer> arrCustomer;

    private Datastore() {
        // nnti isi attribut menggunakan parser
        this.arrBarang = new ArrayList<Barang>();
        this.arrFixedBill = new ArrayList<FixedBill>();
        this.arrCustomer = new ArrayList<Customer>();

        hardCodeCustomer();
        hardCodeBarang();
        hardCodeBill();
    }

    public static Datastore getInstance() {
        return instance;
    }
    // semua hard code nnti diapus diganti dengan parser

    private void hardCodeCustomer() {
        NonFixedBill nonFix1 = new NonFixedBill();
        nonFix1.addIdBarang(1, 2);
        nonFix1.addIdBarang(2, 1);

        NonFixedBill nonFix2 = new NonFixedBill();
        nonFix1.addIdBarang(2, 3);
        nonFix1.addIdBarang(3, 2);

        Customer cust1 = new NonMember(1, 1);
        Customer cust2 = new Member(2, nonFix1, "Bob", "0812241231", new ArrayList<Integer>(Arrays.asList(1,2,3)), 2000, false, false);
        Customer cust3 = new Member(3, nonFix2,"Charlie", "089623734", new ArrayList<Integer>(Arrays.asList(4,5,6)), 3000, false, true);
        Customer cust4 = new Member(4, nonFix1, "Dodo", "082136237", new ArrayList<Integer>(Arrays.asList(7,8,9)), 4000, true, true);
        arrCustomer.add(cust1);
        arrCustomer.add(cust2);
        arrCustomer.add(cust3);
        arrCustomer.add(cust4);
    }

    private void hardCodeBarang() {
        Barang b1 = new Barang(1, "Teh botol", "Minuman", 4, 5000, 4000, "path");
        Barang b2 = new Barang(2, "Pringless", "Makanan", 14, 13000, 10000, "path");
        Barang b3 = new Barang(3, "Kntl Manis", "Minuman", 9, 8000, 6000, "path");
        Barang b4 = new Barang(4, "Susu UHT Frisian Flag", "Minuman", 19, 17000, 14000, "path");
        Barang b5 = new Barang(5, "Regal", "Makanan", 11, 7000, 9000, "path");
        Barang b6 = new Barang(6, "Silver Queen", "Makanan", 21, 19000, 15000, "path");

        arrBarang.add(b1);
        arrBarang.add(b2);
        arrBarang.add(b3);
        arrBarang.add(b4);
        arrBarang.add(b5);
        arrBarang.add(b6);
    }
    private void hardCodeBill() {
        NonFixedBill nonFix1 = new NonFixedBill();
        nonFix1.addIdBarang(1, 2);
        nonFix1.addIdBarang(2, 1);

        NonFixedBill nonFix2 = new NonFixedBill();
        nonFix2.addIdBarang(2, 3);
        nonFix2.addIdBarang(3, 2);

        NonFixedBill nonFix3 = new NonFixedBill();
        nonFix3.addIdBarang(3, 4);
        nonFix3.addIdBarang(4, 3);

        NonFixedBill nonFix4 = new NonFixedBill();
        nonFix4.addIdBarang(4, 5);
        nonFix4.addIdBarang(5, 4);

        FixedBill f1 = new FixedBill(nonFix1.getMapBarang(), 1);
        FixedBill f2 = new FixedBill(nonFix2.getMapBarang(), 1);
        FixedBill f3 = new FixedBill(nonFix3.getMapBarang(), 1);
        FixedBill f4 = new FixedBill(nonFix4.getMapBarang(), 1);

        arrFixedBill.add(f1);
        arrFixedBill.add(f2);
        arrFixedBill.add(f3);
        arrFixedBill.add(f4);
    }

}


