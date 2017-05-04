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

import com.github.noxchimaera.hydra.core.model.HyVisitor;
import com.github.noxchimaera.hydra.core.model.nodes.*;

/**
 * @author Nox
 */
public class HydraToJavaTransformer implements HyVisitor<Void> {

    private StringBuilder builder;
    private int level = 0;
    private final String NL = System.lineSeparator();

    public HydraToJavaTransformer() {
        builder = new StringBuilder();
    }

    public String java() {
        return builder.toString();
    }

    private void append(String str) {
        builder.append(str);
    }

    private void append(HyNode node) {
        node.accept(this);
    }

    private void nl() {
        append(NL);
    }

    private void tab() {
        append("\t");
    }

    private void indent() {
        for (int i = 0; i < level; ++i) {
            tab();
        }
    }

    private void line(String str) {
        indent();
        append(str);
        append(";");
    }

    private void line(HyNode node) {
        indent();
        node.accept(this);
        append(";");
    }


    @Override
    public Void empty(HyEmpty empty) {
        return null;
    }

    @Override
    public Void seq(HySequence seq) {
        for (HyNode hyNode : seq.get()) {
            line(hyNode);
            nl();
        }
        return null;
    }

    @Override
    public Void action(HyAction action) {
        String effect = action.getEffect().trim();
        if (effect.endsWith(";")) {
            effect = effect.substring(0, effect.length() - 1);
        }
        append(effect);
        return null;
    };

    @Override
    public Void cond(HyConditional cond) {
        return null;
    }

    @Override
    public Void loop(HyForLoop loop) {
        indent();
        append("for (");
        append(loop.getSetup());
        append("; ");
        append(loop.getTest());
        append("; ");
        append(loop.getStep());
        append(") {");
        nl();
        ++level;
        append(loop.getBody());
        --level;
        indent();
        append("}");
        return null;
    }

}
