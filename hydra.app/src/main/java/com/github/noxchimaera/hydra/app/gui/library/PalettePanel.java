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

package com.github.noxchimaera.hydra.app.gui.library;

import com.github.noxchimaera.hydra.app.swing.ShadowBorder;
import com.github.noxchimaera.hydra.app.uml.UmlCell;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxEventSource.mxIEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Nox
 */
public class PalettePanel extends JPanel {

    private JLabel selectedEntry;
    private mxEventSource eventSource;

    public PalettePanel() {
        eventSource = new mxEventSource(this);
        initialize();
    }

    private void initialize() {
        setBackground(new Color(149, 230, 190));
        setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clearSelection();
            }
        });
        setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
                return true;
            }
        });
    }

    private void clearSelection() {
        setSelectionEntry(null, null);
    }

    public void setSelectionEntry(JLabel entry, mxGraphTransferable transferable) {
        JLabel prev = selectedEntry;
        selectedEntry = entry;
        if (prev != null) {
            prev.setBorder(null);
            prev.setOpaque(false);
        }
        if (selectedEntry != null) {
            selectedEntry.setBorder(ShadowBorder.get());
            selectedEntry.setOpaque(true);
        }

        eventSource.fireEvent(new mxEventObject(
            mxEvent.SELECT,
            "entry", selectedEntry,
            "transferable", transferable,
            "previous", prev));
    }


    public static int EntrySize = 50;
    public static int Padding = 5;

    public void setPreferredWidth(int width) {
        int cols = Math.max(1, width / (EntrySize + Padding));
        setPreferredSize(new Dimension(
            width,
            ((getComponentCount() * (EntrySize + Padding)) / cols) + 30
        ));
        revalidate();
    }

    public void addTemplate(String title, ImageIcon icon, mxCell cell) {
        mxRectangle bounds = (mxGeometry)cell.getGeometry().clone();
        mxGraphTransferable t = new mxGraphTransferable(new Object[] { cell }, bounds);
        if (icon != null) {
            // Resize icon if larger than 32x32
            if (icon.getIconWidth() > 32 || icon.getIconHeight() > 32) {
                icon = new ImageIcon(icon.getImage().getScaledInstance(32, 32, 0));
            }
        }

        JLabel entry = new JLabel(icon);
        entry.setPreferredSize(new Dimension(EntrySize, EntrySize));
        entry.setBackground(getBackground().brighter());
        entry.setFont(new Font(entry.getFont().getFamily(), Font.PLAIN, 10));
        entry.setVerticalTextPosition(JLabel.BOTTOM);
        entry.setHorizontalAlignment(JLabel.CENTER);
        entry.setIconTextGap(0);
        entry.setText(title);
        entry.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setSelectionEntry(entry, t);
            }
        });

        // Setup drag-and-drop
        DragGestureListener listener = dge
            -> dge.startDrag(null, mxSwingConstants.EMPTY_IMAGE, new Point(), t, null);
        DragSource dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(entry, DnDConstants.ACTION_COPY, listener);
        add(entry);
    }

    public void addTemplate(String title, ImageIcon icon, String style, int width, int height, Object val) {
        mxCell c = new mxCell(val, new mxGeometry(0, 0, width, height), style);
        c.setVertex(true);
        addTemplate(title, icon, c);
    }

    public void addListener(String eventName, mxEventSource.mxIEventListener listener) {
        eventSource.addListener(eventName, listener);
    }

    public boolean isEventsEnabled() {
        return eventSource.isEventsEnabled();
    }

    public void removeListener(mxIEventListener listener) {
        eventSource.removeListener(listener);
    }

    public void setEventsEnabled(boolean enabled) {
        eventSource.setEventsEnabled(enabled);
    }

}
