package org.posapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NonMember extends Customer {
    Integer FixedBillID;

}
