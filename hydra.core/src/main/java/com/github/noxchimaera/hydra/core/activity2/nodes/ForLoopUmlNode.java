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
import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecifications;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.utils.Collections;
import com.github.noxchimaera.hydra.utils.Contracts;

import java.util.*;

/**
 * @author Nox
 */
public class ForLoopUmlNode extends StructuredUmlNode {

    public static final String Setup = "Setup";
    public static final String Test = "Test";
    public static final String Step = "Step";
    public static final String Body = "Body";

    public ForLoopUmlNode(long id) {
        super(id, UmlNodeTypes.RegionHeader, "For-Loop", UmlNodeSpecifications.Loop);
        regions.add(Setup);
        regions.add(Test);
        regions.add(Step);
        regions.add(Body);
    }

    @Override
    public UmlNode copy() {
        ForLoopUmlNode clone = new ForLoopUmlNode(id());
        clone.input = input;
        clone.output = output;
        clone.regions = new HashSet<>(regions);
        clone.regionRoots = new HashMap<>(regionRoots);

        clone.view = view;

        clone.stereotype = Stereotype.from(stereotype);
        return clone;
    }

    @Override
    public <T> T accept(UmlVisitor<T> visitor) {
        return visitor.loop(this);
    }

    @Override
    public List<Edge> edges() {
        return Collections.<Edge>toList(true,
            input, output,
            regionRoots.get(Setup), regionRoots.get(Test), regionRoots.get(Step), regionRoots.get(Body));
    }

}
