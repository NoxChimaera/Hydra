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
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecification;
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecifications;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.core.graph.NodeType;
import com.github.noxchimaera.hydra.utils.ListUtils;

import javax.swing.*;
import java.util.*;

/**
 * @author Nox
 */
public class LoopUmlNode extends StructuredUmlNode {

    public final String Setup = "Setup";
    public final String Test = "Test";
    public final String Body = "Body";

    public LoopUmlNode(long id) {
        super(id, UmlNodeTypes.RegionHeader, "", UmlNodeSpecifications.Fake);
    }

    @Override
    public UmlNode deepClone() {
        LoopUmlNode clone = new LoopUmlNode(getId());
        clone.input = input;
        clone.output = output;
        clone.regions = new HashSet<>(regions);
        clone.regionRoots = new HashMap<>(regionRoots);

        clone.view = view;

        clone.stereotypes = new ArrayList<>(stereotypes);
        return clone;
    }

    @Override
    public void accept(UmlVisitor visitor) {
        visitor.loop(this);
    }

    @Override
    public List<Edge> getEdges() {
        return ListUtils.<Edge>toList(true,
            input, output,
            regionRoots.get(Setup), regionRoots.get(Test), regionRoots.get(Body));
    }

}
