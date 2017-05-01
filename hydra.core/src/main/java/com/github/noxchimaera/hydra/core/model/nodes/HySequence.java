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

package com.github.noxchimaera.hydra.core.model.nodes;

import com.github.noxchimaera.hydra.core.model.HyVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Max Balushkin
 */
public class HySequence extends HyNode {

    private List<HyNode> nodes;

    public HySequence() {
        nodes = new ArrayList<>();
    }

    public HySequence add(HyNode node) {
        nodes.add(node);
        return this;
    }

    public List<HyNode> get() {
        return new ArrayList<>(nodes);
    }

    public HyNode get(int idx) {
        return nodes.get(idx);
    }

    public int size() {
        return nodes.size();
    }

    @Override public void accept(HyVisitor visitor) {
        visitor.seq(this);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("[ ")
            .append(nodes.stream().map(HyNode::toString).collect(Collectors.joining(", ")))
            .append(" ]");
        return b.toString();
    }

}
