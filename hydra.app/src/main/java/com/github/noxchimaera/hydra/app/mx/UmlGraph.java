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
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.UmlEdge;
import com.github.noxchimaera.hydra.core.activity2.UmlEdgeTypes;
import com.github.noxchimaera.hydra.core.activity2.UmlFactory;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.edges.types.UmlEdgeType;
import com.github.noxchimaera.hydra.core.activity2.nodes.StructuredUmlNode;
import com.github.noxchimaera.hydra.core.activity2.specification.cardinality.ControlflowUmlCardinalitySpecification;
import com.github.noxchimaera.hydra.core.graph.EdgeFlowDirection;
import com.github.noxchimaera.hydra.core.specification.cardinality.ConnectionCardinalitySpecification;
import com.github.noxchimaera.hydra.utils.Contracts;
import com.github.noxchimaera.hydra.utils.properties.MutableProperty;
import com.github.noxchimaera.hydra.utils.properties.Property;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.view.mxGraph;

import static com.github.noxchimaera.hydra.app.mx.utils.GraphxEventUtils.*;

import java.util.Map;

/**
 * @author Nox
 */
public class UmlGraph extends mxGraph {

    public final MutableProperty<UmlEdgeType> CurrentEdgeType;

    private UmlCellFactory cellFactory;

    private UmlFactory umlFactory;

    public UmlGraph(UmlFactory umlFactory) {
        super();
        allowDanglingEdges = false;
        multigraph = false;
        portsEnabled = false;

        CurrentEdgeType = new MutableProperty<>(UmlEdgeTypes.Controlflow);

        cellFactory = new UmlCellFactory(this, umlFactory);
        this.umlFactory = umlFactory;

        addListener(mxEvent.CELL_CONNECTED, this::onCellConnected);
        addListener(mxEvent.CELLS_REMOVED, this::onCellRemoved);

        Map<String, Object> style = stylesheet.getDefaultVertexStyle();
        style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        style.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
        style.put(mxConstants.STYLE_STROKECOLOR, "#AAAAAA");

        stylesheet.getDefaultEdgeStyle()
            .put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }

    public UmlCellFactory getCellFactory() {
        return cellFactory;
    }

    public UmlFactory getUmlFactory() {
        return umlFactory;
    }

    private void onCellConnected(Object sender, mxEventObject evt) {
        // Handles only source connection event
        if ((Boolean)evt.getProperty("source")) {
            return;
        }
        mxCell edge = (mxCell)evt.getProperty("edge");
        UmlCell source = (UmlCell)edge.getSource();
        UmlCell target = (UmlCell)edge.getTarget();
        cellFactory.connect(source, target, CurrentEdgeType.get());
    }

    private void onCellRemoved(Object sender, mxEventObject evt) {
        mxCell[] mxEdges = edges(cells(evt));
        for (mxCell mxEdge : mxEdges) {
            UmlCell source = (UmlCell)mxEdge.getSource();
            UmlCell target = (UmlCell)mxEdge.getTarget();

            UmlNode umlSource = source.getUserObject();
            UmlNode umlTarget = target.getUserObject();

            umlSource.getEdges().stream()
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
        if (cell instanceof UmlCell) {
            return ((UmlCell) cell).getUserObject() instanceof StructuredUmlNode;
        }
        return false;
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
            spec = umlNode.getSpecification().ControlflowCardinality.get();
        }

        if (spec == null) {
            return false;
        }
        return spec.check(umlNode, EdgeFlowDirection.Output);
    }

    @Override
    public boolean isValidTarget(Object cell) {
        return super.isValidTarget(cell);
    }

    @Override
    public boolean isValidConnection(Object rawSrc, Object rawDst) {
        if (!Contracts.is(UmlCell.class, rawSrc, rawDst)) {
            return false;
        }

        UmlCell src = (UmlCell)rawSrc;
        UmlCell dst = (UmlCell)rawDst;

        UmlNode srcNode = src.getUserObject();
        ControlflowUmlCardinalitySpecification cf = srcNode.getSpecification().ControlflowCardinality.get();
        if (cf == null) {
            // Default behaviour?
            return false;
            // return super.isValidConnection(rawSrc, rawDst);
        }
        return cf.check(srcNode, EdgeFlowDirection.Output);
    }

}