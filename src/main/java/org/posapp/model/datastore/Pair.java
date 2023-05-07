package org.posapp.model.datastore;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Pair implements Serializable {
    private static final long serialVersionUID = 1L;
    public Integer num1;
    public Integer num2;

    public Pair() {
        num1 = 1;
        num2 = 2;
    }
    public Pair(Integer n1, Integer n2) {
        num1 = n1;
        num2 = n2;
    }
}
