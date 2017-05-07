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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nox
 */
public class ExprLexerTest {

    String src = "foo :: int = fn(bar :: int[])";

    @Test
    public void lookup() throws Exception {
        ExprLexer lex = new ExprLexer(src);

        assertEquals("foo", lex.lookup(0).lexeme());
        assertEquals("::", lex.lookup(1).lexeme());
        assertEquals("int", lex.lookup(2).lexeme());

        assertEquals("foo", lex.next().lexeme());
        assertEquals("::", lex.next().lexeme());
        assertEquals("int", lex.next().lexeme());
        assertEquals("=", lex.next().lexeme());
    }

    @Test
    public void next() throws Exception {
        ExprLexer lex = new ExprLexer(src);

        assertEquals("foo", lex.next().lexeme());
        assertEquals("::", lex.next().lexeme());
        assertEquals("int", lex.next().lexeme());
        assertEquals("=", lex.next().lexeme());
        assertEquals("fn", lex.next().lexeme());
        assertEquals("(", lex.next().lexeme());
        assertEquals("bar", lex.next().lexeme());
        assertEquals("::", lex.next().lexeme());
        assertEquals("int[]", lex.next().lexeme());
        assertEquals(")", lex.next().lexeme());
    }

}