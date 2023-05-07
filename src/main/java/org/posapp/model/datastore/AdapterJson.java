package org.posapp.model.datastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.posapp.model.Barang;
import org.posapp.model.FixedBill;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class AdapterJson implements AdapterData<FixedBill> {

    @Override
    public ArrayList<FixedBill> read(File file) throws Exception {
        Gson gson = new Gson();
        FileReader reader = new FileReader(file);
        Type type = new TypeToken<ArrayList<FixedBill>>() {}.getType();
        ArrayList<FixedBill> list = gson.fromJson(reader, type);
        reader.close();
        return list;
    }

    @Override
    public void write(ArrayList<FixedBill> list, File file) throws Exception {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(file);
        gson.toJson(list, writer);
        writer.close();
    }


//    public static void main(String[] args){
//
//        AdapterJson adapterJson = new AdapterJson();
//        Barang barang1 = new Barang(1, "test", "testkategori", 3, 300, 300, "path");
//        Barang barang2 = new Barang(1, "test", "testkategori", 3, 300, 300, "path");
//        HashMap<Barang, Integer> hash1 = new HashMap<>();
//        hash1.put(barang1, 100);
//        hash1.put(barang2, 200);
//        TestObject test1 = new FixedBill(hash1);
//        TestObject test2 = new FixedBill(hash1);
//        ArrayList<TestObject> listOfTest = new ArrayList<>();
//        listOfTest.add(test1);
//        listOfTest.add(test2);
//        try {
//            adapterJson.write(listOfTest, new File("test.json"));
//            ArrayList<TestObject> listRead = adapterJson.read(new File("test.json"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}