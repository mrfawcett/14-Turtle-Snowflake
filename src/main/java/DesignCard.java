/**
 * PROVIDED CLASS - do not edit.
 *
 * Turns your name's seed (from Snowflake.nameSeed) into the three numbers on
 * your design card. Main prints the card and writes it on your picture,
 * next to your name; the card is checked against your name when it is graded.
 *
 *   branch pairs  = 2 + seed % 3                  2, 3 or 4
 *   branch angle  = (seed / 3) % 3  ->  0: 30   1: 45   2: 60   degrees
 *   style         = (seed / 9) % 2  ->  0: CRYSTAL   1: FERN
 */
public class DesignCard {

    /** How many places branches leave the spine (each place is a left/right pair). */
    public static int branchPairs(int seed) {
        return 2 + seed % 3;
    }

    /** The angle between every branch and the spine's outward direction, in degrees. */
    public static int branchAngle(int seed) {
        int k = (seed / 3) % 3;
        if (k == 0) {
            return 30;
        } else if (k == 1) {
            return 45;
        } else {
            return 60;
        }
    }

    /** CRYSTAL arms must contain a closed shape; FERN arms must not. */
    public static boolean isCrystal(int seed) {
        return (seed / 9) % 2 == 0;
    }

    /** The design card as text, for Main to print. */
    public static String describe(String designer, int seed) {
        String style;
        if (isCrystal(seed)) {
            style = "CRYSTAL  (every arm contains at least one closed shape)";
        } else {
            style = "FERN     (no closed shapes; twigs grow off the branches)";
        }
        return "Designer:      " + designer + "\n"
             + "Seed:          " + seed + "\n"
             + "Branch pairs:  " + branchPairs(seed) + "\n"
             + "Branch angle:  " + branchAngle(seed) + " degrees from the spine\n"
             + "Style:         " + style;
    }
}
