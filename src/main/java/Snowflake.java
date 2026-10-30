/** READ FIRST
 * Turtle Snowflake - the turtle unit's last drawing.
 *
 * No two snowflakes are alike, and neither are yours. Your NAME decides three
 * numbers on your design card (see the README): how many branch pairs each arm
 * has, the angle the branches leave the spine at, and whether the arm is a
 * CRYSTAL (has a closed shape) or a FERN (does not). Everything else - the
 * lengths, the twigs, the tips - is your design.
 *
 * Coordinates are the same as Turtle Basics: (0, 0) is the TOP-LEFT, y grows
 * DOWNWARD, heading 0 is up, positive turns are clockwise. forward takes an
 * int, so compute every length from size with int arithmetic (size / 4,
 * size * 3 / 10, ...). turn accepts an int or a double.
 *
 * The one habit that makes this possible: EVERY method you write puts the
 * turtle back where it started, facing the way it started. Then drawArm can
 * call drawBranch can call drawTwig, and nobody gets lost.
 */
public class Snowflake {

    /** COMPLETE THIS LINE
     * Your full name, exactly as it appears on the class roster, e.g.
     * "Ada Lovelace". It decides your design card. Using someone else's name
     * gives you their card, and the roster will show it.
     */
    public static final String DESIGNER = "";

    /** COMPLETE THIS METHOD
     * Returns the sum of the letter values in name: A or a = 1, B or b = 2,
     * ... Z or z = 26. Everything that is not a letter (spaces, hyphens,
     * apostrophes, digits) is worth 0.
     * Examples: nameSeed("Ada Lovelace") is 81, nameSeed("ADA") is 6,
     * nameSeed("Mary-Kate O'Neil") is 149, nameSeed("") is 0.
     * Hint: a for loop over name.charAt(i). A char is a number: 'D' - 'A' is 3.
     */
    public static int nameSeed(String name) {
        int total = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                total += c - 'A' + 1;
            } else if (c >= 'a' && c <= 'z') {
                total += c - 'a' + 1;
            }
        }
        return total;
    }

    /** COMPLETE THIS METHOD
     * Precondition: size >= 60
     * Draws ONE arm of your snowflake. The turtle starts at the center of the
     * snowflake facing out along the arm. The arm must:
     *   - have a spine: a straight line from the center out (at least size / 2)
     *   - reach between 90% and 100% of size from the center
     *   - be mirror-symmetric about the spine
     *   - have exactly your number of branch pairs, all at your branch angle
     *   - follow your style: CRYSTAL has a closed shape, FERN has none
     *   - have at least 6 detail lines that do not touch the spine,
     *     at least 3 different lengths, and branches that are not all
     *     the same length
     * and finish at the center, facing out along the arm, pen DOWN, without
     * changing the pen color or width.
     */
    public static void drawArm(Turtle t, int size) {
        int seed = nameSeed(DESIGNER);
        int pairs = DesignCard.branchPairs(seed);
        int angle = DesignCard.branchAngle(seed);
        boolean crystal = DesignCard.isCrystal(seed);
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Precondition: size >= 60
     * Draws the whole snowflake: six arms of the given size, 60 degrees
     * apart, around the turtle's position. Finishes where it started, facing
     * the way it started, pen down.
     */
    public static void drawSnowflake(Turtle t, int size) {
        // Insert your code below

    }

    // Write your own helper methods below this line (drawBranchPair, drawTwigs,
    // drawTip, ...). Make them private static, and make every one of them
    // finish where it started, facing the way it started.
}
