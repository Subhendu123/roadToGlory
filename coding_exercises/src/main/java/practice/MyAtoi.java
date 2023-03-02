package practice;

import java.util.Locale;

public class MyAtoi {

    public static void main(String[] args) {
        String s = "   +0  413";
        int a = myAtoi(s);
        System.out.println("The result is "+a);
    }

    public static int myAtoi(String s) {
        char[] chars = s.toCharArray();
        boolean isIntegerRead = false;
        int PosNegCount = 0;
        StringBuilder sb = new StringBuilder();

        for(char c : chars){
            if(c == '+' || c == '-'){
                if(isIntegerRead){
                    break;
                }
                PosNegCount++;
                sb.append(c);
                if(PosNegCount > 1)
                    break;
            }
//           else if(( (int) c >= 65 && (int) c <= 90 ) ||
//                   ( (int) c >= 97 && (int) c <= 122 )){
//                break;
//            }
            else if((int) c >= 48 && (int) c <= 57 ){
                isIntegerRead = true;
                sb.append(c);
            }
            else if(((int) c >= 28 && (int) c <= 32 ) || (int) c == 11){
                if(isIntegerRead || PosNegCount > 0)
                    break;
                else
                    continue;
            }
            else {
                System.out.println("Char value "+c);
                break;
            }
        }
        if(PosNegCount > 1){
            return 0;
        }
        else if(isIntegerRead){
            try {
                return Integer.parseInt(sb.toString());
            }
            catch (NumberFormatException ne){
                System.out.println("NE Exception! ");
                if(sb.charAt(0) == '-'){
                    return -2147483648;
                }
                return 2147483647;
            }
        }
        else
            return 0;
    }
}
