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

        this.hardCodeCustomer();
    }

    public static Datastore getInstance() {
        return instance;
    }

    // semua hard code nnti diapus diganti dengan parser
    private void hardCodeCustomer() {
        Customer cust1 = new NonMember(1, 1);
        Customer cust2 = new Member(2, "Bob", "0812241231", new ArrayList<Integer>(Arrays.asList(1,2,3)), 2000, false, false);
        Customer cust3 = new Member(3, "Charlie", "089623734", new ArrayList<Integer>(Arrays.asList(4,5,6)), 3000, false, true);
        Customer cust4 = new Member(4, "Dodo", "082136237", new ArrayList<Integer>(Arrays.asList(7,8,9)), 4000, true, true);
        arrCustomer.add(cust1);
        arrCustomer.add(cust2);
        arrCustomer.add(cust3);
        arrCustomer.add(cust4);
    }
}
