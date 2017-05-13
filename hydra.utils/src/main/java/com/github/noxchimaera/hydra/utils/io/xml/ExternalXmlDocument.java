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

package com.github.noxchimaera.hydra.utils.io.xml;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents external XML document.
 *
 * @param <T> internal XML document type
 * @author Nox
 */
public interface ExternalXmlDocument<T> {

    /**
     * Parses XML document from stream.
     *
     * @param in input stream
     * @return parsed document
     */
    T load(InputStream in);

    /**
     * Saves XML document to stream.
     *
     * @param obj internal XML document
     * @param out output stream
     */
    void save(T obj, OutputStream out);

}
