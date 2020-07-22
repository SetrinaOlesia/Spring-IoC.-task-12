package com.softserve.edu.dto;

public class AverageScore {
    private String studentName;
    private double avgScore;

    public String getStudentName() {
        return studentName;
    }

    public AverageScore(String studentName, double avgScore) {
        this.studentName = studentName;
        this.avgScore = avgScore;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }
}
