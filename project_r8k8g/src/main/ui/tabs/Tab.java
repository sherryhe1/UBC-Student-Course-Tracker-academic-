package ui.tabs;

import ui.AcademicInfoUI;

import javax.swing.*;
import java.awt.*;

// This design is based on SmartHome starter: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// An abstract class that creates controller and formats the tab
public abstract class Tab extends JPanel {

    private final AcademicInfoUI controller;

    // REQUIRES: AcademicInfoUI controller that holds this tab
    public Tab(AcademicInfoUI controller) {
        this.controller = controller;
    }

    // EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);
        return p;
    }

    // EFFECTS: returns the AcademicInfoUI controller for this tab
    public AcademicInfoUI getController() {
        return controller;
    }

}
