package persistence;

import model.CourseTaken;
import model.CourseWish;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The design is based on JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Student s = new Student("Sherry");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudent() {
        try {
            Student s = new Student("Sherry");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudent.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStudent.json");
            s = reader.read();
            assertEquals("Sherry", s.getStudentName());
            assertEquals(0, s.getCourseTakenList().size());
            assertEquals(0, s.getCourseWishList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudent() {
        try {
            Student s = new Student("Sherry");
            CourseTaken ct1 = new CourseTaken("MATH221", 3, 76, 97);
            CourseTaken ct2 = new CourseTaken("CPSC110", 4, 79, 92);
            CourseTaken ct3 = new CourseTaken("PHYS119", 1, 80, 91);
            s.addCourseToCourseTakenList(ct1);
            s.addCourseToCourseTakenList(ct2);
            s.addCourseToCourseTakenList(ct3);
            CourseWish cw1 = new CourseWish("CPSC213", 4, 70);
            CourseWish cw2 = new CourseWish("MATH200", 3, 66);
            s.addCourseToCourseWishList(cw1);
            s.addCourseToCourseWishList(cw2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudent.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudent.json");
            s = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
