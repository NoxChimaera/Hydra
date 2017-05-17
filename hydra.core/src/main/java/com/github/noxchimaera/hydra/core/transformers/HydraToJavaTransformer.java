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
import com.github.noxchimaera.hydra.core.modules.DiversifyContext;
import com.github.noxchimaera.hydra.core.modules.diversify.Argument;
import com.github.noxchimaera.hydra.core.modules.diversify.VersionConfiguration;
import com.github.noxchimaera.hydra.utils.Contracts;
import com.github.noxchimaera.hydra.utils.Strings;

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

    private String createDiversifiedMethod(DiversifyContext context) {
        /*  ActorSystem system = ActorSystem.create("random");
         *
         *  GenVersion<Integer> rnd0 = TypedActor.get(system).typedActorOf(
         *      new TypedProps<>(GenVersion.class, TrueRandom.class));
         *  GenVersion<Integer> rnd1 = TypedActor.get(system).typedActorOf(
         *      new TypedProps<>(GenVersion.class, TrueRandom.class));
         *  GenVersion<Integer> rnd2 = TypedActor.get(system).typedActorOf(
         *      new TypedProps<>(GenVersion.class, OtherTrueRandom.class));
         *
         *  GenVoter<Integer> voter = TypedActor.get(system).typedActorOf(
         *      new TypedProps<>(GenVoter.class, MajorityVoter.class));
         *
         *  Integer res = voter.vote(Arrays.asList(rnd0, rnd1, rnd2)).get();
         *  system.terminate();
         */

        String returns = context.algorithm().signature().returnType();
        String methodName = context.algorithm().name();

        StringBuilder b = new StringBuilder();
        b.append("ActorSystem system = ActorSystem.create(\"").append(methodName).append("\"); ");

        /*  GenVersion<Integer> rnd0 = TypedActor.get(system).typedActorOf(
         *      new TypedProps<>(GenVersion.class, TrueRandom.class));
         *  GenVersion<Integer> rnd1 = TypedActor.get(system).typedActorOf(
         *      new TypedProps<>(GenVersion.class, TrueRandom.class));
         *  GenVersion<Integer> rnd2 = TypedActor.get(system).typedActorOf(
         *      new TypedProps<>(GenVersion.class, OtherTrueRandom.class));
         */
        int n = 0;
        for (VersionConfiguration version : context.versions()) {
            StringBuilder fncall = new StringBuilder();
            fncall.append("GenVersion<").append(returns).append("> $").append(n).append(" = ")
                .append("TypeActor.get(system).typedActorOf(new TypedProps<>(GenVersion.class, ")
                .append(version.className()).append(".class)); ");
            b.append(fncall);
            ++n;
        }

        /*  GenVoter<Integer> voter = TypedActor.get(system).typedActorOf(
         *    new TypedProps<>(GenVoter.class, MajorityVoter.class));
         */
        b.append("GenVoter<").append(returns).append("> voter = TypedActor.get(system).typedActorOf(")
            .append("new TypedProps<>(GenVoter.class, ").append(context.genvoter().voterClass().getName()).append(".class)); ");

        /*  Integer res = voter.vote(Arrays.asList(rnd0, rnd1, rnd2)).get();
         *  system.terminate();
         */
        b.append(returns).append(" $$ = voter.vote(Arrays.asList(");
        for (int i = 0; i < n; ++i) {
            b.append("$").append(i);
            if (i != n - 1) {
                b.append(", ");
            }
        }
        b.append(")).get(); ");
        b.append("system.terminate(); ");
        b.append("return $$; ");
        return b.toString();
    }

    @Override
    public Void action(HyDiversifiedAction action) {
        String name = action.context().algorithm().name();
        if (methods.stream().filter(i -> i.name().equals(name)).count() == 0) {
            String returns = action.context().algorithm().signature().returnType();
            List<JavaArgument> args = new ArrayList<>();
            for (Argument arg : action.context().algorithm().signature().arguments()) {
                args.add(new JavaArgument(arg.name(), arg.type()));
            }
            JavaMethod method = new JavaMethod(name, returns, args);
            method.body(createDiversifiedMethod(action.context()));
            methods.add(method);
        }

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
