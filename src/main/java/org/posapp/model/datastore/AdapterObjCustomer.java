package org.posapp.model.datastore;

import org.posapp.model.Customer;
import org.posapp.model.FixedBill;
import org.posapp.model.Member;
import org.posapp.model.NonMember;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdapterObjCustomer implements AdapterData<Customer> {
    @Override
    public ArrayList<Customer> read(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ObjectList<Customer> objectList = (ObjectList<Customer>) ois.readObject();
        ArrayList<Customer> list = objectList.getList();
        ois.close();
        return list;
    }

    @Override
    public void write(ArrayList<Customer> list, File file) throws Exception {
        ObjectList<Customer> objectList = new ObjectList<Customer>();
        objectList.setList(list);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
    }

//    public static void main(String[] args){
//
//        AdapterObjCustomer adapterObj = new AdapterObjCustomer();
//        NonMember n1 = new NonMember(1, 2);
//        HashMap<Integer, Integer> hash1 = new HashMap<>();
//        hash1.put(1,2);
//        hash1.put(2,3);
//        Member n2 = new Member(3,new NonFixedBill(hash1), "namatest", "0821", new ArrayList<Integer>(), (float)500, false, false);
//        Member n3 = new Member(4,new NonFixedBill(hash1), "namatestvip", "0821", new ArrayList<Integer>(), (float)600, false, true);
//        ArrayList<Customer> listOfTest = new ArrayList<>();
//        listOfTest.add(n1);
//        listOfTest.add(n2);
//        listOfTest.add(n3);
//
//        try {
//            File objFile = new File("./src/main/resources/datastore/customers.obj");
//            adapterObj.write(listOfTest, objFile);
//            // Read data from OBJ file
//            ArrayList<Customer> listRead = adapterObj.read(objFile);
////            System.out.println(listRead);
////            System.out.println(listRead.get(1).getCurrentBill().getMapBarang());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}