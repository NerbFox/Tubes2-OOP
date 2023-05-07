package org.posapp.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Datastore {
    private static Datastore instance = new Datastore();
    private ArrayList<Barang> arrBarang;
    private ArrayList<FixedBill> arrFixedBill;
    private ArrayList<Customer> arrCustomer;

    private Datastore() {
        // nnti isi attribut menggunakan parser
        this.arrBarang = new ArrayList<Barang>(Arrays.asList(
                new Barang(1, "rokok", "obat", 10, 1,3, "url"),
                new Barang(2, "teh", "minuman", 11, 1,3, "url"),
                new Barang(3, "kopi", "minuman", 16, 1,3, "url"),
                new Barang(4, "tempe", "makanan", 0, 1,3, "url")
        ));
        this.arrFixedBill = new ArrayList<>();
        this.arrCustomer = new ArrayList<>();
    }

    public static Datastore getInstance() {
        return instance;
    }
}