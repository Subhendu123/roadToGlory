package practice;

public class RomanToInt {

    public static void main(String[] args) {

        System.out.println("LVIII - "+new RomanToInt().romanToInt("LVIII"));
        System.out.println("III - "+new RomanToInt().romanToInt("III"));
        System.out.println("MCMXCIV - "+new RomanToInt().romanToInt("MCMXCIV"));
        System.out.println("MCMXCVI - "+new RomanToInt().romanToInt("MCMXCVI"));
        System.out.println("LIX - "+new RomanToInt().romanToInt("LIX"));
//        cm - 900
//                xc - 90


    }

    public int romanToInt(String s) {

        int result = 0;
        StringBuilder sb = new StringBuilder(s);

        if(sb.indexOf("IV") > -1){
            int start = s.indexOf("IV");
            result = result + 4;
            sb.delete(start, start+2);
        }
        if(sb.indexOf("VI") > -1){
            int start = s.indexOf("VI");
            result = result + 6;
            sb.delete(start, start+2);
        }
        if(sb.indexOf("VII") > -1){
            int start = s.indexOf("VII");
            result = result + 7;
            sb.delete(start, start+3);
        }
        if(sb.indexOf("VIII") > -1){
            int start = s.indexOf("VIII");
            result = result + 8;
            sb.delete(start, start+4);
        }
        if(sb.indexOf("IX") > -1){
            int start = s.indexOf("IX");
            result = result + 9;
            sb.delete(start, start+2);
        }
        if(sb.indexOf("XL") > -1){
            int start = s.indexOf("XL");
            result = result + 40;
            sb.delete(start, start+2);
        }
        if(sb.indexOf("XC") > -1){
            int start = s.indexOf("XC");
            result = result + 90;
            sb.delete(start, start+2);
        }
        if(sb.indexOf("CD") > -1){
            int start = s.indexOf("CD");
            result = result + 400;
            sb.delete(start, start+2);
        }
        if(sb.indexOf("CM") > -1){
            int start = s.indexOf("CM");
            result = result + 900;
            sb.delete(start, start+2);
        }
//
//        System.out.println("Result till now "+result);
//        System.out.println("after deleteing : "+sb);
//        System.out.println();

         for(int i=0;i<sb.length();i++){
             if(sb.charAt(i) == 'I'){
                 result = result + 1;
             }
             else if(sb.charAt(i) == 'V'){
                 result = result + 5;
             }
             else if(sb.charAt(i) == 'X'){
                 result = result + 10;
             }
             else if(sb.charAt(i) == 'L'){
                 result = result + 50;
             }
             else if(sb.charAt(i) == 'C'){
                 result = result + 100;
             }
             else if(sb.charAt(i) == 'D'){
                 result = result + 500;
             }
             else{
                 result = result + 1000;
             }
         }
         return result;

    }
}
