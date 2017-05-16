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
import com.github.noxchimaera.hydra.core.activity2.stereotypes.StereotypeDescriptor;
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

    protected Stereotype stereotype;

    protected final UmlNodeSpecification specification;

    public UmlNode(long id, NodeType type, String value, UmlNodeSpecification specification) {
        super(id, type);
        this.value = value;
        view = new UmlView();
        this.specification = specification;
    }

    public UmlView view() {
        return view;
    }

    public void view(UmlView view) {
        this.view = view;
    }

    public UmlNodeSpecification specification() {
        return specification;
    }

    public boolean hasStereotype() {
        return null != stereotype;
    }

    public Stereotype stereotype() {
        return stereotype;
    }

    public void stereotype(Stereotype stereotype) {
        this.stereotype = stereotype;
    }

    @Override
    public String toString() {
        return value;
    }

    public abstract UmlNode copy();

    public abstract <T> T accept(UmlVisitor<T> visitor);

}
