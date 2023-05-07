package org.posapp.model.datastore;

import lombok.Data;
import org.posapp.model.Barang;
import org.posapp.model.FixedBill;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;


public class AdapterXml implements AdapterData<FixedBill> {
    @Override
    public ArrayList<FixedBill> read(File file) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ObjectList objectList = (ObjectList) unmarshaller.unmarshal(file);
        return objectList.getList();
    }

    @Override
    public void write(ArrayList<FixedBill> list, File file) throws Exception {
        ObjectList objectList = new ObjectList();
        objectList.setList(list);
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectList.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(objectList, file);
    }

//    public static void main(String[] args){
//
//        AdapterXml adapterXml = new AdapterXml();
//
//        TestObject test1 = new TestObject();
//        TestObject test2 = new TestObject();
//        ArrayList<TestObject> listOfTest = new ArrayList<>();
//        listOfTest.add(test1);
//        listOfTest.add(test2);
//        try {
//            adapterXml.write(listOfTest, new File("test.xml"));
//            ArrayList<TestObject> listRead = adapterXml.read(new File("test.xml"));
//            System.out.println(listRead.get(0).listOfPair.get(0).num2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}