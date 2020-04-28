package ru.job4j.tictactoe;

/**
 * Start tictactoe
 */
public class StartTicTacToe {

    private Field field;
    private Logic logic;
    private Input player1;
    private Input player2;

    public StartTicTacToe(Field field, Logic logic, Input player1, Input player2) {
        this.field = field;
        this.logic = logic;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * launch game
     */
    public void init() {
        int i = 0;
        boolean run = true;
        while (run) {
            this.field.show();

            if (i % 2 == 0) {
                int[] cell = this.player1.askInt("Move first player: ");
                this.field.setMarkX(cell[0], cell[1]);
            } else {
                int[] cell = this.player2.askInt("Move second player: ");
                this.field.setMarkO(cell[0], cell[1]);
            }
            i++;

            if (logic.isWinnerX()) {
                this.field.show();
                System.out.println("Winner X!");
                run = false;
            } else if (logic.isWinnerO()) {
                this.field.show();
                System.out.println("Winner O!");
                run = false;
            } else if (!logic.hasGap()) {
                this.field.show();
                System.out.println("Draw!");
                run = false;
            }
        }
    }

    public static void main(String[] args) {
        Field field = new Field(10);
        Logic logic = new HardLogic(field);
        Input user = new UserInput(field);
        Input bot = new BotInput(field);
        new StartTicTacToe(field, logic, user, bot).init();
    }
}
