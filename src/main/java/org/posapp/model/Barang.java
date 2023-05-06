package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Barang {
    private Integer idBarang;
    private String nama;
    private int stok;
    private String kategori;
    private int hargaJual;
    private int hargaBeli;
    private String pathGambar;
}