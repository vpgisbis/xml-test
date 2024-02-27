package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "node")
@XmlType(propOrder = {  "name" })
public class Node {

    public Node() {
    }

    private String name;
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
//    Geometry geom;
}
