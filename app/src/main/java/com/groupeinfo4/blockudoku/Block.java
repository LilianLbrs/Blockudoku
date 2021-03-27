package com.groupeinfo4.blockudoku;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Block {
    private float x, y, w;
    private Rect rect;

    public Block(float x, float y, float w) {
        rect = new Rect((int) x, (int) y, (int) (x + w), (int) (y + w));
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public void setX(float x) {
        this.x = x;
        rect.left = (int) x;
        rect.right = (int) (x + w);
    }

    public void setY(float y) {
        this.y = y;
        rect.top = (int) y;
        rect.bottom = (int) (y + w);
    }

    public void setSize(float size) {
        w = size;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);
    }
}
