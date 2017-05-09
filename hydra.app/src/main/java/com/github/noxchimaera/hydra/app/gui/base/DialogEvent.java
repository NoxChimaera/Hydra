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

package com.github.noxchimaera.hydra.app.gui.base;

/**
 * @author Nox
 */
public class DialogEvent<T> {

    public static <T> DialogEvent<T> ok(Dialog<T> dialog, T value) {
        return new DialogEvent<>(dialog, Status.OK, value);
    }

    public static <T> DialogEvent<T> apply(Dialog<T> dialog, T value) {
        return new DialogEvent<>(dialog, Status.APPLY, value);
    }

    public static <T> DialogEvent<T> cancel(Dialog<T> dialog) {
        return new DialogEvent<>(dialog, Status.CANCEL, null);
    }

    public enum Status {
        OK, APPLY, CANCEL
    }

    private Dialog<T> source;
    private Status status;
    private T value;

    public DialogEvent(Dialog<T> source, Status status, T value) {
        this.source = source;
        this.status = status;
        this.value = value;
    }

    public Dialog<T> getSource() {
        return source;
    }

    public Status getStatus() {
        return status;
    }

    public T getValue() {
        return value;
    }

}
