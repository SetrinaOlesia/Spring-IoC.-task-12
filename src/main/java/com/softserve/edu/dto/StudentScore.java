package com.softserve.edu.dto;

import java.util.List;

public class StudentScore {
    private String studentName;
    private List<SprintScore> sprintScore;

    public StudentScore(String studentName, List<SprintScore> sprintScore) {
        this.studentName = studentName;
        this.sprintScore = sprintScore;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<SprintScore> getSprintScore() {
        return sprintScore;
    }

    public void setSprintScore(List<SprintScore> sprintScore) {
        this.sprintScore = sprintScore;
    }
}
