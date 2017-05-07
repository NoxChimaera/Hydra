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

package com.github.noxchimaera.hydra.app.swing.prompt;

import com.github.noxchimaera.hydra.utils.functional.Action;
import com.github.noxchimaera.hydra.utils.functional.Proc;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nox
 */
public class Ask {

    public enum Answer {
        Undefined,
        Yes,
        No,
        Cancel,
        Closed
    }

    public static Answer dispell(int magicConstant) {
        switch (magicConstant) {
            case JOptionPane.YES_OPTION: return Answer.Yes;
            case JOptionPane.NO_OPTION: return Answer.No;
            case JOptionPane.CANCEL_OPTION: return Answer.Cancel;
            case JOptionPane.CLOSED_OPTION: return Answer.Closed;
            default: return Answer.Undefined;
        }
    }

    public static int enchant(Answer answer) {
        switch (answer) {
            case Yes: return JOptionPane.YES_OPTION;
            case No: return JOptionPane.NO_OPTION;
            case Cancel: return JOptionPane.CANCEL_OPTION;
            case Closed: return JOptionPane.CLOSED_OPTION;
            default: return -42;
        }
    }

    public static void confirmation(Component parent, String message, String title, Proc onConfirm, Proc onCancel) {
        int res = JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.OK_CANCEL_OPTION);
        if (JOptionPane.OK_OPTION == res) {
            onConfirm.exec();
        } else {
            onCancel.exec();
        }
    }

    public static Answer yesOrNo(Component parent, String message, String title) {
        int res = JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
        return dispell(res);
    }

}
