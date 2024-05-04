package org.alireza.menu;

import com.github.mfathi91.time.PersianDate;
import org.alireza.model.*;
import org.alireza.model.Enum.*;
import org.alireza.util.ApplicationContext;
import org.alireza.util.PasswordGen;
import org.alireza.util.Validation;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    public void runMenu() {

        int userChoice = 0;

        System.out.println("====Student Loan System=====");
        System.out.println("What do you want to do? ");
        System.out.println("1. register");
        System.out.println("2. Get a loan");
        System.out.println("2. EXIT");

        userChoice = getValidNumber(3);

        switch (userChoice) {
            case 1 -> studentRegister();
            case 2 -> getLoan();
            case 3 -> refundLoan();
            case 4 -> System.out.println("good luck");
            default -> System.out.println("invalid number");
        }


    }

    private void refundLoan(){

    }

    private void studentRegister() {

        Student student = new Student();

        University university = new University();

        String firstname = getInformation("firstname");
        student.setFirstname(firstname);
        System.out.println();

        String lastname = getInformation("lastname");
        student.setLastname(lastname);
        System.out.println();

        String fatherName = getInformation("fatherName");
        student.setFatherName(fatherName);
        System.out.println();

        String motherName = getInformation("motherName");
        student.setMotherName(motherName);
        System.out.println();

        String password = PasswordGen.generateSecurePassword();
        student.setPassword(password);
        System.out.println();

        String shomareShenasname = getValidCodeMelli("shomare shenasname");
        student.setShomareShenasname(shomareShenasname);
        System.out.println();

        String codeMelli = getValidCodeMelli(" code melli");
        student.setCodeMelli(codeMelli);
        System.out.println();

        String studentCode = getValidStudentCode();
        student.setStudentCode(studentCode);
        System.out.println();

        int entryYear = getValidEntryYear();
        student.setEntryYear(entryYear);
        System.out.println();

        LocalDate birthDate = getBirthDate();
        student.setBirthDate(birthDate);
        System.out.println();

        EducationLevel educationLevel = getEducationLevel();
        student.setEducationLevel(educationLevel);
        System.out.println();

        MaritalStatus maritalStatus = getMaritalStatus();
        student.setMaritalStatus(maritalStatus);
        System.out.println();

        boolean isDormitoryResident = isDormitoryResident();
        student.setDormitoryResident(isDormitoryResident);
        System.out.println();

        City city = getCity();
        student.setCity(city);
        System.out.println();

        String universityName = getInformation("university name");
        university.setName(universityName);
        System.out.println();

        City city1 = getUniversityCity();
        university.setCity(city);
        System.out.println();

        UniversityType universityType = getUnivesityType();
        university.setUniversityType(universityType);
        System.out.println();

        student.setUniversity(university);
        System.out.println();

        try {
            ApplicationContext.getStudentService().saveOrUpdate(student);
            System.out.println("your registry is completed \n " +
                    "your username is your code melli\n" +
                    "and your password is " + student.getPassword());
        } catch (Exception e) {
            System.out.println("something went wrong, please try again");
        }

    }

    public void studentSignIn() {

        System.out.println("Please enter your code melli:");
        String codeMelli = scanner.nextLine().trim();

        while (true) {
            if (!codeMelli.isEmpty()) {
                break;
            } else {
                System.out.println("codeMelli cant be blank, enter again");
                codeMelli = scanner.nextLine().trim();
            }
        }

        System.out.println("Please enter your passwprd: ");
        String password = scanner.nextLine().trim();

        while (true) {
            if (!password.isEmpty()) {
                break;
            } else {
                System.out.println("password cant be blank, enter again");
                password = scanner.nextLine().trim();
            }
        }

        Student student = ApplicationContext.getStudentService().signIn(codeMelli, password);

        if (student != null) {
            SecurityContext.id = student.getId();
            SecurityContext.username = student.getCodeMelli();
            getLoan();
        } else {
            System.out.println("codeMelli or password is not correct, enter again");
        }
    }

    private void getLoan() {

        int userChoice = 0;

        System.out.println("Select a loan: ");
        System.out.println("1. Tuition loan");
        System.out.println("2. Education loan");
        System.out.println("3. Housing loan");
        System.out.println("4. EXIT");

        userChoice = getValidNumber(4);

        switch (userChoice) {
            case 1 -> getTuitionLoan();
            case 2 -> getEdutionLoan();
            case 3 -> getHousingLoan();
            case 4 -> System.out.println("good luck");
            default -> System.out.println("invalid number");
        }
    }

    public void getTuitionLoan() {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = 0;
        BankCard bankCard;

        if (student.getUniversity().getUniversityType().equals(UniversityType.DOWLATI_ROOZANEH)) {
            System.out.println("You can't get tuition loan, because your university is dowlati roozaneh");
        } else {
            loanPrice = loanPriceForStudent(student.getEducationLevel(), LoanType.TUITION_LOAN);
            bankCard = getBankCardInformation();
            if(bankCard == null){
                System.out.println("Please enter a valid bank card");
            }else{
                bankCard.setStudent(student);
                bankCard.setBalance(loanPrice);
                ApplicationContext.getBankCardService().saveOrUpdate(bankCard);
                Loan tuitionLoan = new Loan(student.getEducationLevel(),loanPrice,PaymentMethod.ONCE_PER_TERM,null,LoanType.TUITION_LOAN,student);
                ApplicationContext.getLoanService().saveOrUpdate(tuitionLoan);
                System.out.println("your loan is paid");
            }
        }
    }

    private void getEdutionLoan() {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = 0;
        BankCard bankCard;



    }

    private void getHousingLoan() {

    }

    private long loanPriceForStudent(EducationLevel educationLevel, LoanType loanType) {
        long loanPrice = 0;

        if (loanType == LoanType.EDUCATION_LOAN) {
            switch (educationLevel) {
                case KARDANI, KARSHENASI -> loanPrice = 1900000;
                case KARSHENASI_ARSHAD, DOCTORA_HERFEEI_VA_PEIVASTE -> loanPrice = 2250000;
                case DOCTORA_NAPEIVASTE -> loanPrice = 2600000;
            }
        }

        if (loanType == LoanType.TUITION_LOAN) {
            switch (educationLevel) {
                case KARDANI, KARSHENASI -> loanPrice = 1300000;
                case KARSHENASI_ARSHAD, DOCTORA_HERFEEI_VA_PEIVASTE -> loanPrice = 2600000;
                case DOCTORA_NAPEIVASTE -> loanPrice = 6500000;
            }
        }
        return loanPrice;
    }

    private long housingLoanPriceForStudent(City city) {
        long loanPrice = 0;

        switch (city) {
            case TEHRAN -> loanPrice = 32000000;
            case KALANSHAHR -> loanPrice = 26000000;
            case SAYER -> loanPrice = 19500000;
        }
        return loanPrice;
    }


    public BankCard getBankCardInformation() {

        long cardNumber;
        String bankName;
        int CVV2 = 0;
        LocalDate cardExpiryDate = null;
        BankCard bankCard = null;

        System.out.println("Please enter a card number from Melli, Tejarat, Refah and maskan banks");
        cardNumber = getValidCardNumber();
        bankName = getBankName(cardNumber);

        if (bankName ==null){
            System.out.println("your bank is not acceptable");
        }else {
            System.out.println("Your bank is " + bankName);
            cardExpiryDate = getValidExpiryDate();
            if (cardExpiryDate == null){
                System.out.println("your card date is not valid");
            }else {
                do {
                    System.out.println("Enter CVV2 number : ");
                    String input = scanner.nextLine();

                    if (input.length() != 3 || !input.matches("[0-9]+")) {
                        System.out.println("Invalid input. Please enter a 3 digit number.");
                        continue;
                    }

                    try {
                        CVV2 = Integer.parseInt(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                } while (true);

                bankCard = new BankCard(bankName, cardNumber, cardExpiryDate, CVV2);
            }
        }

        return bankCard;
    }

    private LocalDate getValidExpiryDate() {

        int month = 0;
        int year = 0;
        PersianDate persianDate;
        long julianDay;
        LocalDate localDate = null;


        System.out.println("Enter expiry date (YYYY/MM): ");
        String expiryString = scanner.nextLine();

            try {
                String[] parts = expiryString.split("/");
                year = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                if (month < 4 || month > 12 || year < 1403 || year > 1413) {
                    System.out.println("Invalid date, the expiry date must be after 1403/03");
                }else {
                    persianDate = PersianDate.of(year, month, 1);
                    julianDay = persianDate.toEpochDay();
                    localDate = LocalDate.ofEpochDay(julianDay);
                }
            } catch (Exception e) {
                System.out.println("Invalid expiry date format. Please enter YYYY/MM.");
                expiryString = scanner.nextLine();
            }
        return localDate;
    }


    private long getValidCardNumber() {

        String input = "";
        long cardNumber;

        do {
            System.out.println("Enter bank card number (16 digits): ");
            input = scanner.nextLine();

            if (input.length() != 16 || !input.matches("[0-9]+")) {
                System.out.println("Invalid input. Please enter a 16 digit number.");
                continue;
            }

            try {
                cardNumber = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);

        return cardNumber;
    }

    private String getBankName(long cardNumber) {

        String number = String.valueOf(cardNumber);
        String bin = number.substring(0, 6);
        String bankName = null;

        switch (bin) {
            case "603799" -> bankName = "Melli";
            case "628023" -> bankName = "Maskan";
            case "589463" -> bankName = "Refah";
            case "627353" -> bankName = "Tejarat";
        }

        return bankName;
    }

    private double calFirstYearBaseInstallment(long loanPrice){

        return (double) ((loanPrice/31)/12);
    }

    private double calFirstYearProfit(long loanPrice){

        return (loanPrice * 0.04)/12;
    }

    private double calFirstYearInstallment(long loanPrice){

        return calFirstYearBaseInstallment(loanPrice) + calFirstYearProfit(loanPrice);
    }

    private double calSecondYearBaseInstallment(long loanPrice){

        long baseLoanInstallment = loanPrice/31;
        return (double) (baseLoanInstallment * 2) /12;
    }

    private double calSecondYearProfit(long loanPrice){

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - (firstYearBaseInstallment *12);
        return (remainingLoan * 0.04)/12;
    }

    private double calSecondYearInstallment(long loanPrice){

        return calSecondYearBaseInstallment(loanPrice) + calSecondYearProfit(loanPrice);
    }

    private double calThirdYearBaseInstallment(long loanPrice){

        long baseLoanInstallment = loanPrice/31;
        return (double) (baseLoanInstallment * 4) /12;
    }

    private double calThirdYearProfit(long loanPrice){

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double secondYearBaseInstallment = calSecondYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - ((firstYearBaseInstallment *12) + (secondYearBaseInstallment*12));
        return (remainingLoan * 0.04)/12;
    }

    private double calThirdYearInstallment(long loanPrice){

        return calThirdYearBaseInstallment(loanPrice) + calThirdYearProfit(loanPrice);
    }

    private double calForthYearBaseInstallment(long loanPrice){

        long baseLoanInstallment = loanPrice/31;
        return (double) (baseLoanInstallment * 8) /12;
    }

    private double calForthYearProfit(long loanPrice){

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double secondYearBaseInstallment = calSecondYearBaseInstallment(loanPrice);
        double thirdYearBaseInstallment = calThirdYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - ((firstYearBaseInstallment *12) + (secondYearBaseInstallment*12)
        + (thirdYearBaseInstallment*12));
        return (remainingLoan * 0.04)/12;
    }

    private double calForthYearInstallment(long loanPrice){

        return calForthYearBaseInstallment(loanPrice) + calForthYearProfit(loanPrice);
    }

    private double calFifthYearBaseInstallment(long loanPrice){

        long baseLoanInstallment = loanPrice/31;
        return (double) (baseLoanInstallment * 16) /12;
    }

    private double calFifthYearProfit(long loanPrice){

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double secondYearBaseInstallment = calSecondYearBaseInstallment(loanPrice);
        double thirdYearBaseInstallment = calThirdYearBaseInstallment(loanPrice);
        double forthYearBaseInstallment = calForthYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - ((firstYearBaseInstallment *12) + (secondYearBaseInstallment*12)
                + (thirdYearBaseInstallment*12) + (forthYearBaseInstallment *12));
        return (remainingLoan * 0.04)/12;
    }

    private double calFifthYearInstallment(long loanPrice){
        return calFifthYearBaseInstallment(loanPrice) + calFifthYearProfit(loanPrice);
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


    private int getValidNumber(int numberOfChoice) {
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
