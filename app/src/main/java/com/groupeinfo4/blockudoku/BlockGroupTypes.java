package com.groupeinfo4.blockudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockGroupTypes {

    private List<BlockGroup> listBlock;
    private Random random;

    public BlockGroupTypes () {
        random = new Random();
        listBlock = new ArrayList<>();
        listBlock.add(getGroupBlockOne());
        listBlock.add(getGroupBlockTowHorizontal());
        listBlock.add(getGroupBlockTowVertical());
        listBlock.add(getGroupBlockThreeHorizontal());
        listBlock.add(getGroupBlockThreeVertical());
        listBlock.add(getGroupBlockThreeC());
        listBlock.add(getGroupBlockFourTriangle());
        listBlock.add(getGroupBlockFourZ());
        listBlock.add(getGroupBlockFourL());
        listBlock.add(getGroupBlockFourVertical());
        listBlock.add(getGroupBlockFourHorizontal());
        listBlock.add(getGroupBlockFourSqure());
        listBlock.add(getGroupBlockFiveL());
        listBlock.add(getGroupBlockFiveHorizontal());
        listBlock.add(getGroupBlockNine());
    }

    public BlockGroup getRandomBlock () {
        return listBlock.get(random.nextInt(listBlock.size()));
    }

    public BlockGroup getGroupBlockOne() {
        int[][] matrix = new int[1][1];
        matrix[0][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockTowHorizontal() {
        int[][] matrix = new int[1][2];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockTowVertical() {
        int[][] matrix = new int[2][1];
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockThreeHorizontal() {
        int[][] matrix = new int[1][3];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockThreeVertical() {
        int[][] matrix = new int[3][1];
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[2][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockThreeC() {
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourZ() {
        int[][] matrix = new int[2][3];
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourTriangle() {
        int[][] matrix = new int[2][3];
        matrix[0][1] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourL() {
        int[][] matrix = new int[2][3];
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourHorizontal() {
        int[][] matrix = new int[1][4];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourVertical() {
        int[][] matrix = new int[4][1];
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[2][0] = 1;
        matrix[3][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourSqure() {
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFiveL() {
        int[][] matrix = new int[3][3];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][2] = 1;
        matrix[2][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFiveHorizontal() {
        int[][] matrix = new int[1][5];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 1;
        matrix[0][4] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockNine() {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 1;
            }
        }
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

}

