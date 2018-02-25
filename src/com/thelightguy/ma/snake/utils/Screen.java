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
import com.thelightguy.ma.snake.utils.entity.Apple;
import com.thelightguy.ma.snake.utils.entity.snake.BodyPart;
import com.thelightguy.ma.snake.utils.entity.snake.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel implements Runnable {

    public static final int WIDTH = 500, HEIGHT = 500, FPS = 70;
    private static int tick = 0, aeat, failtick = 0;
    private Thread thread;
    private boolean running = false;
    /*
     * I want to make a multi player snake game, but to much work in such a small time period.
     */
    private List<Snake> snakes = new ArrayList<Snake>();
    private List<Apple> apples = new ArrayList<Apple>();

    public Screen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Snake snake = new Snake(10, 10, Main.color);
        snakes.add(snake);
        addApple();
        start();
    }

    public void frame() {
        if(failtick > 0) return;
        for(Snake snake : snakes) {
            for(BodyPart b : snake.getBody()) {
                if(snake.getBody().get(snake.getBody().size()-1) != b) if(snake.getX() == b.getX() && snake.getY() == b.getY()) failtick = 1;
            }
            if(snake.getY() > HEIGHT/10 || snake.getX() > WIDTH/10) failtick = 1;
            if(snake.getY() < 0 || snake.getX() < 0) failtick = 1;
            if(snake.getBody().size() == 0) {
                BodyPart b = new BodyPart(snake.getX(), snake.getY(), 10, 10, snake.getColor());
                snake.getBody().add(b);
            }
            snake.frame();
        }
        tick++;
        if(tick == 5) snakes.get(0).move();
        if(tick == 10) snakes.get(0).move();
        if(tick == 15) snakes.get(0).move();
        if(tick == 20) {
            snakes.get(0).move();
            tick=0;
        }
        Apple ap = null;
        for(Snake snake : snakes) {
            for(Apple a : apples) {
                if(snake.getX() == a.getX() && snake.getY() == a.getY()) {
                    snake.addSize();
                    ap = a;
                }
            }
        }
        if(ap != null) {
            apples.remove(ap);
            Random random = new Random();
            aeat++;
            addApple();
            if(aeat > 5) if(random.nextInt(5) == 4) addApple();
            if(aeat > 10) {
                if(random.nextInt(10) == 4) {
                    addApple();
                    addApple();
                }
            }

        }
    }

    public void addApple() {
        Random random = new Random();
        Apple apple = new Apple(random.nextInt(WIDTH/10), random.nextInt(HEIGHT/10), 10, 10);
        apples.add(apple);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,WIDTH, HEIGHT);
        if(failtick > 0) {
            Color c = new Color(255-failtick, 0, 0);
            g.setColor(c);
            g.fillRect(0,0,WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.drawString("Score: "+aeat, getWidth()/2-30, getHeight()/2);
            return;
        }
        g.clearRect(0,0,WIDTH, HEIGHT);
        g.setColor(Color.lightGray);
        for(int i = 0; i < WIDTH /10; i++) g.drawLine(i*10, 0, i*10, HEIGHT);
        for(int i = 0; i < HEIGHT /10; i++) g.drawLine(0, i*10, WIDTH, i*10);
        for(Apple a : apples) a.write(g);
        for(Snake snake : snakes) for(BodyPart bo : snake.getBody()) bo.draw(g);
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {

    }

    public void run() {
        while(running) {
            if(failtick > 0) {
                if(failtick >= 255) {
                    System.exit(0);
                }
                failtick++;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            frame();
            repaint();
            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!Main.gameFrame.isActive()) {
                running = false;
                this.thread.stop();
                Main.gameFrame = null;
            }
        }
    }
}
