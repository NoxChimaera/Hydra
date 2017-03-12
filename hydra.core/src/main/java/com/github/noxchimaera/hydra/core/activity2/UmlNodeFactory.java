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

package com.github.noxchimaera.hydra.core.activity2;

import com.github.noxchimaera.hydra.core.activity2.nodes.*;

import java.util.stream.Stream;

/**
 * @author Max Balushkin
 */
public class UmlNodeFactory {

    private long nodeId;
    private long edgeId;

    public UmlNodeFactory(long nodeInitialId, long edgeInitialId) {
        this.nodeId = nodeInitialId;
        this.edgeId = edgeInitialId;
    }

    public UmlInitialNode begin(long id) {
        return new UmlInitialNode(id);
    }

    public UmlInitialNode begin() {
        return begin(++nodeId);
    }

    public UmlFinalNode end(long id) {
        return new UmlFinalNode(id);
    }

    public UmlFinalNode end() {
        return end(++nodeId);
    }

    public UmlActionNode act(String effect) {
        return act(++nodeId, effect);
    }

    public UmlActionNode act(long id, String effect) {
        UmlActionNode node = new UmlActionNode(id);
        node.setEffect(effect);
        return node;
    }

    public <I extends UmlNode & IUmlControlflow> void flow(I... nodes) {
        final int n = nodes.length;
        for (int i = 0; i < n - 1; ++i) {
            I source = nodes[i];
            I target = nodes[i + 1];
            UmlControlflowEdge edge = new UmlControlflowEdge(++edgeId, source, target);
            edge.setSource(source);
            source.setNext(edge);
            edge.setTarget(target);
            target.setPrev(edge);
        }
    }

    public <I extends UmlNode & IUmlControlflow> I flowl(I... nodes) {
        flow(nodes);
        return nodes[0];
    }

    public <I extends UmlNode & IUmlControlflow> I flowr(I... nodes) {
        flow(nodes);
        return nodes[nodes.length - 1];
    }


}
