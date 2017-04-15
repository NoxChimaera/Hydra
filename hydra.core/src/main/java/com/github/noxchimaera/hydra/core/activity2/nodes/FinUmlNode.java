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

package com.github.noxchimaera.hydra.core.activity2.nodes;

import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.UmlNodeTypes;
import com.github.noxchimaera.hydra.core.activity2.commons.HasInput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecifications;
import com.github.noxchimaera.hydra.core.graph.Edge;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nox
 */
public class FinUmlNode extends UmlNode implements HasInput {

    private ControlflowUmlEdge input;

    public FinUmlNode(long id) {
        super(id, UmlNodeTypes.Uml, "", UmlNodeSpecifications.Fake);
    }

    public ControlflowUmlEdge getInput() {
        return input;
    }

    public void setInput(ControlflowUmlEdge input) {
        this.input = input;
    }

    @Override
    public List<Edge> getEdges() {
        return Arrays.asList(input);
    }

}
