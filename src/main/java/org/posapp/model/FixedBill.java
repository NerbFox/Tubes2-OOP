package org.posapp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;

@XmlRootElement
@Getter
@AllArgsConstructor
public class FixedBill implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<Barang, Integer> mapBarang;
    private Integer idFixedBill;
}
