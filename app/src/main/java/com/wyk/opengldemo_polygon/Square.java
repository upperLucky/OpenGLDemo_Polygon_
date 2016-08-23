package com.wyk.opengldemo_polygon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ${wyk} on 2016/8/23 0023.
 */

public class Square {

    // 定义点
    private float vertices[] = {
            -1.0f,1.0f,0.0f,   // v0,左上角点
            -1.0f,-1.0f,0.0f,  // v1,左下角点
            1.0f,-1.0f,0.0f,   // v2,右下角点
            1.0f,1.0f,0.0f     // v3,右上角点
    };

    // 连接点的顺序
    private short indices[] = {0,1,2,0,2,3};

    private FloatBuffer verexBuffer;
    private ShortBuffer indexBuffer;

    public Square() {
        // 一个浮点数是四个字节，因此ByteBuffer的容量需要在基础上乘以4
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        verexBuffer = byteBuffer.asFloatBuffer();
        verexBuffer.put(vertices);
        verexBuffer.position(0);

        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(indices.length * 2);
        byteBuffer1.order(ByteOrder.nativeOrder());
        indexBuffer = byteBuffer1.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    /**
     * 画出正方形
     * @param gl10
     */
    public void draw(GL10 gl10) {


        /**
         * 定义多边形的正面和背面。
         * GL10.GL_CCW:选择逆时针多边形为正面
         * GL10.GL_CW：选择顺时针多边形为正面
         * 默认逆时针多边形为正面
         */
        gl10.glFrontFace(GL10.GL_CCW);

        /**
         * 打开 忽略“后面”设置：
         * 开启或禁止拣选功能，调用glEnable方法和glDisable方法并以GL_CULL_FACE为参数
         * 拣选功能初始值为禁止
         */
        gl10.glEnable(GL10.GL_CULL_FACE);

        /**
         * 明确指明“忽略“哪个面的代码如下
         * 功能:指明“忽略”哪个面
         * 允许的符号常量有：GL_FRONT,GL_BACK和GL_FRONT_AND_BACK。初始值为GL_BACK。
         */
        gl10.glCullFace(GL10.GL_BACK);

        /**
         * 启用客户端的某项功能
         * GL_VERTEX_ARRAY——如果启用，顶点矩阵可以用来写入以及调用glDrawArrays方法或者glDrawElements方法时进行渲染。
         */
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        /**
         * 定义一个顶点坐标矩阵。
         * 参数：
         size——每个顶点的坐标维数，必须是2, 3或者4，初始值是4。
         type——指明每个顶点坐标的数据类型，允许的符号常量有GL_BYTE, GL_SHORT, GL_FIXED和GL_FLOAT，初始值为GL_FLOAT。
         stride——指明连续顶点间的位偏移，如果为0，顶点被认为是紧密压入矩阵，初始值为0。
         pointer——指明顶点坐标的缓冲区，如果为null，则没有设置缓冲区。

         */
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, verexBuffer); // OpenGL docs.

        /**
         * 由矩阵数据渲染图元。
         * mode——指明被渲染的是哪种图元，被允许的符号常量有GL_POINTS,GL_LINE_STRIP,GL_LINE_LOOP,GL_LINES,GL_TRIANGLE_STRIP, GL_TRIANGLE_FAN和GL_TRIANGLES
           count——指明被渲染的元素个数。
          type——指明索引指的类型，不是GL_UNSIGNED_BYTE就是GL_UNSIGNED_SHORT。
          indices——指明存储索引的位置指针。
         */
        gl10.glDrawElements(GL10.GL_TRIANGLES,indices.length,GL10.GL_UNSIGNED_SHORT,indexBuffer);

        /**
         * 禁用客户端的某项功能
         */
        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.

        gl10.glDisable(GL10.GL_CULL_FACE);
    }
}
