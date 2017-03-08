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

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author Max Balushkin
 */
public class UmlActionNode extends UmlNode implements IUmlControlflow {

    private UmlControlflowEdge prev;
    private UmlControlflowEdge next;

    private String effect;

    public UmlActionNode(long id) {
        super(id);
    }

    @Override public UmlControlflowEdge getPrev() {
        return prev;
    }

    @Override public void setPrev(UmlControlflowEdge prev) {
        this.prev = prev;
    }

    @Override public UmlControlflowEdge getNext() {
        return next;
    }

    @Override public void setNext(UmlControlflowEdge next) {
        this.next = next;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override public Collection<UmlEdge> getInputs() {
        return Arrays.asList(prev);
    }

    @Override public Collection<UmlEdge> getOutputs() {
        return Arrays.asList(next);
    }

}
