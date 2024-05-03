package ui;

// This design is based on:
// (1) JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// (2) SmartHome: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public class Main {
    public static void main(String[] args) {

        // runs the graphical user interface
        new AcademicInfoUI();

        // runs the console based user interface
//        try {
//            new AcademicInfoApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
    }
}
