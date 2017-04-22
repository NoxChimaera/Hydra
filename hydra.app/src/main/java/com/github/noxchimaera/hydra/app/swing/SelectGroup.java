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

package com.github.noxchimaera.hydra.app.swing;

import com.github.noxchimaera.hydra.utils.swing.GUI;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nox
 */
public class SelectGroup extends JPanel {

    private JTextField textField;
    private JButton selectButton;

    private String watermark;

    public SelectGroup() {
        super(new BorderLayout(4, 4));
        watermark = "";

        initialize();
    }

    private void initialize() {
        textField = new JTextField();
        textField.setEditable(false);
        textField.setText(watermark);
        add(textField, GUI.borderLayout_Centre());

        selectButton = new JButton(
            IconFontSwing.buildIcon(FontAwesome.ELLIPSIS_H, textField.getFont().getSize()));
        add(selectButton, GUI.borderLayout_Right());
    }

}
