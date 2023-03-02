package practice;

public class MatchingRegx {

    public boolean isMatch(String s, String p) {

        char star = '*'; // Matches zero or more of the preceding element.
        char dot = '.'; // Matches any single character.
        boolean isMatchVar = true;

//        if(s.equalsIgnoreCase("aab") && p.equalsIgnoreCase("c*a*b"))
//            return true;


        // length check

        int regxLength = p.length();
        int inputLength = s.length();
        System.out.println("regxLength " +regxLength);
        System.out.println("inpt length "+inputLength);
        if(inputLength == regxLength){
            // same length
            for(int i = 0; i< regxLength; i++){
                if(p.charAt(i) == star){
                    //checking star
                    char check = p.charAt(regxLength -2);
                    String sub = s.substring(regxLength -1);
                    System.out.println(sub);
                    for(char c : sub.toCharArray()){
                        if(check != c && check != dot){
                            System.out.println(c);
                            System.out.println(check);
                            isMatchVar = false;
                            break;
                        }
                    }
                    if(!isMatchVar)
                        break;
                }
                else if(p.charAt(i) == dot){
                    //isMatchVar = true;
                }
                else{
                   if(s.charAt(i) != p.charAt(i)){
                       isMatchVar = false;
                       break;
                   }
                }

            }
//            return isMatchVar;
        }
        else if(inputLength > regxLength){
            // last character check
            if(p.charAt(regxLength -1) != star && p.charAt(regxLength -1) != dot){
                return false;
            }
            else if(p.charAt(regxLength -1) == dot){
                System.out.println("Inside the last else if bl 2");
                if(inputLength != regxLength + 1){
                    return false;
                }
                else {
                    System.out.println("Inside the last else if bl 2 - true");
                    for(int i = 0; i< regxLength; i++){
                        if(p.charAt(i) == star){
                            //checking star
                            char check = p.charAt(regxLength -2);
                            String sub = s.substring(regxLength -1);
                            for(char c : sub.toCharArray()){
                                if(check != c && check != dot){
                                    System.out.println("Check "+check);
                                    isMatchVar = false;
                                    break;
                                }
                            }
                            if(!isMatchVar)
                                break;
                        }
                        else if(p.charAt(i) == dot){
                            //isMatchVar = true;
                        }
                        else{
                            if(s.charAt(i) != p.charAt(i)){
                                isMatchVar = false;
                                break;
                            }
                        }

                    }
                    return isMatchVar;
                }
            }
            else if(p.charAt(regxLength -1) == star){
                System.out.println("Inside the last else if");
                //checking star
                char check = p.charAt(regxLength -2);
                String sub = s.substring(regxLength -1);
                for(char c : sub.toCharArray()){
                    if(check != c && check != dot){
                        isMatchVar = false;
                        break;
                    }
                }
//                return isMatchVar;
            }
        }
        else {
            if(p.indexOf(star) == -1){
                return false;
            }
            else {
                // need to implement the C*A*
                return true;
            }
        }
        return isMatchVar;

    }

    public static void main(String[] args) {
        MatchingRegx matchingRegx = new MatchingRegx();
        String s ="mississippi";
        String p = "mis*is*ip*.";
//        String s ="aab";
//        String p = "c*a*b";
        System.out.println(matchingRegx.isMatch(s,p));
        System.out.println(matchingRegx.isMatchMiss(s,p));
    }

    private boolean isMatchMiss(String s, String p) {

        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == p.charAt(i))
                continue;
            else if(p.charAt(i) == '*'){

            }
        }
        return false;
    }
}
