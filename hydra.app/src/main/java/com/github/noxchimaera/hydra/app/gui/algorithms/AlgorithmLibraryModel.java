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

package com.github.noxchimaera.hydra.app.gui.algorithms;

import com.github.noxchimaera.hydra.core.modules.diversify.AlgorithmConfiguration;
import com.github.noxchimaera.hydra.core.modules.diversify.DiversifyConfiguration;
import com.github.noxchimaera.hydra.utils.Collections;

import java.util.List;

/**
 * @author Nox
 */
public class AlgorithmLibraryModel {

    private DiversifyConfiguration configuration;

    public AlgorithmLibraryModel(DiversifyConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<AlgorithmConfiguration> algorithms() {
        return Collections.asList(configuration.algorithms());
    }

}
