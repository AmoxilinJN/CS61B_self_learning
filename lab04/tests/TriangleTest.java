import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;
public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    // FIXME: Add additional tests for Triangle.java here that pass on a
    //  correct Triangle implementation and fail on buggy Triangle implementations.

    @Test
    public void test1() {
        // TODO: stub for first test
        Triangle t = getNewTriangle();
        // remember that you'll have to call on Triangle methods like
        // t.functionName(arguments), where t is a Triangle object
        assertWithMessage("Test sidesFormTriangle").that(t.sidesFormTriangle(0,1,1)).isFalse();
        assertWithMessage("Test sidesFormTriangle").that(t.sidesFormTriangle(-1,3,2)).isFalse();
        assertWithMessage("Test sidesFormTriangle").that(t.sidesFormTriangle(3,4,5)).isTrue();
        assertWithMessage("Test pointsFormTriangle").that(t.pointsFormTriangle(0,0,1,1,2,0)).isTrue();
        assertWithMessage("Test triangleType").that(t.triangleType(3,3,3)).isEqualTo("Equilateral");
        assertWithMessage("Test triangleType").that(t.triangleType(3,3,5)).isEqualTo("Isosceles");
        assertWithMessage("Test triangleType").that(t.triangleType(3,4,5)).isEqualTo("Scalene");
        assertWithMessage("Test squaredHypotenuse").that(t.squaredHypotenuse(3,4)).isEqualTo(5);
    }


}
