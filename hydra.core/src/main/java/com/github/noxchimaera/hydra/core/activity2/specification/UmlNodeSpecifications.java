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

package com.github.noxchimaera.hydra.core.activity2.specification;

import com.github.noxchimaera.hydra.core.activity2.specification.cardinality.ControlflowUmlCardinalitySpecification;
import com.github.noxchimaera.hydra.core.graph.EdgeFlowDirection;
import com.github.noxchimaera.hydra.core.specification.cardinality.ConnectionCardinality;

/**
 * @author Nox
 */
public class UmlNodeSpecifications {

    public static final UmlNodeSpecification Fake;

    public static final UmlNodeSpecification Init;
    public static final UmlNodeSpecification Fin;

    public static final UmlNodeSpecification Action;

    public static final UmlNodeSpecification Conditional;
    public static final UmlNodeSpecification Loop;

    static {
        ControlflowUmlCardinalitySpecification fake_cf = new ControlflowUmlCardinalitySpecification()
            .specify(EdgeFlowDirection.Input, ConnectionCardinality.none())
            .specify(EdgeFlowDirection.Output, ConnectionCardinality.none());
        Fake = new UmlNodeSpecification(fake_cf);

        ControlflowUmlCardinalitySpecification init_cf = new ControlflowUmlCardinalitySpecification()
            .specify(EdgeFlowDirection.Input, ConnectionCardinality.none())
            .specify(EdgeFlowDirection.Output, ConnectionCardinality.bounded(1));
        Init = new UmlNodeSpecification(init_cf);

        ControlflowUmlCardinalitySpecification fin_cf = new ControlflowUmlCardinalitySpecification()
            .specify(EdgeFlowDirection.Input, ConnectionCardinality.bounded(1))
            .specify(EdgeFlowDirection.Output, ConnectionCardinality.none());
        Fin = new UmlNodeSpecification(fin_cf);

        ControlflowUmlCardinalitySpecification action_cf = new ControlflowUmlCardinalitySpecification()
            .specify(EdgeFlowDirection.Input, ConnectionCardinality.bounded(1))
            .specify(EdgeFlowDirection.Output, ConnectionCardinality.bounded(1));
        Action = new UmlNodeSpecification(action_cf);

        ControlflowUmlCardinalitySpecification conditional_cf = new ControlflowUmlCardinalitySpecification()
            .specify(EdgeFlowDirection.Input, ConnectionCardinality.bounded(1))
            // Condition, If-Branch, Else-Branch, Next
            .specify(EdgeFlowDirection.Output, ConnectionCardinality.bounded(4));
        Conditional = new UmlNodeSpecification(conditional_cf);

        ControlflowUmlCardinalitySpecification loop_cf = new ControlflowUmlCardinalitySpecification()
            .specify(EdgeFlowDirection.Input, ConnectionCardinality.bounded(1))
            // Setup, Test, Step, Body, Next
            .specify(EdgeFlowDirection.Output, ConnectionCardinality.bounded(5));
        Loop = new UmlNodeSpecification(loop_cf);
    }

    private UmlNodeSpecifications() {
        throw new AssertionError("Can not create");
    }

}
