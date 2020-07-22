package com.softserve.edu.service.impl;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@Service
public class MarathonServiceImpl implements MarathonService {

    private final DataService dataService;

    @Autowired
    public MarathonServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    public List<String> getStudents() {
        return dataService.getAllStudents()
                .stream()
                .map(Entity::getName)
                .collect(Collectors.toList());
    }

    public List<String> getMentors() {
        return dataService.getAllMentors()
                .stream()
                .map(Entity::getName)
                .collect(Collectors.toList());
    }

    public StudentScore studentResult(String studentName) {
        Entity student = getStudentByName(studentName);
        List<Solution> solutions = dataService.getAllSolutions().stream()
                .filter(solution -> solution.getIdStudent() == student.getId())
                .collect(Collectors.toList());
        List<SprintScore> sprintScores = new ArrayList<>();
        for (Solution solution : solutions) {
            SprintScore sprintScore = new SprintScore();
            sprintScore.setScore(solution.getScore());
            sprintScores.add(sprintScore);
            String sprintName = getSprintName(solution.getIdSprint());
            sprintScore.setSprintName(sprintName);
        }
        return new StudentScore(studentName, sprintScores);
    }

    public List<StudentScore> allStudentsResult() {
        return dataService.getAllStudents().stream()
                .map(s -> studentResult(s.getName()))
                .collect(Collectors.toList());
    }

    public AverageScore studentAverage(String studentName) {
        Entity student = getStudentByName(studentName);
        List<Solution> solutions = dataService.getAllSolutions().stream()
                .filter(solution -> solution.getIdStudent() == student.getId())
                .collect(Collectors.toList());
        int sumScore = 0;
        for (Solution solution : solutions) {
            sumScore += solution.getScore();
        }
        double avg = sumScore / solutions.size();
        return new AverageScore(studentName, avg);
    }

    public List<AverageScore> allStudentsAverage() {
        return dataService.getAllStudents().stream()
                .map(s -> studentAverage(s.getName()))
                .collect(Collectors.toList());
    }

    public MentorStudent mentorStudents(String mentorName) {
        Entity mentor = dataService.getAllMentors().stream()
                .filter(m -> m.getName().equals(mentorName))
                .findFirst()
                .get();
        List<Communication> communications = dataService.getAllCommunication().stream()
                .filter(communication -> communication.getIdMentor() == mentor.getId())
                .collect(Collectors.toList());
        List<String> studentNames = new ArrayList<>();
        for (Communication communication : communications) {
            String name = getStudentName(communication.getIdStudent());
            studentNames.add(name);
        }
        return new MentorStudent(mentorName, studentNames);
    }

    private Entity getStudentByName(String studentName) {
        return dataService.getAllStudents().stream()
                .filter(s -> s.getName().equals(studentName))
                .findFirst()
                .get();
    }

    private String getStudentName(int idStudent) {
        return dataService.getAllStudents().stream()
                .filter(student -> student.getId() == idStudent)
                .findFirst()
                .get()
                .getName();
    }

    private String getSprintName(int sprintId) {
        List<Entity> allSprints = dataService.getAllSprints();
        return allSprints.stream()
                .filter(sprint -> sprint.getId() == sprintId)
                .findFirst()
                .get()
                .getName();
    }
}
