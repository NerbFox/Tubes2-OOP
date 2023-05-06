package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class NonMember extends Customer {
    Integer FixedBillID;
    public NonMember(Integer idCust, Integer fixedBillID) {
        super(idCust);
        this.FixedBillID = fixedBillID;
    }
    public String getMemberStatus() {
        return "Non-Member";
    }
}
