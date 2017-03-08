/*
 * Copyright 2016 Max Balushkin.
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

package com.github.noxchimaera.hydra.core.activity2.nodes;

/**
 * @author Max Balushkin
 */
public abstract class UmlEdge {

    private long id;

    private UmlEdgeType type;

    private UmlNode source;
    private UmlNode target;

    private String name;

    public UmlEdge(long id, UmlEdgeType type, UmlNode source, UmlNode target) {
        this.type = type;
        this.source = source;
        this.target = target;
    }

    public UmlEdgeType getType() {
        return type;
    }

    public void setType(UmlEdgeType type) {
        this.type = type;
    }

    public UmlNode getSource() {
        return source;
    }

    public void setSource(UmlNode source) {
        this.source = source;
    }

    public UmlNode getTarget() {
        return target;
    }

    public void setTarget(UmlNode target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

}
