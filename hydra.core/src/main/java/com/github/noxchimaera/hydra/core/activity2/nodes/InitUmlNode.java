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
import com.github.noxchimaera.hydra.core.activity2.UmlVisitor;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecifications;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.utils.Collections;
import com.github.noxchimaera.hydra.utils.Contracts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
public class InitUmlNode extends UmlNode implements HasOutput {

    private ControlflowUmlEdge output;

    public InitUmlNode(long id) {
        super(id, UmlNodeTypes.Uml, "init", UmlNodeSpecifications.Init);
    }

    @Override
    public void setOutput(String id, ControlflowUmlEdge edge) {
        output = edge;
    }

    @Override
    public ControlflowUmlEdge getOutput(String name) {
        return output;
    }

    @Override
    public List<Edge> edges() {
        return Collections.<Edge>toList(true, output);
    }

    @Override
    public UmlNode copy() {
        InitUmlNode clone = new InitUmlNode(id());
        clone.value = value;
        clone.output = output;
        clone.view = view;
        clone.stereotype = Stereotype.from(stereotype);
        return clone;
    }

    @Override
    public <T> T accept(UmlVisitor<T> visitor) {
        return visitor.init(this);
    }

}
