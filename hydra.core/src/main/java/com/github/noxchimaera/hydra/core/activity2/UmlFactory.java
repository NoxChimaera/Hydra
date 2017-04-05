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

import com.github.noxchimaera.hydra.core.activity2.commons.IHasInput;
import com.github.noxchimaera.hydra.core.activity2.commons.IHasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.nodes.*;

/**
 * @author Nox
 */
public class UmlFactory {

    private long nodeId = -1;
    private long edgeId = -1;

    private
    long next() {
        return ++nodeId;
    }

    public
    InitUmlNode init() {
        return new InitUmlNode(next());
    }

    public
    FinUmlNode fin() {
        return new FinUmlNode(next());
    }

    public
    ActionUmlNode action(String effect) {
        return new ActionUmlNode(next(), effect);
    }

    public
    LoopStructuredUmlNode loop() {
        return new LoopStructuredUmlNode(next());
    }

    public
    ConditionStructuredUmlNode cond() {
        return new ConditionStructuredUmlNode(next());
    }

    public <TSrc extends UmlNode & IHasOutput, TDst extends UmlNode & IHasInput>
    void flow(TSrc src, TDst dst) {
        ControlflowUmlEdge edge = new ControlflowUmlEdge(++edgeId);
        edge.setSource(src);
        edge.setDestination(dst);

        src.setOutput(edge);
        dst.setInput(edge);
    }

}
