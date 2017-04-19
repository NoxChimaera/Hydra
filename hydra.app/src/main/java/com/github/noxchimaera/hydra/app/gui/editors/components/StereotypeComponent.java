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

package com.github.noxchimaera.hydra.app.gui.editors.components;

import com.github.noxchimaera.hydra.core.activity2.Stereotype;
import com.github.noxchimaera.hydra.utils.ListUtils;
import com.github.noxchimaera.hydra.utils.swing.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * @author Nox
 */
public class StereotypeComponent extends JPanel {

    private List<Stereotype> stereotypes;
    private JComboBox<Stereotype> stereotypeInput;

    public StereotypeComponent(List<Stereotype> stereotypes) {
        super(new BorderLayout(4, 4));
        this.stereotypes = stereotypes;

        initialize();
    }

    private void initialize() {
        setBorder(new EmptyBorder(8, 8, 8, 8));

        DefaultComboBoxModel<Stereotype> model
            = new DefaultComboBoxModel<>(ListUtils.toArray(stereotypes, Stereotype[]::new));
        stereotypeInput = new JComboBox<>(model);

        JPanel top = new JPanel(new BorderLayout(4, 4));
        top.add(new JLabel("Stereotype: "), GUI.borderLayout_Left());
        top.add(stereotypeInput, GUI.borderLayout_Centre());


        add(top, GUI.borderLayout_Top());
    }

}
