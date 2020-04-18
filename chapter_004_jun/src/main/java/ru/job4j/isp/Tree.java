package ru.job4j.isp;

import java.util.*;

/**
 * Class for add, find and show elements menu
 */
public class Tree {

    /**
     * Head of menu
     */
    private final Node root;

    public Tree(String name) {
        this.root = new Node(name);
    }

    /**
     * Add new element of menu
     * @param parent - name of parent node
     * @param child - name of child node
     * @return
     */
    public boolean add(String parent, String child) {
        boolean rsl = false;
        if (!findBy(child).isPresent()) {
            Optional<Node> element = findBy(parent);
            if (element.isPresent()) {
                element.get().getChildren().add(new Node(child));
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * Find node by name
     * @param name - name of node
     * @return
     */
    public Optional<Node> findBy(String name) {
        Optional<Node> rsl = Optional.empty();
        Queue<Node> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node el = data.poll();
            if (el.getName().equals(name)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    /**
     * Show menu
     */
    public void show() {
        System.out.print(getTreeMenu(this.root, "-"));
    }

    private String getTreeMenu(Node node, String separator) {
        StringBuilder out = new StringBuilder().append(node.getName()).append(System.lineSeparator());
        for (Node child : node.getChildren()) {
            out.append(separator).append(getTreeMenu(child, separator + separator));
        }
        return out.toString();
    }
}