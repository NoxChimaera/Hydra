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

package com.github.noxchimaera.hydra.core.activity2.obsolete.visitors;

import com.github.noxchimaera.hydra.core.activity2.obsolete.IUmlVisitor;
import com.github.noxchimaera.hydra.core.activity2.obsolete.nodes.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Max Balushkin
 */
public class AbstractUmlVisitor implements IUmlVisitor {

    protected List<UmlNode> visitedNodes;

    private ArrayDeque<UmlDecisionNode> decisions;
    private HashMap<UmlDecisionNode, UmlNode> paths;

    public AbstractUmlVisitor() {
        visitedNodes = new ArrayList<>();
        decisions = new ArrayDeque<>();
        paths = new HashMap<>();
    }

    private void acceptIfNotNull(UmlControlflowEdge aEdge) {
        if (aEdge.getTarget() != null) {
            aEdge.getTarget().accept(this);
        }
    }

    @Override public void init(UmlInitialNode aInit) {
        visitedNodes.add(aInit);
        acceptIfNotNull(aInit.getNext());
    }

    @Override public void fin(UmlFinalNode aFin) {
        visitedNodes.add(aFin);
    }

    @Override public void action(UmlActionNode aAction) {
        visitedNodes.add(aAction);
        acceptIfNotNull(aAction.getNext());
    }

    @Override public void decision(UmlDecisionNode aDecision) {
        visitedNodes.add(aDecision);
        decisions.push(aDecision);

        acceptIfNotNull(aDecision.getNextLeft());
        acceptIfNotNull(aDecision.getNextRight());
    }

    @Override public void merge(UmlMergeNode aMerge) {

    }
}
