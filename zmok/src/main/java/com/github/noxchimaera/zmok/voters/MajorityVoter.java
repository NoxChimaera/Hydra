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

package com.github.noxchimaera.zmok.voters;

import akka.japi.Option;
import com.github.noxchimaera.zmok.GenVersion;
import com.github.noxchimaera.zmok.GenVoter;
import com.github.noxchimaera.zmok.matrix.AgreementMatrix;
import com.github.noxchimaera.zmok.matrix.Row;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NVP-MV (Majority) voter.
 *
 * @author Max Balushkin
 */
public class MajorityVoter<T extends Number> implements GenVoter<T> {

    @Override public Option<T> vote(Collection<GenVersion<T>> versions) {
        List<T> results = versions.stream()
            .map(GenVersion::getHeuristic)
            .filter(Option::isDefined)
            .map(Option::get)
            .collect(Collectors.toList());

        AgreementMatrix matrix = AgreementMatrix.create(results, 0.01);
        int acceptanceLevel = (int)Math.ceil((matrix.getHeight() + 1) / 2.0);
        for (int i = 0; i < matrix.getHeight(); ++i) {
            if (matrix.getRowAgreement(i) >= acceptanceLevel) {
                Row<Boolean> row = matrix.getRow(i);
                double sum = 0;
                int len = 0;
                for (int j = 0; j < row.getLength(); ++j) {
                    if (row.get(j)) {
                        sum += results.get(j).doubleValue();
                        ++len;
                    }
                }
                return Option.some((T)(Double)(sum / len));
            }
        }

        return Option.none();
    }

}
