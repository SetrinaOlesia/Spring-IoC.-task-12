package com.softserve.edu.entity;

public class Solution {
    private int idStudent;
    private int idSprint;
    private int score;

    public Solution(int idStudent, int idSprint, int score) {
        this.idStudent = idStudent;
        this.idSprint = idSprint;
        this.score = score;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
