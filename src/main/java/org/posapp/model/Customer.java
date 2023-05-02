package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class Customer {
    Integer idCust;
    NonFixedBill currentBill;
}
