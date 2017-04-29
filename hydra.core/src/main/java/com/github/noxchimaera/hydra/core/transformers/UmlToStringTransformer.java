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

import com.github.noxchimaera.hydra.core.activity2.AbstractUmlVisitor;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.*;

/**
 * @author Nox
 */
public class UmlToStringTransformer extends AbstractUmlVisitor {

    private StringBuilder builder;

    public UmlToStringTransformer() {
        builder = new StringBuilder();
    }

    private String nl() {
        return System.lineSeparator();
    }

    @Override
    public void init(InitUmlNode init) {
        builder.append("{").append(nl());
        super.init(init);
    }

    @Override
    public void fin(FinUmlNode fin) {
        builder.append("}");
        super.fin(fin);
    }

    @Override
    public void action(ActionUmlNode action) {
        builder.append(action.getValue()).append(";").append(nl());
        super.action(action);
    }

    @Override
    public void condition(ConditionalUmlNode condition) {
        super.condition(condition);
    }

    @Override
    public void loop(ForLoopUmlNode loop) {
        super.loop(loop);
    }

    public String transform(UmlNode root) {
        builder = new StringBuilder();
        root.accept(this);
        return builder.toString();
    }

}
