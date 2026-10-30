# Turtle Snowflake

**Unit 2 · Graphics, GUI & Applied Class Design** · The turtle unit's last drawing · builds on 2.6 (Turtle Basics) and 2.7 (Turtle Drawing Algorithms)

No two snowflakes are alike, and in this room no two will be. You will design
**one arm** of a snowflake, then draw it six times, 60 degrees apart. Your
**name** decides three numbers on your **design card**: how many branch pairs
each arm has, the angle the branches leave the spine at, and whether your
arm is a CRYSTAL or a FERN. Everything else is yours: the lengths, the twigs,
the tips, the shapes.

This assignment is graded **by eye, from a screenshot**: there is no
autograder. `Main` writes your name and your card at the top of the picture,
and draws one arm on its own in the corner, so everything in the rubric can be
checked by looking. Read the rules once before you draw anything.

---

## Step 1: your design card

At the top of `Snowflake.java`:

```java
public static final String DESIGNER = "";   // your full name, as on the roster
```

Then write `nameSeed(String name)`: add up the letter values, **A or a = 1,
B or b = 2, ... Z or z = 26**. Spaces, hyphens, apostrophes and digits are
worth nothing. That number is your **seed**, and the card comes from it:

| on the card | rule | possible values |
|---|---|---|
| branch pairs | `2 + seed % 3` | 2, 3 or 4 |
| branch angle | `(seed / 3) % 3` is 0, 1 or 2 | 30, 45 or 60 degrees |
| style | `(seed / 9) % 2` is 0 or 1 | CRYSTAL or FERN |

Worked example, `"Ada Lovelace"`:

```
A1 + D4 + A1 + L12 + O15 + V22 + E5 + L12 + A1 + C3 + E5 = 81
branch pairs:  81 % 3 = 0          ->  2 + 0 = 2 pairs
branch angle:  81 / 3 = 27, 27 % 3 = 0  ->  30 degrees
style:         81 / 9 = 9,  9 % 2 = 1   ->  FERN
```

That is 18 different cards. Run `Main` once `nameSeed` works: it prints your
card at the top of the console. Write it on your sketch.

**Your card is yours.** Your name is printed on your screenshot next to your
card, so a friend's design under your name almost never matches: their numbers
are not your numbers. A screenshot with someone else's name, or a card that
doesn't match your name, scores 0 for the design card.

---

## Step 2: the anatomy of an arm

`drawArm(Turtle t, int size)` starts with the turtle at the **center** of
the snowflake, facing **out** along the arm.

```
                  <>            tip: a closed shape may sit here
          \/      |      \/     twigs: DETAIL lines, they never touch the spine
            \     |     /
              \   |   /         branch pair 2 (shorter)
          \/    \ | /    \/
            \     |     /
              \   |   /         branch pair 1 (longer)
                \ | /
                  |             the SPINE
                  *             center: start here facing out, finish here
```

| word | what it means here |
|---|---|
| **spine** | the straight line from the center out along the direction the turtle started facing. At least `size / 2` long. |
| **branch point** | a place between the center and the end of the spine where any line touches the spine. Lines that meet the spine at the center or at its very end (the tip) do not count. |
| **branch angle** | the angle between a branch and the spine's **outward** direction. From facing out, `turn(-angle)` aims the left branch and `turn(angle)` the right one. |
| **detail line** | a line with **neither** end on the spine: twigs, crystals on branch ends, shapes beyond the tip. |
| **closed shape** | lines that join up into a loop, corner to corner, like a polygon whose last side ends where its first began. |
| **reach** | how far the farthest drawn point is from the center. |

```
    facing out                    turn(-30)  turn(30)
        ^                             \   ^   /
        |   / 45                       \  |  /      a 30-degree pair
        |  /                            \ | /
        | /                              \|/
        |/                                *
        *
```

It never matters **how** you drew a line: one `forward` or five, pen down on
the way back or pen up. Only the picture counts.

---

## Step 3: the rules

