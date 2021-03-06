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

package com.github.noxchimaera.hydra.app.gui.graph;

import com.github.noxchimaera.hydra.app.UmlCellFactory;
import com.github.noxchimaera.hydra.app.gui.editors.ActionUmlNodeEditor;
import com.github.noxchimaera.hydra.app.gui.base.Dialog;
import com.github.noxchimaera.hydra.app.gui.base.DialogEvent;
import com.github.noxchimaera.hydra.app.modules.AppModule;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.UmlNode;
import com.github.noxchimaera.hydra.core.activity2.nodes.ActionUmlNode;
import com.github.noxchimaera.hydra.utils.Contracts;

/**
 * @author Nox
 */
public class UmlGraphController {

    private AppModule app;

    private UmlGraphView view;

    private UmlCellFactory factory;

    public UmlGraphController(UmlGraphView view, UmlCellFactory factory, AppModule app) {
        this.view = view;
        this.factory = factory;
        this.app = app;
    }

    private void updateCell(UmlCell cell) {
        view.getGraph().cellLabelChanged(cell, cell.getValue(), true);
    }

    public void showEditor(UmlCell cell, UmlNode node) {
        // TODO: refactor
        Dialog editor = null;
        if (Contracts.is(ActionUmlNode.class, node)) {
            editor = new ActionUmlNodeEditor(view.getOwner(), (ActionUmlNode)node, app);
            editor.setEventHandler(event -> {
                if (DialogEvent.Status.CANCEL == event.getStatus()) {
                    return;
                }

                ActionUmlNode action = (ActionUmlNode)node;
                ActionUmlNode result = (ActionUmlNode)event.getValue();

                action.value(result.value());
                action.stereotype(result.stereotype());

                updateCell(cell);
            });
        } else {
            return;
        }
        editor.setSize(640, 480);
        editor.setLocationRelativeTo(view.getOwner());
        editor.setVisible(true);
    }

    public UmlGraphView getView() {
        return view;
    }

    public UmlCellFactory getFactory() {
        return factory;
    }

}
