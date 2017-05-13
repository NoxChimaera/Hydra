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

import com.github.noxchimaera.hydra.app.UmlCellFactory;
import com.github.noxchimaera.hydra.app.events.EventBus;
import com.github.noxchimaera.hydra.app.events.RegionConnectionEvent;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.*;
import com.github.noxchimaera.hydra.core.activity2.edges.types.UmlEdgeType;
import com.github.noxchimaera.hydra.core.graph.EdgeFlowDirection;
import com.github.noxchimaera.hydra.core.specification.cardinality.ConnectionCardinalitySpecification;
import com.github.noxchimaera.hydra.utils.Contracts;
import com.github.noxchimaera.hydra.utils.properties.MutableProperty;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.*;
import com.mxgraph.view.mxGraph;

import static com.github.noxchimaera.hydra.app.mx.utils.GraphxEventUtils.*;

import java.util.Map;

/**
 * @author Nox
 */
public class UmlMxGraph extends mxGraph {

    public final MutableProperty<UmlEdgeType> CurrentEdgeType;

    private UmlCellFactory cellFactory;

    private com.github.noxchimaera.hydra.core.activity2.UmlGraph umlGraph;

    public UmlMxGraph(com.github.noxchimaera.hydra.core.activity2.UmlGraph umlGraph) {
        super();
        allowDanglingEdges = false;
        multigraph = false;
        portsEnabled = false;

        CurrentEdgeType = new MutableProperty<>(UmlEdgeTypes.Controlflow);

        cellFactory = new UmlCellFactory(this, umlGraph);
        this.umlGraph = umlGraph;

        setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical;");

        addListener(mxEvent.CELL_CONNECTED, this::onCellConnected);
        addListener(mxEvent.CELLS_REMOVED, this::onCellRemoved);

        Map<String, Object> style = stylesheet.getDefaultVertexStyle();
        style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        style.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
        style.put(mxConstants.STYLE_STROKECOLOR, "#AAAAAA");

        stylesheet.getDefaultEdgeStyle()
            .put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }

    private DrawSection draw() {
        return new DrawSection(model);
    }

    public UmlCellFactory getCellFactory() {
        return cellFactory;
    }

    public com.github.noxchimaera.hydra.core.activity2.UmlGraph getUmlGraph() {
        return umlGraph;
    }

    private void onCellConnected(Object sender, mxEventObject evt) {
        // Handles only source connection event
        if ((Boolean)evt.getProperty("source")) {
            return;
        }
        mxCell edge = (mxCell)evt.getProperty("edge");
        if (Contracts.is(UmlEdge.class, edge.getValue())) {
            // Connection was already handled
            return;
        }

        UmlCell source = (UmlCell)edge.getSource();
        UmlCell target = (UmlCell)edge.getTarget();

        // Hello darkness, my old friend...
        if (UmlNodeTypes.RegionHeader == source.getUmlNode().type()) {
            if (!Contracts.is(UmlEdge.class, edge.getValue())) {
                EventBus.Shared.post(new RegionConnectionEvent(source, target, CurrentEdgeType.get(), cellFactory));
                model.remove(edge);
                return;
            }
        }

        model.remove(edge);
        cellFactory.connect(source, target, CurrentEdgeType.get());
    }

    private void onCellRemoved(Object sender, mxEventObject evt) {
        mxCell[] mxEdges = edges(cells(evt));
        for (mxCell mxEdge : mxEdges) {
            UmlCell source = (UmlCell)mxEdge.getSource();
            UmlCell target = (UmlCell)mxEdge.getTarget();

            UmlNode umlSource = source.getUserObject();
            UmlNode umlTarget = target.getUserObject();

            umlSource.edges().stream()
                .filter(edge -> edge.getSource() == umlSource && edge.getDestination() == umlTarget)
                .findFirst()
                .ifPresent(edge -> {
                    ((UmlEdgeType)edge.getType()).remove(((UmlEdge)edge));
                });
        }
    }

    @Override
    public boolean isCellEditable(Object cell) {
        return false;
        // return cell instanceof UmlCell;
    }

    @Override
    public boolean isCellFoldable(Object cell, boolean collapse) {
        return super.isCellFoldable(cell, collapse);
    }

    @Override
    public boolean isValidSource(Object cell) {
        if (cell == null || !Contracts.is(UmlCell.class, cell)) {
            return false;
        }
        if (!isCellConnectable(cell)) {
            return false;
        }

        UmlCell umlCell = (UmlCell)cell;
        UmlNode umlNode = umlCell.getUserObject();

        UmlEdgeType edgeType = CurrentEdgeType.get();
        ConnectionCardinalitySpecification spec = null;
        if (edgeType == UmlEdgeTypes.Controlflow) {
            spec = umlNode.specification().ControlflowCardinality.get();
        }

        if (spec == null) {
            return false;
        }
        return spec.check(umlNode, EdgeFlowDirection.Output);
    }

    @Override
    public boolean isValidTarget(Object cell) {
        if (cell == null || !Contracts.is(UmlCell.class, cell)) {
            return false;
        }
        if (!isCellConnectable(cell)) {
            return false;
        }

        UmlCell umlCell = (UmlCell)cell;
        UmlNode umlNode = umlCell.getUserObject();

        UmlEdgeType edgeType = CurrentEdgeType.get();
        ConnectionCardinalitySpecification spec = null;
        if (edgeType == UmlEdgeTypes.Controlflow) {
            spec = umlNode.specification().ControlflowCardinality.get();
        }

        if (spec == null) {
            return false;
        }
        return spec.check(umlNode, EdgeFlowDirection.Input);
    }

    @Override
    public boolean isValidConnection(Object rawSrc, Object rawDst) {
        if (!Contracts.is(UmlCell.class, rawSrc, rawDst)) {
            return false;
        }

        return super.isValidConnection(rawSrc, rawDst);
    }

}
