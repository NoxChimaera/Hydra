/*
 * Copyright 2016 Max Balushkin.
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

package com.github.noxchimaera.hydra.app;

import com.github.noxchimaera.hydra.utils.commons.Initializable;
import com.sun.istack.internal.logging.Logger;

import javax.swing.*;
import java.util.logging.Level;

/**
 * @author Max Balushkin
 */
public class EditorApp implements Initializable {

    public EditorApp() {

    }

    @Override public
    void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(EditorApp.class).log(Level.WARNING, ex.getMessage());
        }


    }

    @Override public
    void uninitialize() {

    }

}
