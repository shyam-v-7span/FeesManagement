package com.FeesManagement.util;

import com.FeesManagement.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class InputUtil {

    // Method to get a non-empty string input
    public static String getValidStringValue(Scanner scanner, String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Please enter a valid value.");
        }
    }

    // get a valid integer from user
    public static int getValidIntValue(Scanner scanner, String message) {
        int number;
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Please enter value greater than zero");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid value");
            }
        }
    }

    // get a valid double value from user
    public static double getValidDoubleValue(Scanner scanner, String message) {
        double value;
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Please enter a valid value");
                continue;
            }
            try {
                value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("value must be greater than zero");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid value");
            }
        }
    }

    // get valid date from user
    public static LocalDate getValidDateValue(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Please enter a valid date");
                continue;
            }
            try {
                LocalDate date = LocalDate.parse(input);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date (YYYY-MM-DD)");
            }
        }
    }

    public static String getValidEnrollment(Scanner scanner, Map<String, Student> studentRecords) {
        String enrollment;

        while (true) {
            enrollment = getValidStringValue(scanner, "Enter Enrollment Number: ");

            if (!ValidatorUtil.isValidEnrollment(enrollment)) {
                continue;
            }

            if (studentRecords.containsKey(enrollment)) {
                System.out.println("Enrollment already exists");
                continue;
            }

            return enrollment;
        }
    }


}

