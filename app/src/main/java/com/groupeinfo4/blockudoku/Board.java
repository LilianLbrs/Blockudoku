package com.groupeinfo4.blockudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Board {
    private float gridSeparatorSize;

    private int [][] matrix;
    private float width;
    private float gridWidth;
    private float cellWidth;
    private Paint paint;

    private BlockGroup firstBlockGroup;
    private BlockGroup secondBlockGroup;
    private BlockGroup thirdBlockGroup;
    private float blocksPositionY;
    private float firstBlockPosX;
    private Block block;

    private float x, y;

    public Board() {
        matrix = new int [9][9];
        paint = new Paint();
        BlockGroupTypes bct = new BlockGroupTypes();
        firstBlockGroup =  bct.getRandomBlock();
        secondBlockGroup = bct.getRandomBlock();
        thirdBlockGroup = bct.getRandomBlock();
    }

    public void setBound(float x, float y, float width) {
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas, int w, int h) {
        // We compute some sizes
        gridSeparatorSize = (w / 9f) / 20f;
        gridWidth = w;
        cellWidth = gridWidth / 9f;

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, w, h, paint);

        // --- Draw the grid lines ---
        paint.setColor( Color.GRAY );
        paint.setStrokeWidth( gridSeparatorSize/2 );
        for( int i=0; i<=9; i++ ) {
            canvas.drawLine( i*cellWidth, 200, i*cellWidth, cellWidth*9 +200, paint );
            canvas.drawLine( 0,i*cellWidth +200, cellWidth*9, i*cellWidth +200, paint );
        }
        paint.setColor( Color.BLACK );
        paint.setStrokeWidth( gridSeparatorSize );
        for( int i=0; i<=3; i++ ) {
            canvas.drawLine( i*(cellWidth*3), 200, i*(cellWidth*3), cellWidth*9 +200, paint );
            canvas.drawLine( 0,i*(cellWidth*3) +200, cellWidth*9, i*(cellWidth*3) +200, paint );
        }

        block = new Block (500, 500, cellWidth * 0.7f);
        firstBlockGroup.draw(canvas,paint, cellWidth * 0.7f, 40, 1350);
        secondBlockGroup.draw(canvas,paint, cellWidth * 0.7f, 400, 1600);
        thirdBlockGroup.draw(canvas,paint, cellWidth * 0.7f, 700, 1350);

    }
}
