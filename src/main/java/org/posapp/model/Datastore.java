package org.posapp.model;

import lombok.Data;
import java.util.ArrayList;

@Data
public class Datastore {
    private static Datastore instance = new Datastore();
    private ArrayList<Barang> arrBarang;
    private ArrayList<FixedBill> arrFixedBill;
    private ArrayList<Customer> arrCustomer;

    private Datastore() {
        // nnti isi attribut menggunakan parser
        this.arrBarang = new ArrayList<>();
        this.arrFixedBill = new ArrayList<>();
        this.arrCustomer = new ArrayList<>();
    }

    public static Datastore getInstance() {
        return instance;
    }
}