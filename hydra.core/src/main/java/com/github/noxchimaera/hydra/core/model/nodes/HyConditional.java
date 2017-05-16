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
public class HyConditional extends HyNode {

    private HyAction condition;

    private HyNode trueBranch;
    private HyNode falseBranch;

    public HyConditional(HyAction condition, HyNode trueBranch, HyNode falseBranch) {
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    public HyAction condition() {
        return condition;
    }

    public HyConditional condition(HyAction condition) {
        this.condition = condition;
        return this;
    }

    public HyNode trueBranch() {
        return trueBranch;
    }

    public HyNode falseBranch() {
        return falseBranch;
    }

    @Override public void accept(HyVisitor visitor) {
        visitor.cond(this);
    }

    @Override
    public String toString() {
        return Strings.$("[ If ", condition, " do ", trueBranch, " else ", falseBranch, " ]");
    }

}
