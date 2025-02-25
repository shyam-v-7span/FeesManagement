package com.FeesManagement.util;
import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner scanner = new Scanner(System.in);

    // Public method to access the Scanner object
    public static Scanner getScanner() {
        return scanner;
    }

    // Close scanner
    public static void closeScanner() {
        scanner.close();
    }
}
