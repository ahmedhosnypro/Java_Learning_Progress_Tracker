
class CalculatorTest {
    private static final int EXPECTED = 4;

    void testAddition() {
        int output = new Calculator().add(2, 2);
        Assertions.assertEquals(EXPECTED, output);
    }
}