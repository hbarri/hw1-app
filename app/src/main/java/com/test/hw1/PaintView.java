package com.test.hw1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * PaintView class
 * extends view
 */
public class PaintView extends View {

    private Bitmap bm;
    private Canvas canvas;
    private float xVal;
    private float yVal;
    public int height;
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();

    /**
     * PaintView constructor
     * @param context
     * @param attrs
     */
    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //set drawing cache enabled for bitmap
        this.setDrawingCacheEnabled(true);

        //create new path
        paths.add(new Path());

        //add paint attributes
        paints.add(new Paint());
        paints.get(paints.size()-1).setAntiAlias(true);
        paints.get(paints.size()-1).setColor(Color.BLACK);
        paints.get(paints.size()-1).setStyle(Paint.Style.STROKE);
        paints.get(paints.size()-1).setStrokeJoin(Paint.Join.ROUND);
        paints.get(paints.size()-1).setStrokeWidth(10f);
    }

    /**
     * change color method
     * @param color
     */
    public void setColor(int color) {
        //create new path
        paths.add(new Path());

        //create new color with path
        paints.add(new Paint());
        paints.get(paints.size()-1).setAntiAlias(true);
        paints.get(paints.size()-1).setColor(color);
        paints.get(paints.size()-1).setStyle(Paint.Style.STROKE);
        paints.get(paints.size()-1).setStrokeJoin(Paint.Join.ROUND);
        paints.get(paints.size()-1).setStrokeWidth(10f);
    }

    /**
     * method to start touch
     * @param x
     * @param y
     */
    private void startTouch(float x, float y) {
        //get latest path created and set to x and y
        paths.get(paths.size()-1).moveTo(x, y);
        xVal = x;
        yVal = y;
    }

    /**
     * method for size change
     * @param w
     * @param h
     * @param oldh
     * @param oldw
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //create bitmap
        bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        //attache bitmap to canvas
        canvas = new Canvas(bm);
    }

    /**
     * method to get bitmap
     * @return bitmap
     */
    public Bitmap getBm() {
        return this.getDrawingCache();
    }

    /**
     * move touch method
     * @param x
     * @param y
     */
    private void moveTouch(float x, float y) {
        //find absolute values
        float dx = Math.abs(x- xVal);
        float dy = Math.abs(y- yVal);
        //if values are greater or equal to tolerance
        if(dx >= 5 || dy >= 5) {
            //adjust latest path
            paths.get(paths.size()-1).quadTo(xVal, yVal, (x+ xVal)/2, (y+ yVal)/2);
            xVal = x;
            yVal = y;
        }
    }

    /**
     * upTouch method
     */
    private void upTouch() {
        //set latest path lineTo
        paths.get(paths.size()-1).lineTo(xVal, yVal);
    }

    /**
     * onDraw method
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //draw on canvas with latest path and paint
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
    }

    /**
     * onTouch event method
     * @param event
     * @return bool
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //get x and y values
        float x = event.getX();
        float y = event.getY();

        //run case depending on motion
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}
