package com.softserve.edu;

import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private MarathonService marathonService;

    @Autowired
    private DataService dataService;

//    @Autowired
//    public ApplicationTest(MarathonService marathonService) {
//        this.marathonService = marathonService;
//        fillDataService();
//    }

    private void fillDataService() {
        // TODO for dataService
    }

    @Test
    public void checkGetStudents() {
        int expected = 0;
        List<String> actual = marathonService.getStudents();
        assertEquals(expected, actual.size(), "checkGetStudents()");
    }

}
