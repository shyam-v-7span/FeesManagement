package com.FeesManagement.util;

import com.FeesManagement.model.Student;

import java.util.Map;

public class ValidatorUtil {

    // check if student exist or not based on enrollment
    public static Student validateStudentExist(Map<String,Student> studentRecords,String enrollment){
        Student student = studentRecords.get(enrollment);
        if(student == null){
            System.out.println("student not found");
        }
        return student;
    }
}
