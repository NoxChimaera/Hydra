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

package com.github.noxchimaera.hydra.app.graph;

import com.github.noxchimaera.hydra.core.Log;
import com.github.noxchimaera.hydra.app.UmlCellFactory;
import com.github.noxchimaera.hydra.app.mx.DrawSection;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.UmlEdge;
import com.github.noxchimaera.hydra.core.activity2.UmlGraph;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.UmlVisitor;
import com.github.noxchimaera.hydra.core.activity2.nodes.*;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.utils.Strings;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nox
 */
public class GraphBuilder implements UmlVisitor<Void> {

    private UmlCellFactory factory;
    private mxGraph mxGraph;
    private mxIGraphModel model;
    private Object defaultParent;

    private Map<UmlNode, UmlCell> cells;

    public GraphBuilder(UmlCellFactory factory) {
        this.factory = factory;
        mxGraph = factory.getGraph();
        model = mxGraph.getModel();
        defaultParent = mxGraph.getDefaultParent();
        cells = new HashMap<>();
    }

    public void build(UmlGraph graph) {
        for (UmlNode node : graph.getNodes()) {
            node.accept(this);
        }
        connectAll();
    }

    private void draw(UmlCell cell) {
        try (DrawSection $ = new DrawSection(model)) {
            mxGraph.addCell(cell);
        }
    }

    private double x(UmlNode node) {
        return node.getView().getX();
    }

    private double y(UmlNode node) {
        return node.getView().getY();
    }

    @Override
    public Void init(InitUmlNode init) {
        UmlCell cell = factory.init(init, x(init), y(init));
        cells.put(init, cell);
        draw(cell);
        return null;
    }

    @Override
    public Void fin(FinUmlNode fin) {
        UmlCell cell = factory.fin(fin, x(fin), y(fin));
        cells.put(fin, cell);
        draw(cell);
        return null;
    }

    @Override
    public Void action(ActionUmlNode action) {
        UmlCell cell = factory.action(action, x(action), y(action));
        cells.put(action, cell);
        draw(cell);
        return null;
    }

    @Override
    public Void condition(ConditionalUmlNode condition) {
        UmlCell cell = factory.cond(condition, x(condition), y(condition));
        cells.put(condition, cell);
        draw(cell);
        return null;
    }

    @Override
    public Void loop(ForLoopUmlNode loop) {
        UmlCell cell = factory.forLoop(loop, x(loop), y(loop));
        cells.put(loop, cell);
        draw(cell);
        return null;
    }

    private void connectAll() {
        for (Map.Entry<UmlNode, UmlCell> entry : cells.entrySet()) {
            for (Edge edge : entry.getKey().getEdges()) {
                if (edge.getSource() != entry.getKey()) {
                    continue;
                }
                UmlCell source = entry.getValue();
                UmlCell target = cells.get(edge.getDestination());
                if (null == target) {
                    Log.shared.error(
                        Strings.$("Unknown edge target from ", entry.getKey(), " (", entry.getKey().getId(), ")"));
                    continue;
                }
                factory.connect(source, target, (UmlEdge)edge);
            }
        }
    }

}
