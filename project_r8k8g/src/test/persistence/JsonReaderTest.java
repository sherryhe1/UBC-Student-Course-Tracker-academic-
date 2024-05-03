package persistence;

import model.CourseTaken;
import model.CourseWish;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This design is based on JsonSerializationDemo that was copied and pasted
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Student s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudent() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudent.json");
        try {
            Student s = reader.read();
            assertEquals("Sherry", s.getStudentName());
            assertEquals(0, s.getCourseTakenList().size());
            assertEquals(0, s.getCourseWishList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralStudent() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudent.json");
        try {
            Student s = reader.read();
            assertEquals("Sherry", s.getStudentName());
            List<CourseTaken> courseTakenList = s.getCourseTakenList();
            List<CourseWish> courseWishList = s.getCourseWishList();
            assertEquals(3, courseTakenList.size());
            assertEquals(2, courseWishList.size());
            checkCourseTaken("MATH221", 3, 76, 97, courseTakenList.get(0));
            checkCourseTaken("CPSC110", 4, 79, 92, courseTakenList.get(1));
            checkCourseTaken("PHYS119", 1, 80, 91, courseTakenList.get(2));
            checkCourseWish("CPSC213", 4, 70, courseWishList.get(0));
            checkCourseWish("MATH200", 3, 66, courseWishList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
