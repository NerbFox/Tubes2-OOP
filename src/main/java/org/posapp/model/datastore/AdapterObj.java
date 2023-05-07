package org.posapp.model.datastore;

import org.posapp.model.FixedBill;
import org.posapp.model.datastore.AdapterData;
import org.posapp.model.datastore.TestObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdapterObj implements AdapterData<FixedBill> {
    @Override
    public ArrayList<FixedBill> read(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ObjectList objectList = (ObjectList) ois.readObject();
        ArrayList<FixedBill> list = objectList.getList();
        ois.close();
        return list;
    }

    @Override
    public void write(ArrayList<FixedBill> list, File file) throws Exception {
        ObjectList objectList = new ObjectList();
        objectList.setList(list);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
    }

//    public static void main(String[] args){
//
//        AdapterObj adapterObj = new AdapterObj();
//
//        TestObject test1 = new TestObject();
//        TestObject test2 = new TestObject();
//        ArrayList<TestObject> listOfTest = new ArrayList<>();
//        listOfTest.add(test1);
//        listOfTest.add(test2);
//
//
//        try {
//            File objFile = new File("test.obj");
//            adapterObj.write(listOfTest, objFile);
//            // Read data from OBJ file
//            ArrayList<TestObject> listRead = adapterObj.read(objFile);
//            System.out.println(listRead);
//            System.out.println(listRead.get(0).listOfPair);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}