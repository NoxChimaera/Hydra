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

package com.github.noxchimaera.hydra.core.syntax;

import com.github.noxchimaera.hydra.utils.Strings;

/**
 * @author Nox
 */
public abstract class Token {

    protected int tokenType;

    protected String lexeme;
    protected int line, col;

    public Token(int tokenType, String lexeme, int line, int col) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.line = line;
        this.col = col;
    }

    public int type() {
        return tokenType;
    }

    public String lexeme() {
        return lexeme;
    }

    public int line() {
        return line;
    }

    public int column() {
        return col;
    }

    @Override
    public String toString() {
        return Strings.$(lexeme, "(", col, ":", line, ")");
    }

}
