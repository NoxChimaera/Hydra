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

package com.github.noxchimaera.hydra.core.activity2.stereotypes.constraints;

import com.github.noxchimaera.hydra.core.activity2.UmlNode;

import java.util.function.Function;

/**
 * @author Nox
 */
@FunctionalInterface
public interface StereotypeConstraint<T extends UmlNode> {

    boolean checkConstraint(T node);

    default <U extends UmlNode> StereotypeConstraint<T> compose(StereotypeConstraint<U> other, Function<T, U> mapper) {
        return node -> {
            if(checkConstraint(node)) {
                return other.checkConstraint(mapper.apply(node));
            }
            return false;
        };
    }

    default StereotypeConstraint<T> compose(StereotypeConstraint<T> other) {
        return node -> {
            if (checkConstraint(node)) {
                return other.checkConstraint(node);
            }
            return false;
        };
    }

}
