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

package com.github.noxchimaera.hydra.core.model.nodes;

import com.github.noxchimaera.hydra.core.model.HyVisitor;
import com.github.noxchimaera.hydra.utils.Strings;

/**
 * @author Max Balushkin
 */
public class HyForLoop extends HyNode {

    private HyAction setup;
    private HyAction test;
    private HyAction step;
    private HyNode body;

    public HyForLoop(HyAction setup, HyAction test, HyAction step, HyNode body) {
        this.setup = setup;
        this.test = test;
        this.step = step;
        this.body = body;
    }

    public HyAction setup() {
        return setup;
    }

    public HyAction test() {
        return test;
    }

    public HyAction step() {
        return step;
    }

    public HyNode body() {
        return body;
    }

    @Override public void accept(HyVisitor visitor) {
        visitor.loop(this);
    }

    @Override
    public String toString() {
        return Strings.$("[ For ", setup, " while ", test, " with ", step, " do ", body);
    }

}
