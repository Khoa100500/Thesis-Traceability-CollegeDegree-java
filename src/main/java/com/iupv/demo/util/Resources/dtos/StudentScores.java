package com.iupv.demo.util.Resources.dtos;

import java.time.LocalDate;

public record StudentScores(String studentID, String studentName, LocalDate birthDay, String classID, Integer inClass,
                            Integer midterm, Integer finalGrade, String description) {

}