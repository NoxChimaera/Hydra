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

package com.github.noxchimaera.hydra.core.transformers;

import com.github.noxchimaera.hydra.core.Log;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.UmlVisitor;
import com.github.noxchimaera.hydra.core.activity2.commons.HasOutput;
import com.github.noxchimaera.hydra.core.activity2.nodes.*;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.DiversifiedStereotype;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotypes;
import com.github.noxchimaera.hydra.core.graph.Edge;
import com.github.noxchimaera.hydra.core.model.nodes.*;
import com.github.noxchimaera.hydra.core.modules.DiversifyContext;
import com.github.noxchimaera.hydra.utils.Contracts;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Nox
 */
public class UmlToHydraTransformer implements UmlVisitor<Void> {

    private ArrayDeque<HySequence> sequences;
    private List<HySequence> result;

    private HySequence sequence;

    public UmlToHydraTransformer() {
        sequences = new ArrayDeque<>();
        result = new ArrayList<>();
    }

    public List<HySequence> getResult() {
        return result;
    }

    private void next(HasOutput node) {
        next(node, "");
    }

    private void next(HasOutput node, String edge) {
        Edge<UmlNode> umlEdge = node.getOutput(edge);
        if (umlEdge != null) {
            umlEdge.getDestination().accept(this);
        }
    }

    private <T extends HyNode>
    T seq(T node) {
        if (node != null) {
            sequence.add(node);
        }
        return node;
    }

    private HySequence start() {
        sequence = new HySequence();
        sequences.push(sequence);
        return sequence;
    }

    private HySequence end() {
        HySequence last = sequences.pop();
        sequence = sequences.peek();
        return last;
    }

    @Override
    public Void init(InitUmlNode init) {
        start();
        next(init);
        return null;
    }

    @Override
    public Void fin(FinUmlNode fin) {
        result.add(end());
        return null;
    }

    @Override
    public Void action(ActionUmlNode action) {
        if (action.hasStereotype() && Stereotypes.Diversified == action.stereotype().descriptor()) {
            DiversifyContext ctx = ((DiversifiedStereotype)action.stereotype()).context();
            seq(new HyDiversifiedAction(action.value(), ctx));
        } else {
            seq(new HyAction(action.value()));
        }
        next(action);
        return null;
    }

    private <T extends HyNode>
    T singleOrElse(HySequence seq, Class<T> nodeType, Supplier<T> orElse) {
        if (seq.size() == 0 || !Contracts.is(nodeType, seq.get(0))) {
            return orElse.get();
        } else {
            return nodeType.cast(seq.get(0));
        }
    }

    @Override
    public Void condition(ConditionalUmlNode condition) {
        start();
        next(condition, ConditionalUmlNode.Test);
        HySequence testSeq = end();
        HyAction test = singleOrElse(testSeq, HyAction.class, () -> {
            Log.shared.warning("`Test` branch must be an action");
            return new HyAction("[ERROR]");
        });

        start();
        next(condition, ConditionalUmlNode.IfBranch);
        HyNode trueBranch = end();

        start();
        next(condition, ConditionalUmlNode.ElseBranch);
        HyNode falseBranch = end();

        seq(new HyConditional(test, trueBranch, falseBranch));
        next(condition);
        return null;
    }

    @Override
    public Void loop(ForLoopUmlNode loop) {
        start();
        next(loop, ForLoopUmlNode.Setup);
        HyAction setup = singleOrElse(end(), HyAction.class, () -> {
            Log.shared.warning("`Setup` branch must be an action");
            return new HyAction("[ERROR]");
        });

        start();
        next(loop, ForLoopUmlNode.Test);
        HyAction test = singleOrElse(end(), HyAction.class, () -> {
            Log.shared.warning("`Test` branch must be an action");
            return new HyAction("[ERROR]");
        });

        start();
        next(loop, ForLoopUmlNode.Step);
        HyAction step = singleOrElse(end(), HyAction.class, () -> {
            Log.shared.warning("`Step` branch must be an action");
            return new HyAction("[ERROR]");
        });

        start();
        next(loop, ForLoopUmlNode.Body);
        HySequence body = end();

        seq(new HyForLoop(setup, test, step, body));
        next(loop);
        return null;
    }

}
