package org.alireza.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern STUDENT_CODE_PATTERN;

    private static final Pattern ENTRY_YEAR_PATTERN;
    private static final Pattern FIRST_NAME_PATTERN;

    static {

        STUDENT_CODE_PATTERN = Pattern.compile("^(139[0-9]|140[0-3])[0-9]{3}$");

        ENTRY_YEAR_PATTERN = Pattern.compile("^139[0-9]|140[0-3]$");

        FIRST_NAME_PATTERN = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");

    }

    public static boolean isValidFirstName(String firstname){
        Matcher matcher = FIRST_NAME_PATTERN.matcher(firstname);
        return matcher.find();
    }
    public static boolean isValidStudentCode(String studentCode){
        Matcher matcher = STUDENT_CODE_PATTERN.matcher(studentCode);
        return matcher.find();
    }

    public static boolean isValidEntryYear(String entryYear){
        Matcher matcher = ENTRY_YEAR_PATTERN.matcher(entryYear);
        return matcher.find();
    }

    public static boolean validateMelliCode(String melliCode) {

        String[] identicalDigits = {"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};

        if (melliCode.trim().isEmpty()) {
            System.out.println("empty");
            return false; // National Code is empty
        } else if (melliCode.length() != 10) {
            System.out.println(" must be exactly 10 digits");
            return false; // National Code is less or more than 10 digits
        } else if (Arrays.asList(identicalDigits).contains(melliCode)) {
            System.out.println("is not valid ");
            return false; // Fake National Code
        } else {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(melliCode.charAt(i)) * (10 - i);
            }

            int lastDigit;
            int divideRemaining = sum % 11;

            if (divideRemaining < 2) {
                lastDigit = divideRemaining;
            } else {
                lastDigit = 11 - (divideRemaining);
            }

            if (Character.getNumericValue(melliCode.charAt(9)) == lastDigit) {
                System.out.println(" valid");
                return true;
            } else {
                System.out.println(" is not valid");
                return false; // Invalid MelliCode
            }
        }
    }
    


}
