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
    private static Datastore instance;
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
    }

    public static Datastore getInstance() {
        if (instance == null) {
            instance = new Datastore();
        }
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

        Customer cust1 = new NonMember(1, 10);
        Customer cust2 = new Member(2, nonFix1, "Bob", "0812241231", new ArrayList<Integer>(Arrays.asList(1,2,3)), 2000F, false, false);
        Customer cust3 = new Member(3, nonFix2,"Charlie", "089623734", new ArrayList<Integer>(Arrays.asList(4,5,6)), 3000F, false, true);
        Customer cust4 = new Member(4, nonFix1, "Dodo", "082136237", new ArrayList<Integer>(Arrays.asList(7,8,9)), 4000F, true, true);

        ArrayList<Customer> temp = new ArrayList<>();
        temp.add(cust1);
        temp.add(cust2);
        temp.add(cust3);
        temp.add(cust4);

        // Initialize data file (obj)
        AdapterData newAdapter = AdapterFactory.createAdapter("./src/main/resources/datastore/customer.obj");
        File fileObj = new File("./src/main/resources/datastore/customer.obj");
        try {
            newAdapter.write(temp, fileObj);
            arrCustomer = newAdapter.read(fileObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        arrCustomer.add(cust1);
//        arrCustomer.add(cust2);
//        arrCustomer.add(cust3);
//        arrCustomer.add(cust4);
    }

    private void hardCodeBarang() {
        Barang b1 = new Barang(1, "Teh botol", "Minuman", 4, 5000F, 4000F, "file:./src/main/resources/image/50.png");
        Barang b2 = new Barang(2, "Pringless", "Makanan", 14, 13000F, 10000F, "file:./src/main/resources/image/50.png");
        Barang b3 = new Barang(3, "Kntl Manis", "Minuman", 9, 8000F, 6000F, "file:./src/main/resources/image/50.png");
        Barang b4 = new Barang(4, "Susu UHT Frisian Flag", "Minuman", 19, 17000F, 14000F, "file:./src/main/resources/image/50.png");
        Barang b5 = new Barang(5, "Regal", "Makanan", 11, 7000F, 9000F, "file:./src/main/resources/image/50.png");
        Barang b6 = new Barang(6, "Silver Queen", "Makanan", 21, 19000F, 15000F, "file:./src/main/resources/image/50.png");


        ArrayList<Barang> barangList = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6));
//        arrBarang.addAll(barangList);
        // Initialize data file (obj)
        AdapterData newAdapter = AdapterFactory.createAdapter("./src/main/resources/datastore/barang.obj");
        File fileObj = new File("./src/main/resources/datastore/barang.obj");
        try {
            newAdapter.write(barangList, fileObj);
            arrBarang = newAdapter.read(fileObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void hardCodeBill() {
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
        FixedBill f2 = new FixedBill(nonFix2.getMapBarang(), 2);
        FixedBill f3 = new FixedBill(nonFix3.getMapBarang(), 3);

        FixedBill f4 = new FixedBill(nonFix4.getMapBarang(), 4);
        FixedBill f5 = new FixedBill(nonFix1.getMapBarang(), 5);
        FixedBill f6 = new FixedBill(nonFix2.getMapBarang(), 6);

        FixedBill f7 = new FixedBill(nonFix3.getMapBarang(), 7);
        FixedBill f8 = new FixedBill(nonFix4.getMapBarang(), 8);
        FixedBill f9 = new FixedBill(nonFix1.getMapBarang(), 9);

        FixedBill f10 = new FixedBill(nonFix1.getMapBarang(), 10);

        ArrayList<FixedBill> FixedBillList = new ArrayList<>(Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10));

        AdapterData newAdapter = AdapterFactory.createAdapter("./src/main/resources/datastore/fixedbill.obj");
        File fileObj = new File("./src/main/resources/datastore/fixedbill.obj");
        try {
            newAdapter.write(FixedBillList, fileObj);
            arrFixedBill = newAdapter.read(fileObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        arrFixedBill = new ArrayList<>();
//        arrFixedBill.addAll(FixedBillList );
    }
}


