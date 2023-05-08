package org.posapp.model.datastore;

import java.io.File;

public class AdapterFactory {
    public static AdapterData createAdapter(String filePath) {

        File file = new File(filePath);
        String fullFilename = file.getName();
        String name = fullFilename.substring(0, fullFilename.lastIndexOf('.'));
        String extension = fullFilename.substring(fullFilename.lastIndexOf('.') + 1);

        switch (extension) {
            case "xml":
                if (name.equals("fixedbill")) {
                    return new AdapterXmlFixedBill();
                } else if (name.equals("customer")) {
                    return new AdapterXmlCustomer();
                } else if (name.equals("barang")) {
                    return new AdapterXmlBarang();
                } else {
                    throw new IllegalArgumentException("Filename not valid: " + fullFilename);
                }
            case "json":
                if (name.equals("fixedbill")) {
                    return new AdapterJsonFixedBill();
                } else if (name.equals("customer")) {
                    return new AdapterJsonCustomer();
                } else if (name.equals("barang")) {
                    return new AdapterJsonBarang();
                } else {
                    throw new IllegalArgumentException("Filename not valid: " + fullFilename);
                }
            case "obj":
                if (name.equals("fixedbill")) {
                    return new AdapterObjFixedBill();
                } else if (name.equals("customer")) {
                    return new AdapterObjCustomer();
                } else if (name.equals("barang")) {
                    return new AdapterObjBarang();
                } else {
                    throw new IllegalArgumentException("Filename not valid: " + fullFilename);
                }
            default:
                throw new IllegalArgumentException("Unsupported file extension: " + extension);
        }
    }
}
