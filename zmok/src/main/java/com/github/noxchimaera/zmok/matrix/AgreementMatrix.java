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

/**
 * @author Max Balushkin
 */

import java.util.List;

/**
 * @author Max Balushkin
 */
public class AgreementMatrix extends Matrix2<Boolean> {

    public static <U extends Number> AgreementMatrix create(List<U> data, double epsilon) {
        final int n = data.size();
        AgreementMatrix matrix = new AgreementMatrix(n);
        for (int i = 0; i < n; ++i) {
            double xi = data.get(i).doubleValue();
            for (int j = 0; j < n; ++j) {
                double xj = data.get(j).doubleValue();
                matrix.set(Math.abs(xi - xj) <= epsilon, i, j);
            }
        }

        while (!matrix.isTransitive()) {
            matrix = (AgreementMatrix)matrix.zipWith((a, b) -> a | b, matrix);
        }

        return matrix;
    }

    public AgreementMatrix(int n) {
        super(false, n, n);
    }

    public AgreementMatrix(Boolean[][] data) {
        super(data);
        if (n != m) {
            throw new IllegalArgumentException("Expecting NxN matrix");
        }
    }

    public int getRowAgreement(int i) {
        return getRow(i).stream()
            .mapToInt(val -> val ? 1 : 0)
            .sum();
    }

    public boolean isTransitive() {
        final int n = getHeight();
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                for (int j = 0; j < n; ++j) {
                    if (get(i, k) && get(k, j)) {
                        if (!get(i, j)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public AgreementMatrix booleanComposition() {
        final int n = getHeight();
        Boolean[][] res = new Boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                for (int j = 0; j < n; ++j) {
                    res[i][j] |= get(i, k) && get(k, j);
                }
            }
        }
        return new AgreementMatrix(res);
    }

}
