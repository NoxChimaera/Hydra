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

package com.github.noxchimaera.hydra.app.mx.style;

import com.github.noxchimaera.hydra.utils.Strings;
import com.mxgraph.util.mxConstants;

/**
 * @author Nox
 */
public class CellRounded extends SimpleCellStyle {

    private boolean enabled;

    public CellRounded(boolean enabled) {
        super(mxConstants.STYLE_ROUNDED);
        this.enabled = enabled;
    }

    @Override
    public String parameter() {
        return String.valueOf(enabled);
    }

}
