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

import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.nodes.*;

/**
 * Assumption: there is no any loop in an input graph.
 *
 * @author Nox
 */
public class AbstractUmlVisitor implements UmlVisitor {

    private void nullsafeFlow(ControlflowUmlEdge edge) {
        if (edge != null && edge.getDestination() != null) {
            edge.getDestination().accept(this);
        }
    }

    @Override
    public void init(InitUmlNode init) {
        nullsafeFlow(init.getOutput(""));
    }

    @Override
    public void fin(FinUmlNode fin) { }

    @Override
    public void action(ActionUmlNode action) {
        nullsafeFlow(action.getOutput(""));
    }

    @Override
    public void condition(ConditionalUmlNode condition) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void loop(LoopUmlNode loop) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
