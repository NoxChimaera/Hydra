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
import com.github.noxchimaera.hydra.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
public class StereotypeConstraintSequence implements StereotypeConstraint {

    private List<StereotypeConstraint> constraints;

    public StereotypeConstraintSequence() {
        constraints = new ArrayList<>();
    }

    public StereotypeConstraintSequence(StereotypeConstraint... constraints) {
        this.constraints = ListUtils.toList(constraints);
    }

    public StereotypeConstraintSequence add(StereotypeConstraint constraint) {
        constraints.add(constraint);
        return this;
    }

    @Override
    public boolean checkConstraint(UmlNode node) {
        return ListUtils.every(constraint -> constraint.checkConstraint(node), constraints);
    }

}
