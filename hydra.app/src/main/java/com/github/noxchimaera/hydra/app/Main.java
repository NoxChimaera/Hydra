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

package com.github.noxchimaera.hydra.app;

import com.github.noxchimaera.hydra.core.activity2.UmlNodeFactory;
import com.github.noxchimaera.hydra.core.activity2.nodes.UmlInitialNode;
import com.github.noxchimaera.hydra.core.model.HyPrinter;
import com.github.noxchimaera.hydra.core.model.nodes.*;

/**
 * @author Max Balushkin
 */
public class Main {

    public static void main(String[] args) {

        UmlNodeFactory fct = new UmlNodeFactory(-1, -1);
        UmlInitialNode umlInit = fct.begin();
        fct.flow(
        fct.flow(
            umlInit,
            fct.act("print \"Hello!\"")),
            fct.end());

        HyAction init = new HyAction("arr = [0, 1, 5, 3, -1], isSorted = false");
        HyLoop loop = new HyLoop(
            new HyAction("!isSorted"),
            new HySequence()
                .add(new HyAction("isSorted = false"))
                .add(new HyLoop(
                    new HyAction("i < n - 1"),
                    new HyConditional(
                        new HyAction("arr[i] > arr[i + 1]"),
                        new HySequence()
                            .add(new HyAction("arr[i], arr[i + 1] = arr[i + 1], arr[i]"))
                            .add(new HyAction("isSorted = false")),
                        new HyEmpty())))
                .add(new HyAction("++i"))
        );
        HySequence program = new HySequence().add(init).add(loop);
        program.accept(new HyPrinter());

    }

}
