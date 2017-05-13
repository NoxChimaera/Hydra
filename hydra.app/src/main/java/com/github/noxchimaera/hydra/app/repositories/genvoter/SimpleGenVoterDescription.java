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

package com.github.noxchimaera.hydra.app.repositories.genvoter;

import com.github.noxchimaera.hydra.core.modules.diversify.GenVoterConfiguration;

/**
 * @author Nox
 */
public class SimpleGenVoterDescription extends GenVoterConfiguration {

    public SimpleGenVoterDescription(String name, Class voterClass) {
        super(name, voterClass);
    }

    @Override
    public String java() {
        return "";
    }

}
