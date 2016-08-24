package com.wyk.opengldemo_polygon;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ${wyk} on 2016/8/23 0023.
 */

public class SquareRenderer implements GLSurfaceView.Renderer {

    //    Square mSquare = new Square();
//    FlatColoredSquare mSquare = new FlatColoredSquare();
    SmoothColoredSquare mSquare = new SmoothColoredSquare();
    private float angle;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);  // 设置背景颜色（rgba），取值范围是0-1
        gl10.glShadeModel(GL10.GL_SMOOTH);  // 选择恒定(GL10.GL_FLAT)或者光滑着色模式(GL10.GL_SMOOTH)
        gl10.glClearDepthf(1.0f); // 指明深度缓冲区的清理值，并通过glClear()方法清理深度缓冲区，取值范围为0-1，初始值为1
        gl10.glEnable(GL10.GL_DEPTH_TEST); // 启用服务端的GL功能。  GL10.GL_DEPTH_TEST：如果启用，做深度比较和更新深度缓存
        gl10.glDepthFunc(GL10.GL_LEQUAL); //
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); // 控制GL某些行为。
        // GL_NICEST：选择正确或者质量最好的选项。
        // GL_PERSPECTIVE_CORRECTION_HINT：表明颜色和纹理坐标插值的效果
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        gl10.glViewport(0, 0, width, height); // 设置一个视口
                                                /*x——指明视口矩形的左下角ｘ坐标，初始值为0。
                                                  y——指明视口矩形的左下角ｙ坐标，初始值为0。
                                                  width——指明视口的宽，如果ＧＬ上下文首次附于一个surface则宽、高为这个surface大小。
                                                  height——指明视口的高，如果ＧＬ上下文首次附于一个surface则宽、高为这个surface大小。*/
        gl10.glMatrixMode(GL10.GL_PROJECTION); // 设置当前矩阵模式
        // GL_PROJECTION——应用投射矩阵堆的后续矩阵操作。
        gl10.glLoadIdentity();  // 用特征矩阵代替当前矩阵。
        GLU.gluPerspective(gl10, 45.0f, (float) width / (float) height, 0.1f, 100.0f); // Set up a perspective projection matrix

                    /*Parameters
                    gl  a GL10 interface
                    fovy  specifies the field of view angle, in degrees, in the Y direction.
                    aspect  specifies the aspect ration that determins the field of view in the x direction. The aspect ratio is the ratio of x (width) to y (height).
                    zNear  specifies the distance from the viewer to the near clipping plane (always positive).
                    zFar  specifies the distance from the viewer to the far clipping plane (always positive).*/

        gl10.glMatrixMode(GL10.GL_MODELVIEW); // 应用视图矩阵堆的后续矩阵操作。
        gl10.glLoadIdentity();
    }

    /**
     * 矩形
     */
   /* @Override
    public void onDrawFrame(GL10 gl10) {

        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glLoadIdentity();
        gl10.glTranslatef(0,0,-4);
        mSquare.draw(gl10);
    }*/

    /**
     * 坐标变换
     * @param gl10
     */
    @Override
    public void onDrawFrame(GL10 gl10) {

        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glLoadIdentity();
        gl10.glTranslatef(0,0,-10);

        // SQUARE A
        // Save the current matrix.
        gl10.glPushMatrix();
        // Rotate square A counter-clockwise.
        gl10.glRotatef(angle, 0, 0, 1);
        // Draw square A.
        mSquare.draw(gl10);
        // Restore the last matrix.
        gl10.glPopMatrix();

        // SQUARE B
        // Save the current matrix
        gl10.glPushMatrix();
        // Rotate square B before moving it, making it rotate around A.
        gl10.glRotatef(-angle, 0, 0, 1);
        // Move square B.
        gl10.glTranslatef(2, 0, 0);
        // Scale it to 50% of square A
        gl10.glScalef(.5f, .5f, .5f);
        // Draw square B.
        mSquare.draw(gl10);

        // SQUARE C
        // Save the current matrix
        gl10.glPushMatrix();
        // Make the rotation around B
        gl10.glRotatef(-angle, 0, 0, 1);
        gl10.glTranslatef(2, 0, 0);
        // Scale it to 50% of square B
        gl10.glScalef(.5f, .5f, .5f);
        // Rotate around it's own center.
        gl10.glRotatef(angle*10, 0, 0, 1);
        // Draw square C.
        mSquare.draw(gl10);

        // Restore to the matrix as it was before C.
        gl10.glPopMatrix();
        // Restore to the matrix as it was before B.
        gl10.glPopMatrix();

        // Increse the angle.
        angle++;
    }
}
