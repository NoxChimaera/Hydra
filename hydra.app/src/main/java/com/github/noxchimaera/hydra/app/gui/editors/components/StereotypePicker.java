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

import com.github.noxchimaera.hydra.app.events.explicit.ActionEventShim;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.DiversifiedStereotype;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.utils.Contracts;
import com.github.noxchimaera.hydra.utils.ListUtils;
import com.github.noxchimaera.hydra.utils.swing.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author Nox
 */
public class StereotypePicker extends JPanel {

    public final ActionEventShim<Stereotype> SelectionChanged;

    private List<Stereotype> stereotypes;
    private JComboBox<Stereotype> stereotypeInput;

    private WeakHashMap<Class, StereotypeComponent> componentCache;
    private StereotypeComponent component;

    public StereotypePicker(List<Stereotype> stereotypes) {
        super(new BorderLayout(4, 4));
        SelectionChanged = new ActionEventShim<>();

        this.stereotypes = stereotypes;
        componentCache = new WeakHashMap<>();
        component = EmptyStereotypeComponent.Shared;
        initialize();
    }

    private void initialize() {
        setBorder(new EmptyBorder(8, 8, 8, 8));

        DefaultComboBoxModel<Stereotype> model
            = new DefaultComboBoxModel<>(ListUtils.toArray(stereotypes, Stereotype[]::new));
        stereotypeInput = new JComboBox<>(model);
        stereotypeInput.addActionListener(this::onItemChanged);

        JPanel top = new JPanel(new BorderLayout(4, 4));
        top.add(new JLabel("Stereotype: "), GUI.borderLayout_Left());
        top.add(stereotypeInput, GUI.borderLayout_Centre());

        add(top, GUI.borderLayout_Top());
    }

    private void onItemChanged(ActionEvent event) {
        Object selected = stereotypeInput.getSelectedItem();
        if (selected == component) {
            // Nothing was changed
            return;
        }
        if (component != null && component != EmptyStereotypeComponent.Shared) {
            // Caching previous component
            componentCache.put(component.getClass(), component);
        }
        if (null == stereotypeInput.getSelectedItem()) {
            remove(component);
            component = EmptyStereotypeComponent.Shared;
        } else {
            StereotypeComponent cached = componentCache.get(selected.getClass());
            if (null == cached) {
                // TODO: Properly implement this feature. Now it only a stub
                if (Contracts.is(DiversifiedStereotype.class, selected)) {
                    cached = new DiversifiedStereotypeComponent();
                } else {
                    throw new UnsupportedOperationException("Not implemented yet");
                }

                remove(component);
                component = cached;
            }
        }
        add(component, GUI.borderLayout_Centre());

        validate();
        repaint();

        SelectionChanged.post(this, (Stereotype)selected);
    }

}
