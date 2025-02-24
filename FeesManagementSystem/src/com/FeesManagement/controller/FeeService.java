package com.FeesManagement.controller;

import com.FeesManagement.DAL.StudentsCollection;
import com.FeesManagement.enums.FeeStatus;
import com.FeesManagement.model.Student;
import com.FeesManagement.util.ScannerUtil;

import java.time.Duration;
import java.util.Map;
import java.util.Scanner;

public class FeeService {
    //object of Scanner
    Scanner scanner = ScannerUtil.getScanner();

    StudentsCollection studentsCollection = new StudentsCollection();

    Map<String, Student> studentRecords = studentsCollection.getDataOfStudents();

    //display fee details enrollment wise
    public Student displayFeeDetails(){

        System.out.println("Enter Enrollment Number : ");
        String enrollment = scanner.nextLine();

        Student student = studentRecords.get(enrollment);

        if (student != null) {
            student.displayStudentDetails();
        } else {
            System.out.println("Student not found!");
        }
        return student;
    }

    public boolean payFees(){
        System.out.println("Enter Enrollment Number : ");

        String enrollment = scanner.nextLine();
        Student student = studentRecords.get(enrollment);

        if (student != null) {
            //display student's details
            student.displayStudentDetails();

            //take amount from user
            System.out.println("enter amount : ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount != student.getDueAmount()) {
                System.out.println("please enter valid amount");
                return false;
            }

            System.out.println(FeeStatus.Processing + " Payment...");
            try {
                Thread.sleep(Duration.ofSeconds(10));
            } catch (InterruptedException e) {
                student.setStatus(FeeStatus.Failed);
                throw new RuntimeException(e);
            }
            student.setStatus(FeeStatus.Success);
            student.displayStudentDetails();
            return true;


        }
        else {
            System.out.println("Student not found!");
            return false;
        }
    }

}
