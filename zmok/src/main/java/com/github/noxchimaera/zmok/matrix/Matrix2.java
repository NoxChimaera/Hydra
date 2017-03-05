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

import java.util.function.BiFunction;

/**
 * Represents 2D matrix.
 *
 * @author Max Balushkin
 */
public class Matrix2<T> {

    protected Object[][] data;

    protected int n;
    protected int m;

    /**
     * Creates new 2D matrix.
     *
     * @param defaultValue default value of matrix items
     * @param n            matrix height
     * @param m            matrix width
     */
    public Matrix2(T defaultValue, int n, int m) {
        this.n = n;
        this.m = m;
        data = new Object[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                data[i][j] = defaultValue;
            }
        }
    }

    /**
     * Creates new 2D matrix with specified data. <br>
     * Expects rectangular array.
     *
     * @param data data
     *
     * @throws IllegalArgumentException if matrix is not rectangular
     */
    public Matrix2(T[][] data) {
        n = data.length;
        for (T[] arr : data) {
            if (arr.length != n) {
                throw new IllegalArgumentException("Expecting rectangular matrix");
            }
            m = arr.length;
        }
        this.data = data;
    }

    /**
     * Returns matrix item.
     *
     * @param i row index
     * @param j column index
     *
     * @return item
     */
    public T get(int i, int j) {
        return (T)data[i][j];
    }

    /**
     * Sets matrix item.
     *
     * @param value item value
     * @param i     row index
     * @param j     column index
     */
    public void set(T value, int i, int j) {
        data[i][j] = value;
    }

    /**
     * Returns matrix row by index.
     *
     * @param i row index
     *
     * @return matrix row
     */
    public Row<T> getRow(int i) {
        return new Row<T>((T[])data[i]);
    }

    /**
     * Sets matrix row.
     *
     * @param row row
     * @param i   row index
     */
    public void setRow(Row<T> row, int i) {
        for (int j = 0; j < getHeight(); ++j) {
            data[i][j] = row.get(j);
        }
    }

    /**
     * Returns matrix column.
     *
     * @param j column index
     *
     * @return matrix column
     */
    public Row<T> getColumn(int j) {
        Object[] col = new Object[m];
        for (int i = 0; i < n; i++) {
            col[j] = data[i][j];
        }
        return new Row<T>((T[])col);
    }

    /**
     * Returns matrix height (<code>n</code>).
     *
     * @return matrix height
     */
    public int getHeight() {
        return n;
    }

    /**
     * Returns matrix width (<code>m</code>).
     *
     * @return matrix width
     */
    public int getWidth() {
        return m;
    }

    /**
     * Creates new matrix from this and specified.
     *
     * @param functor zipping function
     * @param b       other matrix
     *
     * @return new matrix
     */
    public Matrix2<T> zipWith(BiFunction<T, T, T> functor, Matrix2<T> b) {
        if (getWidth() != b.getWidth() && getHeight() != b.getHeight()) {
            throw new IllegalArgumentException("Must have same dimension");
        }
        final int n = getHeight();
        final int m = getWidth();
        Matrix2<T> res = new Matrix2<T>(null, n, m);
        for (int i = 0; i < getHeight(); ++i) {
            Row<T> aRow = getRow(i);
            Row<T> bRow = b.getRow(i);
            Row<T> cRow = new Row<T>(null, m);
            for (int j = 0; j < m; ++j) {
                T c = functor.apply(aRow.get(j), bRow.get(j));
                cRow.set(c, j);
            }
            res.setRow(cRow, i);
        }
        return res;
    }

}
