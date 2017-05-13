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

import com.alee.laf.text.WebTextField;
import com.github.noxchimaera.hydra.app.gui.algorithms.AlgorithmLibraryModel;
import com.github.noxchimaera.hydra.app.gui.algorithms.AlgorithmLibraryView;
import com.github.noxchimaera.hydra.app.gui.base.DialogEvent;
import com.github.noxchimaera.hydra.app.modules.diversify.DiversifyModule;
import com.github.noxchimaera.hydra.app.swing.GridConstraint;
import com.github.noxchimaera.hydra.app.swing.SelectGroup;
import com.github.noxchimaera.hydra.app.swing.prompt.Warn;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.ActionUmlNode;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.DiversifiedStereotype;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.core.modules.DiversifyContext;
import com.github.noxchimaera.hydra.core.modules.diversify.GenVoterConfiguration;
import com.github.noxchimaera.hydra.core.modules.diversify.VersionConfiguration;
import com.github.noxchimaera.hydra.core.syntax.expr.ExprInvalidSyntax;
import com.github.noxchimaera.hydra.core.syntax.expr.ExprLexer;
import com.github.noxchimaera.hydra.core.syntax.expr.ExprParser;
import com.github.noxchimaera.hydra.utils.Contracts;
import com.github.noxchimaera.hydra.utils.Strings;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

import static com.github.noxchimaera.hydra.app.swing.GridConstraint.AnchorAbsolute;
import static com.github.noxchimaera.hydra.app.swing.GridConstraint.Fill;

/**
 * @author Nox
 */
public class DiversifiedStereotypeComponent extends StereotypeComponent {

    private Frame owner;
    private DiversifyModule module;

    private JComboBox<GenVoterConfiguration> votersInput;
    private SelectGroup algorithmInput;
    private WebTextField versionInput;

    private DiversifyContext context;

    public DiversifiedStereotypeComponent(Frame owner, DiversifyModule module) {
        super();
        this.owner = owner;
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
        algorithmInput.onSelectButtonClick(this::onAlgorithmSelectButtonClick);

        versionInput = new WebTextField();
        versionInput.setEditable(false);
        addProperty(root, 2, new JLabel("Versions: "), versionInput);

        add(root);
    }

    private void onAlgorithmSelectButtonClick() {
        AlgorithmLibraryModel model = new AlgorithmLibraryModel(module.configuration());
        AlgorithmLibraryView view = new AlgorithmLibraryView(owner, "Algorithm library", true, model);
        view.setEventHandler(event -> {
            if (DialogEvent.Status.CANCEL == event.getStatus()) {
                return;
            }
            context = event.getValue();
            algorithmInput.setText(context.algorithm().name());
            versionInput.setText(Strings.joined(", ", context.versions(), VersionConfiguration::name));
        });
        view.fill(context);
        view.setLocationRelativeTo(owner);
        view.setVisible(true);
    }

    @Override
    public Class type() {
        return DiversifiedStereotype.class;
    }

    @Override
    public void fill(Stereotype stereotype) {
        if (!Contracts.is(DiversifiedStereotype.class, stereotype)) {
            return;
        }

        DiversifiedStereotype diversify = (DiversifiedStereotype)stereotype;
        context = diversify.context();

        votersInput.setSelectedItem(context.genvoter());
        algorithmInput.setText(context.algorithm().name());
        versionInput.setText(Strings.joined(", ", context.versions(), VersionConfiguration::name));
    }

    @Override
    public Stereotype stereotype() {
        StringBuilder errors = new StringBuilder();

        GenVoterConfiguration voter = (GenVoterConfiguration)votersInput.getSelectedItem();
        if (null == voter) {
            errors.append("A voter algorithm wasn't selected. ").append(System.lineSeparator());
        }

        if (null == context) {
            errors.append("A diversification context wasn't set. ").append(System.lineSeparator());
        }

        if (errors.length() > 0) {
            Warn.that(this, errors.toString(), "Error");
            return null;
        } else {
            return new DiversifiedStereotype(new DiversifyContext(voter, context.algorithm(), context.versions()));
        }
    }

    @Override
    public boolean test(UmlNode node) {
        if (!Contracts.is(ActionUmlNode.class, node)) {
            return false;
        }

        ExprParser p = new ExprParser(new ExprLexer(node.value()));
        try {
            p.parse();
        } catch (ExprInvalidSyntax ex) {
            Warn.that(this, ex.getMessage(), "Syntax error");
            return false;
        }
        return true;
    }

}
