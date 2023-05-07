package org.posapp.view.History;

import lombok.Data;
import org.posapp.model.Barang;
import java.util.HashMap;

@Data
public class TransHisDetail {
    Integer idBarang;
    String nama;
    Float harga;
    Integer quantity;
    Float subtotal;
    public TransHisDetail(Barang barang, Integer q) {
        idBarang = barang.getIdBarang();
        nama = barang.getNama();
        harga = barang.getHargaJual();
        quantity = q;
        subtotal = harga * quantity;
    }
}
