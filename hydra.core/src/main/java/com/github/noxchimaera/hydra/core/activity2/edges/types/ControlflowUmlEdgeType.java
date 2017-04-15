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
import com.github.noxchimaera.hydra.core.activity2.UmlFactory;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.commons.HasInput;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;

import java.util.logging.Logger;

/**
 * @author Nox
 */
public class ControlflowUmlEdgeType extends UmlEdgeType {

    public ControlflowUmlEdgeType() {
        super("uml_controlflow");
    }

    @Override
    public UmlEdge create(UmlNode source, UmlNode target, UmlFactory factory) {
        if (!(source instanceof HasOutput)) {
            UmlFactory.LOGGER.severe(String.format("Can not create Controlflow edge for {0} source", source));
            return null;
        }
        if (!(target instanceof HasInput)) {
            UmlFactory.LOGGER.severe(String.format("Can not create Controlflow edge for {0} target", target));
            return null;
        }

        return factory.flow((UmlNode & HasOutput) source, (UmlNode & HasInput) target);
    }

}
