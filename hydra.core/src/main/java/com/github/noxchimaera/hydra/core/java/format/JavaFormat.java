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

package com.github.noxchimaera.hydra.core.java.format;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Nox
 */
public class JavaFormat {

    // private final String NL = "";
    private final String NL = System.lineSeparator();

    private StringBuilder builder = new StringBuilder();

    private int braces = 0;
    private int parentheses = 0;

    private void indent() {
        for (int i = 0; i < braces; ++i) {
            builder.append('\t');
        }
    }

    public String apply(String str) {
        final int n = str.length();
        for (int i = 0; i < n; ++i) {
            char ch = str.charAt(i);
            if ('{' == ch) {
                ++braces;
                builder.append('{').append(NL);
                indent();
                ++i;
            } else if ('}' == ch) {
                --braces;
                builder.append(NL);
                indent();
                builder.append('}').append(NL);
                indent();
            } else  if ('(' == ch) {
                ++parentheses;
                builder.append('(');
            } else if (')' == ch) {
                --parentheses;
                builder.append(')');
            } else if (';' == ch) {
                builder.append(';');
                if (parentheses == 0) {
                    builder.append(NL);
                    indent();
                    ++i;
                }
            } else if ('\n' == ch) {
                builder.append(NL);
                indent();
            } else {
                builder.append(ch);
            }
        }

        return Arrays.stream(builder.toString().split("\n"))
            .filter(i -> !i.trim().isEmpty())
            .collect(Collectors.joining(NL));
    }

}
