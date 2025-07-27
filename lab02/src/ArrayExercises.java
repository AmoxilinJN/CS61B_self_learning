import java.util.ArrayList;
import java.util.List;

public class ArrayExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // TODO: Fill in this function.
        int[] array=new int[]{1,2,3,4,5,6};
        return array;
    }

    /** Returns the positive difference between the maximum element and minimum element of the given array.
     *  Assumes array is nonempty. */
    public static int findMinMax(int[] array) {
        // TODO: Fill in this function.
        int min=array[0],max=array[0],result;
        for(int count=1;count<array.length;count++){
            if(min>array[count]){min=array[count];}
            if(max<array[count]){max=array[count];}
        }
        result=max-min;
        return result;
    }

}
