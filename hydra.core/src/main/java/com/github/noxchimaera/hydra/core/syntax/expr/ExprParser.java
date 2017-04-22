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

/**
 * Parses expressions: {@code type id = id LPAR [ value DCOLON type ]* RPAR}.
 * </p>
 * Where {@code type} is type identifier, {@code id} - any valid identifier,
 * value - any valid value (identifier, string, number or boolean),
 * LPAR and RPAR - parentheses, DCOLON - double colon.
 *
 * @author Nox
 */
public class ExprParser {
}
