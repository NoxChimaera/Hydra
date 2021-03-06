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

package com.github.noxchimaera.hydra.core.modules.diversify;

import com.github.noxchimaera.hydra.utils.Strings;
import com.github.noxchimaera.hydra.utils.annotations.ImplicitUsage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author Nox
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Argument implements Serializable {

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Type")
    private String type;

    @Deprecated
    @ImplicitUsage(cause = "JAXB")
    public Argument() { }

    public Argument(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String hyxml() {
        return Strings.$(
            "<Argument>",
            "<Name>", name, "</Name>",
            "<Type>", type, "</Type>",
            "</Argument>");
    }

    @Override
    public String toString() {
        return Strings.$(name, " :: ", type);
    }

}
