package com.ia04nf28.colladia.model.Elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.UUID;

/**
 * Created by Mar on 17/05/2016.
 */
public abstract class Element {

    private String idElement = UUID.randomUUID().toString();
    private float x;
    private float y;
    private float width;
    private float height;
    private Paint paint;
    protected PointF center;

    public Element()
    {
        center = new PointF();

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
    }

    public Element(float x, float y, Paint paint) {
        this.x = x;
        this.y = y;
        this.paint = paint;
    }
    public Element(float x, float y) {
        this.x = x;
        this.y = y;

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void set(PointF topLeftCorner, PointF bottomRightCorner)
    {
        //TODO issue topleftCorner is not alaways the topleftcorner
        if(topLeftCorner.x > bottomRightCorner.x || topLeftCorner.y > bottomRightCorner.y){//case where the two corner are in the wrong order then inverse them
            PointF tempPoint = topLeftCorner;
            topLeftCorner = bottomRightCorner;
            bottomRightCorner = tempPoint;
        }

        this.x = topLeftCorner.x;
        this.y = topLeftCorner.y;
        this.width = bottomRightCorner.x;
        this.height = bottomRightCorner.y;
        center.set((this.x + this.width) / 2, (this.y + this.height) / 2);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public PointF getCenter() {
        return center;
    }

    public void setCenter(PointF center) {
        this.center = center;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public String getIdElement() {
        return idElement;
    }

    public abstract void drawElement(Canvas canvas);
    public abstract boolean isTouch(PointF finger);
    public abstract void resize(float resizeFactor);

    public  void move(PointF newPosition){
        PointF translate =  new PointF(newPosition.x - center.x, newPosition.y - center.y);
        x += translate.x;
        y += translate.y;
        width += translate.x;
        height += translate.y;
        center.set(newPosition.x, newPosition.y);
    }


    public void selectElement(){
        paint.setColor(Color.RED);
    }
    public void deselectElement(){
        paint.setColor(Color.BLUE);
    }
}