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

package com.github.noxchimaera.hydra.app.mx.style.align;

import com.mxgraph.util.mxConstants;

/**
 * @author Nox
 */
public enum HorizontalAlignment {

    Left(mxConstants.ALIGN_LEFT),
    Centre(mxConstants.ALIGN_CENTER),
    Right(mxConstants.ALIGN_RIGHT);

    private String value;

    private HorizontalAlignment(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

}
