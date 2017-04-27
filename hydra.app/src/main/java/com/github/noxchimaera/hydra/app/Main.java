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

import com.github.noxchimaera.hydra.app.gui.app.AppWindowView;
import com.github.noxchimaera.hydra.app.gui.graph.UmlGraphView;
import com.github.noxchimaera.hydra.app.gui.library.LibraryPanel;
import com.github.noxchimaera.hydra.app.gui.library.PalettePanel;
import com.github.noxchimaera.hydra.app.mx.UmlGraph;
import com.github.noxchimaera.hydra.app.mx.UmlGraphComponent;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.UmlFactory;
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

        UmlFactory factory = new UmlFactory();
        UmlGraph graph = new UmlGraph(factory);
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

        UmlCellFactory cellFactory = graph.getCellFactory();
        UmlCell init = cellFactory.init(factory.init(), 50, 50);
        graph.addCell(init);

        UmlCell fin1 = cellFactory.fin(factory.fin(), 50, 150);
        graph.addCell(fin1);

        UmlCell fin2 = cellFactory.fin(factory.fin(), 150, 50);
        graph.addCell(fin2);

        UmlCell act = cellFactory.action(factory.action("print :hello"), 300, 300);
        graph.addCell(act);

        junk();
    }

    private static void junk() {
        // Dialog ed = new ActionUmlNodeEditor(null, null);
        //
        // ed.setSize(640, 480);
        // ed.setLocationRelativeTo(null);
        // ed.setVisible(false);



    }

    private static void setupLibrary(LibraryPanel lib, UmlGraph graph) {
        Object dp = graph.getDefaultParent();
        UmlCellFactory cellFactory = graph.getCellFactory();
        UmlFactory umlFactory = graph.getUmlFactory();

        PalettePanel uml = lib.createPalette("UML Activity 2");
        uml.addTemplate(
            "Initial", null,
            cellFactory.init(umlFactory.init(), 0, 0));
        uml.addTemplate(
            "Final", null,
            cellFactory.fin(umlFactory.fin(), 0, 0));
        uml.addTemplate(
            "Action", null,
            cellFactory.action(umlFactory.action(""), 0, 0));
        uml.addTemplate(
            "Conditional", null,
            cellFactory.cond(umlFactory.cond(), 0, 0));
    }

}
