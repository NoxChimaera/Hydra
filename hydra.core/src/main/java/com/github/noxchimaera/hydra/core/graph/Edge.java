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

import java.io.Serializable;

/**
 * @author Nox
 */
public abstract class Edge<TNode> implements Serializable {

    protected final long id;

    protected TNode src;
    protected TNode dst;

    protected EdgeDirection direction;

    protected EdgeType type;

    public Edge(long id, EdgeType type, TNode src, TNode dst) {
        this(id, type, src, dst, EdgeDirection.OneWay);
    }

    public Edge(long id, EdgeType type, TNode src, TNode dst, EdgeDirection direction) {
        this.id = id;
        this.src = src;
        this.dst = dst;
        this.direction = direction;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public TNode getSource() {
        return src;
    }

    public Edge<TNode> setSource(TNode node) {
        src = node;
        return this;
    }

    public TNode getDestination() {
        return dst;
    }

    public Edge<TNode> setDestination(TNode node) {
        dst = node;
        return this;
    }

    public EdgeDirection getDirection() {
        return direction;
    }

    public Edge<TNode> setDirection(EdgeDirection direction) {
        this.direction = direction;
        return this;
    }

    public EdgeType getType() {
        return type;
    }

    public Edge<TNode> setType(EdgeType type) {
        this.type = type;
        return this;
    }

}
