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

package com.github.noxchimaera.hydra.app.modules.diversify.gui;

import com.alee.laf.button.WebButton;
import com.alee.laf.filechooser.WebFileChooser;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.github.noxchimaera.hydra.app.swing.prompt.Notify;
import com.github.noxchimaera.hydra.app.swing.prompt.Warn;
import com.github.noxchimaera.hydra.core.java.JavaSource;
import com.github.noxchimaera.hydra.utils.swing.GUI;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Nox
 */
public class DiversifyResultViewer extends WebDialog {

    private JavaSource source;
    private String formatted;

    public DiversifyResultViewer(Frame owner, JavaSource source) {
        super(owner, "Result viewer", true);
        this.source = source;

        initialize();
    }

    private void initialize() {
        WebPanel root = new WebPanel(new BorderLayout(4, 4));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));

        setLayout(new BorderLayout(4, 4));

        RSyntaxTextArea sourceViewer = new RSyntaxTextArea(20, 60);
        sourceViewer.setText(formatted = source.formatted());
        sourceViewer.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        sourceViewer.setCodeFoldingEnabled(true);
        sourceViewer.setTabsEmulated(true);
        RTextScrollPane scroll = new RTextScrollPane(sourceViewer);

        WebPanel controlPanel = new WebPanel(new FlowLayout(FlowLayout.RIGHT, 4, 4));

        WebButton saveButton = new WebButton("Save");
        saveButton.onMouseClick(e -> save());
        controlPanel.add(saveButton);

        WebButton cancelButton = new WebButton("Cancel");
        cancelButton.onMouseClick(e -> cancel());
        controlPanel.add(cancelButton);

        root.add(scroll, GUI.borderLayout_Centre());
        root.add(controlPanel, GUI.borderLayout_Bottom());

        setContentPane(root);
    }

    public void assemble() {
        Dimension size = new Dimension(800, 600);
        setSize(size);
        setMinimumSize(size);
        setLocationRelativeTo(getOwner());
    }

    private void save() {
        WebFileChooser chooser = new WebFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Java source code (*.java)", "java"));
        // TODO: remove in release
        chooser.setCurrentDirectory("d:/Projects");
        int result = chooser.showSaveDialog(this);
        if (0 != result) {
            return;
        }

        File file = chooser.getSelectedFile();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(formatted);
            writer.close();
        } catch (FileNotFoundException e) {
            Warn.that(this, "File not found. ", "Not found");
            return;
        } catch (IOException e) {
            Warn.that(this,
                "Something went wrong while saving.\nException thrown: " + e.getMessage(),
                "Error");
            return;
        }

        Notify.that(this, "Source was saved. ", "Success");
        setVisible(false);
        dispose();
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

}
