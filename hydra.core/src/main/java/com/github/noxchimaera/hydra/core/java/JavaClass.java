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

import com.github.noxchimaera.hydra.utils.Collections;
import com.github.noxchimaera.hydra.utils.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
public class JavaClass {

    private String classPackage;

    private List<String> imports;

    private String name;

    private List<JavaMethod> methods;

    public JavaClass(String classPackage, String name, List<String> imports) {
        this.classPackage = classPackage;
        this.name = name;
        this.methods = new ArrayList<>();
        this.imports = imports;
    }

    public void addMethod(JavaMethod method) {
        methods.add(method);
    }

    public String classPackage() {
        return classPackage;
    }

    public String name() {
        return name;
    }

    public List<JavaMethod> methods() {
        return Collections.asList(methods);
    }

    public void methods(List<JavaMethod> methods) {
        this.methods = methods;
    }

    public String str() {
        return Strings.$(
            "package ", classPackage, "; ",
            Strings.joined("", imports, i -> "import " + i),
            "public class ", name, " { ",
            Strings.joined("", methods, i -> i.str()),
            "}"
        );
    }

}
