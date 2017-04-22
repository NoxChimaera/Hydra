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

package com.github.noxchimaera.hydra.app.gui.editors.base;

import com.github.noxchimaera.hydra.utils.swing.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Nox
 */
public abstract class Editor<T> extends JDialog {

    protected EditorEventHandler<T> eventHandler;

    protected JPanel contentPanel;

    protected JPanel controlPanel;
    protected JButton okButton;
    protected JButton applyButton;
    protected JButton cancelButton;

    public Editor(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        preinitialize();
    }

    private void preinitialize() {
        JPanel root = new JPanel(new BorderLayout(4, 4));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));

        contentPanel = new JPanel();
        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 4));

        okButton = new JButton("OK");
        okButton.addActionListener(e -> ok(result()));
        controlPanel.add(okButton);

        applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> apply(result()));
        controlPanel.add(applyButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancel());
        controlPanel.add(cancelButton);

        root.add(contentPanel, GUI.borderLayout_Centre());
        root.add(controlPanel, GUI.borderLayout_Bottom());

        setContentPane(root);
    }

    protected abstract T result();

    protected void ok(T result) {
        if (eventHandler != null) {
            eventHandler.handleEvent(EditorEvent.ok(this, result));
        }
        setVisible(false);
        dispose();
    }

    protected void apply(T result) {
        if (eventHandler != null) {
            eventHandler.handleEvent(EditorEvent.apply(this, result));
        }
    }

    protected void cancel() {
        if (eventHandler != null) {
            eventHandler.handleEvent(EditorEvent.cancel(this));
        }
        setVisible(false);
        dispose();
    }

    public void setEventHandler(EditorEventHandler<T> handler) {
        eventHandler = handler;
    }

}
