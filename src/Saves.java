import java.io.*;

public class Saves {
    public static GameSettings loadSettings() {
        GameSettings settings = new GameSettings();
        try (BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("size=")) {
                    settings.setBoardSize(Integer.parseInt(line.substring(5)));
                } else if (line.startsWith("player1=")) {
                    settings.setPlayer1Name(line.substring(8));
                } else if (line.startsWith("player2=")) {
                    settings.setPlayer2Name(line.substring(8));
                }
            }
        } catch (IOException e) {

        }
        return settings;
    }

    public static void saveSettings(GameSettings settings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("settings.txt"))) {
            bw.write("size=" + settings.getBoardSize());
            bw.newLine();
            bw.write("player1=" + settings.getPlayer1Name());
            bw.newLine();
            bw.write("player2=" + settings.getPlayer2Name());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Помилка збереження налаштувань.");
        }
    }

    public static void saveStats(GameStats stats) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("stats.txt", true))) {
            bw.write(stats.getFormattedStats());
        } catch (IOException e) {
            System.out.println("Помилка збереження статистики.");
        }
    }

    public static void showStats() {
        try (BufferedReader br = new BufferedReader(new FileReader("stats.txt"))) {
            String line;
            System.out.println("Статистика ігор: ");
            while ((line = br.readLine()) != null) {
                if (line.equals("---")) {
                    System.out.println();
                } else {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Файл статистики не знайдено.");
        }
    }
}