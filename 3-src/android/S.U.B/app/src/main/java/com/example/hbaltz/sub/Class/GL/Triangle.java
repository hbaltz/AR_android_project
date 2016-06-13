package com.example.hbaltz.sub.Class.GL;

import android.opengl.GLES30;
import android.util.Log;

import com.example.hbaltz.sub.Renderer.GeoGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by hbaltz on 6/13/2016.
 */
public class Triangle {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FIELDS: ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private FloatBuffer vertexBuffer;

    private float triangleCoords[];
    private float color[];

    private int Program;
    private int PositionHandle;
    private int ColorHandle;
    private int MVPMatrixHandle;

    private int vertexCount;
    private int vertexStride;

    private int COORDS_PER_VERTEX;

    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Triangle() {
        this.triangleCoords = new float[]{   // in counterclockwise order:
                0.0f, 0.622008459f, 0.0f, // top
                -0.5f, -0.311004243f, 0.0f, // bottom left
                0.5f, -0.311004243f, 0.0f  // bottom right
        };

        this.color = new float[] { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

        initializeVertexBuffer();
    }

    public Triangle(float[] triangleCoords, float[] color) {
        this.triangleCoords = triangleCoords;
        this.color = color;

        initializeVertexBuffer();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// GETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public FloatBuffer getVertexBuffer() {
        return this.vertexBuffer;
    }

    public float[] getTriangleCoords() {
        return this.triangleCoords;
    }

    public float[] getColor() {
        return this.color;
    }

    public int getCOORDS_PER_VERTEX() {
        return this.COORDS_PER_VERTEX;
    }

    public int getVertexCount() {
        return this.vertexCount;
    }

    public int getVertexStride() {
        return this.vertexStride;
    }

    public int getProgram() {
        return this.Program;
    }

    public int getColorHandle() {
        return this.ColorHandle;
    }

    public int getMVPMatrixHandle() {
        return this.MVPMatrixHandle;
    }

    public int getmPositionHandle() {
        return this.PositionHandle;
    }

    public String getVertexShaderCode() {
        return this.vertexShaderCode;
    }

    public String getFragmentShaderCode() {
        return this.fragmentShaderCode;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public void setVertexBuffer(FloatBuffer vertexBuffer) {
        this.vertexBuffer = vertexBuffer;
    }

    public void setTriangleCoords(float[] triangleCoords) {
        this.triangleCoords = triangleCoords;
    }

    public void setColor(float[] color) {
        this.color = color;
    }

    public void setCOORDS_PER_VERTEX(int COORDS_PER_VERTEX) {
        this.COORDS_PER_VERTEX = COORDS_PER_VERTEX;
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public void setVertexStride(int vertexStride) {
        this.vertexStride = vertexStride;
    }

    public void setColorHandle(int mColorHandle) {
        this.ColorHandle = mColorHandle;
    }

    public void setMVPMatrixHandle(int mMVPMatrixHandle) {
        this.MVPMatrixHandle = mMVPMatrixHandle;
    }

    public void setPositionHandle(int mPositionHandle) {
        this.PositionHandle = mPositionHandle;
    }

    public void setProgram(int mProgram) {
        this.Program = mProgram;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void initializeVertexBuffer(){

        Log.d("initializeVertexBuffer", "OK");


        // Initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        COORDS_PER_VERTEX = 3;

        vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
        vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

        // prepare shaders and OpenGL program
        int vertexShader = GeoGLRenderer.loadShader(
                GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GeoGLRenderer.loadShader(
                GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);

        Program = GLES30.glCreateProgram();             // create empty OpenGL Program
        GLES30.glAttachShader(Program, vertexShader);   // add the vertex shader to program
        GLES30.glAttachShader(Program, fragmentShader); // add the fragment shader to program
        GLES30.glLinkProgram(Program);                  // create OpenGL program executables
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * this shape.
     */

    //@param mvpMatrix - The Model View Project matrix in which to draw
    public void draw() {

        Log.d("draw", "OK");

        // Add program to OpenGL ES environment
        GLES30.glUseProgram(Program);

        // get handle to vertex shader's vPosition member
        PositionHandle = GLES30.glGetAttribLocation(Program, "vPosition");

        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(PositionHandle);

        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(PositionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        ColorHandle = GLES30.glGetUniformLocation(Program, "vColor");

        // Set color for drawing the triangle
        GLES30.glUniform4fv(ColorHandle, 1, color, 0);

        // Draw the triangle
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(PositionHandle);
    }
}
