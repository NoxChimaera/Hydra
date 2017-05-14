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

package com.github.noxchimaera.hydra.app.gui.algorithms;

import com.alee.extended.list.CheckBoxCellData;
import com.alee.extended.list.CheckBoxListModel;
import com.alee.extended.list.WebCheckBoxList;
import com.alee.laf.list.WebList;
import com.alee.laf.list.WebListModel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.text.WebTextArea;
import com.github.noxchimaera.hydra.app.gui.base.Dialog;
import com.github.noxchimaera.hydra.app.swing.prompt.Warn;
import com.github.noxchimaera.hydra.core.modules.DiversifyContext;
import com.github.noxchimaera.hydra.core.modules.diversify.AlgorithmConfiguration;
import com.github.noxchimaera.hydra.core.modules.diversify.VersionConfiguration;
import com.github.noxchimaera.hydra.utils.Strings;
import com.github.noxchimaera.hydra.utils.swing.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Nox
 */
public class AlgorithmLibraryView extends Dialog<DiversifyContext> {

    private AlgorithmLibraryModel model;

    private WebList algorithms;
    private WebCheckBoxList versions;
    WebTextArea info;

    private Map<AlgorithmConfiguration, CheckBoxListModel> versionsModels;

    public AlgorithmLibraryView(Frame owner, String title, boolean modal, AlgorithmLibraryModel model) {
        super(owner, title, modal);
        versionsModels = new HashMap<>();
        this.model = model;
        initialize();
    }

    private void initialize() {
        applyButton.setVisible(false);
        // contentPanel

        WebSplitPane split = new WebSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        algorithms = new WebList(Arrays.asList("Alg 1", "Alg 2"));
        WebListModel<AlgorithmConfiguration> algorithmsModel = new WebListModel<>(model.algorithms());
        algorithms.setModel(algorithmsModel);
        WebScrollPane algScroll = new WebScrollPane(algorithms);

        versions = new WebCheckBoxList(new CheckBoxListModel());
        WebScrollPane verScroll = new WebScrollPane(versions);

        algorithms.addListSelectionListener(e -> onAlgorithmSelectionChanged());

        split.add(algScroll, JSplitPane.LEFT);
        split.add(verScroll, JSplitPane.RIGHT);
        split.setResizeWeight(0.3);

        info = new WebTextArea();

        WebSplitPane whole = new WebSplitPane(JSplitPane.VERTICAL_SPLIT);
        whole.add(split, JSplitPane.TOP);
        whole.add(new WebScrollPane(info), JSplitPane.BOTTOM);
        whole.setResizeWeight(0.5);

        contentPanel.add(whole, GUI.borderLayout_Centre());
        split.setBackground(contentPanel.getBackground());

        setMinimumSize(new Dimension(600, 480));
    }

    public void fill(DiversifyContext config) {
        if (null == config) {
            return;
        }

        algorithms.setSelectedValue(config.algorithm());
        onAlgorithmSelectionChanged();

        List<VersionConfiguration> vs = config.versions();
        for (CheckBoxCellData cellData : this.versions.getCheckBoxListModel().getElements()) {
            if (vs.contains(cellData.getUserObject())) {
                cellData.setSelected(true);
            }
        }
    }

    private void onAlgorithmSelectionChanged() {
        AlgorithmConfiguration algorithm = ((AlgorithmConfiguration)algorithms.getSelectedValue());
        if (null == algorithm) {
            versions.setModel(new CheckBoxListModel());
            return;
        }
        CheckBoxListModel cached = versionsModels.get(algorithm);
        if (null != cached) {
            versions.setModel(cached);
        } else {
            CheckBoxListModel model = new CheckBoxListModel();
            for (VersionConfiguration version : algorithm.versions()) {
                model.addCheckBoxElement(version);
            }
            versionsModels.put(algorithm, model);
        }

        info.setText(Strings.$(
            algorithm.name(), " = ", algorithm.signature()
        ));
    }

    @Override
    protected void ok(DiversifyContext result) {
        if (versions.getCheckedValues().size() < 1) {
            Warn.that(this,
                "Can not create such diversification context. At least one version must be chosen. ",
                "Can not create context");
            return;
        }
        super.ok(result);
    }

    @Override
    protected DiversifyContext result() {
        AlgorithmConfiguration algorithm = (AlgorithmConfiguration)algorithms.getSelectedValue();
        List<VersionConfiguration> vs = versions.getCheckedValues()
            .stream()
            .map(v -> (VersionConfiguration)v)
            .collect(Collectors.toList());

        return new DiversifyContext(null, algorithm, vs);
    }

}
