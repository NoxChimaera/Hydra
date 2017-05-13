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
import com.github.noxchimaera.hydra.utils.io.xml.GenericExternalXmlDocument;

import javax.xml.bind.annotation.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "HydraNvp")
public class DiversifyConfiguration extends GenericExternalXmlDocument<DiversifyConfiguration> {

    @XmlElementWrapper(name = "Algorithms")
    @XmlElement(name = "Algorithm")
    private List<AlgorithmConfiguration> algorithms;

    @Deprecated
    @ImplicitUsage(cause = "JAXB")
    public DiversifyConfiguration() {
        this(new ArrayList<>());
    }

    public DiversifyConfiguration(List<AlgorithmConfiguration> algorithms) {
        super(DiversifyConfiguration.class);
        this.algorithms = algorithms;
    }

    public List<AlgorithmConfiguration> algorithms() {
        return new ArrayList<>(algorithms);
    }

    @Override
    public DiversifyConfiguration load(InputStream in) {
        DiversifyConfiguration cfg = super.load(in);
        algorithms = cfg.algorithms();
        return this;
    }

    public String hyxml() {
        return Strings.$(
            "<HydraNvp>",
            "<Algorithms>", Strings.joined(" ", algorithms, i -> i.hyxml()), "</Algorithms>",
            "</HydraNVP>");
    }

}
