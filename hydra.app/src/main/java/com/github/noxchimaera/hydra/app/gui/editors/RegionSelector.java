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

package com.github.noxchimaera.hydra.app.gui.editors;

import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.github.noxchimaera.hydra.app.gui.base.Dialog;
import com.github.noxchimaera.hydra.utils.swing.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Nox
 */
public class RegionSelector extends Dialog<String> {

    private List<String> regions;

    private JComboBox<String> comboBox;

    public RegionSelector(Frame owner, String title, List<String> regions) {
        super(owner, title, true);
        this.regions = regions;
        initialize();
    }

    private void initialize() {
        setApplyVisible(false);

        WebPanel root = new WebPanel(new BorderLayout(4, 4));
        root.add(new WebLabel("Region: "), GUI.borderLayout_Left());

        comboBox = new WebComboBox(regions);
        root.add(comboBox, GUI.borderLayout_Centre());

        if (regions.size() == 0) {
            okButton.setEnabled(false);
        }

        contentPanel.add(root);
    }

    @Override
    protected String result() {
        return comboBox.getSelectedItem().toString();
    }

}
