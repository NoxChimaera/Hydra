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

package com.github.noxchimaera.hydra.core.activity2.specification.cardinality;

import com.github.noxchimaera.hydra.core.activity2.UmlEdgeTypes;
import com.github.noxchimaera.hydra.core.activity2.edges.ControlflowUmlEdge;
import com.github.noxchimaera.hydra.core.graph.*;
import com.github.noxchimaera.hydra.core.specification.cardinality.ConnectionCardinality;
import com.github.noxchimaera.hydra.core.specification.cardinality.ConnectionCardinalitySpecification;

import java.util.Optional;

/**
 * @author Nox
 */
public class ControlflowUmlCardinalitySpecification extends ConnectionCardinalitySpecification {

    private static final EdgeType edgeType = UmlEdgeTypes.Controlflow;

    public ControlflowUmlCardinalitySpecification() {
        super();
    }

    @Override
    public <T>
    boolean check(Node<T> node, EdgeFlowDirection dir) {
        Optional<ConnectionCardinality> cardinality_opt = specs.get(dir);
        if (!cardinality_opt.isPresent()) {
            return false;
        }
        ConnectionCardinality cardinality = cardinality_opt.get();
        long count = node.getEdges().stream()
            .filter(edge -> edge.getType() == edgeType)
            .filter((Edge edge) -> EdgeFlowDirection.get(node, edge) == dir)
            // .filter((Edge edge) -> edge.getType() == edgeType)
            .count();
        return cardinality.test(count);


        // return specs.get(dir).map(cardinality
        //     -> cardinality.test(
        //     node.getEdges().stream()
        //         .filter(edge -> EdgeFlowDirection.get(node, edge) == dir)
        //         .filter(edge -> edge.getType() == edgeType)
        //         .count()))
        //     .orElse(false);
    }

}
