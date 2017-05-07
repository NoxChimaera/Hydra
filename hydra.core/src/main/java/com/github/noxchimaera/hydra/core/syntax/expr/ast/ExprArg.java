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

package com.github.noxchimaera.hydra.core.syntax.expr.ast;

import com.github.noxchimaera.hydra.utils.Strings;

/**
 * @author Nox
 */
public class ExprArg extends ExprNode {

    private ExprSymbol identifier;
    private ExprSymbol type;

    public ExprArg(ExprSymbol identifier, ExprSymbol type) {
        this.identifier = identifier;
        this.type = type;
    }

    public ExprSymbol identifier() {
        return identifier;
    }

    public ExprSymbol type() {
        return type;
    }

    @Override
    public String str() {
        return Strings.$(identifier.str(), " :: ", type.str());
    }

}
