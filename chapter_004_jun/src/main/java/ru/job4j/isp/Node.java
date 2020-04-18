package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Model node for describing menu elements
 */
public class Node {

    private final String name;
    private final List<Node> children = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Node> getChildren() {
        return this.children;
    }
}