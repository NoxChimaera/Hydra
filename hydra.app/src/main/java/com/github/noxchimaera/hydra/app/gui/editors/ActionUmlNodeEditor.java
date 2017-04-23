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

import com.github.noxchimaera.hydra.app.gui.editors.base.Editor;
import com.github.noxchimaera.hydra.app.gui.editors.components.StereotypePicker;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotype;
import com.github.noxchimaera.hydra.core.activity2.stereotypes.Stereotypes;
import com.github.noxchimaera.hydra.core.activity2.nodes.ActionUmlNode;
import com.github.noxchimaera.hydra.utils.swing.GUI;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.stream.Collectors;

/**
 * @author Nox
 */
public class ActionUmlNodeEditor extends Editor<ActionUmlNode> {

    private ActionUmlNode node;

    private RSyntaxTextArea effectInput;

    private JLabel statusIcon;
    private JLabel languageTitle;

    public ActionUmlNodeEditor(Frame owner, ActionUmlNode node) {
        super(owner, "Action UML node", true);
        if (node == null) {
            this.node = new ActionUmlNode(-1, "");
            applyButton.setEnabled(false);
        } else {
            this.node = node;
        }

        initialize();
        fill(node);
    }

    private void fill(ActionUmlNode source) {
        effectInput.setText(source.getValue());
    }

    private void initialize() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        StereotypePicker stereotypes = new StereotypePicker(
            Stereotypes.getAll(true).stream()
                .filter(stereotype -> stereotype == null || stereotype.isAcceptable(node))
                .collect(Collectors.toList()));
        stereotypes.SelectionChanged.register(this::OnStereotypeChanged);

        contentPanel.add(stereotypes);

        JPanel effectPanel = new JPanel(new BorderLayout());
        effectPanel.setBorder(new CompoundBorder(
            new TitledBorder("Effect"),
            new EmptyBorder(8, 8, 8, 8)));

        effectInput = new RSyntaxTextArea(20, 60);
        effectInput.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        effectInput.setCodeFoldingEnabled(true);
        effectInput.setTabsEmulated(true);

        RTextScrollPane scroll = new RTextScrollPane(effectInput);
        effectPanel.add(scroll, GUI.borderLayout_Centre());

        JPanel langPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 4));
        langPanel.setBorder(new EmptyBorder(4, 4, 4, 4));

        statusIcon = new JLabel(
            IconFontSwing.buildIcon(FontAwesome.CHECK_CIRCLE, 16, Color.GREEN.darker()));
        languageTitle = new JLabel("Java");

        // langPanel.add(statusIcon);
        // langPanel.add(languageTitle);

        effectPanel.add(langPanel, GUI.borderLayout_Bottom());

        contentPanel.add(effectPanel);
    }

    private void OnStereotypeChanged(Object sender, Stereotype stereotype) {
        validate();
        repaint();
    }

    @Override
    protected ActionUmlNode result() {
        ActionUmlNode result = new ActionUmlNode(-1, effectInput.getText());
        return result;
    }

}
