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

package com.github.noxchimaera.hydra.core.activity2;

import com.github.noxchimaera.hydra.core.activity2.edges.types.ControlflowUmlEdgeType;
import com.github.noxchimaera.hydra.core.activity2.edges.types.UmlEdgeType;
import com.github.noxchimaera.hydra.core.graph.EdgeType;

/**
 * @author Nox
 */
public class UmlEdgeTypes {

    public final static UmlEdgeType Controlflow = new ControlflowUmlEdgeType();
    public final static EdgeType Comment = new EdgeType("uml_comment");

}
