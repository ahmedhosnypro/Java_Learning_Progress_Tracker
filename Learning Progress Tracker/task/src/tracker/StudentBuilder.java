package tracker;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentBuilder {
    private static final String NAME_REGEX = "([a-zA-Z]{2})|^[a-zA-Z][-'a-zA-Z ]+[a-zA-Z]$";
    private static final String ADJACENT_CHARACTERS_REGEX = "-{2,}|'{2,}|-'|'-|_{2,}|\\.{2,}";
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern ADJACENT_PATTERN = Pattern.compile(ADJACENT_CHARACTERS_REGEX);
    private static final Random random = new Random();
    private static final int ID_LENGTH = 6;

    private StudentBuilder() {
    }

    static void createNewStudent(String[] credentials) {
        if (!isInValidCredentials(credentials)) {
            Main.data.addStudent(new Student(createNewId(), credentials[0], credentials[1], credentials[2]));
            Main.lastLog = "The student has been added.";
            System.out.println(Main.lastLog);
        }
    }

    private static int createNewId() {
        StringBuilder idBuilder = new StringBuilder("1");
        for (int i = 0; i < ID_LENGTH - 1; i++) {
            idBuilder.append(random.nextInt(9));
        }
        int id = Integer.parseInt(idBuilder.toString());
        if (Main.data.getIdList().contains(id)) {
            return createNewId();
        }
        return id;
    }


    private static boolean isInValidCredentials(String[] credentials) {
        if (credentials.length != 3) {
            Main.lastLog = "Incorrect credentials.";
            System.out.println(Main.lastLog);
            return true;
        } else {
            if (isInValidName(credentials[0])) {
                Main.lastLog = "Incorrect first name.";
                System.out.println(Main.lastLog);
                return true;
            } else if (isInValidName(credentials[1])) {
                Main.lastLog = "Incorrect last name.";
                System.out.println(Main.lastLog);
                return true;
            } else if (isInValidEmail(credentials[2])) {
                Main.lastLog = "Incorrect email.";
                System.out.println(Main.lastLog);
                return true;
            } else if (isTakenEmail(credentials[2])) {
                Main.lastLog = "This email is already taken.";
                System.out.println(Main.lastLog);
                return true;
            }
            return false;
        }
    }


    private static boolean isTakenEmail(String email) {
        return Main.data.getEmailList().contains(email);
    }

    private static boolean isInValidName(String name) {
        Matcher nameMatcher = NAME_PATTERN.matcher(name);
        Matcher adjacentCharacterMatcher = ADJACENT_PATTERN.matcher(name);
        if (nameMatcher.matches()) {
            return adjacentCharacterMatcher.find();
        } else {
            return true;
        }
    }

    private static boolean isInValidEmail(String email) {
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        Matcher adjacentCharacterMatcher = ADJACENT_PATTERN.matcher(email);
        if (emailMatcher.matches()) {
            return adjacentCharacterMatcher.find();
        } else {
            return true;
        }
    }


}
