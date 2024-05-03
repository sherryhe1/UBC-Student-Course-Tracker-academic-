package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The design of toJson(), courseTakenListToJson() and courseWishListToJson() methods is based on JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a student, including their name, a list of courses they have taken,
// and a list of courses they wish to take.
public class Student implements Writable {

    private String studentName;
    private List<CourseTaken> courseTakenList;
    private List<CourseWish> courseWishList;

    // EFFECTS: constructs a student with given name.
    //          Initially, set courseTakenList and courseWishList to empty.
    public Student(String studentName) {
        this.studentName = studentName;
        courseTakenList = new ArrayList<>();
        courseWishList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if the given CourseTaken is not in the courseTakenList,
    //          then add it to courseTakenList.
    public void addCourseToCourseTakenList(CourseTaken ct) {
        if (!isCourseInTakenList(ct)) {
            courseTakenList.add(ct);
            EventLog.getInstance().logEvent(new Event("Added a new course: " + ct.getCourseName()
                    + " to the course-taken list."));
        }
    }

    // MODIFIES: this
    // EFFECTS: if the given CourseWish is not in the courseWishList,
    //          then add it to courseWishList.
    public void addCourseToCourseWishList(CourseWish cw) {
        if (!isCourseInWishList(cw)) {
            courseWishList.add(cw);
            EventLog.getInstance().logEvent(new Event("Added a new course: " + cw.getCourseName()
                    + " to the course wishlist."));
        }
    }

    // REQUIRES: courseWishList must be non-empty
    // MODIFIES: this
    // EFFECTS: if the given CourseWish is in the courseWishList,
    //          then remove it from courseWishList.
    public void removeCourseFromCourseWishList(CourseWish cw)  {
        if (isCourseInWishList(cw)) {
            courseWishList.remove(cw);
            EventLog.getInstance().logEvent(new Event("Removed a course: " + cw.getCourseName()
                    + " from the course wishlist."));
        }
    }

    // EFFECTS: returns true if the given CourseTaken is in the courseTakenList,
    //          and false otherwise
    public boolean isCourseInTakenList(CourseTaken ct) {
        return courseTakenList.contains(ct);
    }

    // EFFECTS: returns true if the given CourseWish is in the courseWishList,
    //          and false otherwise
    public boolean isCourseInWishList(CourseWish cw) {
        return courseWishList.contains(cw);
    }

    // REQUIRES: courseTakenList must be non-empty
    // EFFECTS: returns a list of CourseTaken with the highest student grade in the courseTakenList.
    //          If there are multiple CourseTaken with the same and highest grade, return all of them.
    //          If there is only one CourseTaken with the highest grade, the list contains only that one CourseTaken.
    public List<CourseTaken> getHighestCourse() {
        List<CourseTaken> highestList = new ArrayList<>(courseTakenList);
        CourseTaken highest = courseTakenList.get(0);
        for (CourseTaken ct : courseTakenList) {
            if (ct.getGrade() > highest.getGrade()) {
                highestList.remove(highest);
                highest = ct;
            } else if (ct.getGrade() < highest.getGrade()) {
                highestList.remove(ct);
            }
        }
        EventLog.getInstance().logEvent(new Event("Got the course with the highest grade."));
        return highestList;
    }

    // REQUIRES: courseTakenList must be non-empty
    // EFFECTS: returns a list of CourseTaken with the lowest student grade in the courseTakenList.
    //          If there are multiple CourseTaken with the same and lowest grade, return all of them.
    //          If there is only one CourseTaken with the lowest grade, the list contains only that one CourseTaken.
    public List<CourseTaken> getLowestCourse() {
        List<CourseTaken> lowestList = new ArrayList<>(courseTakenList);
        CourseTaken lowest = courseTakenList.get(0);
        for (CourseTaken ct : courseTakenList) {
            if (ct.getGrade() < lowest.getGrade()) {
                lowestList.remove(lowest);
                lowest = ct;
            } else if (ct.getGrade() > lowest.getGrade()) {
                lowestList.remove(ct);
            }
        }
        EventLog.getInstance().logEvent(new Event("Got the course with the lowest grade."));
        return lowestList;
    }

    // REQUIRES: courseTakenList must be non-empty
    // EFFECTS: calculates the student's weighted average of all courses in the courseTakenList and returns it.
    //          Each course is weighted according to its credits.
    public double calculateStudentWeightedAverage() {
        int sum = 0;
        for (CourseTaken ct : courseTakenList) {
            sum += ct.getGrade() * ct.getCourseCredit();
        }
        int cre = 0;
        for (CourseTaken ct : courseTakenList) {
            cre += ct.getCourseCredit();
        }
        EventLog.getInstance().logEvent(new Event("Calculated the student's weighted average."));
        return (double) sum / cre;
    }

    // REQUIRES: courseTakenList must be non-empty
    // EFFECTS: calculates the weighted average of all courses in the courseTakenList using their section averages
    //          and returns it. Each course is weighted according to its credits.
    public double calculateStudentSectionedAverage() {
        int sum = 0;
        for (CourseTaken ct : courseTakenList) {
            sum += ct.getSectionAverage() * ct.getCourseCredit();
        }
        int cre = 0;
        for (CourseTaken ct : courseTakenList) {
            cre += ct.getCourseCredit();
        }
        EventLog.getInstance().logEvent(
                new Event("Calculated the section average of courses in course-taken list."));
        return (double) sum / cre;
    }

    // REQUIRES: courseWishList must be non-empty
    // EFFECTS: calculates the weighted average of all courses in the courseWishList using their past averages,
    //          and returns it. Each course is weighted according to its credits.
    public double calculateWishListPastAverage() {
        int sum = 0;
        for (CourseWish cw : courseWishList) {
            sum += cw.getPastAverage() * cw.getCourseCredit();
        }
        int cre = 0;
        for (CourseWish cw : courseWishList) {
            cre += cw.getCourseCredit();
        }
        EventLog.getInstance().logEvent(
                new Event("Calculated the section average of courses in course wishlist."));
        return (double) sum / cre;
    }

    // EFFECTS: adds the credits of all courses in the courseTakenList, and returns the total credits.
    //          If the courseTakenList is empty, returns 0.
    public int totalCreditsEarned() {
        int cre = 0;
        for (CourseTaken ct : courseTakenList) {
            cre += ct.getCourseCredit();
        }
        EventLog.getInstance().logEvent(new Event("Calculated the total credits the student has earned."));
        return cre;
    }

    // EFFECTS: adds the credits of all courses in the courseWishList, and returns the total credits.
    //          If the courseWishList is empty, returns 0.
    public int totalCreditsInWishList() {
        int cre = 0;
        for (CourseWish cw : courseWishList) {
            cre += cw.getCourseCredit();
        }
        EventLog.getInstance().logEvent(new Event("Calculated the total credits in course wishlist."));
        return cre;
    }

    // getters
    public String getStudentName() {
        return studentName;
    }

    public List<CourseTaken> getCourseTakenList() {
        return courseTakenList;
    }

    public List<CourseWish> getCourseWishList() {
        return courseWishList;
    }

    // EFFECTS: put a Student to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Student Name", studentName);
        json.put("Course-Taken List", courseTakenListToJson());
        json.put("Course Wishlist", courseWishListToJson());
        return json;
    }

    // EFFECTS: returns courseTakenList in this student as a JSON array
    private JSONArray courseTakenListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CourseTaken ct : courseTakenList) {
            jsonArray.put(ct.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns courseWishList in this student as a JSON array
    private JSONArray courseWishListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CourseWish cw : courseWishList) {
            jsonArray.put(cw.toJson());
        }

        return jsonArray;
    }
}
