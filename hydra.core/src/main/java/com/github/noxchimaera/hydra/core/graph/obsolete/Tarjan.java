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

package com.github.noxchimaera.hydra.core.graph.obsolete;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Max Balushkin
 */
public class Tarjan<T> {

//    public static class Root extends ObsoleteMark {
//
//        public static int removeRoot(ObsoleteNode<?> node) {
//            Optional<Root> root = node.getMark(Root.class);
//            node.unmark(root.get());
//            return root.get().index;
//        }
//
//        public static int of(ObsoleteNode<?> node) {
//            return node.getMark(Root.class).get().index;
//        }
//
//        int index;
//
//        public Root(int index) {
//            this.index = index;
//        }
//
//        @Override public String toString() {
//            return String.format("Root: %d", index);
//        }
//    }
//
//    // Strongly connected components (SCC)
//    private List<List<ObsoleteNode<T>>> components;
//    private int index = 0;
//    private ArrayDeque<ObsoleteNode<T>> stack;
//
//    private List<ObsoleteEdge<T>> ignoringEdges;
//
//    public Tarjan() {
//        components = new ArrayList<>();
//        stack = new ArrayDeque<>();
//        ignoringEdges = new ArrayList<>();
//    }
//
//    public void addIgnoringEdge(ObsoleteEdge<T> edge) {
//        ignoringEdges.add(edge);
//    }
//
//    public void addIgnoringEdges(Collection<ObsoleteEdge<T>> edges) {
//        ignoringEdges.addAll(edges);
//    }
//
//    public void accept(ObsoleteNode<T> node) {
//        if (!node.isMarked(Index.class)) {
//            connect(node);
//        }
//    }
//
//    private List<ObsoleteNode<T>> popComponent(ArrayDeque<ObsoleteNode<T>> nodeStack, ObsoleteNode<T> node) {
//        List<ObsoleteNode<T>> scc = new ArrayList<>();
//        ObsoleteNode<T> member;
//        do {
//            member = nodeStack.pop();
//            scc.add(0, member);
//        } while (member != node);
//
//        return scc;
//    }
//
//    private boolean isRoot(ObsoleteNode<T> node) {
//        return Root.of(node) == Index.of(node);
//    }
//
//    private void setRoot(ObsoleteNode<T> node, int min) {
//        node.mark(
//            new Root(Math.min(Root.removeRoot(node), min)));
//    }
//
//    private void connect(ObsoleteNode<T> node) {
//        int idxValue = ++index;
//        node.mark(new Index(idxValue));
//        node.mark(new Root(idxValue));
//        stack.push(node);
//
//        for (ObsoleteEdge<T> edge : node.getEdges()) {
//            ObsoleteNode<T> next = edge.getTarget();
//            if (ignoringEdges.contains(edge)) {
//                continue;
//            }
//            if (!next.isMarked(Index.class)) {
//                connect(next);
//                setRoot(node, Root.of(next));
//            } else if (stack.contains(next)) {
//                setRoot(node, Index.of(next));
//            }
//        }
//        if (isRoot(node)) {
//            components.add(0, popComponent(stack, node));
//        }
//    }
//
//    public List<List<ObsoleteNode<T>>> getComponents() {
//        return new ArrayList<>(components);
//    }

}
