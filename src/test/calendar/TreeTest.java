package test.calendar;

import calendar.Tree;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    public void testAddAndContains() {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(2);

        assertTrue(tree.contains(3));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(4));
        assertFalse(tree.contains(5));
    }

    @Test
    public void testAddToLeftBranch() {
        Tree<Integer> tree = new Tree<>();
        tree.add(10);
        tree.add(15);
        tree.add(5);

        Iterator<Integer> it = tree.iterator();
        int left = it.next();
        assertEquals(left, 5);
    }

    @Test
    public void testAddToRightBranch() {
        Tree<Integer> tree = new Tree<>();
        tree.add(10);
        tree.add(5);
        tree.add(15);

        Iterator<Integer> it = tree.iterator();

        it.next();
        it.next();
        int right = it.next();
        assertEquals(right, 15);
    }

    @Test
    public void testErase() {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(1);
        tree.add(4);

        tree.erase(3);
        assertFalse(tree.contains(3));
    }

    @Test
    public void testEraseRoot() {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(1);
        tree.add(4);

        tree.erase(3);
        assertFalse(tree.contains(3));
    }

    @Test
    public void testEraseInnerElement() {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(1);
        tree.add(4);

        tree.erase(1);
        assertFalse(tree.contains(1));
    }

    @Test
    public void testIterator() {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(2);

        Iterator<Integer> it = tree.iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals( Integer.valueOf(2), it.next());
    }
}