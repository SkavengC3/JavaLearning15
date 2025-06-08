public class GameSettings {
    private int boardSize;
    private String player1Name;
    private String player2Name;

    public GameSettings() {
        this.boardSize = 7;
        this.player1Name = "Гравець 1";
        this.player2Name = "Гравець 2";
    }

    public GameSettings(int boardSize, String player1Name, String player2Name) {
        this.boardSize = boardSize;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getGameSize() {
        return (boardSize - 1) / 2;
    }
}