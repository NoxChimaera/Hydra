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

package com.github.noxchimaera.hydra.core.activity2.obsolete.nodes;

import com.github.noxchimaera.hydra.core.activity2.obsolete.IUmlVisitor;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Max Balushkin
 */
public class UmlDecisionNode extends UmlNode implements IHasInput {

    private UmlControlflowEdge prev;

    private UmlControlflowEdge nextLeft;
    private UmlControlflowEdge nextRight;

    public UmlDecisionNode(long id) {
        super(id);
    }

    @Override public Collection<UmlEdge> getInputs() {
        return Arrays.asList(prev);
    }

    @Override public Collection<UmlEdge> getOutputs() {
        return Arrays.asList(nextLeft, nextRight);
    }

    @Override public void setPrev(UmlControlflowEdge prev) {
        this.prev = prev;
    }

    @Override public UmlControlflowEdge getPrev() {
        return prev;
    }

    public UmlControlflowEdge getNextLeft() {
        return nextLeft;
    }

    public void setNextLeft(UmlControlflowEdge nextLeft) {
        this.nextLeft = nextLeft;
    }

    public UmlControlflowEdge getNextRight() {
        return nextRight;
    }

    public void setNextRight(UmlControlflowEdge nextRight) {
        this.nextRight = nextRight;
    }

    @Override public void accept(IUmlVisitor aVisitor) {
        aVisitor.decision(this);
    }

}
