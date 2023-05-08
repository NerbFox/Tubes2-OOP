package org.posapp.model.datastore;

import org.posapp.model.FixedBill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdapterObjFixedBill implements AdapterData<FixedBill> {
    @Override
    public ArrayList<FixedBill> read(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ObjectList<FixedBill> objectList = (ObjectList<FixedBill>) ois.readObject();
        ArrayList<FixedBill> list = objectList.getList();
        ois.close();
        return list;
    }

    @Override
    public void write(ArrayList<FixedBill> list, File file) throws Exception {
        ObjectList<FixedBill> objectList = new ObjectList<FixedBill>();
        objectList.setList(list);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
    }

//    public static void main(String[] args){
//
//        AdapterObjFixedBill adapterObj = new AdapterObjFixedBill();
//
//        Barang barang1 = new Barang(1, "test", "testkategori", 3, 300, 300, "path");
//        Barang barang2 = new Barang(2, "testtest", "testkategori2222", 6, 600, 800, "path22");
//        HashMap<Barang, Integer> hash1 = new HashMap<>();
//        HashMap<Barang, Integer> hash2 = new HashMap<>();
//        hash1.put(barang1, 1000);
//        hash1.put(barang2, 2000);
//        hash2.put(barang2, 3000);
//        hash2.put(barang1, 4000);
//
//        FixedBill test1 = new FixedBill(hash1, 1);
//        FixedBill test2 = new FixedBill(hash2, 2);
//        ArrayList<FixedBill> listOfTest = new ArrayList<>();
//        listOfTest.add(test1);
//        listOfTest.add(test2);
//
//        try {
//            File objFile = new File("./src/main/resources/datastore/test.obj");
//            adapterObj.write(listOfTest, objFile);
//            // Read data from OBJ file
//            ArrayList<FixedBill> listRead = adapterObj.read(objFile);
//            System.out.println(listRead);
//            System.out.println(listRead.get(0).getMapBarang().keySet());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}