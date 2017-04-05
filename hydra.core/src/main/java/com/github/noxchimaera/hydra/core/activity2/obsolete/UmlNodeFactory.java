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

package com.github.noxchimaera.hydra.core.activity2.obsolete;

import com.github.noxchimaera.hydra.core.activity2.obsolete.nodes.*;
import com.github.noxchimaera.hydra.utils.Proc2;
import com.github.noxchimaera.hydra.utils.Tuple;

import static com.github.noxchimaera.hydra.utils.ListUtils.*;

import java.util.List;

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

    public UmlDecisionNode decision() {
        return new UmlDecisionNode(++nodeId);
    }

    public UmlMergeNode merge() {
        return new UmlMergeNode(++nodeId);
    }

    public <TSource extends UmlNode & IHasOutput, TTarget extends UmlNode & IHasInput>
    void link(TSource source, TTarget target) {
        UmlControlflowEdge edge = new UmlControlflowEdge(++edgeId, source, target);
        edge.setSource(source);
        edge.setTarget(target);
        source.setNext(edge);
        target.setPrev(edge);
    }

    public <TSource extends UmlNode, TTarget extends UmlNode & IHasInput>
    void link(TSource source, TTarget target, String guard, Proc2<TSource, UmlControlflowEdge> edgeSetter) {
        UmlControlflowEdge edge = new UmlControlflowEdge(++edgeId, source, target, guard);
        edge.setSource(source);
        edge.setTarget(target);
        edgeSetter.exec(source, edge);
        target.setPrev(edge);
    }

    public <TSource extends UmlNode & IHasOutput, TTarget extends UmlNode>
    void link(TSource source, TTarget target, Proc2<TTarget, UmlControlflowEdge> edgeSetter) {
        UmlControlflowEdge edge = new UmlControlflowEdge(++edgeId, source, target);
        edge.setSource(source);
        edge.setTarget(target);
        source.setNext(edge);
        edgeSetter.exec(target, edge);
    }

    public <TNode extends UmlNode & IHasOutput & IHasInput>
    void flow(TNode... nodes) {
        final int n = nodes.length;
        for (int i = 0; i < n - 1; ++i) {
            link(nodes[i], nodes[i + 1]);
        }
    }

    public <TNode extends UmlNode & IHasOutput & IHasInput>
    TNode flowl(TNode... nodes) {
        flow(nodes);
        return nodes[0];
    }

    public <TNode extends UmlNode & IHasOutput & IHasInput>
    TNode flowr(TNode... nodes) {
        flow(nodes);
        return nodes[nodes.length - 1];
    }

    public <TNode extends UmlNode & IHasInput & IHasOutput>
    void flow(List<TNode> nodes) {
        final int n = nodes.size();
        for (int i = 0; i < n - 1; ++i) {
            link(nodes.get(i), nodes.get(i + 1));
        }
    }

    public <TNode extends UmlNode & IHasInput & IHasOutput>
    TNode flowl(List<TNode> nodes) {
        flow(nodes);
        return nodes.get(0);
    }

    public <TNode extends UmlNode & IHasInput & IHasOutput>
    TNode flowr(List<TNode> nodes) {
        flow(nodes);
        return nodes.get(nodes.size() - 1);
    }

    public <THead extends UmlNode & IHasOutput, TTail extends UmlNode & IHasOutput & IHasInput>
    void flow(THead head, TTail... tail) {
        link(head, tail[0]);
        final int n = tail.length;
        for (int i = 0; i < n - 1; ++i) {
            link(tail[i], tail[i + 1]);
        }
    }

    public <THead extends UmlNode & IHasOutput, TTail extends UmlNode & IHasOutput & IHasInput>
    THead flowl(THead head, TTail... tail) {
        flow(head, tail);
        return head;
    }

    public <THead extends UmlNode & IHasOutput, TTail extends UmlNode & IHasOutput & IHasInput>
    TTail flowr(THead head, TTail... tail) {
        flow(head, tail);
        return tail[tail.length - 1];
    }

    public <TBegin extends UmlNode & IHasOutput, TEnd extends UmlNode & IHasInput>
    void flow(TBegin head, TEnd end) {
        link(head, end);
    }

    public <TBegin extends UmlNode & IHasOutput, TEnd extends UmlNode & IHasInput>
    TBegin flowl(TBegin head, TEnd end) {
        link(head, end);
        return head;
    }

    public <TBegin extends UmlNode & IHasOutput, TEnd extends UmlNode & IHasInput>
    TEnd flowr(TBegin head, TEnd end) {
        link(head, end);
        return end;
    }

    public <TBegin extends UmlNode & IHasOutput, TMiddle extends UmlNode & IHasInput & IHasOutput, TEnd extends UmlNode & IHasInput>
    void flow(TBegin begin, List<TMiddle> middle, TEnd end) {
        link(begin, middle.get(0));
        final int n = middle.size();
        for (int i = 0; i < n - 1; ++i) {
            link(middle.get(i), middle.get(i + 1));
        }
        link(middle.get(n - 1), end);
    }

    public <TBegin extends UmlNode & IHasOutput, TMiddle extends UmlNode & IHasInput & IHasOutput, TEnd extends UmlNode & IHasInput>
    TBegin flowl(TBegin begin, List<TMiddle> middle, TEnd end) {
        flow(begin, middle, end);
        return begin;
    }

    public <TBegin extends UmlNode & IHasOutput, TMiddle extends UmlNode & IHasInput & IHasOutput, TEnd extends UmlNode & IHasInput>
    TEnd flowr(TBegin begin, List<TMiddle> middle, TEnd end) {
        flow(begin, middle, end);
        return end;
    }

    public <TMiddle extends UmlNode & IHasInput & IHasOutput>
    void flow(UmlDecisionNode decision, Proc2<UmlDecisionNode, UmlControlflowEdge> sourceSetter,
              String guard, List<TMiddle> branch,
              UmlMergeNode end, Proc2<UmlMergeNode, UmlControlflowEdge> targetSetter) {
        link(decision, flowl(branch), guard, sourceSetter);
        link(last(branch), end, targetSetter);
    }

    public <TMiddle extends UmlNode & IHasInput & IHasOutput>
    Tuple<UmlDecisionNode, UmlMergeNode> flow(UmlDecisionNode decision,
               String lGuard, List<TMiddle> lBranch,
               String rGuard, List<TMiddle> rBranch,
               UmlMergeNode merge) {
        link(decision, flowl(lBranch), lGuard, UmlDecisionNode::setNextLeft);
        link(decision, flowl(rBranch), rGuard, UmlDecisionNode::setNextRight);
        link(last(lBranch), merge, UmlMergeNode::setPrevLeft);
        link(last(rBranch), merge, UmlMergeNode::setPrevRight);

        return new Tuple<>(decision, merge);
    }

    public <TMiddle extends UmlNode & IHasInput & IHasOutput>
    UmlDecisionNode flowl(UmlDecisionNode begin,
                       String lGuard, List<TMiddle> lBranch,
                       String rGuard, List<TMiddle> rBranch,
                       UmlMergeNode end) {
        flow(begin, lGuard, lBranch, rGuard, rBranch, end);
        return begin;
    }

    public <TMiddle extends UmlNode & IHasInput & IHasOutput>
    UmlMergeNode flowr(UmlDecisionNode begin,
              String lGuard, List<TMiddle> lBranch,
              String rGuard, List<TMiddle> rBranch,
              UmlMergeNode end) {
        flow(begin, lGuard, lBranch, rGuard, rBranch, end);
        return end;
    }

}
