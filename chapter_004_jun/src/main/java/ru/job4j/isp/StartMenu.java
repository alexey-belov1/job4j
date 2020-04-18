package ru.job4j.isp;

/**
 * Class for start menu
 */
public class StartMenu {

    private final Input input;
    private final Tree tree;
    private final TreeAction treeAction;

    public StartMenu(Input input, Tree tree, TreeAction treeAction) {
        this.input = input;
        this.tree = tree;
        this.treeAction = treeAction;
    }

    /**
     * launch menu
     */
    public void init() {
        String select = "";
        while (!select.equals("Exit")) {
            tree.show();
            select = this.input.askStr("Select: ");
            this.treeAction.select(select);
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();

        Tree tree = new Tree("Task 1");
        tree.add("Task 1", "Task 1.1");
        tree.add("Task 1", "Task 1.2");
        tree.add("Task 1.1", "Task 1.1.1");

        TreeAction treeAction = new TreeAction(tree);
        treeAction.addAction("Task 1.1", () -> System.out.println("Test"));

        new StartMenu(input, tree, treeAction).init();
    }
}