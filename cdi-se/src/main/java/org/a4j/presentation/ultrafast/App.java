
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
            Player mario = Player.builder()
                    .name("Mario")
                    .score(10L)
                    .position(Position.ATTACKER)
                    .city("Salvador")
                    .build();

            Player luigi = Player.builder()
                    .name("Luigi")
                    .score(20L)
                    .position(Position.ATTACKER)
                    .city("Lisbon")
                    .build();

            service.add(mario);
            service.add(luigi);

            System.out.println("The players are: " + service.getNames());
            System.out.println("The Mario search: " + service.findById("Mario"));
            service.delete("Mario");
            System.out.println("The Sun search: " + service.findById("Mario"));

        }
    }
}
