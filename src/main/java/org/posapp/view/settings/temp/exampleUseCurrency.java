package org.posapp.view.settings;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class exampleUseCurrency {
    private double amount;
    public exampleUseCurrency() {
        this.amount = 14000;
    }
    public double getAmount() {
        return this.amount;
    }
    public static void main(String[] args) throws ParserConfigurationException {
        exampleUseCurrency example = new exampleUseCurrency();
        CurrencyConverter converter = new CurrencyConverterClass();
        System.out.println(converter.convert(example.getAmount()));
        // read the XML file
        File xmlFile = new File("a.xml");
//        try {
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(xmlFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        readXML("a.xml");


    }
    public static void readXML(String filePath) {
        try {
            System.out.println("Reading XML file: " + filePath);
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // Do something with the document
            // For example, you could loop through all the "price" elements
            NodeList priceList = doc.getElementsByTagName("price");
            for (int i = 0; i < priceList.getLength(); i++) {
                Element priceElement = (Element) priceList.item(i);
                String currency = priceElement.getAttribute("currency");
                double value = Double.parseDouble(priceElement.getTextContent());
                // Do something with the currency and value
                System.out.println("Currency: " + currency);
                System.out.println("Value: " + value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
