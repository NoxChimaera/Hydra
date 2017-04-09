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

import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Nox
 */
public class NodeCell extends mxCell {

    private Map<String, NodePort> ports;

    public
    NodeCell(UmlNode value, double x, double y, double width, double height) {
        super(value);
        geometry = new mxGeometry(x - width / 2, y - height / 2, width, height);
        geometry.setRelative(false);

        connectable = false;
        vertex = true;
        ports = new HashMap<>();
    }

    public <T>
    T getUserObject() {
        return (T)value;
    }

    public
    void addPort(String portName, NodePort port) {
        ports.put(portName, port);
    }

    public
    void removePort(String portName) {
        ports.remove(portName);
    }

    public
    NodePort getPort(String portName) {
        return ports.get(portName);
    }

    public
    List<NodePort> getPorts(NodePort.Type portType) {
        return ports.values().stream()
            .filter(p -> p.getType() == portType)
            .collect(Collectors.toList());
    }

}
