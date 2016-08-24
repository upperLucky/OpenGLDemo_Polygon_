package com.wyk.opengldemo_polygon;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ${wyk} on 2016/8/24 0024.
 * 单色上色
 */

public class FlatColoredSquare extends Square {
    @Override
    public void draw(GL10 gl10) {
        gl10.glColor4f(0.0f,0.0f,1.0f,0.0f);
        super.draw(gl10);
    }
}
