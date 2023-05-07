package org.posapp.model.datastore;

import lombok.Data;
import org.posapp.model.Barang;
import org.posapp.model.Customer;
import org.posapp.model.FixedBill;

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
    }

    public static Datastore getInstance() {
        return instance;
    }


}