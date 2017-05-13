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

package com.github.noxchimaera.hydra.app.modules.diversify.configuration;

import com.github.noxchimaera.hydra.core.modules.diversify.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nox
 */
public class DiversifyConfigurationTest {

    @Test public void test_XmlMarshalling() throws Exception {
        Signature alg0_signature = new Signature("int", Arrays.asList(
            new Argument("data", "int[]"),
            new Argument("count", "int")
        ));
        List<VersionConfiguration> alg0_versions = Arrays.asList(
            new VersionConfiguration("Version 0", "Version0", "versions.alg0", "/path/alg/"),
            new VersionConfiguration("Version 1", "Version1", "versions.alg1", "/path/alg/")
        );

        Signature alg1_signature = new Signature("int[]", Arrays.asList(
            new Argument("data", "int[]")
        ));
        List<VersionConfiguration> alg1_versions = Arrays.asList(
            new VersionConfiguration("Shuffle", "Shuffle0", "versions.shuffle", "/path/shuffle/")
        );

        List<AlgorithmConfiguration> algorithms = Arrays.asList(
            new AlgorithmConfiguration("Algorithm", alg0_signature, alg0_versions),
            new AlgorithmConfiguration("Shuffle", alg1_signature, alg1_versions)
        );
        DiversifyConfiguration root = new DiversifyConfiguration(algorithms);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // File file = new File("../config/modules/diversify/diversify.config");
        // FileOutputStream out = new FileOutputStream(file);
        root.save(root, out);

    }

}