package com.roadtoglory.ds.matrices;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.matrices
   @Author Subhendu
   @Date 24-Sep-2024 07:40
*
*
*/
public class LowerTriangularMatrix
{


    private static int[][] matrix;

    public LowerTriangularMatrix (int size)
    {
        if (size > 0)
        {
            matrix = new int[size][size];
        }
    }

    public void set (int row, int col, int element)
    {
        if (row >= col)
        {
            matrix[row - 1][col - 1] = element;
            System.out.println("The matrix [row,col] is [" + (row - 1) + "," + (col - 1) + "] and the matrix element is " + matrix[row - 1][col - 1]);
        }
        else
        {
            System.out.println("The Element Could not be set");
        }
    }

    public int get (int row, int col)
    {
        if (row >= col)
        {
            return matrix[row - 1][col - 1];
        }
        return 0;
    }

    public int[] rowMajorRepresentation ()
    {
        System.out.println("The matrix length is " + matrix.length);
        int[] rowMajor = new int[matrix.length * matrix.length];
        int index = 0;

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                if (matrix[i][j] != 0)
                {
                    rowMajor[index] = matrix[i][j];
                    index++;
                }
            }
        }
        return rowMajor;
    }


}
