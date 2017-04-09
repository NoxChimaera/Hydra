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

package com.github.noxchimaera.hydra.core.activity2;

import com.github.noxchimaera.hydra.core.graph.Node;
import com.github.noxchimaera.hydra.core.graph.NodeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Nox
 */
public abstract class UmlNode extends Node<String> {

    protected List<Stereotype> stereotypes;

    public UmlNode(long id, NodeType type, String value) {
        super(id, type);
        setValue(value);
        stereotypes = new ArrayList<>();
    }

    public
    UmlNode addStereotype(Stereotype aStereotype) {
        stereotypes.add(aStereotype);
        return this;
    }

    public <TStereotype extends Stereotype>
    Optional<TStereotype> getStereotype(Class<TStereotype> stereotypeClass) {
        return stereotypes
            .stream()
            .filter(st -> stereotypeClass.isInstance(st))
            .findFirst()
            .map(st -> stereotypeClass.cast(st));
    }

    @Override public
    String toString() {
        return value;
    }

}
