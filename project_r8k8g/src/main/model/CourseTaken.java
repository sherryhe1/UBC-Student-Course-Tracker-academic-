package model;

import org.json.JSONObject;
import persistence.Writable;

// The design of toJson() method is based on JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a course that the student has already taken, with
// the course name, course credits, course average for the section in which the student was enrolled,
// and the student's grade.
public class CourseTaken implements Writable {

    private String courseName;
    private int courseCredit;
    private int sectionAverage;
    private int grade;

    // EFFECTS: constructs a course that the student has taken,
    //          with given course name, course credits, section average, and the student's grade.
    public CourseTaken(String courseName, int courseCredit, int sectionAverage, int grade) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.sectionAverage = sectionAverage;
        this.grade = grade;
    }

    // getters
    public String getCourseName() {
        return courseName;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public int getSectionAverage() {
        return sectionAverage;
    }

    public int getGrade() {
        return grade;
    }

    // EFFECTS: put a CourseTaken to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Course Name", courseName);
        json.put("Course Credit", courseCredit);
        json.put("Section Average", sectionAverage);
        json.put("Grade", grade);
        return json;
    }
}
