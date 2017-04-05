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

import java.util.List;

/**
 * @author Max Balushkin
 */
public class ListUtils {

    public static <T> T last(List<T> lst) {
        return lst.get(lst.size() - 1);
    }

    public static <T> T head(List<T> lst) {
        return lst.get(0);
    }

    public static <T> List<T> tail(List<T> lst) {
        return lst.subList(1, lst.size() - 1);
    }

}