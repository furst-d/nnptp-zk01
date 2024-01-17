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
        Node<T> parent = null;
        Node<T> traveller = root;
        boolean leftDirection = false;

        while (traveller.data.compareTo(data) != 0) {
            parent = traveller;
            if (data.compareTo(traveller.data) < 0) {
                traveller = traveller.left;
                leftDirection = true;
            } else {
                traveller = traveller.right;
                leftDirection = false;
            }
        }

        if (traveller.right == null && traveller.left == null) {
            if (leftDirection) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (traveller.left != null) {
            Node<T> subparent = traveller;
            Node<T> removal = traveller.left;

            while (removal.right != null) {
                subparent = removal;
                removal = removal.right;
            }

            traveller.data = removal.data;
            if (subparent != traveller) {
                subparent.left = removal.left;
            } else {
                subparent.right = removal.left;
            }
        } else if (traveller.right != null) {
            Node<T> subparent = traveller;
            Node<T> removal = traveller.right;

            while (removal.left != null) {
                subparent = removal;
                removal = removal.left;
            }

            traveller.data = removal.data;
            if (subparent == traveller) {
                subparent.right = removal.right;
            } else {
                subparent.left = removal.right;
            }
        }
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
