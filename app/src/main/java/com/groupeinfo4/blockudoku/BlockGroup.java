package com.groupeinfo4.blockudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BlockGroup{
    public int[][] matrixBlock;
    float middleX, middleY;
    public boolean hidden = false;
    public boolean isPressed = false;

    private float childSizeNormal, childSizeMaximum;
    private float childSizeCurrently;
    private Block blockDraw;

    public BlockGroup(float middleX, float middleY, float childSizeNormal, float childSizeMaximum, int[][] matrixBlock) {
        this.middleX = middleX;
        this.middleY = middleY;
        this.childSizeNormal = childSizeNormal;
        this.childSizeMaximum = childSizeMaximum;
        this.matrixBlock = matrixBlock;
    }

    public boolean isInArea (float x, float y, float cellWidth) {
        return !hidden && x > middleX - 1.5*cellWidth && x < middleX + 1.5*cellWidth
                && y > middleY - 1.5*cellWidth && y < middleY + 1.5*cellWidth;
    }

    public void draw(Canvas canvas, Paint paint, float cellWidth, float x, float y) {
        blockDraw = new Block(0,0, cellWidth * 0.8f);

        for (int row = 0; row < matrixBlock.length; row++) {
            for (int col = 0; col < matrixBlock[0].length; col++) {
                if (matrixBlock[row][col] == 1) {
                    blockDraw.setX(x + row * cellWidth * 0.8f);
                    blockDraw.setY(y + col * cellWidth * 0.8f);
                    blockDraw.drawFill(canvas, paint);
                }
                else {
                    blockDraw.setX(x + row * cellWidth * 0.8f);
                    blockDraw.setY(y + col * cellWidth * 0.8f);
                    blockDraw.drawStroke(canvas, paint);
                }
            }
        }

    }

    public void drawOutline(Canvas canvas, Paint paint, float left, float top, float right, float bottom) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    public void setIsPressed (boolean isPressed) {
        this.isPressed = isPressed;
    }
}