These are what the rubric checks. Rules 1 to 8 are about **one arm**; look
at the close-up arm in the corner of your picture to check them.

1. **Comes home.** `drawArm` ends at the center, facing out the way it
   started, pen **down**, pen color and width untouched.
2. **Has a spine** at least `size / 2` long, and draws nothing behind the center.
3. **Reaches 90% to 100% of `size`.** `drawArm(t, 150)` reaches between 135
   and 150. Every length comes from `size`: `forward(size / 4)`, never
   `forward(30)`, so the arm in the corner (size 120) is the same design as
   the arms of the big snowflake (size 230), just smaller.
4. **Mirror-symmetric.** Every line has a twin on the other side of the spine.
5. **Your branch pairs.** Exactly your card's number of branch points.
6. **Your branch angle.** Every line that touches the spine between the
   center and the tip leaves it at your card's angle, measured from the
   outward direction. Nothing crosses the spine.
7. **Your style.**
   * **CRYSTAL:** at least one closed shape in each arm. A hexagon at the tip, a
     diamond on a branch end, a triangle between two twigs.
   * **FERN:** no closed shapes anywhere. Every line ends in open air. Two twigs
     that meet close a loop, so watch your twig lengths.
8. **Not a stick.** At least **6 detail lines** (3 on each side), at least
   **3 different lengths**, and the branches are **not all the same length**.

And for the whole snowflake:

9. `drawSnowflake(Turtle t, int size)` is `drawArm` six times with `turn(60)`
   after each: exactly six arms, 60 degrees apart, ending at the center
   facing the way it started.

Anything not on this list is your call: where along the spine the branches
sit, how long each one is, how many twigs, what angle the twigs leave the
branch at, what shapes go where.

---

## Step 4: build it from helpers that come home

The one habit that makes this possible: **every method you write puts the
turtle back where it started, facing the way it started.** Then `drawArm`
can call `drawBranchPair`, which calls `drawBranch`, which calls `drawTwigs`,
and nobody gets lost. It is the `drawHouse` lesson from Turtle Basics, all
the way down.

Here is the one helper everybody needs. Write it first:

```java
/** A left and a right branch at the given angle. Ends where it started, facing out. */
private static void drawBranchPair(Turtle t, int length, int angle) {
    t.turn(-angle);          // aim the left branch
    t.forward(length);
    t.backward(length);      // back to the spine
    t.turn(2 * angle);       // swing across to the right branch
    t.forward(length);
    t.backward(length);
    t.turn(-angle);          // facing out along the spine again
}
```

Count the turns: `-angle + 2 * angle - angle = 0`. That zero is why the
turtle faces out again. Every helper you write needs its turns to add up to
zero (or 360) and its moves to bring it back.

Then make it yours. Replace the plain `forward(length); backward(length);`
with a call to your own `drawBranch`, one that walks out, draws some twigs or
a crystal on the way or at the end, and walks back. Angles **inside** a
branch are measured from the branch, not the spine, and they are not limited
by your card. Only lines that touch the spine have to use your angle.

`drawArm` is then a walk up the spine:

```
forward to branch point 1, drawBranchPair(...)
forward to branch point 2, drawBranchPair(...)
...
forward to the end of the spine, maybe draw a tip
back down the spine to the center
```

`drawSnowflake` is the shortest method you will write this unit. 6 x 60 = 360.

If you want a polygon (CRYSTAL), copy your `drawPolygon` from Turtle Drawing
Algorithms into `Snowflake` as a private helper. To stand a polygon on its
corner at the end of a line, symmetric about that line, turn left by **half
its inside angle** first (60 for a hexagon, 45 for a square, 30 for a
triangle), draw it with right turns, then turn right by the same amount.

---

## Traps

* **The branch angle is measured from the spine's outward direction.** A 30
  degree branch leans out steeply, close to the spine; a 60 degree branch
  spreads wide. Neither one is measured from horizontal.
* **A closed shape that touches the spine between the center and the tip
  counts as branches**, at angles that are almost never yours. Put shapes at
  the tip or out on the branches.
