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

package com.github.noxchimaera.hydra.core.syntax.expr;

import com.github.noxchimaera.hydra.core.syntax.Token;

/**
 * @author Nox
 */
public class ExprToken extends Token {

    public static ExprToken eol(int line, int col) {
        return new ExprToken(ExprTokenType.EOL, "<<EOL>>", line, col);
    }

    public ExprToken(ExprTokenType tokenType, String lexeme, int line, int col) {
        super(tokenType.ordinal(), lexeme, line, col);
    }

}
