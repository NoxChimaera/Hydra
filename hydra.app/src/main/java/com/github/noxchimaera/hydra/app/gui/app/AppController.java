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
import com.github.noxchimaera.hydra.app.events.RegionConnectionEvent;
import com.github.noxchimaera.hydra.app.gui.editors.RegionSelector;
import com.github.noxchimaera.hydra.app.gui.base.DialogEvent;
import com.github.noxchimaera.hydra.app.gui.base.DialogEventHandler;
import com.github.noxchimaera.hydra.app.modules.ModuleRepository;
import com.github.noxchimaera.hydra.core.transformers.HydraToJavaTransformer;
import com.github.noxchimaera.hydra.core.transformers.UmlToHydraTransformer;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.UmlNodeTypes;
import com.github.noxchimaera.hydra.core.activity2.nodes.InitUmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.StructuredUmlNode;
import com.github.noxchimaera.hydra.core.model.nodes.HySequence;
import com.github.noxchimaera.hydra.utils.Contracts;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
public class AppController {

    private AppWindowView view;

    private ModuleRepository modules;

    public AppController(AppWindowView view) {
        this.view = view;
        modules = new ModuleRepository();
        setupListeners();
    }

    public ModuleRepository modules() {
        return modules;
    }

    private void setupListeners() {
        EventBus.Shared
            .observe(NodeImportEvent.class)
            .subscribe(this::onNodeImport);
        EventBus.Shared
            .observe(RegionConnectionEvent.class)
            .subscribe(this::onRegionConnect);
    }

    private void onRegionConnect(RegionConnectionEvent event) {
        if (UmlNodeTypes.RegionHeader != event.getSource().getUmlNode().type()) {
            event.consume();
            return;
        }
        if (event.isConsumed()) return;

        StructuredUmlNode node = event.getSource().getUserObject();
        RegionSelector selector = new RegionSelector(view, "Create edge", node.getEmptyConnections());
        selector.pack();
        selector.setResizable(false);
        selector.setLocationRelativeTo(view);

        selector.setEventHandler(new DialogEventHandler<String>() {
            @Override
            public void handleEvent(DialogEvent<String> event) {
                if (DialogEvent.Status.OK == event.getStatus()) {
                    handleOk(event.getValue());
                }
            }

            private void handleOk(String value) {
                event.consume();
                event.getFactory().connect(event.getSource(), event.getTarget(), event.getEdgeType(), value);
            }
        });

        selector.setVisible(true);
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
        UmlNode node = ((UmlNode)cell.getValue()).copy();
        cell.setValue(node);

        EventBus.Shared
            .post(new NodeImportEvent(event.getSender(), cell, event.getDx(), event.getDy(),
                event.getTarget(), event.getLocation(), true));
    }

    public void codegen(mxIGraphModel model, Object defaultParent) {
        List<UmlNode> roots = new ArrayList<>();

        int n = model.getChildCount(defaultParent);
        for (int i = 0; i < n; ++i) {
            mxCell cell = (mxCell)model.getChildAt(defaultParent, i);
            if (!Contracts.is(UmlCell.class, cell)) {
                continue;
            }
            UmlNode node = (UmlNode)cell.getValue();
            if (Contracts.is(InitUmlNode.class, node)) {
                roots.add(node);
            }
        }

        UmlToHydraTransformer t = new UmlToHydraTransformer();
        for (UmlNode root : roots) {
            root.accept(t);
        }

        HydraToJavaTransformer t2 = new HydraToJavaTransformer();
        for (HySequence hySequence : t.getResult()) {
            System.out.println(hySequence);
            t2.seq(hySequence);
            System.out.println(t2.java());
        }

    }

    private void debug(String data) {
        System.out.println(data);
    }

}
