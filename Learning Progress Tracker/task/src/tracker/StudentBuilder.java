package tracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentBuilder {
    private static final String GENERAL_REGEX = "^[a-zA-Z][-.@'\\da-zA-Z ]+[a-zA-Z]$";
    private static final String NAME_REGEX = "([a-zA-Z]{2})|^[a-zA-Z][-'a-zA-Z ]+[a-zA-Z]$";
    private static final String ADJACENT_CHARACTERS_REGEX = "-{2,}|'{2,}|-'|'-|_{2,}|\\.{2,}";
    private static final String MY_EMAIL_REGEX = "[._A-Za-z\\d]+@[._A-Za-z\\d]+[.][._A-Za-z\\d]+";
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final Pattern GENERAL_PATTERN = Pattern.compile(GENERAL_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern ADJACENT_PATTERN = Pattern.compile(ADJACENT_CHARACTERS_REGEX);

    private StudentBuilder() {
    }

    static void createNewStudent(String inputCredentials) {
//        if (isInValidCredentials(inputCredentials)) {
//            return;
//        }

        String[] credentials = partCredentials(inputCredentials);
        if (!isInValidCredentials(credentials)) {
            Main.data.addStudent(new Student(credentials[0], credentials[1], credentials[2]));
            Main.lastLog = "The student has been added.";
            System.out.println(Main.lastLog);
        }
    }

    private static boolean isInValidCredentials(String inputCredentials) {
        Matcher generalMatcher = GENERAL_PATTERN.matcher(inputCredentials);
        if (generalMatcher.matches()) {
            return false;
        } else {
            Main.lastLog = "Incorrect credentials.";
            System.out.println(Main.lastLog);
            return true;
        }
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
            }
            return false;

        }
    }

    private static String[] partCredentials(String inputCredentials) {
        String[] credentials = inputCredentials.split(" ");

        if (credentials.length > 3) {
            String[] retCredentials = new String[3];
            retCredentials[0] = credentials[0];
            retCredentials[1] = "";
            retCredentials[2] = credentials[credentials.length - 1];
            for (int i = 1; i < credentials.length - 2; i++) {
                retCredentials[1] += credentials[i] + " ";
            }
            retCredentials[1] = retCredentials[1].trim();
            return retCredentials;
        }
        return credentials;
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
