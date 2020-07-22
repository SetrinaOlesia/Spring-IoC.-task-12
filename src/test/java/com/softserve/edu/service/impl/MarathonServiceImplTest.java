package com.softserve.edu.service.impl;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarathonServiceImplTest {
    private DataService dataService;
    private MarathonService marathonService;

    @BeforeEach
    public void setUp() {
        dataService = new DataServiceImpl();
        marathonService = new MarathonServiceImpl(dataService);
    }

    @Test
    public void getStudentsTest() {
        //given
        assertTrue(marathonService.getStudents().isEmpty());
        //when
        dataService.addStudent("Student");
        //then
        List<String> allStudents = marathonService.getStudents();
        assertFalse(allStudents.isEmpty());
        assertEquals("Student", allStudents.get(0));
    }

    @Test
    public void getMentorsTest() {
        //given
        assertTrue(marathonService.getMentors().isEmpty());
        //when
        dataService.addMentor("Mentor");
        //then
        List<String> allMentors = marathonService.getMentors();
        assertFalse(allMentors.isEmpty());
        assertEquals("Mentor", allMentors.get(0));
    }

    @Test
    public void studentResultTest() {
        //given
        dataService.addStudent("student_1");
        dataService.addStudent("student_2");
        dataService.addSprint("sprint_1");
        dataService.addSprint("sprint_2");
        dataService.addSolution("student_1", "sprint_1", 5);
        dataService.addSolution("student_1", "sprint_2", 3);
        dataService.addSolution("student_2", "sprint_2", 7);
        //when
        StudentScore studentScore = marathonService.studentResult("student_1");
        //then
        assertEquals("student_1", studentScore.getStudentName());
        assertEquals(2, studentScore.getSprintScore().size());
        assertEquals(5, studentScore.getSprintScore().get(0).getScore());
        assertEquals(3, studentScore.getSprintScore().get(1).getScore());
    }

    @Test
    public void allStudentsResultTest() {
        //given
        assertTrue(dataService.getAllStudents().isEmpty());
        dataService.addStudent("student_1");
        dataService.addStudent("student_2");
        dataService.addSprint("sprint_1");
        dataService.addSprint("sprint_2");
        dataService.addSolution("student_1", "sprint_1", 5);
        dataService.addSolution("student_1", "sprint_2", 3);
        dataService.addSolution("student_2", "sprint_2", 7);
        //when
        List<StudentScore> studentScores = marathonService.allStudentsResult();
        //then
        assertFalse(studentScores.isEmpty());
        assertEquals(2, studentScores.size());
        assertEquals("student_1", studentScores.get(0).getStudentName());
        assertEquals("student_2", studentScores.get(1).getStudentName());
        assertEquals(5, studentScores.get(0).getSprintScore().get(0).getScore());
        assertEquals(3, studentScores.get(0).getSprintScore().get(1).getScore());
        assertEquals(7, studentScores.get(1).getSprintScore().get(0).getScore());
    }

    @Test
    public void studentAverageTest() {
        //given
        dataService.addStudent("student_1");
        dataService.addSprint("sprint_1");
        dataService.addSprint("sprint_2");
        dataService.addSprint("sprint_3");
        dataService.addSolution("student_1", "sprint_1", 5);
        dataService.addSolution("student_1", "sprint_2", 5);
        dataService.addSolution("student_1", "sprint_3", 5);
        //when
        AverageScore average = marathonService.studentAverage("student_1");
        //then
        assertEquals(5.0, average.getAvgScore());
    }

    @Test
    public void allStudentsAverageTest() {
        //given
        dataService.addStudent("student_1");
        dataService.addStudent("student_2");
        dataService.addSprint("sprint_1");
        dataService.addSprint("sprint_2");
        dataService.addSprint("sprint_3");
        dataService.addSolution("student_1", "sprint_1", 5);
        dataService.addSolution("student_1", "sprint_2", 5);
        dataService.addSolution("student_2", "sprint_3", 5);
        //when
        List<AverageScore> averageScores = marathonService.allStudentsAverage();
        //then
        assertEquals(5.0, averageScores.get(0).getAvgScore());
        assertEquals(5.0, averageScores.get(1).getAvgScore());
    }

    @Test
    public void mentorStudentsTest() {
        //given
        dataService.addMentor("Mentor1");
        dataService.addStudent("student_1");
        dataService.addStudent("student_2");
        dataService.addCommunication("student_1", "Mentor1");
        dataService.addCommunication("student_2", "Mentor1");
        //when
        MentorStudent mentorStudent = marathonService.mentorStudents("Mentor1");
        //then
        assertEquals(2, mentorStudent.getStudentNames().size());
        assertEquals("student_1", mentorStudent.getStudentNames().get(0));
        assertEquals("student_2", mentorStudent.getStudentNames().get(1));
    }
}