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
public class UmlMergeNode extends UmlNode implements IHasOutput {

    private UmlControlflowEdge next;

    private UmlControlflowEdge prevLeft;
    private UmlControlflowEdge prevRight;

    public UmlMergeNode(long id) {
        super(id);
    }

    @Override public void setNext(UmlControlflowEdge next) {
        this.next = next;
    }

    @Override public UmlControlflowEdge getNext() {
        return next;
    }

    @Override public Collection<UmlEdge> getInputs() {
        return Arrays.asList(prevLeft, prevRight);
    }

    @Override public Collection<UmlEdge> getOutputs() {
        return Arrays.asList(next);
    }

    public UmlControlflowEdge getPrevLeft() {
        return prevLeft;
    }

    public void setPrevLeft(UmlControlflowEdge prevLeft) {
        this.prevLeft = prevLeft;
    }

    public UmlControlflowEdge getPrevRight() {
        return prevRight;
    }

    public void setPrevRight(UmlControlflowEdge prevRight) {
        this.prevRight = prevRight;
    }

    @Override public void accept(IUmlVisitor aVisitor) {
        aVisitor.merge(this);
    }

}
