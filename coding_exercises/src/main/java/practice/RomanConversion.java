package practice;

public class RomanConversion {

    public static void main(String[] args) {

        for (int i =1 ; i< 3999; i++){
            String res = new RomanConversion().intToRoman(i);
            System.out.println(i+" is "+res);
        }

    }

    public String intToRoman(int num) {

        StringBuilder result = new StringBuilder();
        int input = num;


            while (input > 0) {

                if(input >= 1000) {
                    input = printResult(input,result,1000,"M");
//                    input = input % 1000;
//                    result.append("M");
                }

                else if (input >= 900) {
                    input = printResult(input,result,900,"CM");
//                    input = input % 900;
//                    result.append("CM");
                }
                else if(input >= 500){
                    input = printResult(input,result,500,"D");
//                    input = input % 500;
//                    result.append("D");
                }
                else if(input >= 400){
                    input = printResult(input,result,400,"CD");
//                    input = input % 400;
//                    result.append("CD");
                }
                else if(input >= 100){
                    input = printResult(input,result,100,"C");
//                    input = input % 100;
//                    result.append("C");

                }
                else if(input < 100){
                    if(input >= 90){
                        input = printResult(input,result,90,"XC");
//                        input = input % 90;
//                        result.append("XC");
                    }
                    else if(input >= 50){
                        input = printResult(input,result,50,"L");
//                        input = input % 50;
//                        result.append("L");
                    }
                    else if(input >= 40){
                        input = printResult(input,result,40,"XL");

//                        input = input % 40;
//                        result.append("XL");
//
                    }
                    else if(input >= 10){
                       input = printResult(input,result,10,"X");
//                        int check = input / 10;
//                        input = input % 10;
//                        while (check > 0){
//                            check--;
//                            result.append("X");
//                        }
                    }
                    else {
                        if(input == 9){
                            result.append("IX");
                            break;
                        }
                        else if(input == 4){
                            result.append("IV");
                            break;
                        }
                        else if(input == 1){
                            result.append("I");
                            break;
                        }
                        else if(input == 2){
                            result.append("II");
                            break;
                        }
                        else if(input == 3){
                            result.append("III");
                            break;
                        }
                        else {
                                input = input % 5;
                                result.append("V");
                        }

                    }
                }
            }





        return result.toString();
    }



    private int printResult(int input, StringBuilder result, int base, String romanValue) {
        int check = input / base;
        input = input % base;
        while (check > 0){
            check--;
            result.append(romanValue);
        }
        return input;
    }
}
