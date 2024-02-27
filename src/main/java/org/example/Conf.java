package org.example;

import org.geotools.api.referencing.ObjectFactory;
import org.picocontainer.MutablePicoContainer;

public class Conf {
    protected void configureContext(MutablePicoContainer context) {
        context.registerComponentImplementation( ObjectFactory.class );
    }
}
