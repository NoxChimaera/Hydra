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

import com.github.noxchimaera.hydra.app.gui.graph.UmlGraphView;
import com.github.noxchimaera.hydra.app.gui.library.LibraryPanel;
import com.github.noxchimaera.hydra.app.mx.UmlGraph;
import com.github.noxchimaera.hydra.utils.swing.GUI;
import com.mxgraph.swing.mxGraphOutline;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Nox
 */
public class AppWindowView extends JFrame {

    private AppController controller;

    private JMenuBar menu;

    private LibraryPanel library;

    private UmlGraphView graphView;

    private mxGraphOutline outline;

    public AppWindowView(UmlGraphView graphView, LibraryPanel library) {
        super();
        this.graphView = graphView;
        graphView.setOwner(this);
        this.library = library;
        outline = new mxGraphOutline(graphView.getGraphComponent());

        initialize();
        controller = new AppController(this);
    }

    private void initialize() {
        menu = new JMenuBar();

        JPanel root = new JPanel(new BorderLayout(4, 4));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));
        setContentPane(root);

        JSplitPane rightPart = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphView, outline);
        rightPart.setDividerLocation(800);
        rightPart.setResizeWeight(1);
        rightPart.setDividerSize(6);
        rightPart.setOneTouchExpandable(true);
        rightPart.setBorder(null);

        JSplitPane whole = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, library, rightPart);
        whole.setDividerSize(200);
        whole.setDividerSize(6);
        whole.setOneTouchExpandable(true);
        whole.setBorder(null);
        add(whole, GUI.borderLayout_Centre());

        add(menu, GUI.borderLayout_Top());
    }

    public UmlGraphView getGraphView() {
        return graphView;
    }

}
