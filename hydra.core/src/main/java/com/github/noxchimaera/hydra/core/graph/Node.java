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

package com.github.noxchimaera.hydra.core.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Nox
 */
public abstract class Node<TVal> {

    private final long id;
    private final NodeType type;

    protected TVal value;

    protected List<Mark> marks;

    public
    Node(long id, NodeType type) {
        this.id = id;
        this.type = type;
        marks = new ArrayList<>();
    }

    public
    long getId() {
        return id;
    }

    public
    NodeType getType() {
        return type;
    }

    public
    TVal getValue() {
        return value;
    }

    public
    Node<TVal> setValue(TVal value) {
        this.value = value;
        return this;
    }

    public
    Node<TVal> addMark(Mark mark) {
        if (!marks.contains(mark)) {
            marks.add(mark);
        }
        return this;
    }

    public <TMark extends Mark>
    Optional<TMark> getMark(Class<TMark> markClass) {
        return marks
            .stream()
            .filter(mark -> markClass.isInstance(mark))
            .findFirst()
            .map(mark -> markClass.cast(mark));
    }

    public abstract
    List<Edge> getEdges();

}