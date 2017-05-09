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

package com.github.noxchimaera.zmok;

import akka.dispatch.Futures;
import akka.japi.Option;
import com.github.noxchimaera.zmok.voters.MajorityVoter;
import org.junit.Test;
import scala.concurrent.Future;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Nox
 */
public class GenVoterTest {

    @Test
    public void vote() throws Exception {
        GenVersion<Integer> max0 = new GenVersion<Integer>() {
            @Override
            public Future<Integer> getHeuristicAsync() {
                return Futures.successful(4);
            }

            @Override
            public Option<Integer> getHeuristic() {
                return Option.option(4);
            }
        };

        GenVersion<Integer> max1 = new GenVersion<Integer>() {
            @Override
            public Future<Integer> getHeuristicAsync() {
                return Futures.successful(4);
            }

            @Override
            public Option<Integer> getHeuristic() {
                return Option.option(4);
            }
        };

        GenVersion<Integer> max2 = new GenVersion<Integer>() {
            @Override
            public Future<Integer> getHeuristicAsync() {
                return Futures.successful(5);
            }

            @Override
            public Option<Integer> getHeuristic() {
                return Option.option(5);
            }
        };

        GenVoter<Integer> voter = new MajorityVoter<>();
        Integer res = voter.vote(Arrays.asList(max0, max1, max2)).get();
        assertEquals(4, res.intValue());
    }

}