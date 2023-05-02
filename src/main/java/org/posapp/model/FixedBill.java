package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.HashMap;

@Getter
@AllArgsConstructor
public class FixedBill {
    private HashMap<Barang, Integer> mapBarang;
    private Integer idFixedBill;
}
