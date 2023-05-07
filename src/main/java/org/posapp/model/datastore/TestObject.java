package org.posapp.model.datastore;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

public class TestObject implements Serializable {
    private static final long serialVersionUID = 1L;
    public Double val;
    public String name;

    @XmlElementWrapper(name = "listOfPair")
    @XmlElement(name = "pair")
    ArrayList<Pair> listOfPair;

    public TestObject() {
        val = 5.;
        name = "svt";
        listOfPair = new ArrayList<>();
        listOfPair.add(new Pair(2, 5));
        listOfPair.add(new Pair(1, 2));
    }
}