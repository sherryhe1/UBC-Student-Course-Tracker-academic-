package ui;

import model.CourseTaken;
import model.CourseWish;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This design follows the FitLifeGymKiosk starter provided on edX:
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// Personal academicInfo application - console based
public class AcademicInfoApp {

    private static final String TAKENLIST_INFO_COMMAND = "takenlist info";
    private static final String VIEW_TAKENLIST_COMMAND = "view takenlist";
    private static final String ADD_TO_TAKENLIST_COMMAND = "add to takenlist";
    private static final String GET_CRE_TAKENLIST_COMMAND = "takenlist credits";
    private static final String GET_ACA_TAKENLIST_COMMAND = "academic";
    private static final String GET_STAVG_TAKENLIST_COMMAND = "my average";
    private static final String GET_SCAVG_TAKENLIST_COMMAND = "section average";
    private static final String GET_BEST_TAKENLIST_COMMAND = "best";
    private static final String GET_POOR_TAKENLIST_COMMAND = "poor";
    private static final String COMPARE_TAKENLIST_COMMAND = "comparison";

    private static final String WISHLIST_INFO_COMMAND = "wishlist info";
    private static final String VIEW_WISHLIST_COMMAND = "view wishlist";
    private static final String ADD_TO_WISHLIST_COMMAND = "add to wishlist";
    private static final String REMOVE_FROM_WISHLIST_COMMAND = "remove";
    private static final String GET_CRE_WISHLIST_COMMAND = "wishlist credits";
    private static final String GET_PAVG_WISHLIST_COMMAND = "past average";

    private static final String BACK_COMMAND = "back";
    private static final String QUIT_COMMAND = "quit";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";

    private static final String JSON_STORE = "./data/student.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Student student;

    private Scanner input;
    private boolean runProgram;

    // EFFECTS: runs the academicInfo application
    public AcademicInfoApp() throws FileNotFoundException {
        runAcademicInfo();
    }

    // MODIFIES: this
    // EFFECTS: interacts with the user until user quits
    private void runAcademicInfo() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runProgram = true;
        String command;

        init();

        System.out.println("\nHi " + student.getStudentName() + "!");
        displayMenu0();

        while (runProgram) {
            command = input.nextLine();
            command = command.toLowerCase();
            parseInput(command);
        }

        System.out.println("Bye, see you next time!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a student
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

        input = new Scanner(System.in);
    }

