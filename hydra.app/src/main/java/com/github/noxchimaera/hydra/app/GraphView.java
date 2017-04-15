/*
 * Copyright 2016 Max Balushkin.
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

package com.github.noxchimaera.hydra.app;

import com.github.noxchimaera.hydra.utils.swing.GUI;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

/**
 * @author Max Balushkin
 */
public class GraphView extends JPanel {

    private mxGraphComponent graphComponent;
    private mxGraph graph;
    private mxIGraphModel model;
    private Object defaultParent;

    private mxRubberband rubberbandSelection;
    private mxKeyboardHandler keyboardHandler;

    public GraphView(mxGraphComponent component) {
        super(new BorderLayout());
        graphComponent = component;
        graph = component.getGraph();
        graph.setResetViewOnRootChange(true);
        model = graph.getModel();
        defaultParent = graph.getDefaultParent();

        add(component, GUI.borderLayout_Centre());
    }

    private void installHandlers() {
        rubberbandSelection = new mxRubberband(graphComponent);
        keyboardHandler = new mxKeyboardHandler(graphComponent);
    }

}
