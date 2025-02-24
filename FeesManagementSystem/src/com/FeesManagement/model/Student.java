package com.FeesManagement.model;

import com.FeesManagement.enums.FeeStatus;

import java.time.LocalDate;

public class Student {
    private String studentName;
    private String enrollment;
    private int semester;
    private String department;
    private Double dueAmount;
    private FeeStatus status;
    private LocalDate dueDate;

    public Student(String studentName , String enrollment , int semester , String department , Double dueAmount , FeeStatus status,LocalDate dueDate){
        this.studentName = studentName;
        this.enrollment = enrollment;
        this.semester = semester;
        this.department = department;
        this.dueAmount = dueAmount;
        this.status = status;
        this.dueDate = dueDate;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public void setStatus(FeeStatus status) {
        this.status = status;
    }

    public boolean isPaymentLate(){
        return LocalDate.now().isAfter(dueDate);
    }

    public void displayStudentDetails() {
        System.out.println("\n----- Fee Details -----");
        System.out.println("Name: " + studentName);
        System.out.println("Enrollment: " + enrollment);
        System.out.println("Semester: " + semester);
        System.out.println("Department: " + department);
        System.out.println("Total Amount: Rs." + dueAmount);
        System.out.println("Status : "+status);
        System.out.println("Due Date : "+dueDate);
    }



}
