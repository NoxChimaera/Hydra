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

package com.github.noxchimaera.hydra.core.activity2.stereotypes;

import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.constraints.StereotypeConstraint;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.constraints.StereotypeConstraintSequence;

import java.util.HashSet;

/**
 * @author Nox
 */
public class StereotypeDescriptor {

    private String name;
    private Class<? extends Stereotype> stereotypeClass;

    protected StereotypeConstraintSequence constraints;

    public StereotypeDescriptor(
        String name, Class<? extends Stereotype> stereotypeClass, StereotypeConstraintSequence constraints) {

        this.name = name;
        this.stereotypeClass = stereotypeClass;
        this.constraints = constraints;
    }

    public boolean isAcceptable(UmlNode node) {
        return constraints.checkConstraint(node);
    }

    public String name() {
        return name;
    }

    public Class<? extends Stereotype> stereotypeClass() {
        return stereotypeClass;
    }

    @Override
    public String toString() {
        return name;
    }

}
