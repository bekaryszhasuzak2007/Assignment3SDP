public class Main {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {

        if (args.length != 1 || !args[0].equals("--demo")) {
            System.out.println("Run with: java -cp out Main --demo");
            return;
        }

        Renderer vectorRenderer = new VectorRenderer();
        Renderer rasterRenderer = new RasterRenderer();

        // T1
        Circle circle1 = new Circle("C1", vectorRenderer);

        check(
                "T1",
                "Circle + VectorRenderer",
                circle1.execute(),
                "VECTOR circle radius=2"
        );

        // T2
        Circle circle2 = new Circle("C2", rasterRenderer);

        check(
                "T2",
                "Circle + RasterRenderer",
                circle2.execute(),
                "RASTER circle radius=2"
        );

        // T3
        Square square1 = new Square("S1", vectorRenderer);

        check(
                "T3",
                "Square + VectorRenderer",
                square1.execute(),
                "VECTOR square side=3"
        );

        // T4
        Square square2 = new Square("S2", rasterRenderer);

        check(
                "T4",
                "Square + RasterRenderer",
                square2.execute(),
                "RASTER square side=3"
        );

        // T5
        Circle circle5 = new Circle("C5", vectorRenderer);

        Circle originalCircle = circle5;

        String originalId = circle5.getId();
        int originalRadius = circle5.getRadius();

        String before = circle5.execute();

        circle5.setImplementation(rasterRenderer);

        String after = circle5.execute();

        boolean sameObject = circle5 == originalCircle;

        boolean stateUnchanged =
                circle5.getId().equals(originalId)
                        && circle5.getRadius() == originalRadius;

        boolean t5Pass =
                sameObject
                        && stateUnchanged
                        && before.equals("VECTOR circle radius=2")
                        && after.equals("RASTER circle radius=2");

        total++;

        if (t5Pass) {
            passed++;

            System.out.println(
                    "T5 PASS | sameObject=" + sameObject
                            + " | stateUnchanged=" + stateUnchanged
                            + " | before=" + before
                            + " | after=" + after
            );
        } else {
            System.out.println(
                    "T5 FAIL | sameObject=" + sameObject
                            + " | stateUnchanged=" + stateUnchanged
                            + " | before=" + before
                            + " | after=" + after
            );
        }

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static void check(
            String testId,
            String classes,
            String actual,
            String expected) {

        total++;

        if (actual.equals(expected)) {
            passed++;

            System.out.println(
                    testId + " PASS | "
                            + classes
                            + " | result=" + actual
            );
        } else {
            System.out.println(
                    testId + " FAIL | "
                            + classes
                            + " | actual=" + actual
                            + " | expected=" + expected
            );
        }
    }
}