package org.posapp.model.datastore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.posapp.model.Barang;
import org.posapp.model.FixedBill;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AdapterJsonFixedBill implements AdapterData<FixedBill> {
    private final Gson gson;

    public AdapterJsonFixedBill() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ArrayList.class, new FixedBillListTypeAdapter());
//        gsonBuilder.registerTypeAdapter(HashMap.class, new HashMapTypeAdapter());
        gsonBuilder.setPrettyPrinting();
        this.gson = gsonBuilder.create();
    }

    @Override
    public ArrayList<FixedBill> read(File file) throws Exception {
//        Gson gson = new Gson();
        FileReader reader = new FileReader(file);
        Type type = new TypeToken<ArrayList<FixedBill>>() {
        }.getType();
        ArrayList<FixedBill> list = gson.fromJson(reader, type);
        reader.close();
        return list;
    }
    @Override
    public void write(ArrayList<FixedBill> list, File file) throws Exception {
//        Gson gson = new Gson();
        FileWriter writer = new FileWriter(file);
        gson.toJson(list, writer);
        writer.close();
    }

    public class FixedBillListTypeAdapter extends TypeAdapter<ArrayList<FixedBill>> {
        @Override
        public void write(JsonWriter out, ArrayList<FixedBill> list) throws IOException {
            out.beginArray();
            for (FixedBill fb : list) {
                Set<Barang> keys = fb.getMapBarang().keySet();
                out.beginObject();
                out.name("mapBarang");
                out.beginArray();
                for (Barang key : keys) {
                    out.beginObject();
                    out.name("key");
                    out.jsonValue(new Gson().toJson(key));
                    out.name("value");
                    out.jsonValue(new Gson().toJson(fb.getMapBarang().get(key)));
                    out.endObject();
                }
                out.endArray();
                out.name("idFixedBill");
                out.value(fb.getIdFixedBill());
                out.endObject();
            }
            out.endArray();
        }

        @Override
        public ArrayList<FixedBill> read(JsonReader in) throws IOException {
            ArrayList<FixedBill> list = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                HashMap<Barang, Integer> mapBarang = new HashMap<Barang, Integer>();
                Integer idFixedBill = null;
                in.beginObject();
                while (in.hasNext()) {
                    String name = in.nextName();
                    if (name.equals("mapBarang")) {
                        in.beginArray(); // begin array here
                        while (in.hasNext()) {
                            in.beginObject();
                            Barang newBarang = null;
                            Integer quantity = 0;
                            while (in.hasNext()) {
                                String mapAttribute = in.nextName();
                                if (mapAttribute.equals("key")) {
                                    in.beginObject();
                                    int idBarang = -1;
                                    String nama = null;
                                    String kategori = null;
                                    int stok = -1;
                                    double hargaJual = -1.0;
                                    double hargaBeli = -1.0;
                                    String pathGambar = null;
                                    while (in.hasNext()) {
                                        String attribute = in.nextName();
                                        switch (attribute) {
                                            case "idBarang":
                                                idBarang = in.nextInt();
                                                break;
                                            case "nama":
                                                nama = in.nextString();
                                                break;
                                            case "kategori":
                                                kategori = in.nextString();
                                                break;
                                            case "stok":
                                                stok = in.nextInt();
                                                break;
                                            case "hargaJual":
                                                hargaJual = in.nextDouble();
                                                break;
                                            case "hargaBeli":
                                                hargaBeli = in.nextDouble();
                                                break;
                                            case "pathGambar":
                                                pathGambar = in.nextString();
                                                break;
                                            default:
                                                in.skipValue();
                                                break;
                                        }
                                    }
                                    in.endObject();
                                    newBarang = new Barang(idBarang, nama, kategori, stok, (float) hargaJual, (float) hargaBeli, pathGambar);
                                } else if (mapAttribute.equals("value")) {
                                    quantity = in.nextInt();
                                } else {
                                    in.skipValue();
                                }
                                in.endObject();
                            }
                            mapBarang.put(newBarang, quantity);
                        }
                        in.endArray(); // end array here
                    } else if (name.equals("idFixedBill")) {
                        idFixedBill = in.nextInt();
                    } else {
                        in.skipValue();
                    }
                }
                in.endObject();
                if (mapBarang != null && idFixedBill != null) {
                    list.add(new FixedBill(mapBarang, idFixedBill));
                }
            }
            in.endArray();
            return list;
        }
    }

