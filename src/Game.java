import java.util.Scanner;

public class Game {
    public static void game(Scanner sc, GameSettings settings) {
        boolean start = true;

        while (start) {
            GameBoard board = new GameBoard(settings.getBoardSize());
            int gameSize = settings.getGameSize();

            System.out.print("Введіть символ для " + settings.getPlayer1Name() + ": ");
            char turn1 = sc.nextLine().charAt(0);
            System.out.print("Введіть символ для " + settings.getPlayer2Name() + ": ");
            char turn2 = sc.nextLine().charAt(0);

            char turn = turn1;
            String currentPlayer = settings.getPlayer1Name();

            for (int count = 0; count < gameSize * gameSize; count++) {
                board.printBoard();
                int row, col;

                while (true) {
                    System.out.print(currentPlayer + " (" + turn + "), введіть стовпець (1-" + gameSize + "): ");
                    if (sc.hasNextInt()) {
                        col = sc.nextInt();
                    } else {
                        System.out.println("Введіть число!");
                        sc.next();
                        continue;
                    }

                    System.out.print(currentPlayer + " (" + turn + "), введіть рядок (1-" + gameSize + "): ");
                    if (sc.hasNextInt()) {
                        row = sc.nextInt();
                    } else {
                        System.out.println("Введіть число!");
                        sc.next();
                        continue;
                    }
                    sc.nextLine();

                    if (board.makeMove(row, col, turn)) {
                        break;
                    } else {
                        if (row >= 1 && row <= gameSize && col >= 1 && col <= gameSize) {
                            System.out.println("Клітинка вже зайнята.");
                        } else {
                            System.out.println("Введіть значення від 1 до " + gameSize);
                        }
                    }
                }

                if (count >= 4 && board.checkWin(turn)) {
                    board.printBoard();
                    System.out.println("Виграв " + currentPlayer + " (" + turn + ")!!!");
                    GameStats stats = new GameStats(currentPlayer, gameSize, turn);
                    Saves.saveStats(stats);
                    break;
                }

                if (count == gameSize * gameSize - 1) {
                    board.printBoard();
                    System.out.println("НІЧИЯ!!!");
                    GameStats stats = new GameStats("НІЧИЯ", gameSize, '-');
                    Saves.saveStats(stats);
                    break;
                }

                if (turn == turn1) {
                    turn = turn2;
                    currentPlayer = settings.getPlayer2Name();
                } else {
                    turn = turn1;
                    currentPlayer = settings.getPlayer1Name();
                }
            }

            System.out.println("Натисніть Enter для виходу в меню або введіть 1 для рестарту:");
            String again = sc.nextLine();
            start = again.equals("1");
        }
    }
}