* **Lengths from `size`, in `int` arithmetic.** `forward` takes an `int`.
  `size * 3 / 10` and `size / 10 * 3` are both legal and both fine, but
  they can differ by a pixel; pick one per length and use it on both sides.
* **Mirror twins.** If the left branch is `drawBranch(t, size / 4)` the right
  one must be too. Draw both sides with the same helper and the same
  arguments and symmetry comes free.
* **FERN loops.** Twigs from neighboring branches that touch close a loop.
  Keep twigs short.
* **60 degree cards:** a long branch at 60 runs into the next arm. It still
  passes, but your flake looks better with shorter outer branches.

---

## What you are given

| File | Status | Purpose |
|---|---|---|
| `src/main/java/Snowflake.java` | **you complete this** | `DESIGNER`, `nameSeed`, `drawArm`, `drawSnowflake`, and your own helpers |
| `src/main/java/DesignCard.java` | provided | turns a seed into your card; `Main` uses it to print the card |
| `src/main/java/Turtle.java` | provided | the same turtle as Turtle Basics |
| `src/main/java/World.java` | provided | the canvas |
| `src/main/java/Main.java` | provided | prints your card, draws your snowflake and one arm up close, labels the picture, saves `snowflake.png` |

## Suggested order

1. `DESIGNER` and `nameSeed`. Run `Main`: check the card it prints against the
   Ada Lovelace example above, then against your own name worked by hand.
2. **Sketch one arm on paper** with your card's numbers before you type any
   drawing code. Mark the branch points, the angle, the twigs, the shape (or
   the lack of one).
3. `drawSnowflake` (yes, now). Then every change to `drawArm` shows up six
   times in `Main`.
4. `drawArm` with just the spine, coming home. Run `Main`: six lines from the
   center, and the turtle back in the middle.
5. Add `drawBranchPair` at your branch points. Count them and check the angle.
6. Add twigs, crystals and tips until every rule in Step 3 holds.
7. Run `Main`, look at it, change what you don't like, run it again.

## Submitting

1. Run `Main`. A window opens with your snowflake, and the same picture is
   saved as `snowflake.png` in your project folder.
2. Take a **screenshot of the whole window** (or use `snowflake.png`). Your
   name and card must be readable across the top, and the close-up arm must be
   visible in the corner.
3. **Upload the screenshot to the Canvas assignment.**
4. Commit and push your code (and `snowflake.png`) to your GitHub repo.

## Rubric (20 points)

| What I look for in your screenshot | Points |
|---|---|
| Your own name and a card that matches it are printed across the top (`DESIGNER` and `nameSeed` work) | 2 |
| Six identical arms, evenly spaced 60 degrees apart, meeting at the center | 3 |
| Every arm is mirror-symmetric about its spine | 3 |
| Exactly your card's number of branch pairs, all leaving the spine at your card's angle | 4 |
| Your style: CRYSTAL arms contain a closed shape; FERN arms contain none | 3 |
| Not a stick: at least 3 detail lines on each side of every arm, at least 3 different lengths, branches not all the same length | 4 |
| Craft: clean, deliberate, your own design (nothing crossing the spine, the close-up arm matches the big one) | 1 |

## Rules of the road

- AP Java subset only: `int`, `double`, `char`, `String` methods, `if`,
  `for` loops, and calls to your own methods. Helpers are `private static`.
- Never `moveTo` or `setHeading` inside `Snowflake`. Draw relative to wherever
  the turtle is; that is what lets one `drawArm` draw all six arms.
- Do not change method headers or provided code. `Turtle.java`, `World.java`,
  `DesignCard.java` and `Main.java` are yours to read, not to edit. The
  picture `Main` draws is the one that gets graded.
- `drawArm` and `drawSnowflake` must leave the pen **down** and must not call
  `setPenColor`, `setPenWidth` or `setColor`. `Main` chose those.
- `DESIGNER` is your own name. The design is your own design.
