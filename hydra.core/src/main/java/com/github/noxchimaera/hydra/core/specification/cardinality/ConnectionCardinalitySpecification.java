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

package com.github.noxchimaera.hydra.core.specification.cardinality;

import com.github.noxchimaera.hydra.core.graph.EdgeDirection;
import com.github.noxchimaera.hydra.core.graph.EdgeFlowDirection;
import com.github.noxchimaera.hydra.core.graph.EdgeType;
import com.github.noxchimaera.hydra.core.graph.Node;
import com.github.noxchimaera.hydra.core.specification.SpecificationItem;
import com.github.noxchimaera.hydra.utils.Dictionary;

/**
 * @author Nox
 */
public abstract class ConnectionCardinalitySpecification extends SpecificationItem {

    protected Dictionary<EdgeFlowDirection, ConnectionCardinality> specs;

    protected ConnectionCardinalitySpecification() {
        specs = new Dictionary<>();
    }

    public <T extends ConnectionCardinalitySpecification>
    T specify(EdgeFlowDirection dir, ConnectionCardinality cardinality) {
        specs.put(dir, cardinality);
        return (T)this;
    }

    public abstract <T>
    boolean check(Node<T> node, EdgeFlowDirection dir);

}
