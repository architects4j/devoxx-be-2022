package org.a4j.presentation.ultrafast;

import one.microstream.integrations.cdi.types.extension.StorageExtension;
import org.jboss.weld.junit5.auto.AddExtensions;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@EnableAutoWeld
@AddExtensions(StorageExtension.class)
class GalaxyServiceTest {


    @Inject
    private GalaxyService service;

    @Test
    public void shouldInjectService() {
        Assertions.assertNotNull(service);
    }

}