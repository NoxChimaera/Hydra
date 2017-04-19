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

import com.github.noxchimaera.hydra.core.activity2.stereotypes.DiversifiedStereotype;
import com.github.noxchimaera.hydra.utils.ListUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nox
 */
public class Stereotypes {

    public final static DiversifiedStereotype Diversified = new DiversifiedStereotype();

    public static List<Stereotype> getAll(boolean withEmpty) {
        return ListUtils.<Stereotype>toList(!withEmpty, null, Diversified);
    }

}
