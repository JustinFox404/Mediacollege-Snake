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

package com.thelightguy.ma.snake.utils.entity.snake;

import com.thelightguy.ma.snake.utils.IsKeyPressed;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class Snake {

    private List<BodyPart> body = new ArrayList<BodyPart>();
    private int x, y, d, s;
    private Color color;

    public Snake(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        d=2;
        s=4;
    }

    public void frame() {
        if(IsKeyPressed.isWPressed()) d=1;
        if(IsKeyPressed.isAPressed()) d=4;
        if(IsKeyPressed.isSPressed()) d=3;
        if(IsKeyPressed.isDPressed()) d=2;
    }

    public void move() {
        if(d == 1) y--;
        else if(d == 2) x++;
        else if(d == 3) y++;
        else if(d == 4) x--;
        body.add(new BodyPart(x,y,10,10,color));
        if(body.size() > s) body.remove(0);
    }
    public void updateLocation() {

    }

    public Color getColor() {
        return color;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public List<BodyPart> getBody() {
        return body;
    }

    public void addSize() {
        s++;
    }
}
