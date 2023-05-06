package org.posapp.view.manajemen_barang;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public @Data class ItemBarang {
    @Getter @Setter
    private String nama;
    @Getter @Setter
    private String kategori;
    @Getter @Setter
    private Integer stok;
    @Getter @Setter
    private Double hargajual;
    @Getter @Setter
    private Double hargabeli;

    public ItemBarang(String nama, String kategori, Integer stok, Double hargaJual, Double hargaBeli){
        this.nama = nama;
        this.kategori = kategori;
        this.stok = stok;
        this.hargabeli = hargaBeli;
        this.hargajual = hargaJual;
    }
}
