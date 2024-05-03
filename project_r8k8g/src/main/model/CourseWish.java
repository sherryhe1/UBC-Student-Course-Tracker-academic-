package model;

// The design of toJson() method is based on JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import org.json.JSONObject;
import persistence.Writable;

// Represents a course that the student wishes to take, with
// the course name, course credits, and the past average for this course.
public class CourseWish implements Writable {

    private String courseName;
    private int courseCredit;
    private int pastAverage;

    // EFFECTS: constructs a course that the student wishes to take,
    //           with given course name, course credits, and the course's past average
    public CourseWish(String courseName, int courseCredit, int pastAverage) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.pastAverage = pastAverage;
    }

    // getters
    public String getCourseName() {
        return courseName;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public int getPastAverage() {
        return pastAverage;
    }

    // EFFECTS: put a CourseWish to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Course Name", courseName);
        json.put("Course Credit", courseCredit);
        json.put("Past Average", pastAverage);
        return json;
    }
}
