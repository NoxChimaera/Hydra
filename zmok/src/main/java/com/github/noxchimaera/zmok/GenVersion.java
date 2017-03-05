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

package com.github.noxchimaera.zmok;

import akka.japi.Option;
import scala.concurrent.Future;

/**
 * Algorithm version interface.
 *
 * @author Max Balushkin
 */
public interface GenVersion<T> {

    /**
     * Returns algorithm result (heuristic) asynchronously.
     *
     * @return algorithm result
     */
    Future<T> getHeuristicAsync();

    /**
     * Returns algorithm result (heuristic).
     *
     * @return algorithm result
     */
    Option<T> getHeuristic();

}
