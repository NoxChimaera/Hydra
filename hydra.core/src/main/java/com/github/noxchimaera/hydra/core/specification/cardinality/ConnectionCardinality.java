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

package com.github.noxchimaera.hydra.core.specification.cardinality;

/**
 * @author Nox
 */
public class ConnectionCardinality {

    public enum Type {
        Unbounded, Bounded
    }

    public static ConnectionCardinality unbounded() {
        return new ConnectionCardinality();
    }

    public static ConnectionCardinality none() {
        return new ConnectionCardinality(0);
    }

    public static ConnectionCardinality bounded(int bound) {
        return new ConnectionCardinality(bound);
    }

    private Type type;
    private int bound;

    public ConnectionCardinality() {
        type = Type.Unbounded;
        bound = -1;
    }

    public ConnectionCardinality(int bound) {
        type = Type.Bounded;
        this.bound = bound;
    }

    public Type getType() {
        return type;
    }

    public int getBound() {
        return bound;
    }

    public boolean test(long count) {
        if (Type.Unbounded == type) {
            return true;
        } else {
            return count < bound;
        }
    }

}
