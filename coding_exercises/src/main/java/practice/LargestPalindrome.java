package practice;

import java.time.Clock;
import java.util.ArrayList;

public class LargestPalindrome {

    public static void main(String[] args) {
        long start = Clock.systemDefaultZone().millis();
        String str = "ajgiljtperkvubjmdsefcylksrxtftqrehoitdgdtttswwttmfuvwgwrruuqmxttzsbmuhgfaoueumvbhajqsaxkkihjwevzzedizmrsmpxqavyryklbotwzngxscvyuqjkkaotitddlhhnutmotupwuwyltebtsdfssbwayuxrbgihmtphshdslktvsjadaykyjivbzhwujcdvzdxxfiixnzrmusqvwujjmxhbqbdpauacnzojnzxxgrkmupadfcsujkcwajsgintahwgbjnvjqubcxajdyyapposrkpqtpqfjcvbhlmwfutgognqxgaukpmdyaxghgoqkqnigcllachmwzrazwhpppmsodvxilrccfqgpkmdqhoorxpyjsrtbeeidsinpeyxxpsjnymxkouskyhenzgieybwkgzrhhrzgkwbyeigznehyksuokxmynjspxxyepnisdieebtrsjypxroohqdmkpgqfccrlixvdosmppphwzarzwmhcallcginqkqoghgxaydmpkuagxqngogtufwmlhbvcjfqptqpkrsoppayydjaxcbuqjvnjbgwhatnigsjawckjuscfdapumkrgxxznjozncauapdbqbhxmjjuwvqsumrznxiifxxdzvdcjuwhzbvijykyadajsvtklsdhshptmhigbrxuyawbssfdstbetlywuwputomtunhhlddtitoakkjquyvcsxgnzwtoblkyryvaqxpmsrmzidezzvewjhikkxasqjahbvmueuoafghumbszttxmquurrwgwvufmttwwstttdgdtioherqtftxrsklycfesdmjbuvkreptjligja";
        String s = longestPalindrome(str);
        System.out.println(s);
        long end = Clock.systemDefaultZone().millis();
        System.out.println("Time Elapsed: "+ (end - start));
    }

    public static String longestPalindrome(String s) {

        if(s.length() == 1)
            return s;

        String longestPalindrome = "";
        for (int i = 0; i < s.length() - 1; i++)
        {
            for(int j=i+1;j<s.length();j++){

                String inputString = s.substring(i,j+1);
//                char[] reversed = null;
//                char[] inputCharArr = inputString.toCharArray();
//                int revIndex = 0;
//                for(int index = inputString.length() -1 ; index > -1; index--){
//                    if(reversed == null)
//                        reversed = new char[inputString.length()];
//                    reversed[revIndex] = inputCharArr[index];
//                    revIndex++;
//                }
//                if( reversed != null && String.valueOf(reversed).equalsIgnoreCase(inputString)){
//                    if(inputString.length() > longestPalindrome.length())
//                        longestPalindrome = inputString;
//                }

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
//        for(String st : result){
//            if(st.length() > longestPalindrome.length())
//                longestPalindrome = st;
//        }
        return longestPalindrome;

    }
}
