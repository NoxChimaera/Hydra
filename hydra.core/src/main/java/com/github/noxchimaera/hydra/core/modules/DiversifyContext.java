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

package com.github.noxchimaera.hydra.core.modules;

import com.github.noxchimaera.hydra.core.modules.diversify.AlgorithmConfiguration;
import com.github.noxchimaera.hydra.core.modules.diversify.GenVoterConfiguration;
import com.github.noxchimaera.hydra.core.modules.diversify.VersionConfiguration;
import com.github.noxchimaera.hydra.utils.Collections;
import com.github.noxchimaera.zmok.GenVoter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nox
 */
public class DiversifyContext implements Serializable {

    private GenVoterConfiguration genvoter;

    private AlgorithmConfiguration algorithm;
    private List<VersionConfiguration> versions;

    public DiversifyContext(
        GenVoterConfiguration genvoter, AlgorithmConfiguration algorithm, List<VersionConfiguration> versions) {

        this.genvoter = genvoter;
        this.algorithm = algorithm;
        this.versions = versions;
    }

    public GenVoterConfiguration genvoter() {
        return genvoter;
    }

    public AlgorithmConfiguration algorithm() {
        return algorithm;
    }

    public List<VersionConfiguration> versions() {
        return Collections.asList(versions);
    }

}
