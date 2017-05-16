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

package com.github.noxchimaera.hydra.core.model.nodes;

import com.github.noxchimaera.hydra.core.model.HyVisitor;
import com.github.noxchimaera.hydra.core.modules.DiversifyContext;
import com.github.noxchimaera.hydra.utils.Strings;

/**
 * @author Nox
 */
public class HyDiversifiedAction extends HyNode {

    private String effect;
    private DiversifyContext context;

    public HyDiversifiedAction(String effect, DiversifyContext context) {
        this.effect = effect;
        this.context = context;
    }

    public String effect() {
        return effect;
    }

    public DiversifyContext context() {
        return context;
    }

    @Override
    public void accept(HyVisitor visitor) {
        visitor.action(this);
    }

    @Override
    public String toString() {
        return Strings.$("[ @Diversify ", effect, " ]");
    }

}
