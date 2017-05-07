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

import com.github.noxchimaera.hydra.app.modules.DiversifyModule;
import com.github.noxchimaera.hydra.app.repositories.genvoter.GenVoterDescription;
import com.github.noxchimaera.hydra.app.swing.GridConstraint;
import com.github.noxchimaera.hydra.app.swing.SelectGroup;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.ActionUmlNode;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.DiversifiedStereotype;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.utils.Contracts;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

import static com.github.noxchimaera.hydra.app.swing.GridConstraint.*;

/**
 * @author Nox
 */
public class DiversifiedStereotypeComponent extends StereotypeComponent {

    private DiversifyModule module;

    private JComboBox<GenVoterDescription> votersInput;
    private SelectGroup algorithmInput;
    private SelectGroup versionInput;

    public DiversifiedStereotypeComponent(DiversifyModule module) {
        super();
        this.module = module;

        initialize();
    }

    private void addProperty(Container aContainer, int aRow, JLabel aLabel, Component aCmpt) {
        aContainer.add(aLabel, GridConstraint
            .at(0, aRow).anchor(AnchorAbsolute.WEST).insets(5, 5, 5, 5).build());
        aContainer.add(aCmpt, GridConstraint
            .at(1, aRow).insets(5, 5, 5, 5).weight(1, 0)
            .fill(Fill.HORIZONTAL).width(GridBagConstraints.REMAINDER).build());
    }

    private void initialize() {
        setLayout(new BorderLayout(4, 4));
        setBorder(new TitledBorder("Parameters"));

        JPanel root = new JPanel(new GridBagLayout());

        votersInput = new JComboBox<>(new Vector<>(module.voters().all()));
        addProperty(root, 0, new JLabel("Voter: "), votersInput);

        algorithmInput = new SelectGroup();
        addProperty(root, 1, new JLabel("Algorithm: "), algorithmInput);

        versionInput = new SelectGroup();
        addProperty(root, 2, new JLabel("Versions: "), versionInput);

        add(root);
    }

    @Override
    public Class type() {
        return DiversifiedStereotype.class;
    }

    @Override
    public Stereotype stereotype() {
        return null;
    }

    @Override
    public boolean test(UmlNode node) {
        if (!Contracts.is(ActionUmlNode.class, node)) {
            return false;
        }
        // TODO: is action effect consistent with selected algorithm.
        return false;
    }

}
