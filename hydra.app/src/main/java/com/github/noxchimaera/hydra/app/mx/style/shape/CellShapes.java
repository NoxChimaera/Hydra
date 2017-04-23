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

import com.mxgraph.util.mxConstants;

/**
 * @author Nox
 */
public enum CellShapes {

    Rectangle(mxConstants.SHAPE_RECTANGLE),
    Ellipse(mxConstants.SHAPE_ELLIPSE),
    DoubleRectangle(mxConstants.SHAPE_DOUBLE_RECTANGLE),
    DoubleEllipse(mxConstants.SHAPE_ELLIPSE),
    Rhombus(mxConstants.SHAPE_RHOMBUS),
    Line(mxConstants.SHAPE_LINE),
    Image(mxConstants.SHAPE_IMAGE),
    Arrow(mxConstants.SHAPE_ARROW),
    Curve(mxConstants.SHAPE_CURVE),
    Label(mxConstants.SHAPE_LABEL),
    Cylinder(mxConstants.SHAPE_CYLINDER),
    Swimlane(mxConstants.SHAPE_SWIMLANE),
    Actor(mxConstants.SHAPE_ACTOR),
    Cloud(mxConstants.SHAPE_CLOUD),
    Triangle(mxConstants.SHAPE_TRIANGLE),
    Hexagon(mxConstants.SHAPE_HEXAGON);

    private String value;

    private CellShapes(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

}
