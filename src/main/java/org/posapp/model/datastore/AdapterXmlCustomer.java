package org.posapp.model.datastore;

import org.posapp.model.Customer;
import org.posapp.model.FixedBill;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;


public class AdapterXmlCustomer implements AdapterData<Customer> {
    @Override
    public ArrayList<Customer> read(File file) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ObjectList<Customer> objectList = (ObjectList<Customer>) unmarshaller.unmarshal(file);
        return objectList.getList();
    }

    @Override
    public void write(ArrayList<Customer> list, File file) throws Exception {
        ObjectList<Customer> objectList = new ObjectList<Customer>();
        objectList.setList(list);
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectList.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(objectList, file);
    }

//    public static void main(String[] args){
//
//        AdapterXmlFixedBill adapterXml = new AdapterXmlFixedBill();
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
//        try {
//            adapterXml.write(listOfTest, new File("test.xml"));
//            ArrayList<FixedBill> listRead = adapterXml.read(new File("test.xml"));
////            System.out.println(listRead.get(0).listOfPair.get(0).num2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}