import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * PROVIDED - you do not need to change this file.
 *
 * Prints your design card, draws your snowflake (with one arm drawn again on
 * its own in the corner so you can see it up close), opens a window if your
 * machine has a screen, and saves the picture as snowflake.png in the project
 * folder. Screenshot the window (or use snowflake.png) and upload it to
 * Canvas: that picture is what gets graded. Commit and push it with your code.
 */
public class Main {

    public static void main(String[] args) {
        int seed = Snowflake.nameSeed(Snowflake.DESIGNER);
        if (Snowflake.DESIGNER.isEmpty()) {
            System.out.println("Set DESIGNER at the top of Snowflake.java to your name first.");
        }
        System.out.println(DesignCard.describe(Snowflake.DESIGNER, seed));

        World world = new World(600, 600);       // (0, 0) is the TOP-LEFT
        Turtle t = new Turtle(world);            // starts at the center, (300, 300), facing up
        t.setPenColor(new Color(40, 110, 190));  // ice blue
        t.setPenWidth(2);
        Snowflake.drawSnowflake(t, 230);

        // one arm, up close, in the bottom-left corner
        t.penUp();
        t.moveTo(70, 585);
        t.penDown();
        t.setPenColor(new Color(150, 150, 150));
        t.setPenWidth(1);
        Snowflake.drawArm(t, 120);
        t.hide();

        label(world, seed);
        world.show();                            // opens a window; does nothing without a screen
        try {
            ImageIO.write(world.getImage(), "png", new File("snowflake.png"));
            System.out.println("Saved snowflake.png. Upload a screenshot of the window (or this file) to Canvas, and push your code.");
        } catch (Exception e) {
            System.out.println("Could not save snowflake.png: " + e.getMessage());
        }
    }

    /** Writes the designer's name and card along the top of the picture. */
    private static void label(World world, int seed) {
        Graphics2D g = world.getImage().createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(new Color(60, 60, 60));
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.drawString(Snowflake.DESIGNER.isEmpty() ? "(set DESIGNER in Snowflake.java)" : Snowflake.DESIGNER, 14, 24);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        String style = DesignCard.isCrystal(seed) ? "crystal" : "fern";
        g.drawString(DesignCard.branchPairs(seed) + " branch pairs at " + DesignCard.branchAngle(seed)
                + " degrees, " + style, 14, 42);
        g.dispose();
    }
}