    // EFFECTS: displays the main menu of options for user to choose
    //          between info about courseTakenList and info about courseWishList
    private void displayMenu0() {
        System.out.println("Main menu:");
        System.out.println("Please enter one of the options below to access the information you want.");
        System.out.println("\t" + TAKENLIST_INFO_COMMAND + " -> information about courses that you have taken");
        System.out.println("\t" + WISHLIST_INFO_COMMAND + "  -> information about courses that you wish to take");
        System.out.println("\t" + SAVE_COMMAND + "           -> save the student record to file");
        System.out.println("\t" + LOAD_COMMAND + "           -> load the student record from file");
        System.out.println("\t" + QUIT_COMMAND + "           -> quit");
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: prints new menu or information depending on user's command
    private void parseInput(String command) {
        if (command.length() > 0) {
            switch (command) {
                case TAKENLIST_INFO_COMMAND:
                    displayMenuTakenListInfo();
                    break;
                case VIEW_TAKENLIST_COMMAND:
                    printTakenList();
                    break;
                case ADD_TO_TAKENLIST_COMMAND:
                    addCourseTakenlist();
                    break;
                case GET_CRE_TAKENLIST_COMMAND:
                    printCreditsTakenList();
                    break;
                case GET_ACA_TAKENLIST_COMMAND:
                    displayAcademicInfo();
                    break;
                case GET_STAVG_TAKENLIST_COMMAND:
                    printStudentAvg();
                    break;
                case GET_SCAVG_TAKENLIST_COMMAND:
                    printSectionAvg();
                    break;
                case GET_BEST_TAKENLIST_COMMAND:
                    printBestCourses();
                    break;
                case GET_POOR_TAKENLIST_COMMAND:
                    printPoorCourses();
                    break;
                case COMPARE_TAKENLIST_COMMAND:
                    compareAvg();
                    break;
                case WISHLIST_INFO_COMMAND:
                    displayMenuWishListInfo();
                    break;
                case VIEW_WISHLIST_COMMAND:
                    printWishList();
                    break;
                case ADD_TO_WISHLIST_COMMAND:
                    addCourseWishList();
                    break;
                case REMOVE_FROM_WISHLIST_COMMAND:
                    removeCourseWishList();
                    break;
                case GET_CRE_WISHLIST_COMMAND:
                    printCreditsWishList();
                    break;
                case GET_PAVG_WISHLIST_COMMAND:
                    printPavgWishList();
                    break;
                case SAVE_COMMAND:
                    saveStudent();
                    break;
                case LOAD_COMMAND:
                    loadStudent();
                    break;
                case BACK_COMMAND:
                    displayMenu0();
                    break;
                case QUIT_COMMAND:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Sorry, I didn't understand your request. Please try again.");
                    break;
            }
        }
    }

    // EFFECTS: displays menu of options for user to choose what they want to do with the courseTakenList
    private void displayMenuTakenListInfo() {
        System.out.println("Please enter one of the options below:");
        System.out.println("\t" + VIEW_TAKENLIST_COMMAND + "    -> view all courses that you have taken");
        System.out.println("\t" + ADD_TO_TAKENLIST_COMMAND + "  -> add a course you finished to the existing "
                + "course-taken list");
        System.out.println("\t" + GET_CRE_TAKENLIST_COMMAND + " -> calculate the total credits you have earned");
        System.out.println("\t" + GET_ACA_TAKENLIST_COMMAND + "          -> view your academic performance");
        System.out.println("\t" + SAVE_COMMAND + "           -> save the student record to file");
        System.out.println("\t" + BACK_COMMAND + "              -> go back to the main menu");
        System.out.println("\t" + QUIT_COMMAND + "              -> quit");
    }

    // EFFECTS: displays menu of options for user to choose what they want to do with the academic performance
    private void displayAcademicInfo() {
        System.out.println("Please enter one of the options below:");
        System.out.println("\t" + GET_STAVG_TAKENLIST_COMMAND + "      -> view your weighted average");
        System.out.println("\t" + GET_SCAVG_TAKENLIST_COMMAND + " -> view the weighted section average");
        System.out.println("\t" + GET_BEST_TAKENLIST_COMMAND + "            -> view course(s) that you did the "
                + "best");
        System.out.println("\t" + GET_POOR_TAKENLIST_COMMAND + "            -> view course(s) that you had a poor "
                + "performance");
        System.out.println("\t" + COMPARE_TAKENLIST_COMMAND + "      -> compare your average with the section average");
        System.out.println("\t" + BACK_COMMAND + "            -> go back to the main menu");
        System.out.println("\t" + QUIT_COMMAND + "            -> quit");
    }

    // EFFECTS: displays menu of options for user to choose what they want to do with the courseWishList
    private void displayMenuWishListInfo() {
        System.out.println("Please enter one of the options below:");
        System.out.println("\t" + VIEW_WISHLIST_COMMAND + "    -> view all courses that you wish to take");
        System.out.println("\t" + ADD_TO_WISHLIST_COMMAND + "  -> add a course you wish to take to the existing "
                + "course wishlist");
        System.out.println("\t" + REMOVE_FROM_WISHLIST_COMMAND + "           -> remove a course from the existing "
                + "course wishlist");
        System.out.println("\t" + GET_CRE_WISHLIST_COMMAND + " -> calculate the total credits in the "
                + "course wishlist");
        System.out.println("\t" + GET_PAVG_WISHLIST_COMMAND + "     -> calculate the weighted average of all "
                + "courses in the course wishlist using their past averages");
        System.out.println("\t" + SAVE_COMMAND + "           -> save the student record to file");
        System.out.println("\t" + BACK_COMMAND + "             -> go back to the main menu");
        System.out.println("\t" + QUIT_COMMAND + "             -> quit");
    }

    // EFFECTS: prints out courses in the course-taken list
    private void printTakenList() {
        List<CourseTaken> takenList = student.getCourseTakenList();
        for (CourseTaken ct : takenList) {
            String name = ct.getCourseName();
            int credit = ct.getCourseCredit();
            int avg = ct.getSectionAverage();
            int grade = ct.getGrade();
            System.out.println("Course name: " + name + ", " + "Course credit: " + credit + ", "
                    + "Section average: " + avg + ", " + "Your Grade: " + grade);
        }
    }

    // EFFECTS: adds a course that user has finished to the course-taken list if that course isn't in the list
    private void addCourseTakenlist() {
        System.out.println("What course would you like to add to your course-taken list?");
        System.out.println("Course name: ");
        String name = input.nextLine();
        System.out.println("Course credit: ");
        int credit = input.nextInt();
        System.out.println("Section average: ");
        int avg = input.nextInt();
        System.out.println("Your grade: ");
        int grade = input.nextInt();
        CourseTaken ctNew = new CourseTaken(name, credit, avg, grade);

        List<String> courseNamesInList = new ArrayList<>();
        for (CourseTaken ct : student.getCourseTakenList()) {
            courseNamesInList.add(ct.getCourseName());
        }

        if (courseNamesInList.contains(name)) {
            System.out.println("This course is already in your course-taken list.");
        } else {
            student.addCourseToCourseTakenList(ctNew);
            System.out.println("This course is successfully added to your course-taken list.");
            System.out.println("Below is your new course-taken list:");
            printTakenList();
        }
    }

    // EFFECTS: prints out the total credits in the course-taken list
    private void printCreditsTakenList() {
        int cre = student.totalCreditsEarned();
        System.out.println("The total credits in the course-taken list is " + cre + ".\n");
    }

    // EFFECTS: prints out the student's weighted average of courses in the course-taken list
    private void printStudentAvg() {
        double avg = student.calculateStudentWeightedAverage();
        System.out.println("Your weighted average is " + avg + ".\n");
    }

    // EFFECTS: prints out the weighted section average of courses in the course-taken list
    private void printSectionAvg() {
        double avg = student.calculateStudentSectionedAverage();
        System.out.println("The weighted section average is " + avg + ".\n");
    }

    // EFFECTS: prints out course(s) with the highest student grade in the course-taken list
    private void printBestCourses() {
        System.out.println("You had the best academic performance on the following course(s):");
        List<CourseTaken> bestCourses = student.getHighestCourse();
        for (CourseTaken ct : bestCourses) {
            String name = ct.getCourseName();
            int credit = ct.getCourseCredit();
            int avg = ct.getSectionAverage();
            int grade = ct.getGrade();
            System.out.println("Course name: " + name + ", " + "Course credit: " + credit + ", "
                    + "Section average: " + avg + ", " + "Your Grade: " + grade);
        }
    }

    // EFFECTS: prints out course(s) with the lowest student grade in the course-taken list
    private void printPoorCourses() {
        System.out.println("You had a relatively poor academic performance on the following course(s):");
        List<CourseTaken> poorCourses = student.getLowestCourse();
        for (CourseTaken ct : poorCourses) {
            String name = ct.getCourseName();
            int credit = ct.getCourseCredit();
            int avg = ct.getSectionAverage();
            int grade = ct.getGrade();
            System.out.println("Course name: " + name + ", " + "Course credit: " + credit + ", "
                    + "Section average: " + avg + ", " + "Your Grade: " + grade);
        }
    }

    // EFFECT: compares student's average and the section average using their difference
    private void compareAvg() {
        double stAvg = student.calculateStudentWeightedAverage();
        double scAvg = student.calculateStudentSectionedAverage();
        System.out.println("Your weighted average is " + student.calculateStudentWeightedAverage() + ".");
        System.out.println("The weighted section average is " + student.calculateStudentSectionedAverage() + ".");

        if (stAvg > scAvg) {
            double diff = stAvg - scAvg;
            System.out.println("Your weighted average is " + diff + " points higher than the weighted section "
                    + "average!");
        } else if (stAvg < scAvg) {
            double diff = scAvg - stAvg;
            System.out.println("Your weighted average is " + diff + " points lower than the weighted section average"
                    + "...");
        } else {
            System.out.println("Your weighted average is the same as the weighted section average!");
        }
    }

    // EFFECTS: prints out courses in the course wishlist
    private void printWishList() {
        List<CourseWish> wishlist = student.getCourseWishList();
        for (CourseWish cw : wishlist) {
            String name = cw.getCourseName();
            int credit = cw.getCourseCredit();
            int avg = cw.getPastAverage();
            System.out.println("Course name: " + name + ", " + "Course credit: " + credit + ", "
                    + "Past average: " + avg);
        }
    }

    // EFFECTS: adds a course to the course wishlist if that course isn't in the list
    private void addCourseWishList() {
        System.out.println("What course would you like to add to your course wishlist?");
        System.out.println("Course name: ");
        String name = input.nextLine();
        System.out.println("Course credit: ");
        int credit = input.nextInt();
        System.out.println("Course past average: ");
        int avg = input.nextInt();
        CourseWish cwNew = new CourseWish(name, credit, avg);

        List<String> courseNamesInList = new ArrayList<>();
        for (CourseWish cw : student.getCourseWishList()) {
            courseNamesInList.add(cw.getCourseName());
        }

        if (courseNamesInList.contains(name)) {
            System.out.println("This course is already in your course wishlist.");
        } else {
            student.addCourseToCourseWishList(cwNew);
            System.out.println("This course is successfully added to your course wishlist.");
            System.out.println("Below is your new course wishlist:");
            printWishList();
        }
    }

    // EFFECTS: removes a course from the course wishlist if that new course is in the list
    private void removeCourseWishList() {
        List<CourseWish> wishlist = student.getCourseWishList();
        int listSize = wishlist.size();
        if (wishlist.size() == 0) {
            System.out.println("There are no courses in your course wishlist so you don't have any courses to remove.");
        } else {
            removeCourseWishListHelper();

            if (listSize > student.getCourseWishList().size()) {
                System.out.println("This course is successfully removed from your course wishlist.");
                if (student.getCourseWishList().size() != 0) {
                    System.out.println("Below is your new course wishlist:");
                    printWishList();
                } else {
                    System.out.println("Right now, there are no courses in your course wishlist.");
                }
            } else {
                System.out.println("This course is not in your course wishlist.");
            }
        }
    }

    // EFFECTS: a helper to remove the course -- checks if the course is in the list. If so, removes it.
    private void removeCourseWishListHelper() {
        System.out.println("Below is your course wishlist:");
        printWishList();
        System.out.println("\nWhat course would you like to remove from it?");
        System.out.println("Course name: ");
        String name = input.nextLine();

        List<CourseWish> wishlist = student.getCourseWishList();
        int i = 0;
        while (i < wishlist.size()) {
            String cwName = wishlist.get(i).getCourseName();
            if (cwName.equals(name)) {
                student.removeCourseFromCourseWishList(wishlist.get(i));
            }
            i++;
        }
    }

    // EFFECTS: prints out the total credits in the course wishlist
    private void printCreditsWishList() {
        int cre = student.totalCreditsInWishList();
        System.out.println("The total credits in the course wishlist is " + cre + ".\n");
    }

    // EFFECTS: prints out the weighted average based on past averages for all courses in the course wishlist
    private void printPavgWishList() {
        double pavg = student.calculateWishListPastAverage();
        System.out.println("The weighted average based on past averages for all courses in the course wishlist is "
                + pavg + ".\n");
    }

    // EFFECTS: saves the student to file
    private void saveStudent() {
        try {
            jsonWriter.open();
            jsonWriter.write(student);
            jsonWriter.close();
            System.out.println("Saved " + student.getStudentName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads student from file
    private void loadStudent() {
        try {
            student = jsonReader.read();
            System.out.println("Loaded " + student.getStudentName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
