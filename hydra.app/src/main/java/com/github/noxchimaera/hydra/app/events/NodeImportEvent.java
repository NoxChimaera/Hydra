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

package com.github.noxchimaera.hydra.app.events;

import com.github.noxchimaera.hydra.app.mx.UmlGraphComponent;
import com.github.noxchimaera.hydra.app.uml.UmlCell;

import java.awt.*;

/**
 * @author Nox
 */
public class NodeImportEvent {

    private boolean consumed;
    private UmlCell cell;
    private double dx;
    private double dy;
    private Object target;
    private Point location;

    private Object sender;

    public NodeImportEvent(Object sender, UmlCell cell, double dx, double dy, Object target, Point location, boolean isConsumed) {
        this.sender = sender;
        this.cell = cell;
        this.dx = dx;
        this.dy = dy;
        this.target = target;
        this.location = location;
        consumed = isConsumed;
    }

    public NodeImportEvent(Object sender, UmlCell cell, double dx, double dy, Object target, Point location) {
        this(sender, cell, dx, dy, target, location, false);
    }

    public boolean isConsumed() {
        return consumed;
    }

    public UmlCell getCell() {
        return cell;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Object getTarget() {
        return target;
    }

    public Point getLocation() {
        return location;
    }

    public Object getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "NodeImportEvent{" + "consumed=" + consumed + ", node=" + cell + ", sender=" + sender + '}';
    }

}
