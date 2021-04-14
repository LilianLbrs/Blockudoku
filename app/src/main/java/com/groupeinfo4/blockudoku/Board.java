package com.groupeinfo4.blockudoku;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.TextView;

public class Board implements View.OnTouchListener{
    Context context;
    public TextView scoreView;

    private int score = 0;

    public float screenWidth;
    public float screenHeight;


    private float gridSeparatorSize;
    private float blockSeparator;
    public float blockCellWidth;

    private int[][] matrix;
    private float gridWidth;
    private float cellWidth;
    private float HEADER;

    private Paint paint;
    private int [] index = {-1, -1};

    public BlockGroup selectedBlockGroup;
    public BlockGroup firstBlockGroup;
    public BlockGroup secondBlockGroup;
    public BlockGroup thirdBlockGroup;
    private float blocksPosY;
    private float firstBlockPosX;
    private float secondBlockPosX;
    private float thirdBlockPosX;
    private Block blockDraw;


    public Board() {
        matrix = new int[9][9];
        for (int rows = 0; rows < 9; rows++)
            for (int cols = 0; cols < 9; cols++)
                matrix[rows][cols] = 0;

        paint = new Paint();
    }

    public Board(Context context) {
        this.context = context;

        matrix = new int[9][9];
        for (int rows = 0; rows < 9; rows++)
            for (int cols = 0; cols < 9; cols++)
                matrix[rows][cols] = 0;

        paint = new Paint();
    }


    public void createNewGroups() {
        BlockGroupTypes bgt = new BlockGroupTypes();
        blockDraw = new Block(0, 0, cellWidth);

        //initializing first block
        firstBlockGroup = bgt.getRandomBlock();
        firstBlockGroup.middleX = blockSeparator + blockCellWidth;
        firstBlockGroup.middleY = (screenHeight + gridWidth) / 2 + blockCellWidth;

        //initializing second block
        do {
            secondBlockGroup = bgt.getRandomBlock();
        }while (secondBlockGroup == firstBlockGroup);
        secondBlockGroup.middleX = 2 * blockSeparator + (float) 4.5 * blockCellWidth;
        secondBlockGroup.middleY = (screenHeight + gridWidth) / 2 + (float) 1.5*blockCellWidth;

        //initializing third block
        do{
            thirdBlockGroup = bgt.getRandomBlock();
        }while (thirdBlockGroup == firstBlockGroup || thirdBlockGroup == secondBlockGroup);
        thirdBlockGroup.middleX = 3 * blockSeparator + (float) 7.5 * blockCellWidth;
        thirdBlockGroup.middleY = (screenHeight + gridWidth) / 2 + (float) 1.5*blockCellWidth;
    }

