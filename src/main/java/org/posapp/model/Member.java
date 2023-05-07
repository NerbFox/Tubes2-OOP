package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.posapp.model.datastore.Datastore;

import java.util.ArrayList;

@Data
public class Member extends Customer {
    String name;
    String phone;
    ArrayList<Integer> arrFixedBillId;
    Integer poin;
    Boolean frozen;
    Boolean vipStatus;
    public Member(Integer idCust, NonFixedBill bill, String name, String phone, ArrayList<Integer> arrFixedBillId, Integer poin, Boolean frozen, Boolean vipStatus) {
        super(idCust, bill);
        this.name = name;
        this.phone = phone;
        this.arrFixedBillId = arrFixedBillId;
        this.poin = poin;
        this.frozen = frozen;
        this.vipStatus = vipStatus;
    }
    public  String getMemberStatus() {
        if (this.frozen) return "Deactivated";
        return vipStatus ? "VIP Member" : "Normal Member";
    }

    public ArrayList<FixedBill> getArrFixedBill() {
        ArrayList<FixedBill> allFixedBill = Datastore.getInstance().getArrFixedBill();
        ArrayList<FixedBill> userFixedBill = new ArrayList<>();

        for (FixedBill fixedBill : allFixedBill) {
            if (arrFixedBillId.contains(fixedBill.getIdFixedBill())) {
                userFixedBill.add(fixedBill);
            }
        }
        return userFixedBill;
    }
}