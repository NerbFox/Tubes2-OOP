package org.posapp.model.datastore;

public class AdapterFactory {
    public static AdapterData createAdapter(String fileName) {
        String extension = getFileExtension(fileName);
        switch (extension) {
            case "xml":
                return new AdapterXml();
            case "json":
                return new AdapterJson();
            case "obj":
                return new AdapterObjFixedBill();
            default:
                throw new IllegalArgumentException("Unsupported file extension: " + extension);
        }
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }
}
