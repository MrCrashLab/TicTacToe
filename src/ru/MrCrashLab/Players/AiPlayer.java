package ru.MrCrashLab.Players;

import ru.MrCrashLab.TicTacToeLogic.FieldLogic;

import java.util.*;

public class AiPlayer implements Player {
    private final FieldLogic fieldLogic;
    private final int deepLevel = 10;

    public AiPlayer(FieldLogic fieldLogic) {
        this.fieldLogic = fieldLogic;
    }

    @Override
    public int[] move() {
        HashMap<Integer, int[]> moveCount = new HashMap<>();
        List<Integer> keyList = new ArrayList<>();
        int[][] field;
        int tmp[];
        int count;
        for (int i = 0; i < fieldLogic.getSize(); i++) {
            for (int j = 0; j < fieldLogic.getSize(); j++) {
                field = cloneField(fieldLogic);
                if (field[i][j] == -1) {
                    tmp = new int[]{i, j};
                    count = miniMax(0, tmp, true, 0, field);
                    keyList.add(count);
                    tmp = new int[]{i, j};
                    moveCount.put(count, tmp);
                }
            }
        }
        Collections.sort(keyList);
        Collections.reverse(keyList);
        return moveCount.get(keyList.get(0));
    }

    private int[][] cloneField(FieldLogic logic) {
        int[][] tmp = new int[logic.getSize()][logic.getSize()];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp.length; j++) {
                tmp[i][j] = logic.getField()[i][j];
            }
        }
        return tmp;
    }

    private int miniMax(int count, int[] arr, boolean player, int deep, int[][] field) {

        if (deep >= deepLevel && fieldFull(field))
            return count;
        else {
            while (field[arr[0]][arr[1]] != -1 && !fieldFull(field)) {
                if (arr[0] < fieldLogic.getSize() - 1)
                    arr[0]++;
                else {
                    arr[0] = 0;
                    arr[1]++;
                }
                if (arr[1] >= fieldLogic.getSize())
                    arr[1] = 0;
            }
            field[arr[0]][arr[1]] = player ? 1 : 0;
            FieldLogic lgc = new FieldLogic(field);
            if (lgc.endCheck())
                if (player) {
                    if (lgc.whoWin() == 1) {
                        count += 10;
                    } else if (lgc.whoWin() == 0) {
                        count -= 10;
                        return count;
                    }
                }
            return miniMax(count, arr, !player, ++deep, field);
        }
    }

    private boolean fieldFull(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
