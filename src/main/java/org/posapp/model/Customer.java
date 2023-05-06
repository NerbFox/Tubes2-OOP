package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public abstract class Customer {
    Integer idCust;
    NonFixedBill currentBill;
    abstract public String getMemberStatus();
    public Customer(Integer idCust) {
        this.idCust = idCust;
        this.currentBill = new NonFixedBill();
    }


}
