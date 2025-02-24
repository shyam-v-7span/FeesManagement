package com.FeesManagement;

import com.FeesManagement.controller.FeeService;
import com.FeesManagement.util.ScannerUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = ScannerUtil.getScanner();
        FeeService feeService = new FeeService();
        System.out.println("Fees Payment");

            System.out.println("\n1. View Fee Details");
            System.out.println("2. Submit Fees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> feeService.displayFeeDetails();
                case 2 -> feeService.payFees();
                case 3 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid option! Try again.");
            }

    }
}