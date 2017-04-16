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

package com.github.noxchimaera.hydra.app.mx.utils;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nox
 */
public class GraphxEventUtils {

    private GraphxEventUtils() {
        throw new AssertionError("Can not create utils class");
    }

    public static Object[] cells(mxEventObject eventArgs) {
        return (Object[])eventArgs.getProperty("cells");
    }

    public static mxCell[] edges(Object[] rawCells) {
        List<mxCell> edges = new ArrayList<>();
        for (Object raw : rawCells) {
            if (raw instanceof mxCell) {
                if (((mxCell) raw).isEdge()) {
                    edges.add((mxCell)raw);
                }
            }
        }
        return edges.toArray(new mxCell[edges.size()]);
    }

}
