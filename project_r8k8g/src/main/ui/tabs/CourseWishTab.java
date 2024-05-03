package ui.tabs;

import model.CourseWish;
import ui.AcademicInfoUI;
import ui.ButtonNames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

// This design is based on SmartHome starter: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// Represents a CourseWishTab class that contains 5 buttons: view course wishlist, add a CourseWish,
// calculate section average, calculate total credits, and save
public class CourseWishTab extends Tab {

    private static final String VIEW_COURSES_WISH_MESSAGE = "Below is a list of courses that you wish to take:";
    private static final String SAVE_MESSAGE = "You have successfully saved your file.";

    private JScrollPane reportPane;
    private JTextArea reportText;
    private JLabel reportMessage;

    private JScrollPane reportPane2;
    private JTextArea reportText2;
    private JLabel reportMessage2;

    // REQUIRES: AcademicInfoUI controller that holds this tab
    // EFFECTS: constructs a CourseWishTab for console with 5 buttons related to course wishlist
    public CourseWishTab(AcademicInfoUI controller) {
        super(controller);

        displayViewWishlist();

        placeAddCourseWishButton();
        placeCalcSectionAvgButton();
        displayCalcCredits();

        placeSaveButton();
    }

    // MODIFIES: this
    // EFFECTS: displays a View Course Wishlist button and associated texts and window
    private void displayViewWishlist() {
        placeViewWishlistButton();

        JPanel reportBlock = new JPanel(new GridLayout(2, 1));
        reportBlock.setSize(AcademicInfoUI.WIDTH - (AcademicInfoUI.WIDTH / 5),
                AcademicInfoUI.HEIGHT - (AcademicInfoUI.HEIGHT / 5));
        reportMessage = new JLabel("");
        reportPane = new JScrollPane(new JTextArea(6, 45));
        reportText = new JTextArea("", 6, 45);
        reportText.setVisible(true);

        reportBlock.add(reportMessage);
        reportBlock.add(reportPane);

        this.add(reportBlock);
    }

    // MODIFIES: this
    // EFFECTS: adds a View Course Wishlist button that prints out all courses in course wishlist when clicked
    private void placeViewWishlistButton() {
        JButton b1 = new JButton(ButtonNames.VIEW_WISHLIST.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.VIEW_WISHLIST.getValue())) {
                    reportMessage.setText(VIEW_COURSES_WISH_MESSAGE);
                    reportText.setText(printWishList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: adds an Add CourseWish button that asks the user to add a course to wishlist when clicked
    private void placeAddCourseWishButton() {
        JButton b1 = new JButton(ButtonNames.ADD_TO_WISHLIST.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.ADD_TO_WISHLIST.getValue())) {
                    addCourseWishList();
                    reportText.setText(printWishList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: adds a Calculate Section Average button that calculates the section average
    private void placeCalcSectionAvgButton() {
        JButton b1 = new JButton(ButtonNames.CALC_SEC_AVG.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.CALC_SEC_AVG.getValue())) {
                    String avg = Double.toString(printPavgWishList());
                    String avgMsg = "The weighted section average is " + avg + ".";
                    reportText2.setText(avgMsg);
                    reportPane2.setViewportView(reportText2);

                    reportText.setText(printWishList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: displays a Calculate Credit button and associated texts and window
    private void displayCalcCredits() {
        placeCalcCreditsButton();

        JPanel reportBlock2 = new JPanel(new GridLayout(2, 1));
        reportBlock2.setSize(AcademicInfoUI.WIDTH - (AcademicInfoUI.WIDTH / 5),
                AcademicInfoUI.HEIGHT - (AcademicInfoUI.HEIGHT / 5));
        reportMessage2 = new JLabel("");
        reportPane2 = new JScrollPane(new JTextArea(6, 45));
        reportText2 = new JTextArea("", 6, 45);
        reportText2.setVisible(true);

        reportBlock2.add(reportMessage2);
        reportBlock2.add(reportPane2);

        add(reportBlock2);
    }

    // MODIFIES: this
    // EFFECTS: adds a Calculate Credit button that calculates the total credits in course wishlist
    private void placeCalcCreditsButton() {
        JButton b1 = new JButton(ButtonNames.CALC_CREDITS_WISH.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.CALC_CREDITS_WISH.getValue())) {
                    String credits = Integer.toString(printCreditsWishList());
                    String creditsMsg = "The total number of credits in the course wishlist is " + credits + ".";
                    reportText2.setText(creditsMsg);
                    reportPane2.setViewportView(reportText2);

                    reportText.setText(printWishList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: adds a Save button that saves the program when clicked
    private void placeSaveButton() {
        JButton b1 = new JButton(ButtonNames.SAVE.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.SAVE.getValue())) {
                    reportMessage.setText(SAVE_MESSAGE);
                    saveStudent();
                    reportText.setText(printWishList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // EFFECTS: returns all courses in the course wishlist
    private String printWishList() {
        List<CourseWish> wishlist = getController().getStudent().getCourseWishList();
        String courseWishlist = "";
        for (CourseWish cw : wishlist) {
            String name = cw.getCourseName();
            int credit = cw.getCourseCredit();
            int avg = cw.getPastAverage();
            courseWishlist += ("Course name: " + name + ", " + "Course credit: " + credit + ", "
                    + "Past average: " + avg + "\n");
        }
        return courseWishlist;
    }

    // EFFECTS: asks the user to add a course to the course wishlist if that course isn't in the list
    private void addCourseWishList() {
        String name = JOptionPane.showInputDialog("Course Name: ");
        int credit = Integer.parseInt(JOptionPane.showInputDialog("Course Credit:"));
        int avg = Integer.parseInt(JOptionPane.showInputDialog("Course Average: "));
        CourseWish cwNew = new CourseWish(name, credit, avg);

        List<String> courseNamesInList = new ArrayList<>();
        for (CourseWish cw : getController().getStudent().getCourseWishList()) {
            courseNamesInList.add(cw.getCourseName());
        }

        if (!courseNamesInList.contains(name)) {
            getController().getStudent().addCourseToCourseWishList(cwNew);
            printWishList();
        }
    }

    // EFFECTS: returns the weighted average based on past averages for all courses in the course wishlist
    private double printPavgWishList() {
        return getController().getStudent().calculateWishListPastAverage();
    }

    // EFFECTS: returns the total credits in the course wishlist
    private int printCreditsWishList() {
        return getController().getStudent().totalCreditsInWishList();
    }

    // EFFECTS: saves the current student to file
    private void saveStudent() {
        try {
            getController().getJsonWriter().open();
            getController().getJsonWriter().write(getController().getStudent());
            getController().getJsonWriter().close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + getController().getJsonStore());
        }
    }
}
