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

import com.github.noxchimaera.hydra.core.modules.diversify.Signature;
import com.github.noxchimaera.hydra.core.modules.diversify.VersionConfiguration;
import com.github.noxchimaera.hydra.utils.Strings;
import com.github.noxchimaera.hydra.utils.annotations.ImplicitUsage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AlgorithmConfiguration {

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Signature")
    private Signature signature;

    @XmlElementWrapper(name = "Versions")
    @XmlElement(name = "Version")
    private List<VersionConfiguration> versions;

    @Deprecated
    @ImplicitUsage(cause = "JAXB")
    public AlgorithmConfiguration() { }

    public AlgorithmConfiguration(String name, Signature signature, List<VersionConfiguration> versions) {
        this.name = name;
        this.signature = signature;
        this.versions = versions;
    }

    public String name() {
        return name;
    }

    public Signature signature() {
        return signature;
    }

    public List<VersionConfiguration> versions() {
        return new ArrayList<>(versions);
    }

    public String hyxml() {
        return Strings.$(
            "<Algorithm>",
            "<Name>", name, "</Name>",
            "<Signature>", signature.hyxml(), "</Signature>",
            "<Versions>", Strings.joined(" ", versions, i -> i.hyxml()), "</Versions>",
            "</Algorithm>");
    }

    @Override
    public String toString() {
        return name;
    }

}
