package org.example;

import org.geotools.xsd.AbstractComplexBinding;

import javax.xml.namespace.QName;

public class NodeBinding  extends AbstractComplexBinding {
    @Override
    public QName getTarget() {
        return new QName("file://e4t4et", "Node");
    }

    @Override
    public Class<?> getType() {
        return Node.class;
    }
}
