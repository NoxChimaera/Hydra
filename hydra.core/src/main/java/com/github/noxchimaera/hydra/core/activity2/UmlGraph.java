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

package com.github.noxchimaera.hydra.core.activity2;

import com.github.noxchimaera.hydra.core.activity2.commons.HasInput;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.nodes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Nox
 */
public class UmlGraph {

    public static Logger LOGGER = Logger.getLogger("UmlGraph");

    private long nodeId = -1;
    private long edgeId = -1;

    private List<UmlNode> nodes;

    public UmlGraph() {
        nodes = new ArrayList<>();
    }

    private <T extends UmlNode> T add(T node) {
        nodes.add(node);
        return node;
    }

    private long next() {
        return ++nodeId;
    }

    public InitUmlNode init() {
        return add(new InitUmlNode(next()));
    }

    public FinUmlNode fin() {
        return add(new FinUmlNode(next()));
    }

    public ActionUmlNode action(String effect) {
        return add(new ActionUmlNode(next(), effect));
    }

    public ForLoopUmlNode loop() {
        return add(new ForLoopUmlNode(next()));
    }

    public ConditionalUmlNode cond() {
        return add(new ConditionalUmlNode(next()));
    }

    public <TSrc extends UmlNode & HasOutput, TDst extends UmlNode & HasInput>
    UmlEdge flow(TSrc src, TDst dst, String name) {
        ControlflowUmlEdge edge = new ControlflowUmlEdge(++edgeId);
        edge.setGuard(name);
        edge.setSource(src);
        edge.setDestination(dst);

        src.setOutput(name, edge);
        dst.setInput(edge);

        return edge;
    }

    public List<UmlNode> getNodes() {
        return new ArrayList<>(nodes);
    }

}
