package org.posapp.model.datastore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.posapp.model.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AdapterJsonCustomer implements AdapterData<Customer> {
    private final Gson gson;

    public AdapterJsonCustomer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(ArrayList.class, new CustomerListTypeAdapter());
//        gsonBuilder.registerTypeAdapter(HashMap.class, new HashMapTypeAdapter());
        gsonBuilder.setPrettyPrinting();
        this.gson = gsonBuilder.create();
    }

    @Override
    public ArrayList<Customer> read(File file) throws Exception {
//        Gson gson = new Gson();
        FileReader reader = new FileReader(file);
        Type type = new TypeToken<ArrayList<Customer>>() {}.getType();
        ArrayList<Customer> list = gson.fromJson(reader, type);
        reader.close();
        return list;
    }

    public void write(ArrayList<Customer> list, File file) throws Exception {
//        Gson gson = new Gson();
        FileWriter writer = new FileWriter(file);
        gson.toJson(list, writer);
        writer.close();
    }

    public static void main(String[] args){

        AdapterObjCustomer adapterObj = new AdapterObjCustomer();
        NonMember n1 = new NonMember(1, 2);
        HashMap<Integer, Integer> hash1 = new HashMap<>();
        hash1.put(1,2);
        hash1.put(2,3);
        Member n2 = new Member(3,new NonFixedBill(hash1), "namatest", "0821", new ArrayList<Integer>(), (float)500, false, false);
        Member n3 = new Member(4,new NonFixedBill(hash1), "namatestvip", "0821", new ArrayList<Integer>(), (float)600, false, true);
        ArrayList<Customer> listOfTest = new ArrayList<>();
        listOfTest.add(n1);
        listOfTest.add(n2);
        listOfTest.add(n3);

        try {
            File objFile = new File("./src/main/resources/datastore/customer.obj");
            adapterObj.write(listOfTest, objFile);
            // Read data from OBJ file
            ArrayList<Customer> listRead = adapterObj.read(objFile);
//            System.out.println(listRead);
//            System.out.println(listRead.get(1).getCurrentBill().getMapBarang());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}