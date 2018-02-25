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

package com.thelightguy.ma.snake;

import com.thelightguy.ma.snake.utils.GameFrame;
import com.thelightguy.ma.snake.utils.ImagePanel;
import com.thelightguy.ma.snake.utils.IsKeyPressed;
import com.thelightguy.ma.snake.utils.Startscreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {

    public static GameFrame gameFrame;
    public static Color color;
    public static int speed = 0; // 0 - 100, 0 = minimum

    public static void main(String[] args) {
        IsKeyPressed.register();
        //gameFrame = new GameFrame();\
        Startscreen startscreen = new Startscreen();
        JFrame frame = new JFrame("MediaCollege Snake");
        Dimension d = new Dimension(500, 500);
        frame.setSize(d);
        frame.setMaximumSize(d);
        frame.setMinimumSize(d);
        frame.setContentPane(startscreen.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        BufferedImage bimage= null;
        ImageIcon icon = null;
        try {
            bimage = ImageIO.read(new File("lib/ma.png"));
            icon = new ImageIcon(bimage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startscreen.getMediaCollegeLogo().setIcon(icon);
        frame.setIconImage(icon.getImage());
        //
        frame.pack();
        frame.setVisible(true);
    }
}
