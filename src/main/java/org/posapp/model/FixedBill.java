package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@AllArgsConstructor
public class FixedBill implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<Barang, Integer> mapBarang;
    private Integer idFixedBill;
}
