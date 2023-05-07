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
//        Member n2 = new Member(3, "namatest", "0821", new ArrayList<Integer>(), 500, false, false);
//        Member n3 = new Member(4, "namatestvip", "0821", new ArrayList<Integer>(), 600, false, true);
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
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}