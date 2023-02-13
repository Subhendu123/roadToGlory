package practice;

public class ZigZagConversion {

    private static boolean END_OF_STR = false;

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        String output = convert(s, 1);
        System.out.println(output); // PINALSIGYAHRPI
        System.out.println("test");
        System.out.println(" output matching PINALSIGYAHRPI = " + output+ " equals check "+"PINALSIGYAHRPI".equalsIgnoreCase(output));
        System.out.println("LEngth check "+ output.length());

    }

    public static String convert(String s, int numRows) {
        if(numRows == s.length()){
            return s;
        }
        else{
            StringBuilder[] output = new StringBuilder[numRows];
            int i = 0;

            while(!END_OF_STR){
                // first function - ++ one
                i = forwardCases(s,i, numRows,output);
                // second function - -- one
                i = backwardTraverse(s,i, numRows, output);
            }

            // Printing the output
            StringBuilder finalOutput = new StringBuilder();
            for(StringBuilder res : output){
                System.out.println("Output "+res);
                finalOutput = finalOutput.append(res);
            }
            return finalOutput.toString();
        }




    }

    private static int backwardTraverse(String s, int i, int numRows, StringBuilder[] output) {
        for(int resIndex = numRows-2; resIndex >= 0; resIndex--, i++){
            if(i < s.length()) {
                if (output[resIndex] == null)
                    output[resIndex] = new StringBuilder();
                output[resIndex].append(s.charAt(i));
            }
            else {
                END_OF_STR = true;
                break;
            }
        }
        return i;
    }

    private static int forwardCases(String s, int i, int numRows, StringBuilder[] output) {
        int resIndex = 0;
        if(i > 0)
            resIndex = 1;
        for( ; resIndex < numRows; resIndex++, i++){
            if( i < s.length()) {
                if (output[resIndex] == null)
                    output[resIndex] = new StringBuilder();
                output[resIndex].append(s.charAt(i));
            }
            else {
                END_OF_STR = true;
                break;
            }
        }
        return i;
    }
}
