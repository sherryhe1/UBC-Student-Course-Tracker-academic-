package persistence;

import model.CourseTaken;
import model.CourseWish;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This design is based on JsonSerializationDemo that was copied and pasted
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads student from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads student from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Student read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudent(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses student from JSON object and returns it
    private Student parseStudent(JSONObject jsonObject) {
        String studentName = jsonObject.getString("Student Name");
        Student s = new Student(studentName);
        addCourseTakenList(s, jsonObject);
        addCourseWishList(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses courseTakenList from JSON object and adds them to student
    private void addCourseTakenList(Student s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Course-Taken List");
        for (Object json : jsonArray) {
            JSONObject nextCourseTaken = (JSONObject) json;
            addCourseTaken(s, nextCourseTaken);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses courseWishList from JSON object and adds them to student
    private void addCourseWishList(Student s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Course Wishlist");
        for (Object json : jsonArray) {
            JSONObject nextCourseWish = (JSONObject) json;
            addCourseWish(s, nextCourseWish);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses courseTaken from JSON object and adds it to student
    private void addCourseTaken(Student s, JSONObject jsonObject) {
        String courseName = jsonObject.getString("Course Name");
        int courseCredit = jsonObject.getInt("Course Credit");
        int sectionAverage = jsonObject.getInt("Section Average");
        int grade = jsonObject.getInt("Grade");
        CourseTaken ct = new CourseTaken(courseName, courseCredit, sectionAverage, grade);
        s.addCourseToCourseTakenList(ct);
    }

    // MODIFIES: s
    // EFFECTS: parses courseWish from JSON object and adds it to student
    private void addCourseWish(Student s, JSONObject jsonObject) {
        String courseName = jsonObject.getString("Course Name");
        int courseCredit = jsonObject.getInt("Course Credit");
        int pastAverage = jsonObject.getInt("Past Average");
        CourseWish cw = new CourseWish(courseName, courseCredit, pastAverage);
        s.addCourseToCourseWishList(cw);
    }
}
