package practice;

public class LongestString {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty())
            return 0;
        String strArr[] = s.split("");
        String longestSubstring = "";
        boolean allCorrect = true;
        StringBuilder result = null;
        for(int i=0;i<s.length()-1;i++){
            String valueToCheck = strArr[i];
            result = new StringBuilder();
            result.append(valueToCheck);
            for(int j=i+1;j<s.length();j++){
                if(!valueToCheck.equals(strArr[j]) && result.indexOf(strArr[j]) == -1){
                    result.append(strArr[j]);
                    if(j==s.length()-1){
                        if(longestSubstring.length() < result.length())
                            longestSubstring = result.toString();
                    }
                }
                else {
                    allCorrect = false;
                    if(longestSubstring.length() < result.length())
                    longestSubstring = result.toString();
                    break;
                }
            }

        }
        if(allCorrect)
            longestSubstring = s;

        return longestSubstring.isEmpty()? 1: longestSubstring.length();
    }
}
