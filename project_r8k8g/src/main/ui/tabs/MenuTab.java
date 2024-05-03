package ui.tabs;

import ui.AcademicInfoUI;
import ui.ButtonNames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This design is based on SmartHome starter: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// Represents a MenuTab class that contains 3 buttons: load, view course-taken list info, and view course wishlist info
public class MenuTab extends Tab {

    private String studentName = getController().getStudent().getStudentName();
    private final String initGreeting = "Hello, " + studentName + "!\n Click Load to load your existing file.";
    private static final String LOAD_MESSAGE = "You have successfully loaded your file.";

    private JLabel greeting;

    // REQUIRES: AcademicInfoUI controller that holds this tab
    // EFFECTS: constructs a menu tab with a greeting and 3 buttons
    public MenuTab(AcademicInfoUI controller) {
        super(controller);

        setLayout(new GridLayout(4, 1));

        placeGreeting();

        placeLoadButton();
        placeCourseTakenButton();
        placeCourseWishButton();
    }

    // EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(initGreeting, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: adds a Load button that loads the existing file when clicked, and prints out a LOAD_MESSAGE
    private void placeLoadButton() {
        JPanel buttonRow = new JPanel();
        JButton b1 = new JButton(ButtonNames.LOAD.getValue());
        buttonRow.add(formatButtonRow(b1));

        b1.addActionListener(e -> {
            getController().loadStudent();
            greeting.setText(LOAD_MESSAGE);
        });

        this.add(buttonRow);
    }

    // EFFECTS: constructs a View CourseTaken button that switches to the CoursesTaken tab on the console
    private void placeCourseTakenButton() {
        JPanel courseTakenBlock = new JPanel();
        JButton courseTakenButton = new JButton(ButtonNames.VIEW_TAKEN_LIST_INFO.getValue());
        courseTakenBlock.add(formatButtonRow(courseTakenButton));

        courseTakenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.VIEW_TAKEN_LIST_INFO.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(AcademicInfoUI.COURSE_TAKEN_TAB_INDEX);
                }
            }
        });

        this.add(courseTakenBlock);
    }

    // EFFECTS: constructs a View CourseWish button that switches to the CourseWish tab on the console
    private void placeCourseWishButton() {
        JPanel courseWishBlock = new JPanel();
        JButton courseWishButton = new JButton(ButtonNames.VIEW_WISHLIST_INFO.getValue());
        courseWishBlock.add(formatButtonRow(courseWishButton));

        courseWishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.VIEW_WISHLIST_INFO.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(AcademicInfoUI.COURSE_WISH_TAB_INDEX);
                }
            }
        });

        this.add(courseWishBlock);
    }
}
