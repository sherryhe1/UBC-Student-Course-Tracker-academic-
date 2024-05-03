package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.CourseTakenTab;
import ui.tabs.CourseWishTab;
import ui.tabs.MenuTab;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;


// This design is based on SmartHome starter: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// Personal academicInfo application - graphical user interface
public class AcademicInfoUI extends JFrame {

    public static final int MENU_TAB_INDEX = 0;
    public static final int COURSE_TAKEN_TAB_INDEX = 1;
    public static final int COURSE_WISH_TAB_INDEX = 2;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private JTabbedPane sidebar;
    private Student student;

    private static final String JSON_STORE = "./data/student.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: creates AcademicInfoUI, initiates jsonWriter and jsonReader, and displays sidebar
    public AcademicInfoUI() {
        super("AcademicInfo Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        init();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);

        loadTabs();
        add(sidebar);

        setVisible(true);

        windowClosePrintLog();
    }

    // MODIFIES: this
    // EFFECTS: initializes a Student
    private void init() {
        student = new Student("Sherry");
        CourseTaken ct1 = new CourseTaken("MATH221", 3, 76, 97);
        CourseTaken ct2 = new CourseTaken("CPSC110", 4, 79, 92);
        CourseTaken ct3 = new CourseTaken("PHYS119", 1, 80, 91);
        CourseWish cw1 = new CourseWish("CPSC213", 4, 70);
        CourseWish cw2 = new CourseWish("MATH200", 3, 66);

        student.addCourseToCourseTakenList(ct1);
        student.addCourseToCourseTakenList(ct2);
        student.addCourseToCourseTakenList(ct3);

        student.addCourseToCourseWishList(cw1);
        student.addCourseToCourseWishList(cw2);
    }

    // MODIFIES: this
    // EFFECTS: adds menu tab, courseTaken tab and courseWish tab to this UI
    private void loadTabs() {
        JPanel menuTab = new MenuTab(this);
        JPanel courseTakenTab = new CourseTakenTab(this);
        JPanel courseWishTab = new CourseWishTab(this);

        sidebar.add(menuTab, MENU_TAB_INDEX);
        sidebar.setTitleAt(MENU_TAB_INDEX, "Menu");
        sidebar.add(courseTakenTab, COURSE_TAKEN_TAB_INDEX);
        sidebar.setTitleAt(COURSE_TAKEN_TAB_INDEX, "Courses Taken");
        sidebar.add(courseWishTab, COURSE_WISH_TAB_INDEX);
        sidebar.setTitleAt(COURSE_WISH_TAB_INDEX, "Courses in Wishlist");
    }

    // windowClosePrintLog() is designed based on:
    // https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
    // EFFECTS: prints all the events that have been logged to the console after the user quits the application
    private void windowClosePrintLog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDescription());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads student from file
    public void loadStudent() {
        try {
            student = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns sidebar controlled by this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    // EFFECTS: returns Student object controlled by this UI
    public Student getStudent() {
        return student;
    }

    // EFFECTS: returns jsonWriter of this UI
    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    // EFFECTS: returns jsonReader of this UI
    public String getJsonStore() {
        return JSON_STORE;
    }
}
