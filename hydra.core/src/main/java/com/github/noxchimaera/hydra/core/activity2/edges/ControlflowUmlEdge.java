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

package com.github.noxchimaera.hydra.core.activity2.edges;

import com.github.noxchimaera.hydra.core.activity2.UmlEdge;
import com.github.noxchimaera.hydra.core.activity2.UmlEdgeTypes;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.obsolete.nodes.UmlControlflowEdge;
import com.github.noxchimaera.hydra.core.graph.EdgeDirection;
import com.github.noxchimaera.hydra.core.graph.EdgeType;

/**
 * @author Nox
 */
public class ControlflowUmlEdge extends UmlEdge {

    public
    ControlflowUmlEdge(long id) {
        super(id, UmlEdgeTypes.Controlflow, null, null);
    }

    public
    ControlflowUmlEdge(long id, UmlNode src, UmlNode dst) {
        super(id, UmlEdgeTypes.Controlflow, src, dst);
    }

    public
    ControlflowUmlEdge(long id, UmlNode src, UmlNode dst, EdgeDirection direction) {
        super(id, UmlEdgeTypes.Controlflow, src, dst, direction);
    }

}
