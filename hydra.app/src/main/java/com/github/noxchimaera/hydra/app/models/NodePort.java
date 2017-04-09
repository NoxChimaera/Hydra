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

package com.github.noxchimaera.hydra.app.models;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;

/**
 * @author Nox
 */
public class NodePort extends mxCell {

    public enum Type {
        Input
        , Output
    }

    public static final double DIAMETER = 24;
    public static final double RADIUS = DIAMETER / 2;

    private Type type;
    private NodeCell cell;

    public
    NodePort(Type type, NodeCell cell, String title, double geoX, double geoY) {
        super(title);
        this.type = type;
        this.cell = cell;
        cell.addPort(title, this);

        geometry = new mxGeometry(geoX, geoY, DIAMETER, DIAMETER);
        geometry.setOffset(new mxPoint(-RADIUS, -RADIUS));
        geometry.setRelative(true);

        connectable = true;
        vertex = true;
    }

    public
    Type getType() {
        return type;
    }

    public
    NodeCell getCell() {
        return cell;
    }

    public
    String getName() {
        return (String)value;
    }

}
