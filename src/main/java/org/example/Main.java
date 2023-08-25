package org.example;

import java.util.Random;
import java.util.Scanner;

class Main {
    final char SIGN_X = 'X';
    final char SIGN_O = 'O';
    final char SIGN_EMPTY = '.';
    char[][] table;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new Main().game();
    }

    Main() {
        random = new Random();
        scanner = new Scanner(System.in);
        table = new char[5][5];
    }

    void game() {
        initTable();
        while (true) {
            turnHuman();
            if (checkWin(SIGN_X)) {
                System.out.println("Вы выиграли!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Хода больше нет!");
                break;
            }
            turnAI();
            printTable();
            if (checkWin(SIGN_O)) {
                System.out.println("Компьютер выиграл!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Хода больше нет!");
                break;
            }
        }
        System.out.println("Игра завершена!");
        printTable();
    }

    void initTable() {
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                table[row][col] = SIGN_EMPTY;
    }

    void printTable() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++)
                System.out.print(table[row][col] + " ");
            System.out.println();
        }
    }

    void turnHuman() {
        int x, y;
        do {
            System.out.println("Введите два целочесленных значения точки Икс в диапазоне с '1' по '5'!");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_X;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 5|| y >= 5)
            return false;
        return table[y][x] == SIGN_EMPTY;
    }

    void turnAI() {
        int x, y;
        do {
            x = random.nextInt(5);
            y = random.nextInt(5);
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_O;
    }

    boolean checkWin(char dot) {
        for (int i = 0; i < 5; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][4] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[4][i] == dot))
                return true;
        if ((table[0][0] == dot && table[1][1] == dot &&
                table[4][4] == dot) ||
                (table[4][0] == dot && table[1][1] == dot &&
                        table[0][4] == dot))
            return true;
        return false;
    }

    boolean isTableFull() {
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                if (table[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
}