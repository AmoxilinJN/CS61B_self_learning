import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DistributionSortsTest {

    @Test
    public void testBasic() {
        // TODO: test it out!
        int[] temp = new int[]{2,3,5,1,8,7,6,9,4,5,6,3,2,1,0};
        DistributionSorts.countingSort(temp);
        System.out.println(temp);
    }
    
}

