package O2_SnakeAndLadder;

import O2_SnakeAndLadder.Models.Board;
import O2_SnakeAndLadder.Models.Cell;
import O2_SnakeAndLadder.Models.Dice;
import O2_SnakeAndLadder.Models.Player;
import java.util.Deque;
import java.util.LinkedList;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> players;
    Player winner;

    public Game() {
        initGame();
    }

    private void initGame() {
        board = new Board(10, 5, 4);
        dice = new Dice(2);
        winner = null;

        Player player1 = new Player("P001", 0);
        Player player2 = new Player("P002", 0);

        players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
    }

    public String startGame() {

        while (winner == null) {
            // Get current player
            Player currPlayer = findPlayerTurn();
            System.out.println("Player" + currPlayer.id + "'s current position is : " + currPlayer.position + "\n");

            // Roll the dice
            int rollCount = dice.rollDice();

            // Find updated position if exists
            int playerNewPosition = currPlayer.position + rollCount;
            playerNewPosition = JumpCount(playerNewPosition);
            currPlayer.position = playerNewPosition;
            System.out.println("Player" + currPlayer.id + "'s new position is : " + currPlayer.position);

            // If player is past 99, we got a winner!
            if (playerNewPosition >= board.cell.length * board.cell.length - 1) {
                winner = currPlayer;
            }
        }

        return winner.id + " has won the match!!";
    }

    // Checks if encountered with snake or ladder
    private int JumpCount(int playerNewPosition) {
        if (playerNewPosition > board.cell.length * board.cell.length - 1) {
            return playerNewPosition;
        }

        // Check if there exists a snake or ladder on currPositon
        Cell cell = board.getCell(playerNewPosition);
        if (cell.jump != null && cell.jump.start == playerNewPosition) {
            String jump = (cell.jump.start < cell.jump.end) ? "ladder" : "snake";
            System.out.println("Jump against a " + jump);
            return cell.jump.end;
        }

        return playerNewPosition;
    }

    // Returns current player
    private Player findPlayerTurn() {
        Player currPlayer = players.removeFirst();
        players.addLast(currPlayer);
        return currPlayer;
    }
}
