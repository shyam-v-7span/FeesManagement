package com.FeesManagement.DAL;

import com.FeesManagement.enums.FeeStatus;
import com.FeesManagement.model.Student;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StudentsCollection {

    public Map<String, Student> getDataOfStudents(){
        Map<String, Student> studentRecords = new HashMap<>();

        Student s1 = new Student("shyam","21010101197",8,"CSE",50000.0, FeeStatus.Pending, LocalDate.of(2025,01,25));
        Student s2 = new Student("utsav","21010101088",8,"CSE",50000.0, FeeStatus.Pending,LocalDate.now());
        Student s3 = new Student("jinish","21010101194",8,"CSE",50000.0,FeeStatus.Pending,LocalDate.now());

        studentRecords.put(s1.getEnrollment(),s1);
        studentRecords.put(s2.getEnrollment(),s2);
        studentRecords.put(s3.getEnrollment(),s3);

        return studentRecords;
    }




}
