package ru.job4j.isp;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Сlass for defining actions for a tree
 */
public class TreeAction {

    /**
     * Input tree
     */
    private final Tree tree;

    /**
     * Pairs - name of node and action
     */
    private final Map<String, Action> actions = new HashMap<>();

    public TreeAction(Tree tree) {
        this.tree = tree;
    }

    /**
     * Add action for node in a tree
     * @param name - name of node
     * @param action
     * @return
     */
    public boolean addAction(String name, Action action) {
        boolean rsl = false;
        Optional<Node> node = this.tree.findBy(name);
        if (node.isPresent()) {
            this.actions.put(name, action);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Perform an action for node in a tree
     * @param name - name of node
     */
    public void select(String name) {
        if (this.actions.containsKey(name)) {
            this.actions.get(name).execute();
        }
    }
}