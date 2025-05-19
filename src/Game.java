import java.util.Scanner;

public class Game {
    public static void game(Scanner sc, int height, String player1, String player2) {
        int width = height;
        int size = (height - 1) / 2;
        boolean start = true;

        while (start) {
            char[][] board = createBoard(height, width);

            System.out.print("Введіть символ для " + player1 + ": ");
            char turn1 = sc.nextLine().charAt(0);
            System.out.print("Введіть символ для " + player2 + ": ");
            char turn2 = sc.nextLine().charAt(0);
            char turn = turn1;
            String currentPlayer = player1;

            for (int count = 0; count < size * size; count++) {
                printBoard(board, height, width);
                int row, col;

                while (true) {
                    System.out.print(currentPlayer + " (" + turn + "), введіть стовпець (1-" + size + "): ");
                    if (sc.hasNextInt()) {
                        col = sc.nextInt();
                    } else {
                        System.out.println("Введіть число!");
                        sc.next(); continue;
                    }
                    System.out.print(currentPlayer + " (" + turn + "), введіть рядок (1-" + size + "): ");
                    if (sc.hasNextInt()) {
                        row = sc.nextInt();
                    } else {
                        System.out.println("Введіть число!");
                        sc.next(); continue;
                    }
                    sc.nextLine();

                    if (row >= 1 && row <= size && col >= 1 && col <= size) {
                        int gridRow = row * 2 - 1;
                        int gridCol = col * 2 - 1;
                        if (board[gridRow][gridCol] == ' ') {
                            board[gridRow][gridCol] = turn;
                            break;
                        } else {
                            System.out.println("Клітинка вже зайнята.");
                        }
                    } else {
                        System.out.println("Введіть значення від 1 до " + size);
                    }
                }

                if (count >= 4 && checker(board, turn, size)) {
                    printBoard(board, height, width);
                    System.out.println("Виграв " + currentPlayer + " (" + turn + ")!!!");
                    Saves.saveStats(currentPlayer, size, turn);
                    break;
                }

                if (count == size * size - 1) {
                    printBoard(board, height, width);
                    System.out.println("НІЧИЯ!!!");
                    Saves.saveStats("НІЧИЯ", size, '-');
                    break;
                }

                if (turn == turn1) {
                    turn = turn2;
                    currentPlayer = player2;
                } else {
                    turn = turn1;
                    currentPlayer = player1;
                }
            }

            System.out.println("Натисніть Enter для виходу в меню або введіть 1 для рестарту:");
            String again = sc.nextLine();
            start = again.equals("1");
        }
    }

    public static char[][] createBoard(int height, int width) {
        char[][] board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0) board[i][j] = ' ';
                else if (i == 0 && j % 2 == 1) board[i][j] = (char) ('0' + (j / 2) + 1);
                else if (j == 0 && i % 2 == 1) board[i][j] = (char) ('0' + (i / 2) + 1);
                else if (i % 2 == 1 && j % 2 == 1) board[i][j] = ' ';
                else if (i % 2 == 1) board[i][j] = '|';
                else if (j % 2 == 1) board[i][j] = '-';
                else board[i][j] = '+';
            }
        }
        return board;
    }

    public static void printBoard(char[][] board, int height, int width) {
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean checker(char[][] game, char turn, int size) {
        for (int i = 1; i < size * 2; i += 2) {
            for (int j = 1; j <= size * 2 - 4; j += 2) {
                if (game[i][j] == turn && game[i][j + 2] == turn && game[i][j + 4] == turn) return true;
            }
        }
        for (int j = 1; j < size * 2; j += 2) {
            for (int i = 1; i <= size * 2 - 4; i += 2) {
                if (game[i][j] == turn && game[i + 2][j] == turn && game[i + 4][j] == turn) return true;
            }
        }
        for (int i = 1; i <= size * 2 - 4; i += 2) {
            for (int j = 1; j <= size * 2 - 4; j += 2) {
                if (game[i][j] == turn && game[i + 2][j + 2] == turn && game[i + 4][j + 4] == turn) return true;
            }
        }
        for (int i = 1; i <= size * 2 - 4; i += 2) {
            for (int j = size * 2 - 1; j >= 5; j -= 2) {
                if (game[i][j] == turn && game[i + 2][j - 2] == turn && game[i + 4][j - 4] == turn) return true;
            }
        }
        return false;
    }
}
