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

package com.github.noxchimaera.hydra.app.swing;

import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;

/**
 * @author Nox
 */
public class ShadowBorder implements Border, Serializable {

    private Insets insets;
    public static ShadowBorder sharedInstance = new ShadowBorder();

    private ShadowBorder() {
        insets = new Insets(0, 0, 2, 2);
    }

    @Override
    public Insets getBorderInsets( Component aCmpt ) {
        return insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    private Color average( Color aC1, Color aC2 ) {
        int r = aC1.getRed()    + (aC2.getRed()   - aC1.getRed())   / 2;
        int g = aC1.getGreen()  + (aC2.getGreen() - aC1.getGreen()) / 2;
        int b = aC1.getBlue()   + (aC2.getBlue()  - aC1.getBlue())  / 2;
        return new Color(r, g, b);
    }

    @Override
    public void paintBorder( Component aCmpt, Graphics aGr, int aX, int aY, int aW, int aH ) {
        Color bg = aCmpt.getBackground();
        if (aCmpt.getParent() != null) {
            bg = aCmpt.getParent().getBackground();
        }
        if (bg != null) {
            Color mid = bg.darker();
            Color edge = average(mid, bg);

            aGr.setColor(bg);
            aGr.drawLine(0, aH - 2, aW, aH - 2);
            aGr.drawLine(0, aH - 1, aW, aH - 1);
            aGr.drawLine(aW - 2, 0, aW - 2, aH);
            aGr.drawLine(aW - 1, 0, aW - 1, aH);

            // draw the drop-shadow
            aGr.setColor(mid);
            aGr.drawLine(1, aH - 2, aW - 2, aH - 2);
            aGr.drawLine(aW - 2, 1, aW - 2, aH - 2);

            aGr.setColor(edge);
            aGr.drawLine(2, aH - 1, aW - 2, aH - 1);
            aGr.drawLine(aW - 1, 2, aW - 1, aH - 2);
        }
    }

    public static ShadowBorder get() {
        return sharedInstance;
    }

}
