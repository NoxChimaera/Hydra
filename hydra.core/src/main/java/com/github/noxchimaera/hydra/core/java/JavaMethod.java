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

package com.github.noxchimaera.hydra.core.java;

import com.github.noxchimaera.hydra.utils.Strings;

import java.util.List;

/**
 * @author Nox
 */
public class JavaMethod {

    private String name;
    private String returnType;
    private List<JavaArgument> arguments;

    private String body;

    public JavaMethod(String name, String returnType, List<JavaArgument> arguments) {
        this.name = name;
        this.returnType = returnType;
        this.arguments = arguments;
    }

    public String name() {
        return name;
    }

    public String returnType() {
        return returnType;
    }

    public List<JavaArgument> arguments() {
        return arguments;
    }

    public String body() {
        return body;
    }

    public void body(String body) {
        this.body = body;
    }

    public String str() {
        return Strings.$(
            "public ", returnType, " ", name, "(", Strings.joined(", ", arguments, i -> i.str()), ") { ",
            body, "}"
        );
    }

}
