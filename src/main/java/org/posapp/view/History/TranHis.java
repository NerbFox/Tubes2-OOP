package org.posapp.view.History;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.posapp.model.Barang;
import org.posapp.model.Customer;
import org.posapp.model.FixedBill;
import org.posapp.model.Member;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class TranHis {
    private Integer BillID;
    private Float totalBill;
    private Integer ID;
    private String nama;
    private String membership;
    private String noTel;
    private HashMap<Barang, Integer> mapBarang;

    public TranHis(Customer cust, FixedBill bill) {
        BillID = bill.getIdFixedBill();
        totalBill = new Float(0);

        for (Barang key : bill.getMapBarang().keySet()) {
            Integer value = bill.getMapBarang().get(key);
            totalBill += key.getHargaJual() * value;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedValue = df.format(totalBill);
        totalBill = Float.parseFloat(formattedValue);

        ID = cust.getIdCust();
        if (cust instanceof Member) {
            nama = ((Member) cust).getName();
            noTel = ((Member) cust).getPhone();
        }
        else {
            nama = "No Name";
            noTel = "No Phone";
        }
        membership = cust.getMemberStatus();
        mapBarang = bill.getMapBarang();
    }
}
