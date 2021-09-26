package ru.MrCrashLab.Terminal;

import ru.MrCrashLab.Players.AiPlayer;
import ru.MrCrashLab.Players.Player;
import ru.MrCrashLab.Players.UserPlayer;
import ru.MrCrashLab.TicTacToeLogic.FieldLogic;

import java.util.ArrayList;
import java.util.Scanner;

public class Field {
    private FieldLogic logic;
    private ArrayList<Player> players;
    private Player player1;
    private Player player2;
    private Scanner input;

    public Field() {
        this.logic = new FieldLogic();
        player2 = new AiPlayer(logic);
        player1 = new UserPlayer(logic);
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        input = new Scanner(System.in);
    }

    public void drawField() {
        for (int i = 0; i < logic.getSize(); i++) {
            for (int j = 0; j < logic.getSize(); j++) {
                if (logic.getField()[i][j] == 0) {
                    System.out.print("\u001B[32m O\u001B[0m");
                } else if (logic.getField()[i][j] == 1) {
                    System.out.print("\u001B[34m X\u001B[0m");
                }
                if (j < logic.getSize() - 1)
                    System.out.print("\t\u001B[33m|\u001B[0m");
            }
            if (i < logic.getSize() - 1)
                System.out.println("\n\u001B[33m-------------\u001B[0m");
        }
    }

    public void play() {
        int arr[];
        int choice;
        while (true) {
            logic.clearField();
            System.out.print("(1 - X, 0 - O, else-to exit)\nSelect the player: ");
            choice = input.nextInt();
            if (choice != 0 && choice != 1)
                break;
            logic.selectMarker(choice);
            drawField();
            while (!logic.endCheck()) {
                System.out.println("\nPlayer " + (logic.isMarker() ? "\u001B[34mX\u001B[0m" : "\u001B[32m0\u001B[0m") + " move:");
                arr = players.get(logic.isMarker() ? 1 : 0).move();
                logic.moveOnField(arr[0], arr[1]);
                drawField();
            }
            System.out.println("\n\u001B[91m!!!!END!!!!\u001B[0m");
            if (logic.whoWin() == 1) {
                System.out.println("Player \u001B[34mX\u001B[0m WIN!!!");
            } else if (logic.whoWin() == 0) {
                System.out.println("Player \u001B[32m0\u001B[0m WIN!!!");
            } else {
                System.out.println("\u001B[91m!!!!DRAW!!!!\u001B[0m");
            }
        }
        System.out.println("END!");
    }
}
