package ru.MrCrashLab.Players;

import ru.MrCrashLab.TicTacToeLogic.FieldLogic;

import java.util.Scanner;

public class UserPlayer implements Player{
    private int[] moveArr = new int[2];
    private final FieldLogic fieldLogic;
    private Scanner input;

    public UserPlayer(FieldLogic fieldLogic) {
        this.fieldLogic = fieldLogic;
        input = new Scanner(System.in);
    }

    @Override
    public int[] move() {
        for (int i = 0; i < 2; i++)
            moveArr[i] = input.nextInt();
        return moveArr;
    }
}
