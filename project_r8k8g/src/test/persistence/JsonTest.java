package persistence;

import model.CourseTaken;
import model.CourseWish;

import static org.junit.jupiter.api.Assertions.assertEquals;

// The design is based on JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonTest {
    protected void checkCourseTaken(String courseName, int courseCredit, int sectionAverage, int grade, CourseTaken ct) {
        assertEquals(courseName, ct.getCourseName());
        assertEquals(courseCredit, ct.getCourseCredit());
        assertEquals(sectionAverage, ct.getSectionAverage());
        assertEquals(grade, ct.getGrade());
    }

    protected void checkCourseWish(String courseName, int courseCredit, int pastAverage, CourseWish cw) {
        assertEquals(courseName, cw.getCourseName());
        assertEquals(courseCredit, cw.getCourseCredit());
        assertEquals(pastAverage, cw.getPastAverage());
    }
}
