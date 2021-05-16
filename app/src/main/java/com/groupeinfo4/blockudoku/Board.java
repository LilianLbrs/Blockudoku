package com.groupeinfo4.blockudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.StringTokenizer;

public class Board implements View.OnTouchListener{
    Context context;
    private Activity activity;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private final String HIGH_SCORE_FIELD = "high_score";
    private final String SCORE_FIELD = "score";
    private final String MATRIX_FIELD = "matrix";
    private final String BG1_FIELD = "block_group_1";
    private final String BG2_FIELD = "block_group_2";
    private final String BG3_FIELD = "block_group_3";

    private ImageButton retry;
    public TextView scoreView;
    private int score = 0;
    public TextView highScoreView;
    private int highScore = 0;

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
        activity = getActivity();

        matrix = new int[9][9];
        for (int rows = 0; rows < 9; rows++)
            for (int cols = 0; cols < 9; cols++)
                matrix[rows][cols] = 0;

        paint = new Paint();
    }

    public Activity getActivity() {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
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
        highScoreView = (TextView) ((Activity)context).findViewById(R.id.highScore);
        retry = (ImageButton) ((Activity)context).findViewById(R.id.retryBtn);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });


        sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        highScore = sharedPref.getInt(HIGH_SCORE_FIELD, highScore);
        highScoreView.setText("Record : " + highScore);

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

        createNewGroups();

        if(sharedPref.getInt(SCORE_FIELD, 0) != 0) resumeGame();
    }

    private void resumeGame () {
        getMatrixFromPrefs();
        getBlockGroupsFromPrefs();
        score = sharedPref.getInt(SCORE_FIELD, 0);
    }

    public void saveState () {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                str.append(matrix[i][j]).append(",");
            }
        }
        editor.putString(MATRIX_FIELD, str.toString());

        StringBuilder bg1 = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bg1.append(firstBlockGroup.matrixBlock[i][j]).append(",");
            }
        }
        if(firstBlockGroup.hidden) bg1.append("true");
        else bg1.append("false");
        editor.putString(BG1_FIELD, bg1.toString());

        StringBuilder bg2 = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bg2.append(secondBlockGroup.matrixBlock[i][j]).append(",");
            }
        }
        if(secondBlockGroup.hidden) bg2.append("true");
        else bg2.append("false");
        editor.putString(BG2_FIELD, bg2.toString());

        StringBuilder bg3 = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bg3.append(thirdBlockGroup.matrixBlock[i][j]).append(",");
            }
        }
        if(thirdBlockGroup.hidden) bg3.append("true");
        else bg3.append("false");
        editor.putString(BG3_FIELD, bg3.toString());

        editor.putInt(SCORE_FIELD, score);

        editor.commit();
    }

    private void getMatrixFromPrefs () {
        String savedString = sharedPref.getString(MATRIX_FIELD, "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void getBlockGroupsFromPrefs () {
        String savedString1 = sharedPref.getString(BG1_FIELD, "");
        if (savedString1 != "") {
            StringTokenizer st1 = new StringTokenizer(savedString1, ",");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    firstBlockGroup.matrixBlock[i][j] = Integer.parseInt(st1.nextToken());
                }
            }
            if (st1.nextToken().equals("true")) firstBlockGroup.hidden = true;
            else firstBlockGroup.hidden = false;
        }

        String savedString2 = sharedPref.getString(BG2_FIELD, "");
        if(savedString2 != "") {
            StringTokenizer st2 = new StringTokenizer(savedString2, ",");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    secondBlockGroup.matrixBlock[i][j] = Integer.parseInt(st2.nextToken());
                }
            }
            if (st2.nextToken().equals("true")) secondBlockGroup.hidden = true;
            else secondBlockGroup.hidden = false;
        }

        String savedString3 = sharedPref.getString(BG3_FIELD, "");
        if(savedString3 != "") {
            StringTokenizer st3 = new StringTokenizer(savedString3, ",");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    thirdBlockGroup.matrixBlock[i][j] = Integer.parseInt(st3.nextToken());
                }
            }
            if (st3.nextToken().equals("true")) thirdBlockGroup.hidden = true;
            else thirdBlockGroup.hidden = false;
        }
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

        //Draws the blue squares on the game board
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    blockDraw.setX(row * cellWidth);
                    blockDraw.setY(col * cellWidth + HEADER);
                    blockDraw.drawFill(canvas, paint);
                }
            }

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                //Displays the actual score
                scoreView.setText("Score : " + score);
                highScoreView.setText("Record : " + highScore);

            }
        });


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

        if (selectedBlockGroup != null && checkGameOver(selectedBlockGroup)) gameOver();
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
                    //Create new groups if the three blocks are placed
                    if(firstBlockGroup.hidden && secondBlockGroup.hidden && thirdBlockGroup.hidden) createNewGroups();
                    saveState();
                }

            }
            return false;
        }
        return false;
    }

    private boolean checkGameOver (BlockGroup bg){
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[0].length; cols++) {
                int [] ind = {rows, cols};
                if (canPlaceGroupBlock(ind, bg.matrixBlock)) return false;
            }
        }
        return true;
    }

    private void gameOver () {
        if(score > highScore){
            editor.putInt(HIGH_SCORE_FIELD, score);
            editor.apply();
        }
        AlertDialog.Builder gameOverPopUp = new AlertDialog.Builder(activity);
        gameOverPopUp.setTitle("Game Over !");
        gameOverPopUp.setMessage("Cliquez ci-dessous pour recommencer une partie.");
        gameOverPopUp.setPositiveButton("Recommencer une partie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBoard();
            }
        });
        gameOverPopUp.show();
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
                if (matrixBlock[row][col] == 1 && (matrixRow >= matrix.length || matrixCol >= matrix[0].length
                    || matrixRow < 0 || matrixCol < 0)) {
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

    private boolean checkSquare(int square){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[((int) (square/3)) * 3 + i][((int)(square%3)) * 3 + j] != 1) {
                    return false;
                }
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
        for (int i = 0; i < 9; i++){
            if(checkSquare(i)){
                for (int x = 0; x < 3; x++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[((int) (i/3)) * 3 + x][((int)(i%3)) * 3 + j] = 0;
                    }
                }
                score += matrix.length;
            }
        }
    }

    private void resetBoard () {
        for (int i = 0; i< matrix.length; i++){
            for (int j = 0; j< matrix[0].length; j++){
                matrix[i][j] = 0;
            }
        }
        score = 0;
        createNewGroups();
        selectedBlockGroup = null;
        highScore = sharedPref.getInt(HIGH_SCORE_FIELD, highScore);
        saveState();
    }
}
