package ui.tabs;

import model.CourseTaken;
import ui.AcademicInfoUI;
import ui.ButtonNames;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

// This design is based on SmartHome starter: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// The design of the histogram is copied and pasted from
// https://stackoverflow.com/questions/29708147/custom-graph-java-swing/29709153#29709153

// Represents a CourseTakenTab class that contains 7 buttons: view course-taken list, add a CourseTaken,
// calculate student average, calculate section average, calculate total credits, compare two averages, and save
public class CourseTakenTab extends Tab {

    private static final String VIEW_COURSES_TAKEN_MESSAGE = "Below is a list of courses that you have taken:";
    private static final String SAVE_MESSAGE = "You have successfully saved your file.";

    private JScrollPane reportPane;
    private JTextArea reportText;
    private JLabel reportMessage;

    private JScrollPane reportPane2;
    private JTextArea reportText2;
    private JLabel reportMessage2;

    // REQUIRES: AcademicInfoUI controller that holds this tab
    // EFFECTS: constructs a CourseTakenTab for console with 7 buttons related to course-taken list
    public CourseTakenTab(AcademicInfoUI controller) {
        super(controller);

        displayViewTakenList();

        placeAddCourseTakenButton();
        placeCalcStudentAvgButton();
        placeCalcSectionAvgButton();
        displayCalcCredits();
        placeCompareAvgButton();

        placeSaveButton();
    }

    // MODIFIES: this
    // EFFECTS: displays a View CourseTaken list button and associated texts and window
    private void displayViewTakenList() {
        placeViewTakenListButton();

        JPanel reportBlock = new JPanel(new GridLayout(2, 1));
        reportBlock.setSize(AcademicInfoUI.WIDTH - (AcademicInfoUI.WIDTH / 5),
                AcademicInfoUI.HEIGHT - (AcademicInfoUI.HEIGHT / 5));
        reportMessage = new JLabel("");
        reportPane = new JScrollPane(new JTextArea(6, 45));
        reportText = new JTextArea("", 6, 45);
        reportText.setVisible(true);

        reportBlock.add(reportMessage);
        reportBlock.add(reportPane);

        add(reportBlock);
    }

