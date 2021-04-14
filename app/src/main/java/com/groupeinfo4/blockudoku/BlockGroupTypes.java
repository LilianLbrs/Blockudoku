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
        listBlock.add(getGroupBlockOne1());
        listBlock.add(getGroupBlockOne2());
        listBlock.add(getGroupBlockOne3());
        listBlock.add(getGroupBlockOne4());
        listBlock.add(getGroupBlockTowHorizontal1());
        listBlock.add(getGroupBlockTowHorizontal2());
        listBlock.add(getGroupBlockTowVertical1());
        listBlock.add(getGroupBlockTowVertical2());
        listBlock.add(getGroupBlockThreeHorizontal());
        listBlock.add(getGroupBlockThreeVertical());
        listBlock.add(getGroupBlockThreeC());
        listBlock.add(getGroupBlockFourTriangle());
        listBlock.add(getGroupBlockFourTriangle2());
        listBlock.add(getGroupBlockFourTriangle3());
        listBlock.add(getGroupBlockFourTriangle4());
        listBlock.add(getGroupBlockFourZ());
        listBlock.add(getGroupBlockFourL());
        listBlock.add(getGroupBlockFourSqure());
        listBlock.add(getGroupBlockFourSqure2());
        listBlock.add(getGroupBlockFourSqure3());
        listBlock.add(getGroupBlockFourSqure4());
        listBlock.add(getGroupBlockFiveL());
        listBlock.add(getGroupBlockFiveL2());
        listBlock.add(getGroupBlockNine());

    }

    public BlockGroup getRandomBlock () {
        return listBlock.get(random.nextInt(listBlock.size()));
    }

    public BlockGroup getGroupBlockOne1() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockOne2() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockOne3() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[2][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockOne4() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[2][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockTowHorizontal1() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockTowHorizontal2() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[2][1] = 1;
        matrix[2][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockTowVertical1() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockTowVertical2() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[1][2] = 1;
        matrix[2][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockThreeHorizontal() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockThreeVertical() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[2][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockThreeC() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourZ() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourTriangle() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][1] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourTriangle2() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[2][1] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourTriangle3() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[2][1] = 1;
        matrix[0][1] = 1;
        matrix[1][1] = 1;
        matrix[2][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourTriangle4() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][1] = 1;
        matrix[0][1] = 1;
        matrix[1][1] = 1;
        matrix[2][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourL() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourSqure() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourSqure2() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourSqure3() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[2][0] = 1;
        matrix[2][1] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFourSqure4() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFiveL() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][2] = 1;
        matrix[2][2] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }

    public BlockGroup getGroupBlockFiveL2() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        matrix[2][0] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 1;
        matrix[1][2] = 1;
        matrix[1][0] = 1;
        return new BlockGroup(0, 0, 50, 60, matrix);
    }


    public BlockGroup getGroupBlockNine() {
        int[][] matrix = {{0,0,0}, {0,0,0}, {0,0,0}};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 1;
            }
        }
        return new BlockGroup(0, 0, 50, 60, matrix);
    }



}

