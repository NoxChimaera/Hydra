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

import com.alee.laf.button.WebButton;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.toolbar.WebToolBar;
import com.github.noxchimaera.hydra.app.gui.graph.UmlGraphView;
import com.github.noxchimaera.hydra.app.gui.library.LibraryPanel;
import com.github.noxchimaera.hydra.app.swing.prompt.Ask;
import com.github.noxchimaera.hydra.utils.swing.GUI;
import com.mxgraph.swing.mxGraphOutline;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Nox
 */
public class AppWindowView extends JFrame {

    private AppController controller;

    private WebMenuBar menu;

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

    public AppController getController() {
        return controller;
    }

    private void exit(ActionEvent event) {
        Ask.Answer res = Ask.yesOrNo(this, "Are you really want to exit?", "");
        if (Ask.Answer.Yes == res) {
            System.exit(0);
        }
    }

    private void initializeMenu() {
        menu = new WebMenuBar();

        WebMenu fileMenu = new WebMenu("File");
        WebMenuItem fileNew = new WebMenuItem(
            "New model", IconFontSwing.buildIcon(FontAwesome.FILE, 16));
        fileMenu.add(fileNew);
        WebMenuItem fileOpen = new WebMenuItem(
            "Open...", IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, 16));
        fileMenu.add(fileOpen);
        WebMenu fileOpenRecent = new WebMenu(
            "Open recent");
        fileMenu.add(fileOpenRecent);
        WebMenuItem fileSave = new WebMenuItem(
            "Save", IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, 16));
        fileMenu.add(fileSave);

        WebSeparator fileSeparator = new WebSeparator();
        fileMenu.add(fileSeparator);
        WebMenuItem fileExit = new WebMenuItem("Exit");
        fileExit.addActionListener(this::exit);
        fileMenu.add(fileExit);
        menu.add(fileMenu);

        WebMenu modelMenu = new WebMenu("Model");
        WebMenuItem modelLayout = new WebMenuItem("Do layout");
        modelMenu.add(modelLayout);
        menu.add(modelMenu);

        WebMenu toolsMenu = new WebMenu("Tools");
        WebMenuItem toolsJava = new WebMenuItem("Show Java code");
        toolsMenu.add(toolsJava);
        menu.add(toolsMenu);

        WebMenu helpMenu = new WebMenu("Help");
        menu.add(helpMenu);
    }

    private void initialize() {
        initializeMenu();
        WebPanel root = new WebPanel(new BorderLayout(4, 4));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));

        WebSplitPane rightPart = new WebSplitPane(WebSplitPane.HORIZONTAL_SPLIT, graphView, outline);
        rightPart.setDividerLocation(800);
        rightPart.setResizeWeight(1);
        rightPart.setDividerSize(6);
        rightPart.setOneTouchExpandable(true);
        rightPart.setBorder(null);

        WebSplitPane whole = new WebSplitPane(WebSplitPane.HORIZONTAL_SPLIT, library, rightPart);
        whole.setDividerSize(200);
        whole.setDividerSize(6);
        whole.setOneTouchExpandable(true);
        whole.setBorder(null);
        root.add(whole, GUI.borderLayout_Centre());

        WebToolBar bar = new WebToolBar();
        WebButton codegenButton = new WebButton(
            "Codegen", IconFontSwing.buildIcon(FontAwesome.CUBE, root.getFont().getSize()));
        codegenButton.onMouseClick(mouseEvent -> codegen());
        bar.add(codegenButton);
        root.add(bar, GUI.borderLayout_Top());

        add(menu, GUI.borderLayout_Top());
        add(root, GUI.borderLayout_Centre());
    }

    public UmlGraphView getGraphView() {
        return graphView;
    }

    private void codegen() {
        controller.codegen(getGraphView().getGraph().getModel(), getGraphView().getGraph().getDefaultParent());
    }

}
