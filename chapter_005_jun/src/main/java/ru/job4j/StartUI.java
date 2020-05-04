package ru.job4j;

import ru.job4j.tracker.*;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    public static void main(String[] args) {

        try {
            Thread.sleep(20000);
        } catch (Exception e) { }

        int size = 10_000_000;
        String[] answer = new String[size];
        for (int i = 0; i < size - 2; i++) {
            answer[i] = "0";
        }
        answer[size - 1] = "6";

        Input input = new StubInput(answer);
        //Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Tracker tracker = new Tracker();
        ArrayList<UserAction> actions = new ArrayList<>(List.of(
            new CreateAction(),
            new ShowAction(),
            new ReplaceAction(),
            new DeleteAction(),
            new FindByIdAction(),
            new FindByNameAction(),
            new ExitAction()
        ));

        new ru.job4j.tracker.StartUI(validate, tracker, System.out::println).init(actions);
    }
}
