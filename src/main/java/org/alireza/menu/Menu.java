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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    public void runMenu() {

        int userChoice = 0;

        System.out.println("====Student Loan System=====");
        System.out.println("What do you want to do? ");
        System.out.println("1. register");
        System.out.println("2. Get a loan");
        System.out.println("3. Refund loan");
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

    private void refundLoan() {

    }

    public void studentRegister() {

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

        LocalDate birthDate = getBirthDate("birthDate");
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
        university.setCity(city1);
        System.out.println();

        UniversityType universityType = getUnivesityType();
        university.setUniversityType(universityType);
        System.out.println();

        try {
            ApplicationContext.getUniversityService().saveOrUpdate(university);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        student.setUniversity(university);
        System.out.println();

        boolean isGraduate = isGraduate(student, LocalDate.now());
        student.setGraduate(isGraduate);

        try {
            ApplicationContext.getStudentService().saveOrUpdate(student);
            System.out.println("your registry is completed\n " +
                    "your username is your code melli\n" +
                    "and your password is " + student.getPassword());
        } catch (Exception e) {
            System.out.println("Something went wrong, please try again");
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
            case 2 -> getEducationLoan();
            case 3 -> getHousingLoan();
            case 4 -> System.out.println("good luck");
            default -> System.out.println("invalid number");
        }
    }

    public void getTuitionLoan() {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = 0;
        BankCard bankCard;
        Loan loanHistory = ApplicationContext.getLoanService().findByStudentId(student.getId(), LoanType.TUITION_LOAN);
        LocalDate startDateWinterSemester = LocalDate.of(2024, 2, 14);
        LocalDate endDateWinterSemester = LocalDate.of(2024, 2, 21);

        if (student.getUniversity().getUniversityType().equals(UniversityType.DOWLATI_ROOZANEH)) {
            System.out.println("You can't get tuition loan, because your university is dowlati roozaneh");
        } else if (loanHistory != null
                && (loanHistory.getLoanDate().isAfter((startDateWinterSemester).minusDays(1))
                && loanHistory.getLoanDate().isBefore((endDateWinterSemester.plusDays(1))))) {
            System.out.println("you can get tuituion loan only once per semester");
        } else {
            loanPrice = loanPriceForStudent(student.getEducationLevel(), LoanType.TUITION_LOAN);
            bankCard = getBankCardInformation();
            if (bankCard == null) {
                System.out.println("Please enter a valid bank card");
            } else if (ApplicationContext.getBankCardService().findByNumber(bankCard.getCardNumber()) == null) {
                bankCard.setStudent(student);
                bankCard.setBalance(loanPrice);
                ApplicationContext.getBankCardService().saveOrUpdate(bankCard);
                Loan tuitionLoan = new Loan(student.getEducationLevel(), loanPrice, PaymentMethod.ONCE_PER_TERM, null, LoanType.TUITION_LOAN, student);
                ApplicationContext.getLoanService().saveOrUpdate(tuitionLoan);
                loanInstallmentsFirstYear(tuitionLoan);
                loanInstallmentsSecondYear(tuitionLoan);
                loanInstallmentsThirdYear(tuitionLoan);
                loanInstallmentsForthYear(tuitionLoan);
                loanInstallmentsFifthYear(tuitionLoan);
                System.out.println("your loan is paid, you can check your loan installments in your dashboard");
            } else {
                BankCard savedBankCard = ApplicationContext.getBankCardService().findByNumber(bankCard.getCardNumber());
                long newBalance = savedBankCard.getBalance() + loanPrice;
                savedBankCard.setBalance(newBalance);
                ApplicationContext.getBankCardService().saveOrUpdate(savedBankCard);
                Loan tuitionLoan = new Loan(student.getEducationLevel(), loanPrice, PaymentMethod.ONCE_PER_TERM, null, LoanType.TUITION_LOAN, student);
                ApplicationContext.getLoanService().saveOrUpdate(tuitionLoan);
                loanInstallmentsFirstYear(tuitionLoan);
                loanInstallmentsSecondYear(tuitionLoan);
                loanInstallmentsThirdYear(tuitionLoan);
                loanInstallmentsForthYear(tuitionLoan);
                loanInstallmentsFifthYear(tuitionLoan);
                System.out.println("your loan is paid, you can check your loan installments in your dashboard");
            }
        }
    }

    private void getEducationLoan() {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = 0;
        BankCard bankCard;
        Loan loanHistory = ApplicationContext.getLoanService().findByStudentId(student.getId(), LoanType.EDUCATION_LOAN);
        LocalDate startDateWinterSemester = LocalDate.of(2024, 2, 14);
        LocalDate endDateWinterSemester = LocalDate.of(2024, 2, 21);

        if (loanHistory != null
                && (loanHistory.getLoanDate().isAfter((startDateWinterSemester).minusDays(1))
                && loanHistory.getLoanDate().isBefore((endDateWinterSemester.plusDays(1))))) {
            System.out.println("you can get education loan only once per semester");
        } else {
            loanPrice = loanPriceForStudent(student.getEducationLevel(), LoanType.EDUCATION_LOAN);
            bankCard = getBankCardInformation();
            if (bankCard == null) {
                System.out.println("Please enter a valid bank card");
            } else if (ApplicationContext.getBankCardService().findByNumber(bankCard.getCardNumber()) == null) {
                bankCard.setStudent(student);
                bankCard.setBalance(loanPrice);
                ApplicationContext.getBankCardService().saveOrUpdate(bankCard);
                Loan educationLoan = new Loan(student.getEducationLevel(), loanPrice, PaymentMethod.ONCE_PER_TERM, null, LoanType.EDUCATION_LOAN, student);
                ApplicationContext.getLoanService().saveOrUpdate(educationLoan);
                loanInstallmentsFirstYear(educationLoan);
                loanInstallmentsSecondYear(educationLoan);
                loanInstallmentsThirdYear(educationLoan);
                loanInstallmentsForthYear(educationLoan);
                loanInstallmentsFifthYear(educationLoan);
                System.out.println("your loan is paid, you can check your loan installments in your dashboard");
            } else {
                BankCard savedBankCard = ApplicationContext.getBankCardService().findByNumber(bankCard.getCardNumber());
                long newBalance = savedBankCard.getBalance() + loanPrice;
                savedBankCard.setBalance(newBalance);
                ApplicationContext.getBankCardService().saveOrUpdate(savedBankCard);
                Loan educationLoan = new Loan(student.getEducationLevel(), loanPrice, PaymentMethod.ONCE_PER_TERM, null, LoanType.TUITION_LOAN, student);
                ApplicationContext.getLoanService().saveOrUpdate(educationLoan);
                loanInstallmentsFirstYear(educationLoan);
                loanInstallmentsSecondYear(educationLoan);
                loanInstallmentsThirdYear(educationLoan);
                loanInstallmentsForthYear(educationLoan);
                loanInstallmentsFifthYear(educationLoan);
                System.out.println("your loan is paid, you can check your loan installments in your dashboard");
            }
        }
    }

    private void getHousingLoan() {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = 0;
        BankCard bankCard;
        Loan loanHistory = ApplicationContext.getLoanService().findByStudentId(student.getId(), LoanType.HOUSING_LOAN);
        LocalDate startDateWinterSemester = LocalDate.of(2024, 2, 14);
        LocalDate endDateWinterSemester = LocalDate.of(2024, 2, 21);

        if (loanHistory != null) {
            System.out.println("you can get Housing loan only once per level");
        } else if (!student.isDormitoryResident()) {
            System.out.println("You dont live in dormitory, so you are not eligible to get housing loan");
        } else if (student.getSpouse() == null) {
            System.out.println("you are not married, so you are not eligible to get housing loan");
        } else {
            loanPrice = housingLoanPriceForStudent(student.getCity());
            bankCard = getBankCardInformation();
            if (bankCard == null) {
                System.out.println("Please enter a valid bank card");
            } else if (ApplicationContext.getBankCardService().findByNumber(bankCard.getCardNumber()) == null) {
                bankCard.setStudent(student);
                bankCard.setBalance(loanPrice);
                ApplicationContext.getBankCardService().saveOrUpdate(bankCard);
                Loan housingLoan = new Loan(student.getEducationLevel(), loanPrice, PaymentMethod.ONCE_PER_LEVEL, student.getCity(), LoanType.HOUSING_LOAN, student);
                ApplicationContext.getLoanService().saveOrUpdate(housingLoan);
                loanInstallmentsFirstYear(housingLoan);
                loanInstallmentsSecondYear(housingLoan);
                loanInstallmentsThirdYear(housingLoan);
                loanInstallmentsForthYear(housingLoan);
                loanInstallmentsFifthYear(housingLoan);
                System.out.println("your loan is paid, you can check your loan installments in your dashboard");
            } else {
                BankCard savedBankCard = ApplicationContext.getBankCardService().findByNumber(bankCard.getCardNumber());
                long newBalance = savedBankCard.getBalance() + loanPrice;
                savedBankCard.setBalance(newBalance);
                ApplicationContext.getBankCardService().saveOrUpdate(savedBankCard);
                Loan housingLoan = new Loan(student.getEducationLevel(), loanPrice, PaymentMethod.ONCE_PER_LEVEL, student.getCity(), LoanType.HOUSING_LOAN, student);
                ApplicationContext.getLoanService().saveOrUpdate(housingLoan);
                loanInstallmentsFirstYear(housingLoan);
                loanInstallmentsSecondYear(housingLoan);
                loanInstallmentsThirdYear(housingLoan);
                loanInstallmentsForthYear(housingLoan);
                loanInstallmentsFifthYear(housingLoan);
                System.out.println("your loan is paid, you can check your loan installments in your dashboard");
            }
        }
    }

    public void getSpouseInformation() {
        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);

        String firstname = getInformation("your spouse firstname");
        String lastname = getInformation("your spouse lastname");
        String fatherName = getInformation("ِyour spouse father name");
        String motherName = getInformation("your spouse mother name");
        String codeMelli = getValidCodeMelli("your spouse national id");
        LocalDate birthDate = getBirthDate("spouse birtDate");

        Spouse spouse = new Spouse(firstname, lastname, fatherName, motherName, codeMelli, birthDate, student);
        try {
            ApplicationContext.getSpouseService().saveOrUpdate(spouse);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Something went wrong please try again");
        }
    }


    private void loanInstallmentsFirstYear(Loan loan) {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = loan.getPrice();
        LocalDate graduationDate = calGraduationDate(student);
        double firstYearInstallmentPrice = calFirstYearInstallment(loanPrice);
        LoanInstallment firstYearInstallment = new LoanInstallment(loan, graduationDate, firstYearInstallmentPrice, false);

        for (int i = 0; i < 12; i++) {
            ApplicationContext.getLoanInstallmentService().saveOrUpdate(firstYearInstallment);
//            System.out.println(firstYearInstallment);
            try {
                graduationDate = graduationDate.plusMonths(1);
            } catch (Exception e) {
                graduationDate = graduationDate.withMonth(1).plusYears(1);
            }
            firstYearInstallment = new LoanInstallment(loan, graduationDate, firstYearInstallmentPrice, false);
        }
    }

    private void loanInstallmentsSecondYear(Loan loan) {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = loan.getPrice();
        LocalDate startDate = LocalDate.of((calGraduationDate(student).getYear() + 1), calGraduationDate(student).getMonthValue(), calGraduationDate(student).getDayOfMonth());
        double secondYearInstallmentPrice = calSecondYearInstallment(loanPrice);
        LoanInstallment secondYearInstallment = new LoanInstallment(loan, startDate, secondYearInstallmentPrice, false);

        for (int i = 0; i < 12; i++) {
            ApplicationContext.getLoanInstallmentService().saveOrUpdate(secondYearInstallment);
//            System.out.println(firstYearInstallment);
            try {
                startDate = startDate.plusMonths(1);
            } catch (Exception e) {
                startDate = startDate.withMonth(1).plusYears(1);
            }
            secondYearInstallment = new LoanInstallment(loan, startDate, secondYearInstallmentPrice, false);
        }
    }

    private void loanInstallmentsThirdYear(Loan loan) {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = loan.getPrice();
        LocalDate startDate = LocalDate.of((calGraduationDate(student).getYear() + 2), calGraduationDate(student).getMonthValue(), calGraduationDate(student).getDayOfMonth());
        double thirdYearInstallmentPrice = calThirdYearInstallment(loanPrice);
        LoanInstallment thirdYearInstallment = new LoanInstallment(loan, startDate, thirdYearInstallmentPrice, false);

        for (int i = 0; i < 12; i++) {
            ApplicationContext.getLoanInstallmentService().saveOrUpdate(thirdYearInstallment);
//            System.out.println(firstYearInstallment);
            try {
                startDate = startDate.plusMonths(1);
            } catch (Exception e) {
                startDate = startDate.withMonth(1).plusYears(1);
            }
            thirdYearInstallment = new LoanInstallment(loan, startDate, thirdYearInstallmentPrice, false);
        }
    }

    public void loanInstallmentsForthYear(Loan loan) {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = loan.getPrice();
        LocalDate startDate = LocalDate.of((calGraduationDate(student).getYear() + 3), calGraduationDate(student).getMonthValue(), calGraduationDate(student).getDayOfMonth());
        double forthYearInstallmentPrice = calForthYearInstallment(loanPrice);
        LoanInstallment forthYearInstallment = new LoanInstallment(loan, startDate, forthYearInstallmentPrice, false);

        for (int i = 0; i < 12; i++) {
            ApplicationContext.getLoanInstallmentService().saveOrUpdate(forthYearInstallment);
//            System.out.println(firstYearInstallment);
            try {
                startDate = startDate.plusMonths(1);
            } catch (Exception e) {
                startDate = startDate.withMonth(1).plusYears(1);
            }
            forthYearInstallment = new LoanInstallment(loan, startDate, forthYearInstallmentPrice, false);
        }
    }

    public void loanInstallmentsFifthYear(Loan loan) {

        Student student = ApplicationContext.getStudentService().findById(SecurityContext.id);
        long loanPrice = loan.getPrice();
        LocalDate startDate = LocalDate.of((calGraduationDate(student).getYear() + 4), calGraduationDate(student).getMonthValue(), calGraduationDate(student).getDayOfMonth());
        double fifthYearInstallmentPrice = calFifthYearInstallment(loanPrice);
        LoanInstallment fifthYearInstallment = new LoanInstallment(loan, startDate, fifthYearInstallmentPrice, false);

        for (int i = 0; i < 12; i++) {
            ApplicationContext.getLoanInstallmentService().saveOrUpdate(fifthYearInstallment);
//            System.out.println(firstYearInstallment);
            try {
                startDate = startDate.plusMonths(1);
            } catch (Exception e) {
                startDate = startDate.withMonth(1).plusYears(1);
            }
            fifthYearInstallment = new LoanInstallment(loan, startDate, fifthYearInstallmentPrice, false);
        }
    }

    private boolean isGraduate(Student student, LocalDate nowDate) {

        PersianDate persianDate = PersianDate.of(student.getEntryYear(), 7, 1);
        long julianDay = persianDate.toEpochDay();
        LocalDate entryDate = LocalDate.ofEpochDay(julianDay);

        switch (student.getEducationLevel()) {
            case KARDANI, KARSHENASI_ARSHAD_NAPEIVASTE -> {
                if (nowDate.getYear() > (entryDate.getYear() + 2))
                    return true;
            }
            case KARSHENASI -> {
                if (nowDate.getYear() > (entryDate.getYear() + 4))
                    return true;
            }
            case KARSHENASI_ARSHAD_PEIVASTE -> {
                if (nowDate.getYear() > (entryDate.getYear() + 6))
                    return true;
            }
            case DOCTORA_NAPEIVASTE, DOCTORA_HERFEEI_VA_PEIVASTE -> {
                if (nowDate.getYear() > (entryDate.getYear() + 5))
                    return true;
            }
        }
        return false;
    }

    public LocalDate calGraduationDate(Student student) {

        PersianDate persianDate = PersianDate.of(student.getEntryYear(), 7, 1);
        long julianDay = persianDate.toEpochDay();
        LocalDate entryDate = LocalDate.ofEpochDay(julianDay);
        LocalDate graduationDate = null;

        switch (student.getEducationLevel()) {
            case KARDANI, KARSHENASI_ARSHAD_NAPEIVASTE ->
                    graduationDate = LocalDate.of((entryDate.getYear() + 2), 9, 23);
            case KARSHENASI -> graduationDate = LocalDate.of((entryDate.getYear() + 4), 9, 23);
            case KARSHENASI_ARSHAD_PEIVASTE -> graduationDate = LocalDate.of((entryDate.getYear() + 6), 9, 23);
            case DOCTORA_NAPEIVASTE, DOCTORA_HERFEEI_VA_PEIVASTE ->
                    graduationDate = LocalDate.of((entryDate.getYear() + 5), 9, 23);
        }

        return graduationDate;
    }

    private long loanPriceForStudent(EducationLevel educationLevel, LoanType loanType) {
        long loanPrice = 0;

        if (loanType == LoanType.EDUCATION_LOAN) {
            switch (educationLevel) {
                case KARDANI, KARSHENASI -> loanPrice = 1900000;
                case KARSHENASI_ARSHAD_PEIVASTE, KARSHENASI_ARSHAD_NAPEIVASTE, DOCTORA_HERFEEI_VA_PEIVASTE ->
                        loanPrice = 2250000;
                case DOCTORA_NAPEIVASTE -> loanPrice = 2600000;
            }
        }

        if (loanType == LoanType.TUITION_LOAN) {
            switch (educationLevel) {
                case KARDANI, KARSHENASI -> loanPrice = 1300000;
                case KARSHENASI_ARSHAD_PEIVASTE, KARSHENASI_ARSHAD_NAPEIVASTE, DOCTORA_HERFEEI_VA_PEIVASTE ->
                        loanPrice = 2600000;
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

        if (bankName == null) {
            System.out.println("your bank is not acceptable");
        } else {
            System.out.println("Your bank is " + bankName);
            cardExpiryDate = getValidExpiryDate();
            if (cardExpiryDate == null) {
                System.out.println("your card date is not valid");
            } else {
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
            } else {
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

    private double calFirstYearBaseInstallment(long loanPrice) {

        return (double) ((loanPrice / 31) / 12);
    }

    private double calFirstYearProfit(long loanPrice) {

        return (loanPrice * 0.04) / 12;
    }

    private double calFirstYearInstallment(long loanPrice) {

        return calFirstYearBaseInstallment(loanPrice) + calFirstYearProfit(loanPrice);
    }

    private double calSecondYearBaseInstallment(long loanPrice) {

        long baseLoanInstallment = loanPrice / 31;
        return (double) (baseLoanInstallment * 2) / 12;
    }

    private double calSecondYearProfit(long loanPrice) {

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - (firstYearBaseInstallment * 12);
        return (remainingLoan * 0.04) / 12;
    }

    private double calSecondYearInstallment(long loanPrice) {

        return calSecondYearBaseInstallment(loanPrice) + calSecondYearProfit(loanPrice);
    }

    private double calThirdYearBaseInstallment(long loanPrice) {

        long baseLoanInstallment = loanPrice / 31;
        return (double) (baseLoanInstallment * 4) / 12;
    }

    private double calThirdYearProfit(long loanPrice) {

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double secondYearBaseInstallment = calSecondYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - ((firstYearBaseInstallment * 12) + (secondYearBaseInstallment * 12));
        return (remainingLoan * 0.04) / 12;
    }

    private double calThirdYearInstallment(long loanPrice) {

        return calThirdYearBaseInstallment(loanPrice) + calThirdYearProfit(loanPrice);
    }

    private double calForthYearBaseInstallment(long loanPrice) {

        long baseLoanInstallment = loanPrice / 31;
        return (double) (baseLoanInstallment * 8) / 12;
    }

    private double calForthYearProfit(long loanPrice) {

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double secondYearBaseInstallment = calSecondYearBaseInstallment(loanPrice);
        double thirdYearBaseInstallment = calThirdYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - ((firstYearBaseInstallment * 12) + (secondYearBaseInstallment * 12)
                + (thirdYearBaseInstallment * 12));
        return (remainingLoan * 0.04) / 12;
    }

    private double calForthYearInstallment(long loanPrice) {

        return calForthYearBaseInstallment(loanPrice) + calForthYearProfit(loanPrice);
    }

    private double calFifthYearBaseInstallment(long loanPrice) {

        long baseLoanInstallment = loanPrice / 31;
        return (double) (baseLoanInstallment * 16) / 12;
    }

    private double calFifthYearProfit(long loanPrice) {

        double firstYearBaseInstallment = calFirstYearBaseInstallment(loanPrice);
        double secondYearBaseInstallment = calSecondYearBaseInstallment(loanPrice);
        double thirdYearBaseInstallment = calThirdYearBaseInstallment(loanPrice);
        double forthYearBaseInstallment = calForthYearBaseInstallment(loanPrice);
        double remainingLoan = loanPrice - ((firstYearBaseInstallment * 12) + (secondYearBaseInstallment * 12)
                + (thirdYearBaseInstallment * 12) + (forthYearBaseInstallment * 12));
        return (remainingLoan * 0.04) / 12;
    }

    private double calFifthYearInstallment(long loanPrice) {
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
        System.out.println("3. Karshenasi Arshad Peivaste");
        System.out.println("4. Karshenasi Arshad Napeivaste");
        System.out.println("5. Doctora herfei ya peivaste");
        System.out.println("6. Doctora napeivaste");

        educationLevel = getValidNumber(6);

        switch (educationLevel) {
            case 1 -> educationLevel1 = EducationLevel.KARDANI;
            case 2 -> educationLevel1 = EducationLevel.KARSHENASI;
            case 3 -> educationLevel1 = EducationLevel.KARSHENASI_ARSHAD_PEIVASTE;
            case 4 -> educationLevel1 = EducationLevel.KARSHENASI_ARSHAD_NAPEIVASTE;
            case 5 -> educationLevel1 = EducationLevel.DOCTORA_HERFEEI_VA_PEIVASTE;
            case 6 -> educationLevel1 = EducationLevel.DOCTORA_NAPEIVASTE;
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

    private LocalDate getBirthDate(String whomBirthDate) {

        System.out.println("Please enter your " + whomBirthDate + "in YYYY-MM-DD format (for example: 1378-12-12");
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
                System.out.println("please enter your " + whomBirthDate );
                input = scanner.nextLine();
            }
        }
        return birthDate;
    }
}
