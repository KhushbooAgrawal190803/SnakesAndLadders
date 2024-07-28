package O2_SnakeAndLadder.Models;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    int diceCount = 1;
    static int min;
    static int max;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
        Dice.min = 1;
        Dice.max = 6;
    }

    public int rollDice() {
        int total = 0;
        int diceRolls = 0;

        while (diceCount > diceRolls) {
            total += ThreadLocalRandom.current().nextInt(min, max + 1);
            diceRolls++;
        }

        return total;
    }
}
