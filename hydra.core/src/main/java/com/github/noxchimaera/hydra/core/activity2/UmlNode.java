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

import com.github.noxchimaera.hydra.core.activity2.specification.UmlNodeSpecification;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.core.graph.Node;
import com.github.noxchimaera.hydra.core.graph.NodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Nox
 */
public abstract class UmlNode extends Node<String> {

    protected UmlView view;

    protected List<Stereotype> stereotypes;

    protected final UmlNodeSpecification specification;

    public UmlNode(long id, NodeType type, String value, UmlNodeSpecification specification) {
        super(id, type);
        this.value = value;
        view = new UmlView();
        stereotypes = new ArrayList<>();
        this.specification = specification;
    }

    public UmlView getView() {
        return view;
    }

    public void setView(UmlView view) {
        this.view = view;
    }

    public UmlNodeSpecification getSpecification() {
        return specification;
    }

    public UmlNode addStereotype(Stereotype aStereotype) {
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

    @Override
    public String toString() {
        return value;
    }

    public abstract UmlNode deepClone();

}
