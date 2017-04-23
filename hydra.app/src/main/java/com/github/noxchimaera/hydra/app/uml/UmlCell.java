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

package com.github.noxchimaera.hydra.app.uml;

import com.github.noxchimaera.hydra.app.mx.style.CellStyle;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

/**
 * @author Nox
 */
public class UmlCell extends mxCell {

    public UmlCell(UmlNode value, double x, double y, double width, double height) {
        super(value);
        geometry = new mxGeometry(x - width / 2, y - height / 2, width, height);
        geometry.setRelative(false);

        vertex = true;
    }

    public <T>
    T getUserObject() {
        return (T) value;
    }

    public UmlCell clearStyle() {
        setStyle("");
        return this;
    }

    public UmlCell addStyle(CellStyle cellStyle) {
        String style = getStyle() + String.format("%s", cellStyle.value());
        setStyle(style);
        return this;
    }

    public UmlCell addStyle(String property, String value) {
        String style = getStyle() + String.format("%s=%s;", property, value);
        setStyle(style);
        return this;
    }

}
