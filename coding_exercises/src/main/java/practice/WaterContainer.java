package practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class WaterContainer {

    public static void main(String[] args) throws IOException {

        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(new WaterContainer().maxArea(height));
    }

    public int maxArea(int[] height) {

        int result = 0;

        for(int i=0;i<height.length;i++){
            int element = height[i];
//            for(int j=height.length -1; j > -1; j--){
            int j=height.length-1;

                if(i != j){

                    int tempRes = height[j] < element? height[j] : element;
                    tempRes = tempRes * (j - i);
                    tempRes = tempRes < 0 ? (-1) * tempRes : tempRes;
                    if(tempRes > result)
                        result = tempRes;

//                    while (height[i] <= tempRes && i < j) i++;
//                    while (height[j] <= tempRes && i < j) j--;
                }
        }

        return result;


    }
}
