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

import com.github.noxchimaera.hydra.core.activity2.nodes.ActionUmlNode;
import com.github.noxchimaera.hydra.core.java.JavaArgument;
import com.github.noxchimaera.hydra.core.java.JavaClass;
import com.github.noxchimaera.hydra.core.java.JavaMethod;
import com.github.noxchimaera.hydra.core.model.HyVisitor;
import com.github.noxchimaera.hydra.core.model.nodes.*;
import com.github.noxchimaera.hydra.utils.Contracts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nox
 */
public class HydraToJavaTransformer implements HyVisitor<Void> {

    private List<JavaMethod> methods;

    private StringBuilder builder;
    private int level = 0;
    private final String NL = System.lineSeparator();

    public HydraToJavaTransformer() {
        methods = new ArrayList<>();
        builder = new StringBuilder();
    }

    public JavaClass java(String className, String packageName) {
        JavaClass jc = new JavaClass(packageName, className);

        JavaMethod main = new JavaMethod("main", "void", Arrays.asList());
        main.body(builder.toString());
        builder = new StringBuilder();
        methods.add(main);
        jc.methods(methods);

        return jc;
    }

    // private void append(String str) {
    //     builder.append(str);
    // }
    //
    // private void append(HyNode node) {
    //     node.accept(this);
    // }
    //
    // private void nl() {
    //     append(NL);
    // }
    //
    // private void tab() {
    //     append("\t");
    // }
    //
    // private void indent() {
    //     for (int i = 0; i < level; ++i) {
    //         tab();
    //     }
    // }
    //
    // private void line(String str) {
    //     indent();
    //     append(str);
    //     append(";");
    // }
    //
    // private void line(HyNode node) {
    //     indent();
    //     node.accept(this);
    //     append(";");
    // }

    private void append(String str) {
        builder.append(str);
    }

    private void append(HyNode node) {
        node.accept(this);
    }

    @Override
    public Void empty(HyEmpty empty) {
        return null;
    }

    @Override
    public Void seq(HySequence seq) {
        for (HyNode hyNode : seq.get()) {
            append(hyNode);
            if (Contracts.is(HyAction.class, hyNode) || Contracts.is(HyDiversifiedAction.class, hyNode)) {
                append("; ");
            } else {
                append(" ");
            }
            // nl();
        }
        return null;
    }

    private String flat(String str) {
        return Arrays.stream(str.split("\n"))
            .map(i -> i.trim())
            .collect(Collectors.joining(" "));
    }

    @Override
    public Void action(HyAction action) {
        String effect = action.effect().trim();
        if (effect.endsWith(";")) {
            effect = effect.substring(0, effect.length() - 1);
        }
        append(flat(effect));
        return null;
    }

    @Override
    public Void action(HyDiversifiedAction action) {
        // TODO: create method

        String effect = action.effect().trim();
        if (effect.endsWith(";")) {
            effect = effect.substring(0, effect.length() - 1);
        }
        append(flat(effect));
        return null;
    }

    @Override
    public Void cond(HyConditional cond) {
        // indent();
        append("if (");
        append(cond.condition());
        append(") { ");
        // nl();
        // ++level;
        append(cond.trueBranch());
        // --level;
        if (null != cond.falseBranch()) {
            append("} else { ");
            // ++level;
            append(cond.falseBranch());
            // --level;
        }
        append("} ");
        return null;
    }

    @Override
    public Void loop(HyForLoop loop) {
        // indent();
        append("for (");
        append(loop.setup());
        append("; ");
        append(loop.test());
        append("; ");
        append(loop.step());
        append(") { ");
        // nl();
        // ++level;
        append(loop.body());
        // --level;
        // indent();
        append("} ");
        return null;
    }
}
