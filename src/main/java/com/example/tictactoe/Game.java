package com.example.tictactoe;

public class Game {
    private char[][] board;
    private char currentPlayer;
    private String status;

    public Game() {
        board = new char[3][3];
        currentPlayer = 'X';
        status = "IN_PROGRESS";
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == '\0') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }
}
