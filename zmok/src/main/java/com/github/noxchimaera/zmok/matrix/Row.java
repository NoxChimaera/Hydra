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

package com.github.noxchimaera.zmok.matrix;

import java.util.stream.Stream;

/**
 * Represents matrix row or column.
 *
 * @author Max Balushkin
 */
public class Row<T> {

    private Object[] data;
    private final int n;

    /**
     * Creates new row.
     *
     * @param defaultValue default item value
     * @param n            row length
     */
    public Row(T defaultValue, int n) {
        this.n = n;
        data = new Object[n];
        for (int i = 0; i < n; ++i) {
            data[i] = defaultValue;
        }
    }

    /**
     * Creates new row with specified data.
     *
     * @param data row data
     */
    public Row(T[] data) {
        this.data = data;
        n = data.length;
    }

    /**
     * Returns row item by index.
     *
     * @param i item index
     *
     * @return item
     */
    public T get(int i) {
        return (T)data[i];
    }

    /**
     * Sets row item.
     *
     * @param value item
     * @param i     item index
     */
    public void set(T value, int i) {
        data[i] = value;
    }

    /**
     * Returns row length.
     *
     * @return row length
     */
    public int getLength() {
        return n;
    }

    /**
     * Returns stream of row items.
     *
     * @return stream
     */
    public Stream<T> stream() {
        return Stream.of(data).map(i -> (T)i);
    }

}
