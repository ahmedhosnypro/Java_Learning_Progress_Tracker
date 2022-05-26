
class SampleClassTests {
    SampleClass instance;

    // @BeforeAll
    static void beforeAll() {
        TestUtils.timeConsumingSetup();
    }

    // @AfterAll
    static void afterAll() {
        // TODO
    }

    // @BeforeEach
    void beforeEach() {
        instance = TestUtils.getSampleClassInstance();
    }

    // @AfterEach
    void afterEach() {
        // TODO
    }

    // @Test
    void testMethodOne() {
        Assertions.assertTrue(instance.methodOne());
    }

    // @Test
    void testMethodTwo() {
        Assertions.assertTrue(instance.methodTwo());
    }
}