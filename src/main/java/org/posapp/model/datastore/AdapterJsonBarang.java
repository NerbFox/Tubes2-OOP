package org.posapp.model.datastore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.posapp.model.Barang;
import org.posapp.model.FixedBill;
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

public class AdapterJsonBarang implements AdapterData<Barang> {
    private final Gson gson;

    public AdapterJsonBarang() {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(ArrayList.class, new BarangListTypeAdapter());
//        gsonBuilder.registerTypeAdapter(HashMap.class, new HashMapTypeAdapter());
        gsonBuilder.setPrettyPrinting();
        this.gson = gsonBuilder.create();
    }

    @Override
    public ArrayList<Barang> read(File file) throws Exception {
//        Gson gson = new Gson();
        FileReader reader = new FileReader(file);
        Type type = new TypeToken<ArrayList<Barang>>() {}.getType();
        ArrayList<Barang> list = gson.fromJson(reader, type);
        reader.close();
        return list;
    }

    public void write(ArrayList<Barang> list, File file) throws Exception {
//        Gson gson = new Gson();
        FileWriter writer = new FileWriter(file);
        gson.toJson(list, writer);
        writer.close();
    }

//        public static void main(String[] args){
//
//        AdapterJsonBarang adapterJson = new AdapterJsonBarang();
//        Barang barang1 = new Barang(1, "test", "testkategori", 3, (float)300.1, (float)300.3, "path");
//        Barang barang2 = new Barang(2, "testtest", "testkategori2222", 6, (float)600.2, (float)800.4, "path22");
////        HashMap<Barang, Integer> hash1 = new HashMap<>();
////        HashMap<Barang, Integer> hash2 = new HashMap<>();
////        hash1.put(barang1, 1000);
////        hash1.put(barang2, 2000);
////        hash2.put(barang2, 3000);
////        hash2.put(barang1, 4000);
//
////        FixedBill test1 = new FixedBill(hash1, 1);
////        FixedBill test2 = new FixedBill(hash2, 2);
//        ArrayList<Barang> listOfTest = new ArrayList<>();
//        listOfTest.add(barang1);
//        listOfTest.add(barang2);
//        try {
//            adapterJson.write(listOfTest, new File("./src/main/resources/datastore/test.json"));
//            ArrayList<Barang> listRead = adapterJson.read(new File("./src/main/resources/datastore/test.json"));
//            System.out.println("gg sekali");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}