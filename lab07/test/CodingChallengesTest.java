import org.junit.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class CodingChallengesTest {

    @Test
    public void testMissingNumber() {
	// TODO
        int[] a1=new int[]{0,1,2,3};
        int[] a2=new int[]{2,1,3};
        int[] a3=new int[]{0};
        assertWithMessage("test a1").that(CodingChallenges.missingNumber(a1)).isEqualTo(4);
        assertWithMessage("test a2").that(CodingChallenges.missingNumber(a2)).isEqualTo(0);
        assertWithMessage("test a3").that(CodingChallenges.missingNumber(a3)).isEqualTo(1);
    }

    @Test
    public void testIsPermutation() {
	// TODO
        String s1="test_first";
        String s2="first_test";
        String s3="test_second";
        assertWithMessage("test s1 s2").that(CodingChallenges.isPermutation(s1,s2)).isTrue();
        assertWithMessage("test s1 s3").that(CodingChallenges.isPermutation(s1,s3)).isFalse();
    }
}
