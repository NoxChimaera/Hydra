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

import com.github.noxchimaera.hydra.core.activity2.UmlEdgeTypes;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.UmlNodeTypes;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecification;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecifications;
import com.github.noxchimaera.hydra.core.activity2.specification.cardinality.ControlflowUmlCardinalitySpecification;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.core.graph.EdgeDirection;
import com.github.noxchimaera.hydra.core.graph.EdgeFlowDirection;
import com.github.noxchimaera.hydra.core.specification.cardinality.ConnectionCardinality;
import com.github.noxchimaera.hydra.core.specification.cardinality.ConnectionCardinalitySpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nox
 */
public class InitUmlNode extends UmlNode implements HasOutput {

    private ControlflowUmlEdge output;

    public InitUmlNode(long id) {
        super(id, UmlNodeTypes.Uml, "init", UmlNodeSpecifications.Init);
    }

    public ControlflowUmlEdge getOutput() {
        return output;
    }

    public void setOutput(ControlflowUmlEdge output) {
        this.output = output;
    }

    @Override
    public List<Edge> getEdges() {
        if (output == null) {
            return Arrays.asList();
        } else {
            return Arrays.asList(output);
        }
    }

    @Override
    public UmlNode deepClone() {
        InitUmlNode clone = new InitUmlNode(getId());
        clone.value = value;
        clone.output = output;

        // copy?
        clone.view = view;

        clone.stereotypes = new ArrayList<>(stereotypes);
        return clone;
    }

}
