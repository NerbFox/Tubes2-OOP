package org.posapp.view.History;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.posapp.model.Barang;

import java.util.List;

@Data
@AllArgsConstructor
public class TranHis {
    private Integer BillID;
    private String Date;
    private String totalBill;
    private Integer ID;
    private String nama;
    private String membership;
    private String noTel;
    private List<Barang> hisBarang;
}
