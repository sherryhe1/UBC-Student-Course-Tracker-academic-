package ui;

// This design is based on SmartHome starter: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// A class containing a list of button names
public enum ButtonNames {
    VIEW_TAKEN_LIST_INFO("View information about courses that you have taken"),
    VIEW_WISHLIST_INFO("View information about courses that you wish to take"),

    VIEW_TAKEN_LIST("View the list of courses that you have taken"),
    ADD_TO_TAKEN_LIST("Add a new course you have taken"),
    CALC_CREDITS("Calculate the total credits earned"),
    CALC_STU_AVG("Calculate weighted average"),
    CALC_SEC_AVG("Calculate section average"),
    COMPARE_AVG("Compare your average with the section average"),

    VIEW_WISHLIST("View the list of courses that you wish to take"),
    ADD_TO_WISHLIST("Add a new course you wish to take"),
    CALC_CREDITS_WISH("Calculate the total credits in the wishlist"),

    SAVE("Save"),
    LOAD("Load");

    private final String name;

    // EFFECTS: constructs a button name
    ButtonNames(String name) {
        this.name = name;
    }

    // EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
