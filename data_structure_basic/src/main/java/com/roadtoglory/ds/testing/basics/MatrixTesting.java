/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.basics;

import com.roadtoglory.ds.matrices.LowerTriangularMatrix;


/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.testing
   @Author Subhendu
   @Date 24-Sep-2024 07:53
*
*
*/
public class MatrixTesting {


    public static void main(String[] args) {
        int size = 5;
        LowerTriangularMatrix lowerTriangularMatrix = new LowerTriangularMatrix(5);
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= i; j++) {
                lowerTriangularMatrix.set(i, j, 10 * i + j * 5);
            }
        }
        //        lowerTriangularMatrix.set(1, 1, 10);
        //        lowerTriangularMatrix.set(2, 1, 20);
        //        lowerTriangularMatrix.set(3, 1, 30);
        //        lowerTriangularMatrix.set(3, 2, 40);
        //        lowerTriangularMatrix.set(3, 3, 50);
        //        lowerTriangularMatrix.set(3, 4, 323);
        //        lowerTriangularMatrix.set(4, 1, 55);
        //        lowerTriangularMatrix.set(4, 2, 60);
        //        lowerTriangularMatrix.set(4, 3, 65);
        //        lowerTriangularMatrix.set(4, 4, 70);

        int[] res = lowerTriangularMatrix.rowMajorRepresentation();
        System.out.println();
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ,");
        }
    }


}
