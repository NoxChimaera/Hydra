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

package com.github.noxchimaera.hydra.app;

import com.github.noxchimaera.hydra.app.mx.style.CellRounded;
import com.github.noxchimaera.hydra.app.mx.style.CellSpacing;
import com.github.noxchimaera.hydra.app.mx.style.align.CellLabelAlignment;
import com.github.noxchimaera.hydra.app.mx.style.align.CellLabelPosition;
import com.github.noxchimaera.hydra.app.mx.style.align.HorizontalAlignment;
import com.github.noxchimaera.hydra.app.mx.style.align.VerticalAlignment;
import com.github.noxchimaera.hydra.app.mx.style.shape.CellShape;
import com.github.noxchimaera.hydra.app.mx.style.shape.CellShapes;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.app.mx.DrawSection;
import com.github.noxchimaera.hydra.core.activity2.UmlEdge;
import com.github.noxchimaera.hydra.core.activity2.UmlFactory;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.edges.types.UmlEdgeType;
import com.github.noxchimaera.hydra.core.activity2.nodes.ActionUmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.ConditionalUmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.FinUmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.InitUmlNode;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;

/**
 * @author Nox
 */
public class UmlCellFactory {

    private final mxGraph g;
    private final mxIGraphModel m;

    private UmlFactory uml;

    public UmlCellFactory(mxGraph graph, UmlFactory uml) {
        g = graph;
        m = graph.getModel();
        this.uml = uml;
    }

    private DrawSection draw() {
        return new DrawSection(m);
    }

    public void connect(UmlCell source, UmlCell target, UmlEdgeType edgeType) {
        UmlNode umlSource = source.getUserObject();
        UmlNode umlTarget = target.getUserObject();

        edgeType.create(umlSource, umlTarget, uml, "");
    }

    public void connect(UmlCell source, UmlCell target, UmlEdgeType edgeType, String value) {
        UmlNode umlSource = source.getUserObject();
        UmlNode umlTarget = target.getUserObject();

        UmlEdge edge = edgeType.create(umlSource, umlTarget, uml, value);
        try (DrawSection $ = draw()) {
            g.insertEdge(g.getDefaultParent(), null, edge, source, target);
        }
    }

    public UmlCell init(InitUmlNode value, double x, double y) {
        UmlCell c = new UmlCell(value, x, y, 60, 60);
        c.clearStyle()
            .addStyle(new CellShape(CellShapes.DoubleEllipse));
        return c;
    }

    public UmlCell action(ActionUmlNode value, double x, double y) {
        UmlCell c = new UmlCell(value, x, y, 120, 60);
        c.clearStyle()
            .addStyle(new CellShape(CellShapes.Rectangle))
            .addStyle(new CellRounded(true))
            .addStyle(new CellLabelPosition(HorizontalAlignment.Centre, VerticalAlignment.Centre))
            .addStyle(new CellLabelAlignment(HorizontalAlignment.Left, VerticalAlignment.Centre))
            .addStyle(new CellSpacing(16));
        return c;
    }

    public UmlCell fin(FinUmlNode value, double x, double y) {
        UmlCell c = new UmlCell(value, x, y, 60, 60);
        c.clearStyle()
            .addStyle(new CellShape(CellShapes.DoubleEllipse));
        return c;
    }

    public UmlCell cond(ConditionalUmlNode value, double x, double y) {
        UmlCell c = new UmlCell(value, x, y, 120, 60);
        c.clearStyle()
            .addStyle(new CellShape(CellShapes.DoubleRectangle));
        return c;
    }

}
