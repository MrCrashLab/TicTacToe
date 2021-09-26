package ru.MrCrashLab.TicTacToeLogic;

public class FieldLogic {
    private int[][] field;
    //true  - X
    //false - 0
    private boolean marker;
    private final int size = 3;


    public FieldLogic() {
        clearField();
    }

    public FieldLogic(int[][] field) {
        this.field = field;
    }

    /**
     * @param choice 1 this X player; 0 this 0 player
     */
    public void selectMarker(int choice) {
        if (choice == 1)
            marker = true;
        else if (choice == 0)
            marker = false;
    }

    public void moveOnField(int i, int j) {
        if ((i < size && j < size) && field[i][j] == -1) {
            field[i][j] = marker ? 1 : 0;
            marker = !marker;
        }
    }

    /**
     * @return false then the game continues; true then the game is over
     */
    public boolean endCheck() {
        if (whoWin() != -1)
            return true;
        boolean flag = true;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (field[i][j] == -1) {
                    flag = false;
                    break;
                }
        return flag;
    }

    public int whoWin() {
        int tmp = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (field[i][j] != field[i][j - 1] || field[i][j] == -1) {
                    tmp = -1;
                    break;
                }
                tmp = field[i][j];
            }
            if (tmp != -1)
                return tmp;
        }
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    if (field[j][i] != field[j - 1][i] || field[j][i] == -1) {
                        tmp = -1;
                        break;
                    }
                    tmp = field[j][i];
                }
                if (tmp != -1)
                    return tmp;
            }

            for (int i = 1; i < size; i++) {
                if (field[size - 1 - i][i] != field[size - i][i - 1] || field[size - i][i - 1] == -1) {
                    tmp = -1;
                    break;
                }
                tmp = field[size - i][i - 1];
            }
            if (tmp != -1)
                return tmp;


            for (int i = 1; i < size; i++) {
                if (field[i][i] != field[i - 1][i - 1] || field[i][i] == -1) {
                    tmp = -1;
                    break;
                }
                tmp = field[i][i];
            }
        return tmp;
    }

    public int[][] getField() {
        return field;
    }

    public boolean isMarker() {
        return marker;
    }

    public int getSize() {
        return size;
    }

    public void clearField() {
        field = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                field[i][j] = -1;
    }
}
