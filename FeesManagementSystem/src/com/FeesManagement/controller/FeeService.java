package com.FeesManagement.controller;

import com.FeesManagement.DAL.StudentsCollection;
import com.FeesManagement.enums.FeeStatus;
import com.FeesManagement.model.Student;
import com.FeesManagement.util.InputUtil;
import com.FeesManagement.util.ScannerUtil;
import com.FeesManagement.util.ValidatorUtil;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class FeeService {
    //object of Scanner
    Scanner scanner = ScannerUtil.getScanner();

    // get list of student
    StudentsCollection studentsCollection = new StudentsCollection();
    Map<String, Student> studentRecords = studentsCollection.getDataOfStudents();

    //display fee details enrollment wise
    public void displayFeeDetails() {
        String enrollment = InputUtil.getValidStringValue(scanner,"Enter enrollment number : ");

        Student student = ValidatorUtil.StudentExistOrNot(studentRecords, enrollment);

        if (student != null) {
            student.displayStudentDetails();
        }
    }

    public void payFees() {

        String enrollment = InputUtil.getValidStringValue(scanner,"Enter enrollment number : ");

        Student student = ValidatorUtil.StudentExistOrNot(studentRecords, enrollment);

        if (student != null) {
            //display student's details
            student.displayStudentDetails();

            double penalty = 0.0;

            if (ValidatorUtil.isPaymentLate(student.getDueDate())) {
                penalty = student.getDueAmount() * 0.10;

                System.out.println();
                System.out.println("Due Date has been gone , penalty = " + penalty + " Rs. will be applied");
                System.out.println("\n--> payable amount = " + (student.getDueAmount() + penalty));
            }

            double finalAmount = student.getDueAmount() + penalty;
            double userAmount = InputUtil.getValidDoubleValue(scanner,"enter amount : ");

            //process payment for 10 seconds
            System.out.println(FeeStatus.Processing + " Payment...");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // check amount is valid or not
            if (userAmount != finalAmount) {
                System.err.println("please enter valid amount");
                System.err.println("Transaction : " + FeeStatus.Failed);
                student.setStatus(FeeStatus.Pending);
            } else {
                System.out.println("Payment Successful");
                student.setStatus(FeeStatus.Success);
                student.displayStudentDetails();
            }
        }

    }

    // show options to user
    public void showMenu() {

        while (true) {
            System.out.println("---- Fees Payment System ----");
            System.out.println("\n1. View Fee Details");
            System.out.println("2. Pay Fees");
            System.out.println("3. Register Student");
            System.out.println("4. Exit");

            int choice = InputUtil.getValidIntValue(scanner,"Choose an option: ");

            switch (choice) {
                case 1 -> displayFeeDetails();
                case 2 -> payFees();
                case 3 -> feesFormForAddStudent();
                case 4 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("please choose valid option");
            }


        }
    }

    public void feesFormForAddStudent(){

        String name = InputUtil.getValidStringValue(scanner, "Enter Student Name: ");
        String enrollment = InputUtil.getValidEnrollment(scanner,studentRecords);

        int semester = InputUtil.getValidIntValue(scanner, "Enter Semester: ");
        while (semester < 0 || semester>8){
            System.out.println("select between 1 to 8");
            semester = InputUtil.getValidIntValue(scanner, "Enter Semester: ");
        }

        String department = InputUtil.getValidStringValue(scanner, "Enter Department: ");
        double amount = InputUtil.getValidDoubleValue(scanner, "Enter Amount: ");
        LocalDate dueDate = InputUtil.getValidDateValue(scanner, "Enter Due Date (Y-M-D): ");

        // Create and add student to hashmap
        Student student = new Student(name,enrollment,semester,department,amount,FeeStatus.Pending, dueDate);
        studentRecords.put(enrollment, student);
    }
}
