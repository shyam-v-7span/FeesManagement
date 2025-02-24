package com.FeesManagement.util;
import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner scanner = new Scanner(System.in);

    // Public method to access the Scanner instance
    public static Scanner getScanner() {
        return scanner;
    }

    // Close scanner when needed
    public static void closeScanner() {
        scanner.close();
    }
}
