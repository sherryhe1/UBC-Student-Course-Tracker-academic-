package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseWishTest {

    private CourseWish cw;

    @BeforeEach
    void runBefore() {
        cw = new CourseWish("CPSC213", 4, 70);
    }

    @Test
    void testConstructor() {
        assertEquals("CPSC213", cw.getCourseName());
        assertEquals(4, cw.getCourseCredit());
        assertEquals(70, cw.getPastAverage());
    }
}
