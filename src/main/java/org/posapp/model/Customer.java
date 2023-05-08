package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Data
public abstract class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer idCust;
    NonFixedBill currentBill;
    abstract public String getMemberStatus();
    public Customer(Integer idCust, NonFixedBill bill) {
        this.idCust = idCust;
        this.currentBill = bill;
    }


}
