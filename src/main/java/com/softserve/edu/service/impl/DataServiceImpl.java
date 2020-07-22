package com.softserve.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.service.DataService;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    private List<Entity> students = new ArrayList<>();
    private List<Entity> mentors = new ArrayList<>();
    private List<Entity> sprints = new ArrayList<>();
    private List<Communication> communication = new ArrayList<>();
    private List<Solution> solutions = new ArrayList<>();

    public void addStudent(String studentName) {
        students.add(new Entity(studentName));
    }

    public void addMentor(String mentorName) {
        mentors.add(new Entity(mentorName));
    }

    public void addSprint(String sprintName) {
        sprints.add(new Entity(sprintName));
    }

    public void addCommunication(String studentName, String mentorName) {
        Entity student = students.stream()
                .filter(entity -> entity.getName().equals(studentName))
                .findFirst()
                .get();
        Entity mentor = mentors.stream()
                .filter(entity -> entity.getName().equals(mentorName))
                .findFirst()
                .get();
        communication.add(new Communication(student.getId(), mentor.getId()));
    }

    public void addSolution(String studentName, String sprintName, int score) {
        Entity student = students.stream()
                .filter(entity -> entity.getName().equals(studentName))
                .findFirst()
                .get();
        Entity sprint = sprints.stream()
                .filter(entity -> entity.getName().equals(sprintName))
                .findAny()
                .get();
        Solution solution = new Solution(student.getId(), sprint.getId(), score);
        solutions.add(solution);
    }

    @Override
    public List<Entity> getAllStudents() {
        return students;
    }

    @Override
    public List<Entity> getAllMentors() {
        return mentors;
    }

    @Override
    public List<Entity> getAllSprints() {
        return sprints;
    }

    @Override
    public List<Solution> getAllSolutions() {
        return solutions;
    }

    @Override
    public List<Communication> getAllCommunication() {
        return communication;
    }

}
