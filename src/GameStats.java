import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameStats {
    private String winner;
    private String gameTime;
    private int boardSize;
    private char playerSymbol;

    public GameStats(String winner, int boardSize, char playerSymbol) {
        this.winner = winner;
        this.boardSize = boardSize;
        this.playerSymbol = playerSymbol;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.gameTime = dtf.format(LocalDateTime.now());
    }

    public String getWinner() {
        return winner;
    }

    public String getGameTime() {
        return gameTime;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public String getFormattedStats() {
        String result = "";
        result += "Виграв = " + winner + "\n";
        result += "Час = " + gameTime + "\n";
        result += "Розмір поля = " + boardSize + "\n";
        result += "Відмітка гравця=" + playerSymbol + "\n";
        result += "---\n";
        return result;
    }
}