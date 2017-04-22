/*
 * Copyright 2016 Nox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.noxchimaera.hydra.app.gui.app;

import com.github.noxchimaera.hydra.app.UmlCellFactory;
import com.github.noxchimaera.hydra.app.events.EventBus;
import com.github.noxchimaera.hydra.app.events.NodeImportEvent;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;

/**
 * @author Nox
 */
public class AppController {

    private AppWindowView view;

    public AppController(AppWindowView view) {
        this.view = view;

        setupListeners();
    }

    private void setupListeners() {
        EventBus.Shared
            .observe(NodeImportEvent.class)
            .subscribe(this::onNodeImport);
    }

    private void onNodeImport(NodeImportEvent event) {
        if (event.isConsumed()) return;

        UmlNode node = event.getCell().getUserObject();
        UmlCellFactory factory = view.getGraphView().getGraph().getCellFactory();
        UmlCell orig = event.getCell();

        if (false) {
            // stub for wizards
        } else {
            commitImport(event, event.getCell());
        }
    }

    private void commitImport(NodeImportEvent event, UmlCell cell) {
        UmlNode node = ((UmlNode)cell.getValue()).deepClone();
        cell.setValue(node);

        EventBus.Shared
            .post(new NodeImportEvent(event.getSender(), cell, event.getDx(), event.getDy(),
                event.getTarget(), event.getLocation(), true));
    }

}