//    public class HashMapTypeAdapter extends TypeAdapter<HashMap<Barang, Integer>> {
//        @Override
//        public void write(JsonWriter out, HashMap<Barang, Integer> map) throws IOException {
//            out.beginObject();
//            for (HashMap.Entry<Barang, Integer> entry : map.entrySet()) {
//                out.name(entry.getKey().toString()).value(entry.getValue());
//            }
//            out.endObject();
//        }
//
//        @Override
//        public HashMap<Barang, Integer> read(JsonReader in) throws IOException {
//            HashMap<Barang, Integer> map = new HashMap<>();
////            in.beginObject();
//            while (in.hasNext()) {
//                String keyStr = in.nextName();
//                String[] parts = keyStr.replace("Barang(", "")
//                        .replace(")", "")
//                        .replaceAll("\\s","")
//                        .split(",");
//                String[][] result = new String[parts.length][];
//                for (int i = 0; i < parts.length; i++) {
//                    result[i] = parts[i].split("=");
//                }
//
//                Integer idBarang = Integer.parseInt(result[0][1]);
//                String nama = result[1][1];
//                String kategori = result[2][1];
//                Integer stok = Integer.parseInt(result[3][1]);
//                float hargaJual = Float.parseFloat(result[4][1]);
//                float hargaBeli = Float.parseFloat(result[5][1]);
//                String pathGambar = result[6][1];
//                Barang key = new Barang(idBarang, nama, kategori, stok, hargaJual, hargaBeli, pathGambar);
//
//                int value = in.nextInt();
//
//                map.put(key, value);
//            }
////            in.endObject();
//            return map;
//        }
//    }


//    private static class BarangTypeAdapter extends TypeAdapter<Barang> {
//        @Override
//        public void write(JsonWriter out, Barang barang) throws IOException {
//            if (barang == null) {
//                out.nullValue();
//                return;
//            }
//
//            out.beginObject();
//            out.name("idBarang").value(barang.getIdBarang());
//            out.name("nama").value(barang.getNama());
//            out.name("kategori").value(barang.getKategori());
//            out.name("stok").value(barang.getStok());
//            out.name("hargaJual").value(barang.getHargaJual());
//            out.name("hargaBeli").value(barang.getHargaBeli());
//            out.name("pathGambar").value(barang.getPathGambar());
//            out.endObject();
//        }
//
//        @Override
//        public Barang read(JsonReader in) throws IOException {
//            if (in.peek() == JsonToken.NULL) {
//                in.nextNull();
//                return null;
//            }
//
//            in.beginObject();
//            int idBarang = 0;
//            String nama = null;
//            String kategori = null;
//            int stok = 0;
//            double hargaJual = 0;
//            double hargaBeli = 0;
//            String pathGambar = null;
//
//            while (in.hasNext()) {
//                String name = in.nextName();
//                switch (name) {
//                    case "idBarang":
//                        idBarang = in.nextInt();
//                        break;
//                    case "nama":
//                        nama = in.nextString();
//                        break;
//                    case "kategori":
//                        kategori = in.nextString();
//                        break;
//                    case "stok":
//                        stok = in.nextInt();
//                        break;
//                    case "hargaJual":
//                        hargaJual = in.nextDouble();
//                        break;
//                    case "hargaBeli":
//                        hargaBeli = in.nextDouble();
//                        break;
//                    case "pathGambar":
//                        pathGambar = in.nextString();
//                        break;
//                    default:
//                        in.skipValue();
//                        break;
//                }
//            }
//            in.endObject();
//
//            return new Barang(idBarang, nama, kategori, stok, (float) hargaJual, (float) hargaBeli, pathGambar);
//        }
//    }


//    public static void main(String[] args){
//
//        AdapterJsonFixedBill adapterJson = new AdapterJsonFixedBill();
//        Barang barang1 = new Barang(1, "test", "testkategori", 3, 300.1, 300.3, "path");
//        Barang barang2 = new Barang(2, "testtest", "testkategori2222", 6, 600.2, 800.4, "path22");
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
//            adapterJson.write(listOfTest, new File("test.json"));
//            ArrayList<FixedBill> listRead = adapterJson.read(new File("test.json"));
//            System.out.println("gg sekali");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}