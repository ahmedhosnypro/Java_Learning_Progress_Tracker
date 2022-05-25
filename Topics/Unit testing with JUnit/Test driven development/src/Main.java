import java.util.Objects;

class Person {
    public static final String DEFAULT_NAME = "Unknown";
    public static final int MAX_AGE = 130;
    public static final int MIN_AGE = 0;
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = Objects.isNull(name) || name.isBlank() || name.isEmpty() ? DEFAULT_NAME : name;
        this.age = age < MIN_AGE ? MIN_AGE : Math.min(age, MAX_AGE);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}