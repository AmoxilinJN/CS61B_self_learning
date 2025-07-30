import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class CodingChallenges {

    /**
     * Return the missing number from an array of length N containing all the
     * values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        // TODO
        int result=0;
        for (int i=0;i<= values.length;i++){
            int j=0;
            while (values[j]!=i){
                if(j== values.length-1){
                    result=i;
                    return result;
                }
                j++;
            }
        }
        return 0;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        // TODO
        int[] count=new int[128];
        if(s1.length()!=s2.length()){
            return false;
        }
        for(int i=0;i<s1.length();i++){
            char a1=s1.charAt(i);
            count[a1]++;
        }
        for (int i=0;i<s2.length();i++){
            char a2=s2.charAt(i);
            count[a2]--;
            if(count[a2]<0){
                return false;
            }
        }
        return true;
    }
}
