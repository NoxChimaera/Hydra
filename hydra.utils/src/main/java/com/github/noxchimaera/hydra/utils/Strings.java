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

package com.github.noxchimaera.hydra.utils;

import java.util.List;
import java.util.function.Function;

/**
 * @author Nox
 */
public class Strings {

    private Strings() {
        throw new AssertionError("Can not create utils class");
    }

    public static String $(Object... args) {
        StringBuilder b = new StringBuilder();
        for (Object arg : args) {
            b.append(arg);
        }
        return b.toString();
    }

    public static <T extends Object>
    String joined(String separator, List<T> args) {
        return joined(separator, args, i -> i.toString());
    }

    public static <T extends Object>
    String joined(String separator, List<T> args, Function<T, String> map) {
        StringBuilder b = new StringBuilder();
        final int n = args.size();
        for (int i = 0; i < n; ++i) {
            b.append(map.apply(args.get(i)));
            if (i < n - 1) {
                b.append(separator);
            }
        }
        return b.toString();
    }

}
