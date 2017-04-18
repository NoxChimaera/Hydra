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

package com.github.noxchimaera.hydra.app.mx.style.opacity;

import com.github.noxchimaera.hydra.app.Log;
import com.github.noxchimaera.hydra.app.mx.style.CellStyle;
import com.github.noxchimaera.hydra.utils.Strings;

/**
 * @author Nox
 */
public class CellTextOpacity extends CellStyle {

    private float opacity;

    public CellTextOpacity(float opacity) {
        super("textOpacity");
        if (opacity < 0) {
            opacity = 0;
            Log.shared.warning("Opacity level < 0");
        } else if (opacity > 100) {
            opacity = 100;
            Log.shared.warning("Opacity level > 100");
        }
        this.opacity = opacity;
    }

    @Override
    public String getValue() {
        return Strings.$(property, "=", opacity, ";");
    }

}