package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.posapp.view.custom_components.FixedSizeTable;
import org.posapp.model.datastore.Datastore;

import java.util.ArrayList;

@Data
public class NonMember extends Customer {
    Integer fixedBillID;
    public NonMember(Integer idCust, Integer fixedBillID) {
        super(idCust, new NonFixedBill());
        this.fixedBillID = fixedBillID;
    }
    public String getMemberStatus() {
        return "Non-Member";
    }

    public Member makeMember(String name, String phone) {
        ArrayList<Integer> newFixBillId = new ArrayList<>();
        newFixBillId.add(fixedBillID);
        Member newMember = new Member(getIdCust(), getCurrentBill(), name, phone, newFixBillId, 0, false, false);
        return newMember;
    }

    public FixedBill getFixedBill() {
        ArrayList<FixedBill> allFixedBill = Datastore.getInstance().getArrFixedBill();
        ArrayList<FixedBill> userFixedBill = new ArrayList<>();

        for (FixedBill fixedBill : allFixedBill) {
            if (fixedBillID.equals(fixedBill.getIdFixedBill())) {
                return fixedBill;
            }
        }
        return null;
    }
}
