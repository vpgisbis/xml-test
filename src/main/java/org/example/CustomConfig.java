package org.example;

import org.geotools.filter.v1_1.OGCConfiguration;
import org.geotools.wfs.bindings.*;
import org.geotools.wfs.v1_1.FeatureCollectionTypeBinding;
import org.geotools.wfs.v1_1.WFS;
import org.geotools.wfs.v1_1.WFSConfiguration;
import org.geotools.xsd.XSD;
import org.geotools.xsd.ows.OWSConfiguration;
import org.picocontainer.MutablePicoContainer;

import javax.xml.namespace.QName;

public class CustomConfig  extends  org.geotools.wfs.WFSConfiguration{
    protected CustomConfig() {
        super(WFS.getInstance());
//        this.addDependency(new WFSConfiguration());
        this.addDependency(new OWSConfiguration());
        this.addDependency(new OGCConfiguration());
    }
    protected void configureBindings(MutablePicoContainer container) {
        super.configureBindings(container);
        container.registerComponentImplementation(new QName("file://www.opengis.net/ogc", "Node"), NodeBinding.class);
        container.registerComponentImplementation(WFS.TransactionResponseType, TransactionResponseTypeBinding.class);
        container.registerComponentImplementation(WFS.InsertResultsType, InsertResultsTypeBinding.class);
        container.registerComponentImplementation(WFS.TransactionResultsType, TransactionResultsTypeBinding.class);
        container.registerComponentImplementation(WFS.LockFeatureResponseType, LockFeatureResponseTypeBinding.class);
        container.registerComponentImplementation(WFS.OperationsType, OperationsTypeBinding.class);
        container.registerComponentImplementation(WFS.FeatureCollectionType, FeatureCollectionTypeBinding.class);
    }
}
