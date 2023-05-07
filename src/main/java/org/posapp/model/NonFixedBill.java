package org.posapp.model;

import lombok.Data;
import org.posapp.model.datastore.Datastore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Data
public class NonFixedBill implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<Integer, Integer> mapId;

    public NonFixedBill() {
        mapId = new HashMap<Integer, Integer>();
    }

    public NonFixedBill(HashMap<Integer, Integer> mapId) {
        this.mapId = mapId;
    }

    public void addIdBarang(Integer idBarang) {
        // add IdBarang quantity by 1
        addIdBarang(idBarang, 1);
    }

    public void addIdBarang(Integer idBarang, Integer quantity) {
        // add IdBarang quantity by input
        if (mapId.containsKey(idBarang)) {
            mapId.put(idBarang, mapId.get(idBarang) + quantity);
        } else {
            mapId.put(idBarang, quantity);
        }
    }

    public void substractIdBarangByValue(Integer id) {
        // remove IdBarang quantity by 1
        if (mapId.containsKey(id)) {
            int newQuantity = mapId.get(id) - 1;
            if (newQuantity > 0) {
                mapId.put(id, newQuantity);
            } else {
                mapId.remove(id);
            }
        }
    }

    public void removeIdBarangByValue(Integer id) {
        // remove IdBarang quantity by 1
        if (mapId.containsKey(id)) {
            mapId.remove(id);
        }
    }

    public HashMap<Barang, Integer> getMapBarang() {
        HashMap<Barang, Integer> mapBrg = new HashMap<>();
//        Datastore.getInstance().hardCodeBill();
        ArrayList<Barang> arrBarang =  Datastore.getInstance().getArrBarang();

        for (Barang barang : arrBarang) {
            for (Integer key : mapId.keySet()) {
                if (key.equals(barang.getIdBarang())) {
                    mapBrg.put(barang, mapId.get(key));
                }
            }
        }
        return mapBrg;
    }
}