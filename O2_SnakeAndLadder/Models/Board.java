package O2_SnakeAndLadder.Models;

import java.util.concurrent.*;

public class Board {

    public Cell[][] cell;

    public Board(int size, int ladderCount, int snakeCount) {
        initializeCells(size);
        addSnakeAndLadder(cell, ladderCount, snakeCount);
    }

    // Initialize all cells
    private void initializeCells(int size) {
        cell = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell icell = new Cell();
                cell[i][j] = icell;
            }
        }
    }

    // add snakes(where head > tail) and ladders(where start < end) to cells
    private void addSnakeAndLadder(Cell[][] cell, int ladderCount, int snakeCount) {
        int snakeCheck = snakeCount;

        while (snakeCheck > 0) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cell.length * cell.length - 2); // from 1 to 99
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cell.length * cell.length - 2);

            if (snakeHead <= snakeTail) {
                continue;
            }

            Jump jump = new Jump();
            jump.start = snakeHead;
            jump.end = snakeTail;

            Cell icell = getCell(snakeHead);
            icell.jump = jump;

            snakeCheck--;
        }

        int ladderCheck = ladderCount;

        while (ladderCheck > 0) {
            int ladderStart = ThreadLocalRandom.current().nextInt(1, cell.length * cell.length - 2); // from 1 to 99
            int ladderEnd = ThreadLocalRandom.current().nextInt(1, cell.length * cell.length - 2);

            if (ladderStart >= ladderEnd) {
                continue;
            }

            Jump jump = new Jump();
            jump.start = ladderStart;
            jump.end = ladderEnd;

            Cell icell = getCell(ladderStart);
            icell.jump = jump;

            ladderCheck--;
        }
    }

    // Get a cell from board
    public Cell getCell(int playerPosition) {
        int row = playerPosition / cell.length;
        int col = playerPosition % cell.length;

        return cell[row][col];
    }
}
