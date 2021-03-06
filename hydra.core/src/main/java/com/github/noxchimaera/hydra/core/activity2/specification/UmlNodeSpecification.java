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

package com.github.noxchimaera.hydra.core.activity2.specification;

import com.github.noxchimaera.hydra.core.activity2.specification.cardinality.ControlflowUmlCardinalitySpecification;
import com.github.noxchimaera.hydra.utils.properties.Property;

import java.io.Serializable;

/**
 * @author Nox
 */
public class UmlNodeSpecification implements Serializable {

    transient public final Property<ControlflowUmlCardinalitySpecification> ControlflowCardinality;

    public UmlNodeSpecification(ControlflowUmlCardinalitySpecification controlflowCardinality) {
        ControlflowCardinality = new Property<>(controlflowCardinality);
    }

}
