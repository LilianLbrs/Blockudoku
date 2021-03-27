package com.groupeinfo4.blockudoku;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BlockGroup {
    private int[][] matrixBlock;
    private float middleX, middleY;
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

    public void draw(Canvas canvas, Paint paint, float cellWidth, int x, int y) {
        blockDraw = new Block(0,0, cellWidth * 0.8f);

        for (int row = 0; row < matrixBlock.length; row++) {
            for (int col = 0; col < matrixBlock[0].length; col++) {
                if (matrixBlock[row][col] == 1) {
                    blockDraw.setX(x + col * cellWidth * 0.8f);
                    blockDraw.setY(y + row * cellWidth * 0.8f);
                    blockDraw.draw(canvas, paint);
                }
            }
        }
    }
}