    public void init(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        scoreView = (TextView) ((Activity)context).findViewById(R.id.score);


        // We compute some sizes
        gridWidth = Math.min(screenWidth, 1500);
        gridSeparatorSize = (gridWidth / 9f) / 20f;
        cellWidth = gridWidth / 9f;
        blockSeparator = gridWidth / 19f;
        blockCellWidth = gridWidth / 19f * 5 / 3;
        firstBlockPosX = blockSeparator;
        secondBlockPosX = 2 * blockSeparator + 3 * blockCellWidth;
        thirdBlockPosX = 3 * blockSeparator + 6 * blockCellWidth;
        blocksPosY = (screenHeight + gridWidth) / 2;
        HEADER = screenHeight /10;

        // --------- test ---------
        createNewGroups();
        // --------------------------
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        // --- Draw the grid lines ---
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(gridSeparatorSize / 2);
        for (int i = 0; i <= 9; i++) {
            canvas.drawLine(i * cellWidth, HEADER, i * cellWidth, cellWidth * 9 + HEADER, paint);
            canvas.drawLine(0, i * cellWidth + HEADER, cellWidth * 9, i * cellWidth + HEADER, paint);
        }
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(gridSeparatorSize);
        for (int i = 0; i <= 3; i++) {
            canvas.drawLine(i * (cellWidth * 3), HEADER, i * (cellWidth * 3), cellWidth * 9 + HEADER, paint);
            canvas.drawLine(0, i * (cellWidth * 3) + HEADER, cellWidth * 9, i * (cellWidth * 3) + HEADER, paint);
        }


        //Draws the groups to place
        if(!firstBlockGroup.hidden) {
            firstBlockGroup.draw(canvas, paint, blockCellWidth, firstBlockPosX, blocksPosY);
            if(firstBlockGroup.isPressed){
                firstBlockGroup.drawOutline(canvas, paint, firstBlockPosX, blocksPosY, firstBlockPosX + 3 * blockCellWidth * 0.8f, blocksPosY + 3 * blockCellWidth * 0.8f);
            }

        }
        if(!secondBlockGroup.hidden) {
            secondBlockGroup.draw(canvas, paint, blockCellWidth, secondBlockPosX, blocksPosY);
            if(secondBlockGroup.isPressed){
                secondBlockGroup.drawOutline(canvas, paint, secondBlockPosX, blocksPosY, secondBlockPosX + 3 * blockCellWidth * 0.8f, blocksPosY + 3 * blockCellWidth * 0.8f);
            }
        }
        if(!thirdBlockGroup.hidden) {
            thirdBlockGroup.draw(canvas, paint, blockCellWidth, thirdBlockPosX, blocksPosY);
            if(thirdBlockGroup.isPressed){
                thirdBlockGroup.drawOutline(canvas, paint, thirdBlockPosX, blocksPosY, thirdBlockPosX + 3 * blockCellWidth * 0.8f, blocksPosY + 3 * blockCellWidth * 0.8f);
            }
        }

        //Create new groups if the three blocks are placed
        if(firstBlockGroup.hidden && secondBlockGroup.hidden && thirdBlockGroup.hidden) createNewGroups();

        //Draws the blue squares on the game board
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    blockDraw.setX(row * cellWidth);
                    blockDraw.setY(col * cellWidth + HEADER);
                    blockDraw.drawFill(canvas, paint);
                }
            }

        //Displays the actual score
        scoreView.setText("Score : " + score);
    }

    public void processPressing(MotionEvent motionEvent) {
        if (firstBlockGroup.isInArea(motionEvent.getX(), motionEvent.getY(), blockCellWidth) && !firstBlockGroup.hidden) {
            firstBlockGroup.setIsPressed(true);
            selectedBlockGroup = firstBlockGroup;
            //System.out.println("Piece 1 cliquée");
        }
        if (secondBlockGroup.isInArea(motionEvent.getX(), motionEvent.getY(), blockCellWidth) && !secondBlockGroup.hidden) {
            secondBlockGroup.setIsPressed(true);
            selectedBlockGroup = secondBlockGroup;
            //System.out.println("Piece 2 cliquée");
        }
        if (thirdBlockGroup.isInArea(motionEvent.getX(), motionEvent.getY(), blockCellWidth) && !thirdBlockGroup.hidden) {
            thirdBlockGroup.setIsPressed(true);
            selectedBlockGroup = thirdBlockGroup;
            //System.out.println("Piece 3 cliquée");
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (selectedBlockGroup == null)
                processPressing(event);
            else {
                getIndexByCoordinate(event.getX(), event.getY());
                if (canPlaceGroupBlock(index, selectedBlockGroup.matrixBlock)) {
                    placeGroupBlock(index, selectedBlockGroup.matrixBlock);
                    score += countPoints(selectedBlockGroup);
                    selectedBlockGroup.hidden = true;
                    selectedBlockGroup = null;
                    index[0] = -1;
                    index[1] = -1;
                    removeBlocks();
                }

            }
            return false;
        }
        return false;
    }

    public int[] getIndexByCoordinate(float x, float y) {
        float blockWidth = this.gridWidth / 9;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                float blockX1 = col * blockWidth ; //+ this.x;
                float blockX2 = blockX1 + blockWidth;
                float blockY1 = (row * blockWidth) + HEADER; //+ this.y;
                float blockY2 = blockY1 + blockWidth;
                if (x >= blockX1 && x < blockX2 && y >= blockY1 && y < blockY2) {
                    index[0] = col;
                    index[1] = row;
                    return index;
                }
            }
        }
        return null;
    }

    public boolean canPlaceGroupBlock(int [] index, int[][] matrixBlock) {
        if (index[0] < 0 || index[1] < 0) return false;
        for (int row = 0; row < matrixBlock.length; row++) {
            for (int col = 0; col < matrixBlock[0].length; col++) {
                int matrixRow = index[0] - 1 + row;
                int matrixCol = index[1] - 1 + col;
                if (matrixRow >= matrix.length || matrixCol >= matrix[0].length
                    || matrixRow < 0 || matrixCol < 0) {
                    return false;
                }
                if (matrixBlock[row][col] == 1 && matrix[matrixRow][matrixCol] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeGroupBlock(int [] index, int[][] matrixBlock) {
        for (int row = 0; row < matrixBlock.length; row++) {
            for (int col = 0; col < matrixBlock[0].length; col++) {
                int matrixRow = index[0] - 1 + row;
                int matrixCol = index[1] - 1 + col;
                if (matrixRow >= 0 && matrixCol >= 0 && matrixBlock[row][col] == 1) {
                    matrix[matrixRow][matrixCol] = 1;
                }
            }
        }
    }

    public void affMatrix () {
        for (int i = 0; i < matrix.length; i++){
                System.out.println(matrix[i][0] + " | " +matrix[i][1] + " | " +matrix[i][2] + " | " +matrix[i][3] + " | " +matrix[i][4] + " | " +
                        matrix[i][5] + " | " +matrix[i][6] + " | " +matrix[i][7] + " | " +matrix[i][8]);
                System.out.println("\n");
        }
    }

    public int countPoints (BlockGroup bg) {
        int points=0;
        int [][] matrixBg = bg.matrixBlock;
        for (int i = 0; i < matrixBg.length; i++) {
            for (int j = 0; j < matrixBg[0].length; j++) {
                if (matrixBg[i][j] == 1) points++;
            }
        }
        return points;
    }

    private boolean checkRow(int row) {
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[row][col] != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int col) {
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][col] != 1) {
                return false;
            }
        }
        return true;
    }

    private void removeBlocks () {
        for (int i = 0; i < matrix.length; i++){
            if (checkRow(i)){
                for (int cols = 0;  cols < matrix[0].length; cols++){
                    matrix[i][cols] = 0;
                }
                score += matrix.length;
            }
            if (checkCol(i)){
                for (int rows = 0;  rows < matrix.length; rows++){
                    matrix[rows][i] = 0;
                }
                score += matrix[0].length;
            }
        }
    }
}
