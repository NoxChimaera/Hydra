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

import com.github.noxchimaera.hydra.core.modules.diversify.Argument;
import com.github.noxchimaera.hydra.utils.Strings;
import com.github.noxchimaera.hydra.utils.annotations.ImplicitUsage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Signature implements Serializable {

    @XmlElement(name = "Returns")
    private String returnType;

    @XmlElementWrapper(name = "Arguments")
    @XmlElement(name = "Argument")
    private List<Argument> arguments;

    @Deprecated
    @ImplicitUsage(cause = "JAXB")
    public Signature() { }

    public Signature(String returnType, List<Argument> arguments) {
        this.returnType = returnType;
        this.arguments = arguments;
    }

    public String returnType() {
        return returnType;
    }

    public List<Argument> arguments() {
        return new ArrayList<>(arguments);
    }

    public String hyxml() {
        return Strings.$(
            "<Signature>",
            "<Returns>", returnType, "</Returns>",
            "<Arguments>", Strings.joined(" ", arguments, i -> i.hyxml()), "</Arguments>",
            "</Signature>");
    }

    @Override
    public String toString() {
        return Strings.$("(", Strings.joined(", ", arguments), ") -> ", returnType);
    }

}
