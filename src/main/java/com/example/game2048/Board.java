package com.example.game2048;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    int[][] board;
    int emptySize;
    int maxNumber;
    int side;
    // side( = 0: down);( = 1: left);( = 2: up);( = 3: right);
    int boardSize;
    int score;
    public Board() { }
    public Board(Board.savedData loadedBoard) {
        boardSize = loadedBoard.boardSize;
        maxNumber = loadedBoard.maxNumber;
        emptySize = loadedBoard.emptySize;
        side = loadedBoard.side;
        score = loadedBoard.score;
        moving = new ArrayList<>();
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; ++i)
            System.arraycopy(loadedBoard.board[i], 0, board[i], 0, boardSize);
    }

    public void initialize() {
        side = 0;
        score = 0;
        boardSize = 4;
        emptySize = boardSize * boardSize;
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; ++i)
            for (int j = 0; j < boardSize; ++j)
                board[i][j] = 0;
        moving = new ArrayList<>();
        maxNumber = 0;
        genRandomCell();
    }
    private int randomInt(int N) {
        Random r = new Random();
        return Math.abs(r.nextInt())%N;
    }
    public void genRandomCell() {
        assert(emptySize != 0);
        int id = randomInt(emptySize);
        for (int i = 0; i < boardSize; ++i)
            for (int j = 0; j < boardSize; ++j) {
                if (id == 0 && board[i][j] == 0) {
                    board[i][j] = 2;
                    if (maxNumber >= 64 && randomInt(3) == 0) board[i][j] = 4;
                    --emptySize;
                    return;
                }
                if (board[i][j] == 0) --id;
            }
    }
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[\n");
        buffer.append("emptySize: ").append(emptySize).append('\n');
        buffer.append("score: ").append(score).append('\n');
        buffer.append("maxNumber: ").append(maxNumber).append('\n');
        buffer.append("side: ").append(side).append('\n');
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                buffer.append(board[i][j]);
                if (j + 1 != boardSize) buffer.append(',');
            }
            if (i + 1 != boardSize) buffer.append('\n');
        }
        buffer.append(']');
        return buffer.toString();
    }


    static public class Moving {
        int x1, y1;
        int x2, y2;
        Moving(int _x1, int _y1, int _x2, int _y2) {
            x1 = _x1;
            y1 = _y1;
            x2 = _x2;
            y2 = _y2;
        }
        public String toString() {
            return "(" + x1 + ", " + y1 + ")" + " -> (" + x2 + ", " + y2 + ")";
        }
    }
    ArrayList<Moving> moving = new ArrayList<>();
    public boolean sink() {
        boolean moved = false;
        moving.clear();
        for (int j = 0; j < boardSize; ++j) {
            for (int i = boardSize - 1; i > 0; --i) {
                if (board[i][j] == 0) continue;
                int t = i - 1;
                while (t >= 0 && board[t][j] == 0) t--;
                if (t >= 0 && board[i][j] == board[t][j]) {
                    moved = true;
                    board[i][j] *= 2;
                    board[t][j] = 0;
                    score += board[i][j];
                    ++emptySize;
                    maxNumber = Math.max(maxNumber, board[i][j]);
                    if (i + 1 == boardSize) {
                        moving.add(new Moving(t, j, i, j));
                    } else {
                        int te = i + 1;
                        while (te < boardSize && board[te][j] == 0) ++te;
                        --te;
                        if (te != i) {
                            moving.add(new Moving(t, j, te, j));
                            moving.add(new Moving(i, j, te, j));
                            board[te][j] = board[i][j];
                            board[i][j] = 0;
                        } else {
                            moving.add(new Moving(t, j, i, j));
                        }
                    }
                } else if (i + 1 != boardSize) {
                    int te = i + 1;
                    while (te < boardSize && board[te][j] == 0) ++te;
                    --te;
                    if (te != i) {
                        moved = true;
                        moving.add(new Moving(i, j, te, j));
                        board[te][j] = board[i][j];
                        board[i][j] = 0;
                    }
                }
            }
            // specialize row 0
            if (board[0][j] == 0) continue;
            int te = 1;
            while (te < boardSize && board[te][j] == 0) ++te;
            --te;
            if (te != 0) {
                moved = true;
                moving.add(new Moving(0, j, te, j));
                board[te][j] = board[0][j];
                board[0][j] = 0;
            }
        }
        return moved;
    }
    public void rotateLeft() {
        int[][] newBoard = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                newBoard[boardSize - 1 - j][i] = board[i][j];
            }
        }
        for (int i = 0; i < boardSize; ++i)
            System.arraycopy(newBoard[i], 0, board[i], 0, boardSize);
        for (Moving mov: moving) {
            int x = mov.x1, y = mov.y1;
            mov.x1 = boardSize - 1 - y;
            mov.y1 = x;
            x = mov.x2; y = mov.y2;
            mov.x2 = boardSize - 1 - y;
            mov.y2 = x;
        }
    }
    public void rotateTo(int _side) {
        while (side != _side) {
            rotateLeft();
            side = (side + 1) % 4;
        }
    }
    public boolean sinkDown() {
        rotateTo(0);
        return sink();
    }
    public boolean sinkLeft() {
        rotateTo(1);
        boolean moved = sink();
        rotateTo(0);
        return moved;
    }
    public boolean sinkUp() {
        rotateTo(2);
        boolean moved = sink();
        rotateTo(0);
        return moved;
    }
    public boolean sinkRight() {
        rotateTo(3);
        boolean moved = sink();
        rotateTo(0);
        return moved;
    }
    public int at(int x, int y) {
        return board[x][y];
    }
    public boolean existMoving() {
        int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (board[i][j] == 0) return true;
                for (int k = 0; k < 4; ++k) {
                    int dx = i + next[k][0];
                    int dy = j + next[k][1];
                    if (dx < 0 || dx >=boardSize || dy < 0 || dy >= boardSize) continue;
                    if (board[i][j] == board[dx][dy]) return true;
                }
            }
        }
        return false;
    }
    public int getScore() { return score; }
    static class savedData implements Serializable {
        private int[][] board;
        private int emptySize;
        private int maxNumber;
        private int side;
        private int score;
        private int boardSize;
        public savedData(int emptySize, int maxNumber, int side, int score, int boardSize, int[][] board) {
            this.emptySize = emptySize;
            this.maxNumber = maxNumber;
            this.side = side;
            this.score = score;
            this.boardSize = boardSize;
            this.board = new int[4][4];
            for (int i = 0; i < 4; ++i)
                System.arraycopy(board[i], 0, this.board[i], 0, 4);
        }
        public savedData(Board board) {
            this.emptySize = board.emptySize;
            this.maxNumber = board.maxNumber;
            this.side = board.side;
            this.score = board.score;
            this.boardSize = board.boardSize;
            this.board = new int[4][4];
            for (int i = 0; i < 4; ++i)
                System.arraycopy(board.board[i], 0, this.board[i], 0, 4);
        }
    }
    public static void saveData(String path, Board board) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
            savedData player = new savedData(board);
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Board loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"))) {
            savedData loadedBoard = (savedData) ois.readObject();
            return new Board(loadedBoard);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setMax(int number) {
        maxNumber = number;
    }
}