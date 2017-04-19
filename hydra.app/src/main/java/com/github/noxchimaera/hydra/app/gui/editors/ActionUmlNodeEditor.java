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
import com.github.noxchimaera.hydra.app.gui.editors.components.StereotypeComponent;
import com.github.noxchimaera.hydra.core.activity2.Stereotypes;
import com.github.noxchimaera.hydra.core.activity2.nodes.ActionUmlNode;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author Nox
 */
public class ActionUmlNodeEditor extends Editor<ActionUmlNode> {

    private ActionUmlNode node;

    public ActionUmlNodeEditor(Dialog owner, ActionUmlNode node) {
        super(owner, "Action UML node", true);
        this.node = node;

        initialize();
    }

    private void initialize() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        StereotypeComponent stereotypes = new StereotypeComponent(Stereotypes.getAll(true));

        contentPanel.add(stereotypes);

        RSyntaxTextArea effectInput = new RSyntaxTextArea(20, 60);
        effectInput.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        effectInput.setCodeFoldingEnabled(true);

        RTextScrollPane scroll = new RTextScrollPane(effectInput);
        contentPanel.add(scroll);

        // JPanel cp = new JPanel(new BorderLayout());
        // RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        // textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        // textArea.setCodeFoldingEnabled(true);
        // RTextScrollPane sp = new RTextScrollPane(textArea);
        // cp.add(sp);
        //
        // JFrame frame = new JFrame();
        // frame.setContentPane(cp);
        // frame.pack();
        // frame.setLocationRelativeTo(null);
        //
        // frame.setVisible(true);

    }

    @Override
    protected ActionUmlNode result() {
        return null;
    }

}
