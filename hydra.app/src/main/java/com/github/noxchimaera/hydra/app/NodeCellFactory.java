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

import com.github.noxchimaera.hydra.app.models.NodeCell;
import com.github.noxchimaera.hydra.app.models.NodePort;
import com.github.noxchimaera.hydra.app.mx.DrawSection;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.FinUmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.InitUmlNode;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;

/**
 * @author Nox
 */
public class NodeCellFactory {

    private final mxGraph g;
    private final mxIGraphModel m;

    public
    NodeCellFactory(mxGraph graph) {
        g = graph;
        m = graph.getModel();
    }

    private DrawSection draw() {
        return new DrawSection(m);
    }

    public
    NodeCell init(InitUmlNode value, double x, double y) {
        try (DrawSection loop = draw()) {
            NodeCell c = new NodeCell(value, x, y, 60, 60);
            c.setStyle("shape=ellipse");
            NodePort p = new NodePort(NodePort.Type.Output, c, "out", 1, 0.5);
            g.addCell(p, c);
            return c;
        }
    }

    public
    NodeCell fin(FinUmlNode value, double x, double y) {
        try (DrawSection loop = draw()) {
            NodeCell c = new NodeCell(value, x, y, 60, 60);
            c.setStyle("shape=ellipse");
            NodePort p = new NodePort(NodePort.Type.Input, c, "in", 0, 0.5);
            g.addCell(p, c);
            return c;
        }
    }

}
