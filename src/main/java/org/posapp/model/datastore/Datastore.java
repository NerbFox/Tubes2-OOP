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
    private String folderPath;
    private ArrayList<Barang> arrBarang;
    private ArrayList<FixedBill> arrFixedBill;
    private ArrayList<Customer> arrCustomer;

    private Datastore() {
        // nnti isi attribut menggunakan parser
        this.arrBarang = new ArrayList<Barang>();
        this.arrFixedBill = new ArrayList<FixedBill>();
        this.arrCustomer = new ArrayList<Customer>();
        this.folderPath = "./src/main/resources/datastore";
        loadData();
    }

    public static Datastore getInstance() {
        if (instance == null) {
            instance = new Datastore();
        }
        return instance;
    }


    private void loadData() {
        AdapterData newAdapterCustomer = AdapterFactory.createAdapter(String.format("%s/customer.obj", folderPath));
        File fileObjCustomer = new File(String.format("%s/customer.obj", folderPath));
        try {
            arrCustomer = newAdapterCustomer.read(fileObjCustomer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AdapterData newAdapterBarang = AdapterFactory.createAdapter(String.format("%s/barang.obj", folderPath));
        File fileObjBarang = new File(String.format("%s/barang.obj", folderPath));
        try {
            arrBarang = newAdapterBarang.read(fileObjBarang);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AdapterData newAdapterFixedBill = AdapterFactory.createAdapter(String.format("%s/fixedbill.obj", folderPath));
        File fileObjFixedBill = new File(String.format("%s/fixedbill.obj", folderPath));
        try {
            arrFixedBill = newAdapterFixedBill.read(fileObjFixedBill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        AdapterData newAdapterCustomer = AdapterFactory.createAdapter(String.format("%s/customer.obj", folderPath));
        File fileObjCustomer = new File(String.format("%s/customer.obj", folderPath));
        try {
            newAdapterCustomer.write(arrCustomer, fileObjCustomer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AdapterData newAdapterBarang = AdapterFactory.createAdapter(String.format("%s/barang.obj", folderPath));
        File fileObjBarang = new File(String.format("%s/barang.obj", folderPath));
        try {
            newAdapterBarang.write(arrBarang, fileObjBarang);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AdapterData newAdapterFixedBill = AdapterFactory.createAdapter(String.format("%s/fixedbill.obj", folderPath));
        File fileObjFixedBill = new File(String.format("%s/fixedbill.obj", folderPath));
        try {
            newAdapterBarang.write(arrFixedBill, fileObjFixedBill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeDirectory(String newFolderPath) {
        saveData();
        File fileCustomer = new File(String.format("%s/customer.obj", folderPath));
        File fileBarang = new File(String.format("%s/barang.obj", folderPath));
        File fileFixedBill = new File(String.format("%s/fixedbill.obj", folderPath));

        if (fileCustomer.delete()) {
            System.out.println("File customer deleted successfully!");
        } else {
            System.out.println("Delete failed.");
        }

        if (fileBarang.delete()) {
            System.out.println("File barang deleted successfully!");
        } else {
            System.out.println("Delete failed.");
        }

        if (fileFixedBill.delete()) {
            System.out.println("File fixed bill deleted successfully!");
        } else {
            System.out.println("Delete failed.");
        }

        this.folderPath = newFolderPath;
        saveData();
    }
}


