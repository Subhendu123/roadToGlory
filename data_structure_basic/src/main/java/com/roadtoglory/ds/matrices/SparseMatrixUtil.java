package com.roadtoglory.ds.matrices;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.matrices
   @Author Subhendu
   @Date 25-Sep-2024 07:45
*
*
*/
public class SparseMatrixUtil
{


    private static void allocateElement (int[][] assigneeArr, int colCntr, int rowValToAssign, int colValToAssign, int itemVal)
    {
        // add the row and col number
        assigneeArr[0][colCntr] = rowValToAssign;
        assigneeArr[1][colCntr] = colValToAssign;
        // add the value
        assigneeArr[2][colCntr] = itemVal;
    }

    public int[][] flatten (int[][] input, int noOfRows, int noOfCols)
    {
        int[][] arrRep = new int[3][noOfCols];
        //        int rowCnt = 1;
        int colCnt = 1;
        int nonZeroItems = 0;

        for (int row = 0; row < noOfRows; row++)
        {
            for (int col = 0; col < noOfCols; col++)
            {
                if (input[row][col] != 0)
                {
                    allocateElement(arrRep, colCnt, row + 1, col + 1, input[row][col]);
                    // increment col by 1
                    colCnt++;
                    // count the non zero items
                    nonZeroItems++;
                }
            }
        }
        allocateElement(arrRep, 0, noOfRows, noOfCols, nonZeroItems);

        return arrRep;

    }

    public int[][] addAndFlattenResult (int[][] input1, int[][] input2)
    {
        int[][] I1 = input1;
        int[][] I2 = input2;
        int I1Length = input1[0][3];
        int I2Length = input2[0][3];


        if (I2Length > I1Length)
        {
            I2Length = I1Length;
            I1Length = input2[0][3];
            I1 = input2;
            I2 = input1;
        }
        int[][] R = new int[3][I1Length];
        int colCtr = 0;
        int i = 1;
        int j = 1;

        while (i < I1Length && j <= I2Length)
        {
            {
                if (I1[0][i] < I2[0][j])
                {
                    // Rows are not matching and I1 is less than I2. Move I1 to Result and increment i (I1 counter)
                    allocateElement(R, colCtr, I1[0][i], I1[1][i], I1[2][i]);
                }
                else if (I1[0][i] > I2[0][j])
                {
                    // Rows are not matching and I1 is greater. Move I2 to Result and increment j (I2 Counter)
                    allocateElement(R, colCtr, I2[0][i], I2[1][i], I2[2][i]);
                }
                else
                {
                    if ((I1[1][i] == I2[1][j]))
                    {
                        int sum = I1[2][i] + I2[2][j];
                        allocateElement(R, colCtr, I1[0][i], I1[1][i], sum);
                    }
                    else
                    {
                        // cols are not matching, move I1 to the result
                        allocateElement(R, colCtr, I1[0][i], I1[1][i], I1[2][i]);
                    }
                }
            }
            i++;
            j++;
            colCtr++;
        }

        return R;

    }


}
