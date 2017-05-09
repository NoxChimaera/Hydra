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

import com.github.noxchimaera.hydra.core.syntax.Parser;
import com.github.noxchimaera.hydra.core.syntax.Token;
import com.github.noxchimaera.hydra.core.syntax.expr.ast.ExprArg;
import com.github.noxchimaera.hydra.core.syntax.expr.ast.ExprAssignment;
import com.github.noxchimaera.hydra.core.syntax.expr.ast.ExprFuncall;
import com.github.noxchimaera.hydra.core.syntax.expr.ast.ExprSymbol;
import com.github.noxchimaera.hydra.utils.Strings;
import com.github.noxchimaera.hydra.utils.formatters.Parenthesised;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses expressions: {@code type id = id LPAR [ value DCOLON type ]* RPAR}.
 * </p>
 * Where {@code type} is type identifier, {@code id} - any valid identifier,
 * value - any valid value (identifier, string, number or boolean),
 * LPAR and RPAR - parentheses, DCOLON - double colon.
 *
 * @author Nox
 */
public class ExprParser implements Parser<ExprAssignment> {

    private ExprLexer lexer;

    public ExprParser(ExprLexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public ExprAssignment parse() throws ExprInvalidSyntax {
        return assignment();
    }

    private ExprAssignment assignment() {
        // arg '=' funcall

        ExprArg lhs = arg();
        ExprToken assign = lexer.next();
        if (!is(ExprTokenType.Assign, assign)) {
            throw expectedToken("'='", assign);
        }
        ExprFuncall rhs = funcall();
        return new ExprAssignment(lhs, rhs);
    }

    private ExprSymbol symbol() {
        // symbol

        Token t = lexer.next();
        if (ExprTokenType.Symbol.ordinal() != t.type()) {
            throw expectedToken("Symbol", t);
        }
        return new ExprSymbol(t.lexeme());
    }

    private ExprFuncall funcall() {
        // symbol '(' (arg)+ (', ' arg)* ')'

        ExprSymbol function = symbol();
        Token par = lexer.next();
        if (!is(ExprTokenType.LPar, par)) {
            throw expectedToken("'('", par);
        }
        List<ExprArg> args = new ArrayList<>();
        Token last = par;
        while (!is(ExprTokenType.RPar, last)) {
            ExprArg arg = arg();
            args.add(arg);
            last = lexer.next();
            if (!is(ExprTokenType.Comma, last) && !is(ExprTokenType.RPar, last)) {
                throw expectedToken("comma", last);
            }
        }
        return new ExprFuncall(function, args);
    }

    private ExprArg arg() {
        // symbol  :: symbol
        ExprSymbol identifier = symbol();
        ExprToken colons = lexer.next();
        if (ExprTokenType.DoubleColon.ordinal() != colons.type()) {
            throw expectedToken("'::'", colons);
        }
        ExprSymbol type = symbol();
        return new ExprArg(identifier, type);
    }

    private boolean is(ExprTokenType expectedType, Token actual) {
        return expectedType.ordinal() == actual.type();
    }

    private ExprInvalidSyntax expectedToken(String expectedToken, Token actual) {
        return new ExprInvalidSyntax(
            Strings.$("Token expected: '", expectedToken, "' but given '", actual.lexeme(),
                "' (at column ", actual.column(), ")"));
    }

}
