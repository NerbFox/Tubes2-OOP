package org.posapp.model.datastore;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

// Ini masih fixed bill doang
@XmlRootElement
@Data
public class ObjectList<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<T> list;

    public ObjectList() {
        list = new ArrayList<>();
    }

    public void setList(ArrayList<T> otherList) {
        this.list = otherList;
    }
}