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

package com.github.noxchimaera.hydra.utils.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nox
 */
public class GUI {

    public static
    String borderLayout_Left() {
        return BorderLayout.WEST;
    }

    public static
    String borderLayout_Right() {
        return BorderLayout.EAST;
    }

    public static
    String borderLayout_Top() {
        return BorderLayout.NORTH;
    }

    public static
    String borderLayout_Bottom() {
        return BorderLayout.SOUTH;
    }

    public static
    String borderLayout_Centre() {
        return BorderLayout.CENTER;
    }

    public static
    JFrame frame(JComponent panel, String title) {
        JFrame frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.setSize(1280, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

}
