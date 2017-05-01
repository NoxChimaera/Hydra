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
import com.github.noxchimaera.hydra.core.activity2.commons.HasInput;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecifications;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nox
 */
public class ActionUmlNode extends UmlNode implements HasInput, HasOutput {

    private ControlflowUmlEdge input;
    private ControlflowUmlEdge output;

    public ActionUmlNode(long id, String value) {
        super(id, UmlNodeTypes.Uml, value, UmlNodeSpecifications.Action);
    }

    public ControlflowUmlEdge getInput() {
        return input;
    }

    public void setInput(ControlflowUmlEdge input) {
        this.input = input;
    }

    public ControlflowUmlEdge getOutput(String name) {
        return output;
    }

    public void setOutput(String id, ControlflowUmlEdge output) {
        this.output = output;
    }

    @Override
    public List<Edge> getEdges() {
        return ListUtils.<Edge>toList(true, input, output);
    }

    @Override
    public UmlNode deepClone() {
        ActionUmlNode clone = new ActionUmlNode(getId(), value);
        clone.input = input;
        clone.output = output;

        clone.view = view;

        clone.stereotypes = new ArrayList<>(stereotypes);
        return clone;
    }

    @Override
    public <T> T accept(UmlVisitor<T> visitor) {
        return visitor.action(this);
    }

}
