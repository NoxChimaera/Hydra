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

import com.alee.extended.layout.VerticalFlowLayout;
import com.github.noxchimaera.hydra.app.events.explicit.ActionEventShim;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
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

    private JPanel placeholder;

    private WeakHashMap<Class, StereotypeComponent> componentCache;
    private StereotypeComponent component;

    private StereotypeComponentFactory factory;

    public StereotypePicker(List<Stereotype> stereotypes, StereotypeComponentFactory factory) {
        super(new BorderLayout(4, 4));
        SelectionChanged = new ActionEventShim<>();

        this.stereotypes = stereotypes;
        componentCache = new WeakHashMap<>();
        component = EmptyStereotypeComponent.Shared;
        this.factory = factory;
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

        // placeholder = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        placeholder = new JPanel(new BorderLayout(4, 4));
        placeholder.setBackground(Color.CYAN);

        add(placeholder, GUI.borderLayout_Centre());
    }

    private void onItemChanged(ActionEvent event) {
        Stereotype selected = (Stereotype)stereotypeInput.getSelectedItem();
        if (component.type().isInstance(selected)) {
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
                cached = factory.get(selected.getClass());
                if (null == cached) {
                    throw new UnsupportedOperationException("There is no such stereotype editor");
                }
                remove(component);
                component = cached;
            }
        }
        placeholder.removeAll();
        placeholder.add(component, GUI.borderLayout_Centre());

        placeholder.revalidate();
        placeholder.repaint();
        revalidate();
        repaint();

        SelectionChanged.post(this, selected);
    }

    public boolean isSelectedStereotypeAppliedTo(UmlNode node) {
        return component.test(node);
    }

    public Stereotype getSelected() {
        return component.stereotype();
    }

}
