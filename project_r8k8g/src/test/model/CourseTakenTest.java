package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTakenTest {

    private CourseTaken ct;

    @BeforeEach
    void runBefore() {
        ct = new CourseTaken("CPSC110", 4, 79, 92);
    }

    @Test
    void testConstructor() {
        assertEquals("CPSC110", ct.getCourseName());
        assertEquals(4, ct.getCourseCredit());
        assertEquals(79, ct.getSectionAverage());
        assertEquals(92, ct.getGrade());
    }
}
