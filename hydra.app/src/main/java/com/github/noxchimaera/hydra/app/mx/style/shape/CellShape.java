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

package com.github.noxchimaera.hydra.app.mx.style.shape;

import com.github.noxchimaera.hydra.app.mx.style.CellStyle;
import com.github.noxchimaera.hydra.utils.Strings;

/**
 * @author Nox
 */
public class CellShape extends CellStyle {

    private CellShapes shape;

    public CellShape(CellShapes shape) {
        super("shape");
        this.shape = shape;
    }

    @Override
    public String getValue() {
        return Strings.$(property, "=", shape.asString(), ";");
    }

}
