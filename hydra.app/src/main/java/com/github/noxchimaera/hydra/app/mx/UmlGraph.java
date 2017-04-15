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
import com.github.noxchimaera.hydra.utils.Contracts;
import com.github.noxchimaera.hydra.utils.properties.MutableProperty;
import com.github.noxchimaera.hydra.utils.properties.Property;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.view.mxGraph;

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
    }

    public UmlCellFactory getCellFactory() {
        return cellFactory;
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

    }

    @Override
    public boolean isCellEditable(Object cell) {
        return cell instanceof UmlCell;
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
        if (cell == null || !isCellConnectable(cell)) {
            return false;
        }

        return super.isValidSource(cell);
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
            return super.isValidConnection(rawSrc, rawDst);
        }
        return cf.check(srcNode, EdgeFlowDirection.Output);
    }

}
