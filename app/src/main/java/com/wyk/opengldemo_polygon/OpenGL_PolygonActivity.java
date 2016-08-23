package com.wyk.opengldemo_polygon;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OpenGL_PolygonActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_open_gl__polygon);

        GLSurfaceView surfaceView = new GLSurfaceView(this);
        surfaceView.setRenderer(new SquareRenderer());
        setContentView(surfaceView);
    }


}
