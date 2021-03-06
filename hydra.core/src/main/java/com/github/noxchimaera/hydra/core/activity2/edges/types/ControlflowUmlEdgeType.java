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

package com.github.noxchimaera.hydra.core.activity2.edges.types;

import com.github.noxchimaera.hydra.core.activity2.UmlEdge;
import com.github.noxchimaera.hydra.core.activity2.UmlGraph;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.commons.HasInput;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.activity2.nodes.StructuredUmlNode;
import com.github.noxchimaera.hydra.utils.Contracts;

/**
 * @author Nox
 */
public class ControlflowUmlEdgeType extends UmlEdgeType {

    public ControlflowUmlEdgeType() {
        super("uml_controlflow");
    }

    @Override
    public UmlEdge create(UmlNode source, UmlNode target, UmlGraph factory, String name) {
        if (!Contracts.is(HasOutput.class, source) && !Contracts.is(StructuredUmlNode.class, source)) {
            UmlGraph.LOGGER.severe(String.format(
                "Can not create Controlflow edge for `%s :: %s` source", source, source.getClass()));
            return null;
        }
        if (!(target instanceof HasInput)) {
            UmlGraph.LOGGER.severe(String.format(
                "Can not create Controlflow edge for `%s :: %s` target", target, target.getClass()));
            return null;
        }
        return factory.flow((UmlNode & HasOutput)source, (UmlNode & HasInput)target, name);
    }

    @Override
    public void remove(UmlEdge edge) {
        UmlNode source = edge.getSource();
        UmlNode target = edge.getDestination();
        if (!(source instanceof HasOutput)) {
            UmlGraph.LOGGER.severe(
                String.format("Can not remove Controlflow edge for `%s :: %s` source", source, source.getClass()));
            return;
        }
        if (!(target instanceof HasInput)) {
            UmlGraph.LOGGER.severe(
                String.format("Can not remove Controlflow edge for `%s :: %s` target", target, target.getClass()));
            return;
        }

        ControlflowUmlEdge cf = ((ControlflowUmlEdge)edge);
        ((HasOutput)source).setOutput(cf.getGuard(), null);
        ((HasInput)target).setInput(null);
    }

}
