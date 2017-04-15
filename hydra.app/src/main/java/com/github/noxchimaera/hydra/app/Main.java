/*
 * Copyright 2016 Max Balushkin.
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

package com.github.noxchimaera.hydra.app;

import com.github.noxchimaera.hydra.app.mx.UmlGraph;
import com.github.noxchimaera.hydra.app.mx.UmlGraphComponent;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.github.noxchimaera.hydra.core.activity2.UmlFactory;
import com.github.noxchimaera.hydra.utils.swing.GUI;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Max Balushkin
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        initialize();
        UmlFactory uml = new UmlFactory();

        UmlGraph graph = new UmlGraph(uml);
        UmlGraphComponent cmpt = new UmlGraphComponent(graph);
        GraphView view = new GraphView(cmpt);
        GUI.frame(view, "Hydra Modelling System").setVisible(true);

        UmlCellFactory fct = graph.getCellFactory();
        UmlCell cell = fct.init(uml.init(), 50, 50);
        graph.addCell(cell);

        UmlCell c2 = fct.fin(uml.fin(), 50, 150);
        graph.addCell(c2);

        uml.flow(cell.getUserObject(), c2.getUserObject());
    }

    private static void initialize() {

    }


//    private static void unmark(ObsoleteNode node) {
//        node.unmark(Index.class);
//        node.unmark(Tarjan.Root.class);
//    }
//
//    public static void main2(String[] args) {
//        ObsoleteGraphFactory<String> fct = new ObsoleteGraphFactory<>();
//
//        MyObsoleteNode init = new MyObsoleteNode(fct, "init");
//        MyObsoleteNode loop1Header = new MyObsoleteNode(fct, "loop 1");
//        MyObsoleteNode loop2Header = new MyObsoleteNode(fct, "loop 2");
//        MyObsoleteNode loop2End = new MyObsoleteNode(fct, "loop 2 end");
//        MyObsoleteNode loop1Pre = new MyObsoleteNode(fct, "loop 1 pre end");
//        MyObsoleteNode loop1End = new MyObsoleteNode(fct, "loop 1 end");
//        MyObsoleteNode end = new MyObsoleteNode(fct, "end");
//
//        init.connect(loop1Header);
//        loop1Header.connect(loop2Header);
//        loop2Header.connect(loop2End);
//        loop2End.connect(loop2Header);
//        loop2End.connect(loop1Pre);
//        loop1Pre.connect(loop1End);
//        loop1End.connect(loop1Header);
//        loop1End.connect(end);
//
//        Tarjan t = new Tarjan();
//        t.accept(init);
//
//        Tarjan t2 = new Tarjan();
//        unmark(init);
//        unmark(loop1Header);
//        unmark(loop2Header);
//        unmark(loop2End);
//        unmark(loop1Pre);
//        unmark(loop1End);
//        unmark(end);
//
//        t2.addIgnoringEdges(loop1End.getEdges());
//        t2.accept(loop1Header);
//
//        for (Object o : t2.getComponents()) {
//            int f = 3;
//        }
//
//
//
//
////        UmlNodeFactory fct = new UmlNodeFactory(-1, -1);
//        // signum
////        UmlInitialNode init = fct.begin();
////        UmlDecisionNode ifLesser = fct.decision();
////        UmlMergeNode fullMerge = fct.merge();
////        UmlDecisionNode ifGreater = fct.decision();
////        UmlMergeNode subMerge = fct.merge();
////
////        fct.flow(init, ifLesser);
////        fct.flow(ifLesser, UmlDecisionNode::setNextLeft,
////            "n < 0", Arrays.asList(fct.act("n = -1")),
////            fullMerge, UmlMergeNode::setPrevLeft);
////        fct.link(ifLesser, ifGreater, "n >= 0", UmlDecisionNode::setNextRight);
////        fct.flow(ifGreater,
////            "n == 0", Arrays.asList(fct.act("n = 0")),
////            "n > 0", Arrays.asList(fct.act("n = 1")),
////            subMerge);
////        fct.link(subMerge, fullMerge, UmlMergeNode::setPrevRight);
////        fct.flow(fullMerge, fct.end());
//
//
//
////        UmlInitialNode umlInit = fct.flowl(
////            fct.begin(),
////            Arrays.asList(
////                fct.act("print `Hello`")
////            ),
////            fct.end()
////        );
//
//
////        HyAction init = new HyAction("arr = [0, 1, 5, 3, -1], isSorted = false");
////        HyLoop loop = new HyLoop(
////            new HyAction("!isSorted"),
////            new HySequence()
////                .add(new HyAction("isSorted = false"))
////                .add(new HyLoop(
////                    new HyAction("i < n - 1"),
////                    new HyConditional(
////                        new HyAction("arr[i] > arr[i + 1]"),
////                        new HySequence()
////                            .add(new HyAction("arr[i], arr[i + 1] = arr[i + 1], arr[i]"))
////                            .add(new HyAction("isSorted = false")),
////                        new HyEmpty())))
////                .add(new HyAction("++i"))
////        );
////        HySequence program = new HySequence().add(init).add(loop);
////        program.accept(new HyPrinter());
//
//    }

}
