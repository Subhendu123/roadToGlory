package practice;

import java.time.Clock;
import java.util.ArrayList;

public class LargestPalindrome {

    public static void main(String[] args) {
        long start = Clock.systemDefaultZone().millis();
        String str = "twbiqwtafgjbtolwprpdnymaatlbuacrmzzwhkpvuwdtyfjsbsqxrlxxtqkjlpkvpxmlajdmnyepsmczmmfdtjfbyybotpoebilayqzvqztqgddpcgpelwmriwmoeeilpetbxoyktizwcqeeivzgxacuotnlzutdowiudwuqnghjgoeyojikjhlmcsrctvnahnoapmkcrqmwixpbtirkasbyajenknuccojooxfwdeflmxoueasvuovcayisflogtpxtbvcxfmydjupwihnxlpuxxcclbhvutvvshcaikuedhyuksbwwjsnssizoedjkbybwndxpkwcdxaexagazztuiuxphxcedqstahmevkwlayktrubjypzpaiwexkwbxcrqhkpqevhxbyipkmlqmmmogrnexsztsbkvebjgybrolttvnidnntpgvsawgaobycfaaviljsvyuaanguhohsepbthgjyqkicyaxkytshqmtxhilcjxdpcbmvnpippdrpggyohwyswuydyrhczlxyyzregpvxyfwpzvmjuukswcgpenygmnfwdlryobeginxwqjhxtmbpnccwdaylhvtkgjpeyydkxcqarkwvrmwbxeetmhyoudfuuwxcviabkqyhrvxbjmqcqgjjepmalyppymatylhdrazxytixtwwqqqlrcusxyxzymrnryyernrxbgrphsioxrxhmxwzsytmhnosnrpwtphaunprdtbpwapgjjqcnykgspjsxslxztfsuflijbeebwyyowjzpsbjcdabxmxhtweppffglvhfloprfavduzbgkw";
        String s = longestPalindrome(str);
        System.out.println(s);
        long end = Clock.systemDefaultZone().millis();
        System.out.println("Time Elapsed: "+ (end - start));
    }
    public static String longestPalindromeRecursion(String s) {

        return null;
    }

    public static String longestPalindrome(String s) {

        if(s.length() == 1)
            return s;
        if(new StringBuilder(s).reverse().toString().equalsIgnoreCase(s))
            return s;
//        if(s.charAt(0) != s.charAt(s.length()-1))
//            return s.substring(0,1);

        String result = calculatePalindrome(s , 0, s.length()-1);
        return result;
    }

    public static String calculatePalindrome(String s, int start, int end){
        String longestPalindrome = "";
        for (int i = start; i < end; i++)
        {
            for(int j=i+1;j<end+1;j++){

                if(s.charAt(i) != s.charAt(j))
                    continue;

                String inputString = s.substring(i,j+1);
                StringBuilder input = new StringBuilder(inputString);
                if(input.reverse().toString().equalsIgnoreCase(inputString)){
                    if(inputString.length() > longestPalindrome.length())
                        longestPalindrome = inputString;
                }
            }
        }
        if(longestPalindrome.isEmpty()){
            return s.substring(0,1);
        }
        return longestPalindrome;
    }
}
