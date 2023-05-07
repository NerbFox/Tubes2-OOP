package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.posapp.controller.currency.CurrencyWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Data
@AllArgsConstructor
public class Barang implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idBarang;
    private String nama;
    private String kategori;
    private int stok;
    private Float hargaJual;
    private Float hargaBeli;
    private String pathGambar;

    public Float getHargaJual() {
        return CurrencyWrapper.getInstance().getConvertedCurrency(hargaJual);
    }
    public Float getHargaBeli() {
        return CurrencyWrapper.getInstance().getConvertedCurrency(hargaBeli);
    }
}
