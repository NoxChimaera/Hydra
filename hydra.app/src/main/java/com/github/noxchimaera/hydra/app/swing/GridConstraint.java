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

import java.awt.*;

/**
 * @author Nox
 */
public class GridConstraint {

    /**
     * «Absolute» anchor.
     * Enum-wrapper.
     */
    public enum AnchorAbsolute {
        /**
         * Центр.
         */
        CENTER(GridBagConstraints.CENTER),
        /**
         * Север.
         */
        NORTH(GridBagConstraints.NORTH),
        /**
         * Северо-восток.
         */
        NORTHEAST(GridBagConstraints.NORTHEAST),
        /**
         * Восток.
         */
        EAST(GridBagConstraints.EAST),
        /**
         * Юго-восток.
         */
        SOUTHEAST(GridBagConstraints.SOUTHEAST),
        /**
         * Юг.
         */
        SOUTH(GridBagConstraints.SOUTH),
        /**
         * Юго-запад.
         */
        SOUTHWEST(GridBagConstraints.SOUTHWEST),
        /**
         * Запад.
         */
        WEST(GridBagConstraints.WEST),
        /**
         * Северо-запад.
         */
        NORTHWEST(GridBagConstraints.NORTHWEST);

        private final int awtConstant;

        private AnchorAbsolute(int awtConstant) {
            this.awtConstant = awtConstant;
        }

        /**
         * Returns value of wrapped AWT constant.
         *
         * @return value of AWT constant
         */
        public int asAwtConstant() {
            return awtConstant;
        }
    }

    /**
     * Fill mode.
     * Enum-wrapper.
     */
    public enum Fill {
        /**
         * Node.
         */
        NONE(GridBagConstraints.NONE),
        /**
         * Horizontal fill.
         */
        HORIZONTAL(GridBagConstraints.HORIZONTAL),
        /**
         * Vertical fill.
         */
        VERTICAL(GridBagConstraints.VERTICAL),
        /**
         * Horizontal and vertical fill both.
         */
        BOTH(GridBagConstraints.BOTH);

        private final int awtConstant;

        private Fill(int awtConstant) {
            this.awtConstant = awtConstant;
        }

        /**
         * Returns value of wrapped AWT constant.
         *
         * @return value of AWT constant
         */
        public int asAwtConstant() {
            return awtConstant;
        }
    }

    /**
     * Creates cell at specified position.
     *
     * @param x column
     * @param y row
     *
     * @return self
     */
    public static GridConstraint at(int x, int y) {
        return new GridConstraint(x, y);
    }

    private int gridX = 0;
    private int gridY = 0;
    private int gridWidth = 1;
    private int gridHeight = 1;
    private double weightX = 0;
    private double weightY = 0;
    private int anchor = GridBagConstraints.CENTER;
    private int fill = GridBagConstraints.NONE;
    private Insets insets = new Insets(0, 0, 0, 0);
    private int padX = 0;
    private int padY = 0;

    /**
     * Create cell at specified position.
     *
     * @param x column
     * @param y row
     */
    public GridConstraint(int x, int y) {
        gridX = x;
        gridY = y;
    }

    /**
     * Sets cell width.
     *
     * @param width cell width
     *
     * @return self
     */
    public GridConstraint width(int width) {
        gridWidth = width;
        return this;
    }

    /**
     * Sets cell height.
     *
     * @param height cell height
     *
     * @return self
     */
    public GridConstraint height(int height) {
        gridHeight = height;
        return this;
    }

    /**
     * Sets cell size.
     *
     * @param width  cell width
     * @param height cell height
     *
     * @return self
     */
    public GridConstraint size(int width, int height) {
        gridWidth = width;
        gridHeight = height;
        return this;
    }

    public GridConstraint weightHorizontal(double weightX) {
        this.weightX = weightX;
        return this;
    }

    public GridConstraint weightVertical(double weightY) {
        this.weightY = weightY;
        return this;
    }

    public GridConstraint weight(double horizontal, double vertical) {
        weightX = horizontal;
        weightY = vertical;
        return this;
    }

    public GridConstraint anchor(AnchorAbsolute anchor) {
        this.anchor = anchor.asAwtConstant();
        return this;
    }

    public GridConstraint fill(Fill fill) {
        this.fill = fill.asAwtConstant();
        return this;
    }

    public GridConstraint insets(int top, int left, int bottom, int right) {
        insets = new Insets(top, left, bottom, right);
        return this;
    }

    public GridConstraint internalPadding(int horizontal, int vertical) {
        padX = horizontal;
        padY = vertical;
        return this;
    }

    public GridBagConstraints build() {
        return new GridBagConstraints(
            gridX, gridY, gridWidth, gridHeight, weightX, weightY, anchor, fill, insets, padX, padY);
    }

}

