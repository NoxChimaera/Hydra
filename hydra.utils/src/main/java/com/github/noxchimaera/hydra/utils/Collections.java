/*
 * Copyright 2016 Max Balushkin.
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

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * @author Max Balushkin
 */
public class Collections {

    public static <T> boolean any(Predicate<T> predicate, List<T> lst) {
        for (T item : lst) {
            if (predicate.test(item)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean every(Predicate<T> predicate, List<T> lst) {
        return any(predicate.negate(), lst);
    }

    public static <T> T last(List<T> lst) {
        return lst.get(lst.size() - 1);
    }

    public static <T> T head(List<T> lst) {
        return lst.get(0);
    }

    public static <T> List<T> tail(List<T> lst) {
        return lst.subList(1, lst.size() - 1);
    }

    public static <T> ArrayList<T> toList(boolean ignoreNulls, T... items) {
        ArrayList<T> lst = new ArrayList<>(items.length);
        for (T item : items) {
            if (ignoreNulls && item == null) continue;
            lst.add(item);
        }
        return lst;
    }

    public static <T> ArrayList<T> asList(Collection<T> items) {
        return new ArrayList<>(items);
    }

    public static <T> ArrayList<T> toList(T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static <T> T[] toArray(List<T> items, IntFunction<T[]> generator) {
        return items.toArray(generator.apply(items.size()));
    }

    public static <T> HashSet<T> toHashSet(T... items) {
        return new HashSet<>(toList(items));
    }

}
