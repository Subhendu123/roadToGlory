package practice;

import java.util.Arrays;

public class LongestCommon {

    public static void main(String[] args) {
        String[] strs = {"aaa",""};
        System.out.println(new LongestCommon().longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        System.out.println("sss "+strs[0].length());

        if(strs.length == 1)
            return strs[0];

        if(Arrays.stream(strs).filter( s -> s.isEmpty() || s.isBlank()).findAny().isPresent())
            return "";
        StringBuilder result = new StringBuilder();
        int arrIndex = 1;
        int strPtr = 0;

        char c = strs[0].charAt(0);
        boolean checkTillNow = true;

        while (true){

            if(arrIndex <= strs.length - 1 && strPtr <= strs[arrIndex].length() - 1 && c == strs[arrIndex].charAt(strPtr)){
                if(arrIndex + 1 < strs.length){
                    arrIndex++;
//                    checkTillNow = true;
                }
                else{
                    // reached the end of array of strings - this is the last
                    result.append(c);
                    // reset the pointers so that it starts checking the next character from each of the array strings
                    strPtr++;
                    arrIndex = 1;
                    if(strPtr < strs[0].length())
                        c = strs[0].charAt(strPtr);
                    else
                        break; // end of the character in the string of the first element
//                    checkTillNow = false;
                }
                continue;
            }
            else {
                break;
            }

        }
        return result.toString();

    }
}