    // MODIFIES: this
    // EFFECTS: adds a View CourseTaken list button that prints out all CourseTaken when clicked
    private void placeViewTakenListButton() {
        JButton b1 = new JButton(ButtonNames.VIEW_TAKEN_LIST.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.VIEW_TAKEN_LIST.getValue())) {
                    reportMessage.setText(VIEW_COURSES_TAKEN_MESSAGE);
                    reportText.setText(printTakenList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: adds an Add CourseTaken button that asks the user to add a course they've taken when clicked
    private void placeAddCourseTakenButton() {
        JButton b1 = new JButton(ButtonNames.ADD_TO_TAKEN_LIST.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.ADD_TO_TAKEN_LIST.getValue())) {
                    addCourseTakenList();
                    reportText.setText(printTakenList());
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
    // EFFECTS: adds a Calculate Credit button that calculates the total credits the user has earned
    private void placeCalcCreditsButton() {
        JButton b1 = new JButton(ButtonNames.CALC_CREDITS.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.CALC_CREDITS.getValue())) {
                    String credits = Integer.toString(printCreditsTakenList());
                    String creditsMsg = "The total number of credits in the course-taken list is " + credits + ".";
                    reportText2.setText(creditsMsg);
                    reportPane2.setViewportView(reportText2);

                    reportText.setText(printTakenList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: adds a Calculate Student Average button that calculates student's average
    private void placeCalcStudentAvgButton() {
        JButton b1 = new JButton(ButtonNames.CALC_STU_AVG.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.CALC_STU_AVG.getValue())) {
                    String avg = Double.toString(printStudentAvg());
                    String avgMsg = "Your weighted average is " + avg + ".";
                    reportText2.setText(avgMsg);
                    reportPane2.setViewportView(reportText2);

                    reportText.setText(printTakenList());
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
                    String avg = Double.toString(printSectionAvg());
                    String avgMsg = "The weighted section average is " + avg + ".";
                    reportText2.setText(avgMsg);
                    reportPane2.setViewportView(reportText2);

                    reportText.setText(printTakenList());
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
                    reportText.setText(printTakenList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: adds a Compare Average button that compares the student's average with section average,
    //          visualized by a histogram
    private void placeCompareAvgButton() {
        JButton b1 = new JButton(ButtonNames.COMPARE_AVG.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.COMPARE_AVG.getValue())) {

                    HistogramPanel hp = new HistogramPanel();
                    hp.createAndShowGUI();

                    reportText.setText(printTakenList());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // EFFECTS: returns all courses in the course-taken list
    private String printTakenList() {
        List<CourseTaken> takenList = getController().getStudent().getCourseTakenList();
        String courseTakenList = "";
        for (CourseTaken ct : takenList) {
            String name = ct.getCourseName();
            int credit = ct.getCourseCredit();
            int avg = ct.getSectionAverage();
            int grade = ct.getGrade();
            courseTakenList += ("Course name: " + name + ", " + "Course credit: " + credit + ", "
                    + "Section average: " + avg + ", " + "Your Grade: " + grade + "\n");
        }
        return courseTakenList;
    }

    // EFFECTS: asks the user to add a course they finished to the course-taken list if that course isn't in the list
    public void addCourseTakenList() {
        String name = JOptionPane.showInputDialog("Course Name: ");
        int credit = Integer.parseInt(JOptionPane.showInputDialog("Course Credit:"));
        int avg = Integer.parseInt(JOptionPane.showInputDialog("Course Average: "));
        int grade = Integer.parseInt(JOptionPane.showInputDialog("Course Grade: "));
        CourseTaken ctNew = new CourseTaken(name, credit, avg, grade);

        List<String> courseNamesInList = new ArrayList<>();
        for (CourseTaken ct : getController().getStudent().getCourseTakenList()) {
            courseNamesInList.add(ct.getCourseName());
        }

        if (!courseNamesInList.contains(name)) {
            getController().getStudent().addCourseToCourseTakenList(ctNew);
            printTakenList();
        }
    }

    // EFFECTS: returns the total credits in the course-taken list
    private int printCreditsTakenList() {
        return getController().getStudent().totalCreditsEarned();
    }

    // EFFECTS: returns the student's weighted average of courses in the course-taken list
    private double printStudentAvg() {
        return getController().getStudent().calculateStudentWeightedAverage();
    }

    // EFFECTS: returns the weighted section average of courses in the course-taken list
    private double printSectionAvg() {
        return getController().getStudent().calculateStudentSectionedAverage();
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

    // The design below (creating a histogram) is copied and pasted from
    // https://stackoverflow.com/questions/29708147/custom-graph-java-swing/29709153#29709153

    // A class that creates a histogram panel
    private class HistogramPanel extends JPanel {

        private int histogramHeight = 200;
        private int barWidth = 50;
        private int barGap = 10;

        private JPanel barPanel;
        private JPanel labelPanel;

        private List<Bar> bars = new ArrayList<>();

        // MODIFIES: this
        // EFFECTS: constructs a HistogramPanel with a barPanel and a labelPanel
        private HistogramPanel() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new BorderLayout());

            barPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
            Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
            Border inner = new EmptyBorder(10, 10, 0, 10);
            Border compound = new CompoundBorder(outer, inner);
            barPanel.setBorder(compound);

            labelPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
            labelPanel.setBorder(new EmptyBorder(5, 10, 0, 10));

            add(barPanel, BorderLayout.CENTER);
            add(labelPanel, BorderLayout.PAGE_END);
        }

        // EFFECTS: makes a new bar and adds it to a list of bars that have been created
        private void addHistogramColumn(String label, int value, Color color) {
            Bar bar = new Bar(label, value, color);
            bars.add(bar);
        }

        // MODIFIES: this
        // EFFECTS: creates label for each bar and adds them to labelPanel
        private void layoutHistogram() {
            barPanel.removeAll();
            labelPanel.removeAll();

            int maxValue = 0;

            for (Bar bar: bars) {
                maxValue = Math.max(maxValue, bar.getValue());

                JLabel label = new JLabel(bar.getValue() + "");
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.TOP);
                label.setVerticalAlignment(JLabel.BOTTOM);
                int barHeight = (bar.getValue() * histogramHeight) / maxValue;
                Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
                label.setIcon(icon);
                barPanel.add(label);

                JLabel barLabel = new JLabel(bar.getLabel());
                barLabel.setHorizontalAlignment(JLabel.CENTER);
                labelPanel.add(barLabel);
            }
        }

        // Represents a Bar
        private class Bar {
            private String label;
            private int value;
            private Color color;

            // EFFECTS: constructs a Bar with given label, value, and color
            public Bar(String label, int value, Color color) {
                this.label = label;
                this.value = value;
                this.color = color;
            }

            public String getLabel() {
                return label;
            }

            public int getValue() {
                return value;
            }

            public Color getColor() {
                return color;
            }
        }

        // Represents an Icon
        private class ColorIcon implements Icon {
            private int shadow = 3;

            private Color color;
            private int width;
            private int height;

            // EFFECTS: constructs an Icon with given color, width and height
            public ColorIcon(Color color, int width, int height) {
                this.color = color;
                this.width = width;
                this.height = height;
            }

            // EFFECTS: fill the icon with colour and creates shadow
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(color);
                g.fillRect(x, y, width - shadow, height);
                g.setColor(Color.GRAY);
                g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
            }

            public int getIconWidth() {
                return width;
            }

            public int getIconHeight() {
                return height;
            }
        }

        // EFFECTS: adds histogram columns and displays the histogram
        private void createAndShowGUI() {
            HistogramPanel panel = new HistogramPanel();
            panel.addHistogramColumn("Student Average", (int) printStudentAvg(), Color.YELLOW);
            panel.addHistogramColumn("Section Average", (int) printSectionAvg(), Color.BLUE);
            panel.layoutHistogram();

            JFrame frame = new JFrame("Comparison of Student Average with Section Average");
            frame.add(panel);
            frame.setLocationByPlatform(true);
            frame.pack();
            frame.setVisible(true);
        }
    }
}
