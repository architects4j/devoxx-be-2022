
package org.a4j.presentation.ultrafast;

/*-
 * #%L
 * microstream-examples-cdi-javase
 * %%
 * Copyright (C) 2019 - 2021 MicroStream Software
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import one.microstream.storage.types.StorageManager;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App {
    public static void main(final String[] args) {

        try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            StorageManager manager = container.select(StorageManager.class).get();
            Object root = manager.root();
            System.out.println("the root value: " + root);
            final GalaxyService service= container.select(GalaxyService.class).get();
            Player sun = Player.builder().name("Sun")
                    .size(1231231231)
                    .type(Position.STAR)
                    .habitable(false).build();

            Player earth = Player.builder().name("Earth")
                    .size(1231231231)
                    .type(Position.PLANET)
                    .habitable(true)
                    .build();
            service.add(sun);
            service.add(earth);

            System.out.println("The bodies are: " + service.getNames());
            System.out.println("The Sun search: " + service.findById("Sun"));
            service.delete("Sun");
            System.out.println("The Sun search: " + service.findById("Sun"));

        }
    }
}
