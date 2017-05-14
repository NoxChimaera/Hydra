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
@XmlAccessorType(XmlAccessType.FIELD)
public class VersionConfiguration implements Serializable {

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Class")
    private String className;

    @XmlElement(name = "Package")
    private String packageName;

    @XmlElement(name = "Path")
    private String path;

    @Deprecated
    @ImplicitUsage(cause = "JAXB constraints")
    public VersionConfiguration() { }

    public VersionConfiguration(String name, String className, String packageName, String path) {
        this.name = name;
        this.className = className;
        this.packageName = packageName;
        this.path = path;
    }

    public String name() {
        return name;
    }

    public String className() {
        return className;
    }

    public String packageName() {
        return packageName;
    }

    public String path() {
        return path;
    }

    public String hyxml() {
        return Strings.$(
            "<Version>",
            "<Name>", name, "</Name>",
            "<Class>", className, "</Class>",
            "<Package>", packageName, "</Package>",
            "<Path>", path, "</Path>",
            "</Version>");
    }

    @Override
    public String toString() {
        return name;
    }

}
