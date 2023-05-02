package org.posapp.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Member extends Customer {
    String name;
    String phone;
    ArrayList<Integer> arrFixedBillId;
    Integer poin;
    Boolean frozen;
}
