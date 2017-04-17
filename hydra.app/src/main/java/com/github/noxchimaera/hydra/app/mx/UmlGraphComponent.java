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

package com.github.noxchimaera.hydra.app.mx;

import com.github.noxchimaera.hydra.app.events.EventBus;
import com.github.noxchimaera.hydra.app.events.NodeImportEvent;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.*;
import java.awt.print.PageFormat;

/**
 * @author Nox
 */
public class UmlGraphComponent extends mxGraphComponent {

    public UmlGraphComponent(mxGraph graph) {
        super(graph);
        setGridVisible(true);
        getConnectionHandler().setCreateTarget(false);

        getViewport().setOpaque(true);
        getViewport().setBackground(Color.WHITE);

        pageFormat.setOrientation(PageFormat.LANDSCAPE);
        setupListeners();
    }

    private void setupListeners() {
        EventBus.instance.observe(NodeImportEvent.class)
            .subscribe(this::asyncImportCell);
    }

    /**
     * Custom import handling.
     *
     * @param event node import event
     */
    private void asyncImportCell(NodeImportEvent event) {
        if (!event.isConsumed()) return;
        if (event.getSender() != this) return;

        super.importCells(
            new Object[]{event.getCell()},
            event.getDx(), event.getDy(),
            event.getTarget(), event.getLocation());
    }

    /**
     * Intercepts import event and suppress default behaviour.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Object[] importCells(Object[] cells, double dx, double dy, Object target, Point location) {
        final int n = cells.length;
        for (int i = 0; i < n; ++i) {
            if (cells[i] instanceof UmlCell) {
                UmlCell cell = (UmlCell)cells[i];
                // Post custom node import event
                EventBus.instance.post(new NodeImportEvent(this, cell, dx, dy, target, location));
            }
        }
        // Suppress default behaviour
        return new Object[0];
    }

}
