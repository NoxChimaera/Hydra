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
import com.github.noxchimaera.hydra.core.activity2.commons.HasInput;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecifications;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.core.graph.NodeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Nox
 */
public abstract class StructuredUmlNode extends UmlNode implements HasInput, HasOutput {

    protected ControlflowUmlEdge input;
    protected ControlflowUmlEdge output;

    protected List<StructuredUmlNodeRegion> regions;

    public StructuredUmlNode(long id, NodeType type, String value) {
        super(id, type, value, UmlNodeSpecifications.Fake);
        regions = new ArrayList<>();
    }

    @Override
    public void setInput(ControlflowUmlEdge edge) {
        input = edge;
    }

    @Override
    public ControlflowUmlEdge getInput() {
        return input;
    }

    @Override
    public void
    setOutput(ControlflowUmlEdge edge) {
        output = edge;
    }

    @Override
    public ControlflowUmlEdge getOutput() {
        return output;
    }

    public Optional<StructuredUmlNodeRegion> getRegion(String regionName) {
        return regions.stream()
            .filter(region -> region.getName().equals(regionName))
            .findFirst();
    }

    public List<StructuredUmlNodeRegion> getRegions() {
        return new ArrayList<>(regions);
    }

    @Override
    public List<Edge> getEdges() {
        return Arrays.asList(input, output);
    }

}
