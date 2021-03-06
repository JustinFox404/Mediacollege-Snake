/*
 *
 *  Copyright (c) 2018, Foxiko and/or its affiliates. ALL RIGHTS RESERVED.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *    - Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *
 *    - Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *
 *     - Neither the name of Foxiko or the names of its
 *      contributors may be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 *  IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 *
 */

package com.thelightguy.ma.snake.utils;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class IsKeyPressed {

    private static boolean
            dPressed = false, sPressed = false, aPressed = false, wPressed = false,
            wPressedJ = false, aPressedJ = false, sPressedJ = false, dPressedJ = true;

    public static boolean isWPressed() {
        synchronized (IsKeyPressed.class) {
            return wPressed;
        }
    }
    public static boolean isAPressed() {
        synchronized (IsKeyPressed.class) {
            return aPressed;
        }
    }
    public static boolean isSPressed() {
        synchronized (IsKeyPressed.class) {
            return sPressed;
        }
    }
    public static boolean isDPressed() {
        synchronized (IsKeyPressed.class) {
            return dPressed;
        }
    }

    public static void setJFalse() {
        wPressedJ = false;
        aPressedJ = false;
        sPressedJ = false;
        dPressedJ = false;
    }

    public static void register() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_W && !sPressedJ) {
                                wPressed = true;
                                setJFalse();
                                wPressedJ = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_A && !dPressedJ) {
                                aPressed = true;
                                setJFalse();
                                aPressedJ = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_S && !wPressedJ) {
                                sPressed = true;
                                setJFalse();
                                sPressedJ = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_D && !aPressedJ) {
                                dPressed = true;
                                setJFalse();
                                dPressedJ = true;
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_W) wPressed = false;
                            if (ke.getKeyCode() == KeyEvent.VK_A) aPressed = false;
                            if (ke.getKeyCode() == KeyEvent.VK_S) sPressed = false;
                            if (ke.getKeyCode() == KeyEvent.VK_D) dPressed = false;
                            break;
                    }
                    return false;
                }
            }
        });
    }
}