package com.iupv.demo.util;

public record StudentScoresDto(String studentID, String studentName, String birthDay, String classID, Integer inClass,
                               Integer midterm, Integer finalGrade, String description) {

}