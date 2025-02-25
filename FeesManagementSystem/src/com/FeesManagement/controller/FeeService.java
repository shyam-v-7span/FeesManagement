package com.FeesManagement.controller;

import com.FeesManagement.DAL.StudentsCollection;
import com.FeesManagement.enums.FeeStatus;
import com.FeesManagement.model.Student;
import com.FeesManagement.util.LoggerUtil;
import com.FeesManagement.util.ScannerUtil;
import com.FeesManagement.util.ValidatorUtil;

import java.util.Map;
import java.util.Scanner;

public class FeeService {
    //object of Scanner
    Scanner scanner = ScannerUtil.getScanner();

    StudentsCollection studentsCollection = new StudentsCollection();

    Map<String, Student> studentRecords = studentsCollection.getDataOfStudents();

    //display fee details enrollment wise
    public void displayFeeDetails(){
        System.out.println("Enter Enrollment Number : ");
        String enrollment = scanner.nextLine();

        Student student = ValidatorUtil.validateStudentExist(studentRecords,enrollment);

        if (student != null) {
            student.displayStudentDetails();
        }
    }

//    public void findStudent(){
//        System.out.println("Enter Enrollment Number : ");
//        String enrollment = scanner.nextLine();
//
//        studentRecords.values().stream()
//                .filter(student -> student.getEnrollment() == enrollment)
//                .findFirst()
//                .ifPresentOrElse(
//                        Student::displayStudentDetails,
//                        ()->System.out.println("student not found")
//                );
//    }

    public void payFees(){

        System.out.println("Enter Enrollment Number : ");
        String enrollment = scanner.nextLine();

        Student student = ValidatorUtil.validateStudentExist(studentRecords,enrollment);

        if (student != null) {

            //LoggerUtil.logger.info(student.getEnrollment()+" has started fee payment.");

            //display student's details
            student.displayStudentDetails();

            double penalty = 0.0;

            if(student.isPaymentLate()){
                penalty = student.getDueAmount() * 0.10;

                //LoggerUtil.logger.info(student.getEnrollment() + "penalty added");

                System.out.println();
                System.out.println("Due Date has been gone , penalty = "+penalty+" Rs. will be applied");
                System.out.println("\n--> payable amount = "+(student.getDueAmount() + penalty));
            }

            double finalAmount = student.getDueAmount() + penalty;

            //take amount from user
            System.out.println("enter amount : ");
            double userAmount = scanner.nextDouble();
            scanner.nextLine();

            //process payment for 10 seconds
            System.out.println(FeeStatus.Processing + " Payment...");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (userAmount != finalAmount) {
                System.out.println("please enter valid amount");
                //LoggerUtil.logger.severe("Payment failed");
                System.out.println("Transaction : " + FeeStatus.Failed);
                student.setStatus(FeeStatus.Pending);
            }
            else {
                System.out.println("Payment Successful");
                //LoggerUtil.logger.severe("Payment Successful");
                student.setStatus(FeeStatus.Success);
                student.displayStudentDetails();
            }
        }

    }

    public void showMenu(){
        System.out.println("Fees Payment");

        while (true){
            System.out.println("\n1. View Fee Details");
            System.out.println("2. Submit Fees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> displayFeeDetails();
                case 2 -> payFees();
                case 3 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Please select valid option");
            }
        }
    }
}
