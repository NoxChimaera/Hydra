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

package com.github.noxchimaera.hydra.app.events;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * @author Nox
 */
public class EventBus<T> {

    public final static EventBus<Object> Shared = new EventBus<>();

    private final Subject<T, T> subject;

    public EventBus() {
        this(PublishSubject.create());
    }

    public EventBus(Subject<T, T> subject) {
        this.subject = subject;
    }

    public <E extends T> void post(E event) {
        subject.onNext(event);
    }

    public <E extends T> Observable<E> observe(Class<E> eventType) {
        return subject.ofType(eventType);
    }

}
