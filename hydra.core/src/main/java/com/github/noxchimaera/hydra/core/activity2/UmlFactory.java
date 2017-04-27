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

import java.util.logging.Logger;

/**
 * @author Nox
 */
public class UmlFactory {

    public static Logger LOGGER = Logger.getLogger("UmlFactory");

    private long nodeId = -1;
    private long edgeId = -1;

    private long next() {
        return ++nodeId;
    }

    public InitUmlNode init() {
        return new InitUmlNode(next());
    }

    public FinUmlNode fin() {
        return new FinUmlNode(next());
    }

    public ActionUmlNode action(String effect) {
        return new ActionUmlNode(next(), effect);
    }

    public LoopUmlNode loop() {
        return new LoopUmlNode(next());
    }

    public ConditionalUmlNode cond() {
        return new ConditionalUmlNode(next());
    }

    public <TSrc extends StructuredUmlNode, TDst extends UmlNode & HasInput>
    UmlEdge flow(TSrc src, TDst dst, String region) {
        ControlflowUmlEdge edge = new ControlflowUmlEdge(++edgeId);
        edge.setGuard(region);
        edge.setSource(src);
        edge.setDestination(dst);

        src.setConnection(region, edge);
        dst.setInput(edge);

        return edge;
    }

    public <TSrc extends UmlNode & HasOutput, TDst extends UmlNode & HasInput>
    UmlEdge flow(TSrc src, TDst dst) {
        ControlflowUmlEdge edge = new ControlflowUmlEdge(++edgeId);
        edge.setSource(src);
        edge.setDestination(dst);

        src.setOutput(edge);
        dst.setInput(edge);

        return edge;
    }

}
