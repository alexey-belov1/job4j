package ru.job4j.tree;

import java.util.*;

class Tree<E extends Comparable<E>> implements SimpleTree<E>, Iterable<E> {
    private final Node<E> root;
    private int modCount = 0;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (!findBy(child).isPresent()) {
            Optional<Node<E>> element = findBy(parent);
            if (element.isPresent()) {
                element.get().children.add(new Node<>(child));
                modCount++;
                rsl = true;
            }
        }

        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() <= 2) {
                data.addAll(el.children);
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int modCountIt = modCount;
            Queue<Node<E>> data = new LinkedList<>(List.of(root));

            @Override
            public boolean hasNext() {
                return !this.data.isEmpty();
            }

            @Override
            public E next() {
                if (this.modCountIt != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Node<E> el = this.data.remove();
                this.data.addAll(el.children);
                return el.value;
            }
        };
    }
}
