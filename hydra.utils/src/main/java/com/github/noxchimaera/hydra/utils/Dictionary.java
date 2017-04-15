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

import java.util.*;

/**
 * @author Nox
 */
public class Dictionary<TKey, TValue> {

    private Map<TKey, TValue> map;

    public Dictionary() {
        map = new HashMap<TKey, TValue>();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public Optional<TValue> get(Object key) {
        TValue val = map.get(key);
        return val == null
            ? Optional.empty()
            : Optional.ofNullable(val);
    }

    public void put(TKey key, Optional<TValue> value) {
        if (value.isPresent()) {
            map.put(key, value.get());
        }
    }

    public void put(TKey key, TValue value) {
        map.put(key, value);
    }

    public void remove(Object key) {
        map.remove(key);
    }

    public void putAll(Map<? extends TKey, ? extends TValue> m) {
        map.putAll(m);
    }

    public void clear() {
        map.clear();
    }

    public Set<TKey> keySet() {
        return map.keySet();
    }

    public Collection<TValue> values() {
        return map.values();
    }

    public Set<Map.Entry<TKey, TValue>> entrySet() {
        return map.entrySet();
    }

}
