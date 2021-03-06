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

package com.github.noxchimaera.hydra.app.gui.editors.components;

import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.StereotypeDescriptor;

/**
 * @author Nox
 */
public class EmptyStereotypeComponent extends StereotypeComponent {

    public static final EmptyStereotypeComponent Shared = new EmptyStereotypeComponent();

    @Override
    public Class type() {
        return EmptyStereotypeComponent.class;
    }

    @Override
    public void fill(Stereotype stereotype) { }

    @Override
    public Stereotype stereotype() {
        return null;
    }

    @Override
    public boolean test(UmlNode node) {
        return true;
    }

}
