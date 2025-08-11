import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class MinHeapTest {

    @Test
    public void test1() {
        MinHeap<Character> h = new MinHeap<>();
        h.insert('f');
        h.insert('h');
        h.insert('d');
        h.insert('b');
        h.insert('c');
        System.out.println(h);
        assertThat(h.removeMin()).isEqualTo('b');
        h.removeMin();
        assertThat(h.findMin()).isEqualTo('d');
        System.out.println(h);
    }
}
