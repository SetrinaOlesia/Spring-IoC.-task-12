package com.softserve.edu.entity;

public class Communication {
    private int idStudent;
    private int idMentor;

    public Communication(int idStudent, int idMentor) {
        this.idStudent = idStudent;
        this.idMentor = idMentor;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdMentor() {
        return idMentor;
    }

    public void setIdMentor(int idMentor) {
        this.idMentor = idMentor;
    }
}
