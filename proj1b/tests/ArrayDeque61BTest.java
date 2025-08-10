import deque.ArrayDeque61B;

import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    public void testAddGet() {
        ArrayDeque61B<Integer> temp = new ArrayDeque61B<>();
        temp.addFirst(1);
        temp.addFirst(0);
        temp.addLast(2);
        temp.addLast(3);
        for (int i = 0; i < 4; i++){
            assertThat(temp.get(i)).isEqualTo(i);
        }
    }

    @Test
    public void testIsEmptySize(){
        ArrayDeque61B<Integer> temp = new ArrayDeque61B<>();
        assertThat(temp.isEmpty()).isTrue();
        assertThat(temp.size()).isEqualTo(0);
        temp.addFirst(1);
        temp.addFirst(0);
        temp.addLast(2);
        assertThat(temp.isEmpty()).isFalse();
        assertThat(temp.size()).isEqualTo(3);
    }

    @Test
    public void testToList(){
        ArrayDeque61B<Integer> temp = new ArrayDeque61B<>();
        temp.addLast(0);
        temp.addLast(1);
        temp.addLast(2);
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(0,1,2)));
    }

    @Test
    public void testRemove(){
        ArrayDeque61B<Integer> temp = new ArrayDeque61B<>();
        for (int i = 5; i >= 0; i--){
            temp.addFirst(i);
        }
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(0,1,2,3,4,5)));
        temp.removeFirst();
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(1,2,3,4,5)));
        temp.removeLast();
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(1,2,3,4)));
    }

    @Test
    public void testResize(){
        ArrayDeque61B<Integer> temp = new ArrayDeque61B<>();
        for (int i = 7; i >= 0; i--){
            temp.addFirst(i);
        }
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)));
        temp.addFirst(8);
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(8,0,1,2,3,4,5,6,7)));
        temp.addLast(8);
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(8,0,1,2,3,4,5,6,7,8)));
    }

    @Test
    public void testResizeSmall(){
        ArrayDeque61B<Integer> temp = new ArrayDeque61B<>();
        for (int i = 7; i >= 0; i--){
            temp.addFirst(i);
        }
        for (int i = 7; i > 4; i--){
            temp.removeLast();
        }
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(0,1,2,3,4)));
        temp.removeLast();
        assertThat(temp.toList()).isEqualTo(new ArrayList<>(Arrays.asList(0,1,2,3)));
    }

    @Test
    public void testToStringEqual() {
        ArrayDeque61B<Integer> temp = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> temp2 = new ArrayDeque61B<>();
        temp.addLast(0);
        temp.addLast(1);
        temp.addLast(2);
        for (int x : temp) {
            temp2.addLast(x);
        }
        assertThat(temp.equals(temp2)).isTrue();
        System.out.println(temp);
    }
}
