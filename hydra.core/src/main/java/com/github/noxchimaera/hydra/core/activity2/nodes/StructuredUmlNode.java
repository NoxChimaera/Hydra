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
import com.github.noxchimaera.hydra.core.activity2.UmlVisitor;
import com.github.noxchimaera.hydra.core.activity2.commons.HasInput;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecification;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.core.graph.NodeType;
import com.github.noxchimaera.hydra.utils.ListUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @author Nox
 */
public abstract class StructuredUmlNode extends UmlNode implements HasInput, HasOutput, Serializable {

    protected ControlflowUmlEdge input;
    protected ControlflowUmlEdge output;

    protected Set<String> regions;
    protected Map<String, ControlflowUmlEdge> regionRoots;

    public StructuredUmlNode(long id, NodeType type, String value, UmlNodeSpecification specification) {
        super(id, type, value, specification);
        regions = new HashSet<>();
        regionRoots = new HashMap<>();
    }

    @Override
    public void setOutput(String id, ControlflowUmlEdge edge) {
        setConnection(id, edge);
    }

    @Override
    public ControlflowUmlEdge getOutput(String name) {
        if (name.equals("")) {
            return output;
        } else {
            return regionRoots.get(name);
        }
    }

    public void setConnection(String connection, ControlflowUmlEdge edge) {
        if (connection.equals("")) {
            output = edge;
        } else {
            if (edge == null) {
                regionRoots.remove(connection);
            } else {
                regionRoots.put(connection, edge);
            }
        }
    }

    public List<String> getEmptyConnections() {
        List<String> lst = new ArrayList<>(regions);
        for (String key : regionRoots.keySet()) {
            lst.remove(key);
        }
        if (output == null) {
            lst.add(0, "");
        }
        return lst;
    }

    @Override
    public void setInput(ControlflowUmlEdge edge) {
        input = edge;
    }

    @Override
    public ControlflowUmlEdge getInput() {
        return input;
    }

}
