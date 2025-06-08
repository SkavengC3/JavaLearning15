public class GameBoard {
    private char[][] board;
    private int height;
    private int width;
    private int gameSize;

    public GameBoard(int height) {
        this.height = height;
        this.width = height;
        this.gameSize = (height - 1) / 2;
        createBoard();
    }

    private void createBoard() {
        board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0) {
                    board[i][j] = ' ';
                } else if (i == 0 && j % 2 == 1) {
                    board[i][j] = (char) ('0' + (j / 2) + 1);
                } else if (j == 0 && i % 2 == 1) {
                    board[i][j] = (char) ('0' + (i / 2) + 1);
                } else if (i % 2 == 1 && j % 2 == 1) {
                    board[i][j] = ' ';
                } else if (i % 2 == 1) {
                    board[i][j] = '|';
                } else if (j % 2 == 1) {
                    board[i][j] = '-';
                } else {
                    board[i][j] = '+';
                }
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (row >= 1 && row <= gameSize && col >= 1 && col <= gameSize) {
            int gridRow = row * 2 - 1;
            int gridCol = col * 2 - 1;
            if (board[gridRow][gridCol] == ' ') {
                board[gridRow][gridCol] = symbol;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(char symbol) {
        for (int i = 1; i < gameSize * 2; i += 2) {
            for (int j = 1; j <= gameSize * 2 - 4; j += 2) {
                if (board[i][j] == symbol && board[i][j + 2] == symbol && board[i][j + 4] == symbol) {
                    return true;
                }
            }
        }

        for (int j = 1; j < gameSize * 2; j += 2) {
            for (int i = 1; i <= gameSize * 2 - 4; i += 2) {
                if (board[i][j] == symbol && board[i + 2][j] == symbol && board[i + 4][j] == symbol) {
                    return true;
                }
            }
        }

        for (int i = 1; i <= gameSize * 2 - 4; i += 2) {
            for (int j = 1; j <= gameSize * 2 - 4; j += 2) {
                if (board[i][j] == symbol && board[i + 2][j + 2] == symbol && board[i + 4][j + 4] == symbol) {
                    return true;
                }
            }
        }

        for (int i = 1; i <= gameSize * 2 - 4; i += 2) {
            for (int j = gameSize * 2 - 1; j >= 5; j -= 2) {
                if (board[i][j] == symbol && board[i + 2][j - 2] == symbol && board[i + 4][j - 4] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getGameSize() {
        return gameSize;
    }
}