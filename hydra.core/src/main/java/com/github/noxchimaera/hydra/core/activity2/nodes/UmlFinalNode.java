/*
 * Copyright 2016 Max Balushkin.
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

import java.util.Collection;

/**
 * @author Max Balushkin
 */
public class UmlFinalNode extends UmlNode implements IUmlControlflow {

    private UmlControlflowEdge prev;

    public UmlFinalNode(long id) {
        super(id);
    }

    @Override public UmlControlflowEdge getNext() {
        return null;
    }

    @Override public void setNext(UmlControlflowEdge edge) { }

    @Override public UmlControlflowEdge getPrev() {
        return prev;
    }

    @Override public void setPrev(UmlControlflowEdge prev) {
        this.prev = prev;
    }

    @Override public Collection<UmlEdge> getInputs() {
        return null;
    }

    @Override public Collection<UmlEdge> getOutputs() {
        return null;
    }
}
