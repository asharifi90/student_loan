package org.alireza.menu;

import org.alireza.model.Enum.*;
import org.alireza.model.Student;
import org.alireza.model.University;
import org.alireza.util.PasswordGen;
import org.alireza.util.Validation;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    public void runMenu() {

        int userChoice = 0;

        while (true) {
            if (userChoice == 2)
                break;
            else

                System.out.println("====Student Loan System=====");
            System.out.println("What do you want to do? ");
            System.out.println("1. register");
            System.out.println("2. EXIT");

            while (!scanner.hasNextInt()) {
                System.out.println("please enter number 1 or 2");
                scanner.nextLine();
            }

            userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> studentRegister();
                case 2 -> System.out.println("good luck");
                default -> System.out.println("invalid number");
            }
        }

    }

    public void studentRegister() {

        Student student = new Student();

        University university = new University();

        String firstname = getInformation("firstname");
        student.setFirstname(firstname);

        String lastname = getInformation("lastname");
        student.setLastname(lastname);

        String fatherName = getInformation("fatherName");
        student.setFatherName(fatherName);

        String motherName = getInformation("motherName");
        student.setMotherName(motherName);

        String password = PasswordGen.generateSecurePassword();
        student.setPassword(password);

        String shomareShenasname = getValidCodeMelli("shomare shenasname");
        student.setShomareShenasname(shomareShenasname);

        String codeMelli = getValidCodeMelli(" code melli");
        student.setCodeMelli(codeMelli);

        String studentCode = getValidStudentCode();
        student.setStudentCode(studentCode);

        int entryYear = getValidEntryYear();
        student.setEntryYear(entryYear);

        LocalDate birthDate = getBirthDate();
        student.setBirthDate(birthDate);

        EducationLevel educationLevel = getEducationLevel();
        student.setEducationLevel(educationLevel);

        MaritalStatus maritalStatus = getMaritalStatus();
        student.setMaritalStatus(maritalStatus);

        boolean isDormitoryResident = isDormitoryResident();
        student.setDormitoryResident(isDormitoryResident);

        City city = getCity();
        student.setCity(city);

        String universityName = getInformation("university name");
        university.setName(universityName);

        City city1 = getUniversityCity();
        university.setCity(city);

        UniversityType universityType = getUnivesityType();
        university.setUniversityType(universityType);

        student.setUniversity(university);




    }

    private UniversityType getUnivesityType() {
        int userChoice = 0;
        UniversityType universityType = null;

        System.out.println("what is your university? ");
        System.out.println("1. Dowlati roozane ");
        System.out.println("2. Dowlati shabane");
        System.out.println("3. Gheire entefaei");
        System.out.println("4. Pardis");
        System.out.println("5. Zarfiat mazad");
        System.out.println("6. Payam noor");
        System.out.println("7. Elmi karbordi");
        System.out.println("8. Azad");

        userChoice = getValidNumber(8);

        switch (userChoice) {
            case 1 -> universityType = UniversityType.DOWLATI_ROOZANEH;
            case 2 -> universityType = UniversityType.DOWLATI_SHABANEH;
            case 3 -> universityType = UniversityType.GHEIR_ENTEFAII;
            case 4 -> universityType = UniversityType.PARDIS;
            case 5 -> universityType = UniversityType.ZARFIAT_MAZAD;
            case 6 -> universityType = UniversityType.PAYAM_NOOR;
            case 7 -> universityType = UniversityType.ELMI_KARBORDI;
            case 8 -> universityType = UniversityType.AZAD;
            default -> System.out.println("Invalid number");
        }

        return universityType;
    }


    public int getValidNumber(int numberOfChoice) {
        int number = 0;
        do {
            System.out.print("Enter a number: ");
            String input = scanner.nextLine();

            if (input.isEmpty() || input.isBlank()) {
                System.out.println("Invalid input. Please enter a number.");
            } else {
                try {
                    number = Integer.parseInt(input);
                    if (number < numberOfChoice + 1 && number > 0)
                        break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        } while (true);

        return number;
    }


    private City getUniversityCity() {

        int userChoice = 0;
        City city = null;

        System.out.println("Where is your university? ");
        System.out.println("1. Tehran");
        System.out.println("2. Big cities (Gilan, Isfahan, " +
                "Azarbayejan sharghi, Fars, Khouzestan, Qom," +
                "Khorasan razavi, Alborz");
        System.out.println("3. Other cities");

        userChoice = getValidNumber(3);

        switch (userChoice) {
            case 1 -> city = City.TEHRAN;
            case 2 -> city = City.KALANSHAHR;
            case 3 -> city = City.SAYER;
            default -> System.out.println("Invalid number");
        }

        return city;
    }

    private City getCity() {

        int userChoice = 0;
        City city = null;

        System.out.println("Where do you live? ");
        System.out.println("1. Tehran");
        System.out.println("2. Big cities (Gilan, Isfahan, " +
                "Azarbayejan sharghi, Fars, Khouzestan, Qom," +
                "Khorasan razavi, Alborz");
        System.out.println("3. Other cities");

        userChoice = getValidNumber(3);

        switch (userChoice) {
            case 1 -> city = City.TEHRAN;
            case 2 -> city = City.KALANSHAHR;
            case 3 -> city = City.SAYER;
            default -> System.out.println("Invalid number");
        }

        return city;
    }

    private Gender getGender() {

        int userChoice = 0;
        Gender gender = null;

        System.out.println("Are you:");
        System.out.println("1. Male");
        System.out.println("2. Female");

        userChoice = getValidNumber(2);

        switch (userChoice) {
            case 1 -> gender = Gender.MALE;
            case 2 -> gender = Gender.FEMALE;
            default -> System.out.println("Invalid number");
        }

        return gender;
    }

    private boolean isDormitoryResident() {

        int userChoise = 0;
        boolean isResident = false;

        System.out.println("Do you live in dormitory?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        userChoise = getValidNumber(2);

        switch (userChoise) {
            case 1 -> isResident = true;
            case 2 -> isResident = false;
            default -> System.out.println("Invalid number");
        }

        return isResident;
    }

    private EducationLevel getEducationLevel() {

        int educationLevel = 0;
        EducationLevel educationLevel1 = null;

        System.out.println("Please choose your education level");
        System.out.println("1. Kardani");
        System.out.println("2. Karshenasi");
        System.out.println("3. Karshenasi Arshad");
        System.out.println("4. Doctora herfei ya peivaste");
        System.out.println("5. Doctora napeivaste");

        educationLevel = getValidNumber(6);

        switch (educationLevel) {
            case 1 -> educationLevel1 = EducationLevel.KARDANI;
            case 2 -> educationLevel1 = EducationLevel.KARSHENASI;
            case 3 -> educationLevel1 = EducationLevel.KARSHENASI_ARSHAD;
            case 4 -> educationLevel1 = EducationLevel.DOCTORA_HERFEEI_VA_PEIVASTE;
            case 5 -> educationLevel1 = EducationLevel.DOCTORA_NAPEIVASTE;
            default -> System.out.println("Invalid number");
        }

        return educationLevel1;
    }

    private MaritalStatus getMaritalStatus() {

        int userChoice = 0;
        MaritalStatus maritalStatus = null;


        System.out.println("Please choose your marital status");
        System.out.println("1. Married");
        System.out.println("2. Single");

        userChoice = getValidNumber(2);

        switch (userChoice) {
            case 1 -> maritalStatus = MaritalStatus.MARRIED;
            case 2 -> maritalStatus = MaritalStatus.SINGLE;
            default -> System.out.println("Invalid number");
        }

        return maritalStatus;

    }

    private String getInformation(String information) {

        System.out.println("Please enter your " + information + ":");
        String input = scanner.nextLine().trim();

        while (true) {
            if (!input.isEmpty() && !input.matches("\\d+")) {
                break;
            } else {
                System.out.println("Please enter your " + information + ":");
                input = scanner.nextLine().trim();
            }
        }

        return input;
    }

    private String getValidCodeMelli(String codeMelli) {

        System.out.println("Please enter your " + codeMelli + ":");
        String input = scanner.nextLine();

        while (true) {
            if (!input.isEmpty() & Validation.validateMelliCode(input)) {
                break;
            } else {
                System.out.println("The " + codeMelli + " is not valid");
                input = scanner.nextLine();
            }
        }
        return input;
    }

    private int getValidEntryYear() {
        int entryYear;

        do {
            System.out.println("Please enter your entryYear (year -> for example 1402)");
            while (!scanner.hasNextInt()) {
                System.out.println("please enter a number");
                scanner.nextLine();
            }
            entryYear = scanner.nextInt();
            scanner.nextLine();

        } while (!Validation.isValidEntryYear(String.valueOf(entryYear)));
        return entryYear;
    }

    private String getValidStudentCode() {

        System.out.println("Please enter your student code:");
        String input = scanner.nextLine();

        while (true) {
            if (!input.isEmpty() & Validation.isValidStudentCode(input)) {
                break;
            } else {
                System.out.println("The student code is not valid");
                input = scanner.nextLine();
            }
        }
        return input;
    }

    private LocalDate getBirthDate() {

        System.out.println("Please enter your birthdate in YYYY-MM-DD format (for example: 1378-12-12");
        String input = scanner.nextLine();
        LocalDate birthDate;

        while (true) {
            if (!input.isEmpty()) {
                try {
                    birthDate = LocalDate.parse(input);
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter the right format for birthDate");
                    input = scanner.nextLine();
                }
            } else {
                System.out.println("please enter your birthDate");
                input = scanner.nextLine();
            }
        }
        return birthDate;
    }
}
