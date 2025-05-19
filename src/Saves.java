import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Saves {
    public static void loadSettings(int height, String player1, String player2) {
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

    public static void saveSettings(int height, String player1, String player2) {
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

