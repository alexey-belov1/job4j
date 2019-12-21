package ru.job4j.array;

public class MatrixCheck {
    public static boolean isWin(char[][] board) {
        boolean result = false;
        for (int i = 0; i < 5; i++) {
            if (board[i][i] == 'X') {
                if(monoHorizontal(board, i) || monoVertical(board, i)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean monoHorizontal(char[][] board, int index){
        boolean result = true;
        for (int i = 1; i < board[0].length; i ++) {
            if(board[index][0] != board[index][i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean monoVertical(char[][] board, int index){
        boolean result = true;
        for (int i = 1; i < board.length; i ++) {
            if(board[0][index] != board[i][index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
