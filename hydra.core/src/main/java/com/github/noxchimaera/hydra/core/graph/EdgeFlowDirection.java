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

package com.github.noxchimaera.hydra.core.graph;

/**
 * @author Nox
 */
public enum EdgeFlowDirection {

    Input, Output;

    public static <T extends Node<?>>
    EdgeFlowDirection get(T node, Edge<T> edge) {
        return edge.getSource() == node
            ? Output
            : Input;
    }

}
