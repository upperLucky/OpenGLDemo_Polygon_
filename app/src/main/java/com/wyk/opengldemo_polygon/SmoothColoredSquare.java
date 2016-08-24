package com.wyk.opengldemo_polygon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ${wyk} on 2016/8/24 0024.
 * 平滑颜色过渡
 */

public class SmoothColoredSquare extends Square {

    private float colors[] = {
            1f,0f,0f,1f,
            0f,1f,0f,1f,
            0f,0f,1f,1f,
            1f,0f,1f,1f
    };
    private FloatBuffer mFloatBuffer;

    public SmoothColoredSquare() {
        // 把颜色数组放倒Buffer中
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        mFloatBuffer = byteBuffer.asFloatBuffer();
        mFloatBuffer.put(colors);
        mFloatBuffer.position(0);

    }

    @Override
    public void draw(GL10 gl10) {
        gl10.glVertexPointer(3,GL10.GL_FLOAT,0,verexBuffer);

        gl10.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl10.glColorPointer(4,GL10.GL_FLOAT,0,mFloatBuffer);
        super.draw(gl10);
        gl10.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
