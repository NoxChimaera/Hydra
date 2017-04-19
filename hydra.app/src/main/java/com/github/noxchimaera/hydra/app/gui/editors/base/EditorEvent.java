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

package com.github.noxchimaera.hydra.app.gui.editors.base;

/**
 * @author Nox
 */
public class EditorEvent<T> {

    public static <T> EditorEvent<T> ok(Editor<T> editor, T value) {
        return new EditorEvent<>(editor, Status.OK, value);
    }

    public static <T> EditorEvent<T> apply(Editor<T> editor, T value) {
        return new EditorEvent<>(editor, Status.APPLY, value);
    }

    public static <T> EditorEvent<T> cancel(Editor<T> editor) {
        return new EditorEvent<>(editor, Status.CANCEL, null);
    }

    public enum Status {
        OK, APPLY, CANCEL
    }

    private Editor<T> source;
    private Status status;
    private T value;

    public EditorEvent(Editor<T> source, Status status, T value) {
        this.source = source;
        this.status = status;
        this.value = value;
    }

}
