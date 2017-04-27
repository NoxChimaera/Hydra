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

package com.github.noxchimaera.hydra.app.events;

import com.github.noxchimaera.hydra.app.UmlCellFactory;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.edges.types.UmlEdgeType;

/**
 * @author Nox
 */
public class RegionConnectionEvent {

    private boolean consumed;

    private UmlCell source;
    private UmlCell target;
    private UmlEdgeType edgeType;

    private UmlCellFactory factory;

    public RegionConnectionEvent(UmlCell source, UmlCell target, UmlEdgeType edgeType, UmlCellFactory factory) {
        this.source = source;
        this.target = target;
        this.edgeType = edgeType;
        this.factory = factory;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public UmlCell getSource() {
        return source;
    }

    public UmlCell getTarget() {
        return target;
    }

    public UmlEdgeType getEdgeType() {
        return edgeType;
    }

    public UmlCellFactory getFactory() {
        return factory;
    }

    public void consume() {
        consumed = true;
    }

}
