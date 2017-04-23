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

import com.github.noxchimaera.hydra.app.mx.style.CompoundCellStyle;
import com.mxgraph.util.mxConstants;

/**
 * @author Nox
 */
public class CellLabelPosition extends CompoundCellStyle {

    private HorizontalAlignment horizontal;
    private VerticalAlignment vertical;

    public CellLabelPosition(HorizontalAlignment horizontal, VerticalAlignment vertical) {
        super(mxConstants.STYLE_LABEL_POSITION, mxConstants.STYLE_VERTICAL_LABEL_POSITION);
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public String[] parameters() {
        return new String[] { horizontal.asString(), vertical.asString() };
    }

}
