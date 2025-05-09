import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    static String player1 = "Гравець 1";
    static String player2 = "Гравець 2";
    static int height = 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadSettings();

        while (true) {
            System.out.print("""
                    Головне Меню (введіть номер пункту):
                    1. Нова гра
                    2. Налаштування
                    3. Статистика
                    0. Вихід
                    Ваш вибір:
                    """);

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    game(sc, height);
                    break;
                case "2":
                    settings(sc);
                    break;
                case "3":
                    showStats();
                    break;
                case "0":
                    System.out.println("До зустрічі!");
                    return;
                default:
                    System.out.println("Некоректний формат вводу");
            }
        }
    }

    public static void game(Scanner sc, int height) {
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
                    saveStats(currentPlayer, size, turn);
                    break;
                }

                if (count == size * size - 1) {
                    printBoard(board, height, width);
                    System.out.println("НІЧИЯ!!!");
                    saveStats("НІЧИЯ", size, '-');
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

    public static void settings(Scanner sc) {
        System.out.print("Введіть розмір поля (3, 5, 7 або 9): ");
        while (true) {
            if (sc.hasNextInt()) {
                int newSize = sc.nextInt();
                sc.nextLine();
                if (newSize >= 3 && newSize % 2 == 1 && newSize <= 9) {
                    height = newSize * 2 + 1;
                    break;
                } else System.out.println("Невірний розмір!");
            } else {
                System.out.println("Введіть число!");
                sc.nextLine();
            }
        }
        System.out.print("Введіть ім’я першого гравця: ");
        player1 = sc.nextLine();
        System.out.print("Введіть ім’я другого гравця: ");
        player2 = sc.nextLine();
        saveSettings();
    }

    public static void loadSettings() {
        try (BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("size=")) height = Integer.parseInt(line.substring(5));
                else if (line.startsWith("player1=")) player1 = line.substring(8);
                else if (line.startsWith("player2=")) player2 = line.substring(8);
            }
        } catch (IOException e) {
        }
    }

    public static void saveSettings() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("settings.txt"))) {
            bw.write("size=" + height);
            bw.newLine();
            bw.write("player1=" + player1);
            bw.newLine();
            bw.write("player2=" + player2);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Помилка збереження налаштувань.");
        }
    }

    public static void saveStats(String winner, int size, char symbol) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("stats.txt", true))) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = dtf.format(LocalDateTime.now());
            bw.write("Виграв = " + winner); bw.newLine();
            bw.write("Час = " + time); bw.newLine();
            bw.write("Розмір поля = " + size); bw.newLine();
            bw.write("Відмітка гравця=" + symbol); bw.newLine();
            bw.write("---"); bw.newLine();
        } catch (IOException e) {
            System.out.println("Помилка збереження статистики.");
        }
    }

    public static void showStats() {
        try (BufferedReader br = new BufferedReader(new FileReader("stats.txt"))) {
            String line;
            System.out.println("Статистика ігор: ");
            while ((line = br.readLine()) != null) {
                if (line.equals("---")) System.out.println();
                else System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Файл статистики не знайдено.");
        }
    }
}
