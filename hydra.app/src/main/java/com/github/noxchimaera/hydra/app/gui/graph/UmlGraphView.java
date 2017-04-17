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

package com.github.noxchimaera.hydra.app.gui.graph;

import com.github.noxchimaera.hydra.app.gui.app.AppController;
import com.github.noxchimaera.hydra.app.mx.UmlGraph;
import com.github.noxchimaera.hydra.app.mx.UmlGraphComponent;
import com.github.noxchimaera.hydra.utils.swing.GUI;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * @author Nox
 */
public class UmlGraphView extends JPanel {

    private UmlGraphController controller;

    private UmlGraphComponent graphComponent;
    private UmlGraph graph;
    private mxIGraphModel model;
    private Object defaultParent;

    private mxRubberband rubberbandSelection;
    private mxKeyboardHandler keyboardHandler;

    public UmlGraphView(UmlGraphComponent graphComponent) {
        super(new BorderLayout());

        this.graphComponent = graphComponent;
        graph = (UmlGraph)graphComponent.getGraph();
        model = graph.getModel();
        defaultParent = graph.getDefaultParent();

        add(graphComponent, GUI.borderLayout_Centre());
        installHandlers();
        installListeners();

        controller = new UmlGraphController(this, graph.getCellFactory());
    }

    private void installHandlers() {
        keyboardHandler = new mxKeyboardHandler(graphComponent);
        rubberbandSelection = new mxRubberband(graphComponent);
    }

    private void installListeners() {
        graphComponent.addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                onMouseWheelMoved(e);
            }
        });

        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int count = e.getClickCount();
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (count == 2) {
                        // left double click
                    } else if (count == 1) {
                        // left click
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    if (count == 2) {

                    } else if (count == 1) {

                    }
                }
            }
        });
    }

    private void onMouseWheelMoved(MouseWheelEvent event) {
        if (event.getWheelRotation() < 0) {
            graphComponent.zoomIn();
        } else {
            graphComponent.zoomOut();
        }
    }

    public UmlGraphComponent getGraphComponent() {
        return graphComponent;
    }

    public UmlGraph getGraph() {
        return graph;
    }

}
