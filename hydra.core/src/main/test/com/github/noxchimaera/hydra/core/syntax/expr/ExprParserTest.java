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

import com.github.noxchimaera.hydra.core.syntax.expr.ast.ExprAssignment;
import com.github.noxchimaera.hydra.utils.Strings;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nox
 */
public class ExprParserTest {

    @Test public void test__parse_with_arguments() throws Exception {
        String src = "foo :: int[] = sort(data :: int[], asc :: bool)";
        ExprLexer lexer = new ExprLexer(src);
        ExprParser parser = new ExprParser(lexer);

        ExprAssignment expr = parser.parse();
        assertEquals(src, expr.str());
    }

    @Test public void test__parse_nullary_call() throws Exception {
        String src = "foo :: int = fn()";
        ExprLexer lexer = new ExprLexer(src);
        ExprParser parser = new ExprParser(lexer);

        ExprAssignment expr = parser.parse();
        assertEquals(src, expr.str());
    }

}