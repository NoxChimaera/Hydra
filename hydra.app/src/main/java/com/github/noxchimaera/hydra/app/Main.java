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

import com.github.noxchimaera.hydra.app.graph.GraphBuilder;
import com.github.noxchimaera.hydra.app.gui.app.AppWindowView;
import com.github.noxchimaera.hydra.app.gui.graph.UmlGraphView;
import com.github.noxchimaera.hydra.app.gui.library.LibraryPanel;
import com.github.noxchimaera.hydra.app.gui.library.PalettePanel;
import com.github.noxchimaera.hydra.app.mx.UmlGraphComponent;
import com.github.noxchimaera.hydra.app.mx.UmlMxGraph;
import com.github.noxchimaera.hydra.core.activity2.UmlGraph;
import com.github.noxchimaera.hydra.core.activity2.nodes.*;
import com.mxgraph.layout.*;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Max Balushkin
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        IconFontSwing.register(FontAwesome.getIconFont());

        UmlGraph umlGraph = new UmlGraph();
        UmlMxGraph graph = new UmlMxGraph(umlGraph);
        UmlGraphComponent graphComponent = new UmlGraphComponent(graph);
        UmlGraphView umlGraphView = new UmlGraphView(graphComponent);

        LibraryPanel library = new LibraryPanel();
        setupLibrary(library, graph);

        AppWindowView view = new AppWindowView(umlGraphView, library);

        view.setTitle("Hydra Modelling System");
        view.setSize(1280, 768);
        view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        view.setLocationRelativeTo(null);
        view.setVisible(true);

        InitUmlNode init = umlGraph.init();
        ForLoopUmlNode loop = umlGraph.loop();
        ActionUmlNode setup = umlGraph.action("int i = 0");
        ActionUmlNode test = umlGraph.action("i < 10");
        ActionUmlNode step = umlGraph.action("++i");
        ActionUmlNode body = umlGraph.action("System.out.println(i)");
        FinUmlNode fin = umlGraph.fin();

        umlGraph.flow(init, loop, "");
        umlGraph.flow(loop, setup, ForLoopUmlNode.Setup);
        umlGraph.flow(loop, test, ForLoopUmlNode.Test);
        umlGraph.flow(loop, step, ForLoopUmlNode.Step);
        umlGraph.flow(loop, body, ForLoopUmlNode.Body);
        umlGraph.flow(loop, fin, "");

        GraphBuilder builder = new GraphBuilder(graph.getCellFactory());
        builder.build(umlGraph);

        mxIGraphLayout layout = new mxCompactTreeLayout(graph, false);
        layout.execute(graph.getDefaultParent());

        junk();
    }

    private static void junk() {
        // Dialog ed = new ActionUmlNodeEditor(null, null);
        //
        // ed.setSize(640, 480);
        // ed.setLocationRelativeTo(null);
        // ed.setVisible(false);



    }

    private static void setupLibrary(LibraryPanel lib, UmlMxGraph graph) {
        Object dp = graph.getDefaultParent();
        UmlCellFactory cellFactory = graph.getCellFactory();
        UmlGraph umlGraph = graph.getUmlGraph();

        PalettePanel uml = lib.createPalette("UML Activity 2");
        uml.addTemplate(
            "Initial", null,
            cellFactory.init(new InitUmlNode(-1), 0, 0));
        uml.addTemplate(
            "Final", null,
            cellFactory.fin(new FinUmlNode(-1), 0, 0));
        uml.addTemplate(
            "Action", null,
            cellFactory.action(new ActionUmlNode(-1, ""), 0, 0));
        uml.addTemplate(
            "Conditional", null,
            cellFactory.cond(new ConditionalUmlNode(-1), 0, 0));
        uml.addTemplate(
            "Loop", null,
            cellFactory.forLoop(new ForLoopUmlNode(-1), 0, 0));
    }

}
