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

package com.github.noxchimaera.hydra.app.events.explicit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author Nox
 */
public class ActionEventShim<T> {

    private Set<BiConsumer<Object, T>> handlers;

    public ActionEventShim() {
        handlers = new HashSet<>();
    }

    public void register(BiConsumer<Object, T> handler) {
        handlers.add(handler);
    }

    public void unregister(BiConsumer<Object, T> handler) {
        handlers.remove(handler);
    }

    public void post(Object sender, T message) {
        for (BiConsumer<Object, T> handler : handlers) {
            handler.accept(sender, message);
        }
    }

}
