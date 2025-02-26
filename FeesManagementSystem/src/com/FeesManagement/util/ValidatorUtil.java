package com.FeesManagement.util;

import com.FeesManagement.model.Student;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class ValidatorUtil {
    Scanner scanner = ScannerUtil.getScanner();
    String str = scanner.nextLine();

    // check if student exist or not based on enrollment
    public static Student StudentExistOrNot(Map<String,Student> studentRecords,String enrollment){
        Student student = studentRecords.get(enrollment);

        if(student == null){
            System.out.println("student not found");
        }
        return student;
    }

    public static boolean isValidEnrollment(String enrollment) {
        if(enrollment != null && enrollment.length() == 11){
            return true;
        } else {
            System.out.println("Please enter valid enrollment");
            return false;
        }
    }

    public static boolean isPaymentLate(LocalDate dueDate){
        return LocalDate.now().isAfter(dueDate);
    }

}
