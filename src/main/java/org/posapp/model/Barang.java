package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Barang implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idBarang;
    private String nama;
    private String kategori;
    private int stok;
    private int hargaJual;
    private int hargaBeli;
    private String pathGambar;
}
