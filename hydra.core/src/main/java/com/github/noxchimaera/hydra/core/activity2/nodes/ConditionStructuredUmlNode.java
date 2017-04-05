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

import com.github.noxchimaera.hydra.core.activity2.UmlNodeTypes;
import com.github.noxchimaera.hydra.core.graph.NodeType;

/**
 * @author Nox
 */
public class ConditionStructuredUmlNode extends StructuredUmlNode {

    public
    ConditionStructuredUmlNode(long id) {
        super(id, UmlNodeTypes.Uml, "");
        regions.add(new StructuredUmlNodeRegion("Test"));
        regions.add(new StructuredUmlNodeRegion("Body"));
    }

}
