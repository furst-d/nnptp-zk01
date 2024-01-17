package calendar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

public class Tree<T extends Comparable<? super T>> implements Iterable<T> {
    private static class Node<E> {
        public Node<E> left;
        public Node<E> right;
        public E data;

        public Node() {}

        public Node(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" + "left=" + left + ", right=" + right + ", data=" + data + '}';
        }

    }

    private Node<T> root;

    public Tree() {}

    public T get(T data) {
        Node<T> traveller = root;

        while (traveller != null) {
            if (data.compareTo(traveller.data) == 0) {
                return traveller.data;
            }

            if (data.compareTo(traveller.data) < 0) {
                traveller = traveller.left;
            } else {
                traveller = traveller.right;
            }
        }

        return null;
    }

    public void add(T data) {
        if (root == null) {
            root = new Node<>(data);
            return;
        }

        Node<T> parent = null;
        Node<T> traveller = root;
        boolean leftDirection = false;

        while (traveller != null) {
            parent = traveller;
            if (data.compareTo(traveller.data) < 0) {
                traveller = traveller.left;
                leftDirection = true;
            } else {
                traveller = traveller.right;
                leftDirection = false;
            }
        }

        if (leftDirection) {
            parent.left = new Node<>(data);
        } else {
            parent.right = new Node<>(data);
        }
    }

    public void erase(T data) {
        root = eraseRecursive(root, data);
    }

    private Node<T> eraseRecursive(Node<T> current, T data) {
        if (current == null) {
            return null;
        }

        if (data.compareTo(current.data) < 0) {
            current.left = eraseRecursive(current.left, data);
        } else if (data.compareTo(current.data) > 0) {
            current.right = eraseRecursive(current.right, data);
        } else {
            // Uzel má pouze jeden potomek nebo žádného
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Uzel má oba potomky
            current.data = findSmallestValue(current.right);
            current.right = eraseRecursive(current.right, current.data);
        }

        return current;
    }

    private T findSmallestValue(Node<T> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    public boolean contains(T data) {
        Node<T> traveller = root;

        while (traveller != null) {
            if (data.compareTo(traveller.data) == 0) {
                return true;
            }

            if (data.compareTo(traveller.data) < 0) {
                traveller = traveller.left;
            } else {
                traveller = traveller.right;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        List<T> list = new ArrayList<>();
        BiConsumer<BiConsumer, Node<T>> biConsumer = (consumer, node) -> {
            if (node.left != null) {
                consumer.accept(consumer, node.left);
            }
            list.add(node.data);


            if (node.right != null) {
                consumer.accept(consumer, node.right);
            }
        };

        biConsumer.accept(biConsumer, root);
        return list.iterator();
    }

    @Override
    public String toString() {
        return "Tree{" + "root=" + root + '}';
    }

}
