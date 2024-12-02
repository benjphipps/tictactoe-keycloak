package com.example.tictactoe;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    private Game game = new Game();

    @GET
    public Game getGame() {
        return game;
    }

    @POST
    @Path("/move")
    public Response makeMove(@QueryParam("row") int row, @QueryParam("col") int col) {
        if (game.getStatus().equals("IN_PROGRESS") && game.makeMove(row, col)) {
            if (checkWin()) {
                game.setStatus(game.getCurrentPlayer() + " WINS");
            } else if (isBoardFull()) {
                game.setStatus("DRAW");
            } else {
                game.switchPlayer();
            }
            return Response.ok(game).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Invalid move").build();
    }

    private boolean checkWin() {
        char[][] board = game.getBoard();
        char player = game.getCurrentPlayer();

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        char[][] board = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }
}
