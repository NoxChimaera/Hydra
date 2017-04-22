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

import com.github.noxchimaera.hydra.core.syntax.Lexer;
import com.github.noxchimaera.hydra.core.syntax.LexerError;
import com.github.noxchimaera.hydra.utils.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
public class ExprLexer implements Lexer<ExprToken> {

    private final int len;
    private final String source;

    private int pos;

    private List<LexerError> errors;

    public ExprLexer(String source) {
        this.source = source;
        len = source.length();

        pos = 0;
        errors = new ArrayList<>();
    }

    @Override
    public ExprToken lookup(int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ExprToken next() {
        return null;
    }

    @Override
    public void prev(ExprToken token) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private ExprToken error(String message, int errorColumn) {
        errors.add(new LexerError(message, 0, errorColumn));
        return new ExprToken(ExprTokenType.Error, "", 0, errorColumn);
    }

    private char at(int idx) {
        return source.charAt(idx);
    }

    private ExprToken identifier() {
        int current = pos;
        StringBuilder b = new StringBuilder();

        if (Character.isJavaIdentifierStart(at(current))) {
            b.append(at(current));
        } else {
            return error("Unexpected character", current);
        }
        ++current;
        while (Character.isJavaIdentifierPart(at(current))) {
            b.append(at(current));
            ++current;
        }

        String lexeme = b.toString();
        ExprToken token;
        switch (lexeme) {
            case "true":
            case "True":
                token = new ExprToken(ExprTokenType.Boolean, lexeme, 0, pos);
                break;
            case "false":
            case "False":
                token = new ExprToken(ExprTokenType.Boolean, lexeme, 0, pos);
                break;
            default:
                token = new ExprToken(ExprTokenType.Symbol, lexeme, 0, pos);
                break;
        }
        pos = current;
        return token;
    }

    @Override
    public List<LexerError> errors() {
        return new ArrayList<>(errors);
    }

}
