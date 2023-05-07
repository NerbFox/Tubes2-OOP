package org.posapp.model.datastore;

import lombok.Data;
import org.posapp.model.FixedBill;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

// Ini masih fixed bill doang
@XmlRootElement
@Data
public class ObjectList implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<FixedBill> list;

    public ObjectList() {
        list = new ArrayList<>();
    }

    public void setList(ArrayList<FixedBill> otherList) {
        this.list = otherList;
    }
}