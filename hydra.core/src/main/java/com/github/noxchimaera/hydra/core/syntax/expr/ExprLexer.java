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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Nox
 */
public class ExprLexer implements Lexer<ExprToken> {

    private final int len;
    private final String source;

    private int pos;

    private List<LexerError> errors;

    private Deque<ExprToken> stack;

    public ExprLexer(String source) {
        this.source = source;
        len = source.length();

        pos = 0;
        errors = new ArrayList<>();
        stack = new ArrayDeque<>();
    }

    @Override
    public ExprToken lookup(int k) {
        if (!stack.isEmpty()) {
            pos = stack.getLast().column();
            stack.clear();
        }

        for (int i = 0; i <= k; ++i) {
            ExprToken t = unsafeNext();
            stack.push(t);
        }
        return stack.peek();
    }

    private ExprToken unsafeNext() {
        while (Character.isWhitespace(at(pos))) {
            ++pos;
        }
        if (pos >= source.length()) {
            return ExprToken.eol(0, pos);
        }

        char ch = at(pos);
        if (Character.isJavaIdentifierStart(ch)) {
            return identifier();
        }

        if (ch == '(') {
            return new ExprToken(ExprTokenType.LPar, "(", 0, pos++);
        } else if (ch == ')') {
            return new ExprToken(ExprTokenType.RPar, ")", 0, pos++);
        } else if (ch == ':') {
            if (at(pos + 1) == ':') {
                pos += 2;
                return new ExprToken(ExprTokenType.DoubleColon, "::", 0, pos - 2);
            }
        } else if (ch == '=') {
            return new ExprToken(ExprTokenType.Assign, "=", 0, pos++);
        } else if (ch == ',') {
            return new ExprToken(ExprTokenType.Comma, ",", 0, pos++);
        }

        return new ExprToken(ExprTokenType.Error, "", 0, pos);
    }

    @Override
    public ExprToken next() {
        if (!stack.isEmpty()) {
            return stack.pollLast();
        }
        return unsafeNext();
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
        while (current < source.length()) {
            char ch = at(current);
            if (ch == '['
                || ch == ']'
                || Character.isJavaIdentifierPart(ch)) {

                b.append(ch);
                ++current;
            } else {
                break;
            }
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
