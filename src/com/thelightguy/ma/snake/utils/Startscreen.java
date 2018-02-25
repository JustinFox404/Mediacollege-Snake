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

import com.thelightguy.ma.snake.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import java.net.URL;
import java.net.URLConnection;

public class Startscreen {
    private JButton changeColorButton;
    private JPanel panel1;
    private JButton startButton;
    private JLabel mediaCollegeLogo1;
    private JPanel colorPanel;
    private int color = 0;

    public JPanel getPanel() {
        return panel1;
    }

    public JLabel getMediaCollegeLogo() {
        return mediaCollegeLogo1;
    }

    public Startscreen() {
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
                Main.color = color;
                colorPanel.setBackground(color);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gameFrame = new GameFrame();
                if(Main.color == null) {
                    Random r = new Random();
                    Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
                    Main.color = color;
                    colorPanel.setBackground(color);
                }
            }
        });
    }
}
