import java.util.Scanner;

public class Main {
    static String player1 = "Гравець 1";
    static String player2 = "Гравець 2";
    static int height = 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Saves.loadSettings(height, player1, player2);

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
                    Game.game(sc, height, player1, player2);
                    break;
                case "2":
                    settings(sc);
                    break;
                case "3":
                    Saves.showStats();
                    break;
                case "0":
                    System.out.println("До зустрічі!");
                    return;
                default:
                    System.out.println("Некоректний формат вводу");
            }
        }
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
        Saves.saveSettings(height, player1, player2);
    }
}
