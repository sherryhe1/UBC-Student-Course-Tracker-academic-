# My Personal Project

## Project Introduction
**A course tracking application for UBC students**. The application allows students to keep track of 
courses already taken and courses they wish to take. 
- For the course that the student has taken, it records the course name, course credits, the course 
average for the section in which the student was enrolled, and the student's grades.
- For the courses the student wishes to take, it records the course name, course credits, and the past average for 
the course.

## Target Audience:
*UBC students*, as the courses information is provided by UBC.

## Reason for Doing the Project:
I am interested in this project because usually I need to go into several different browsers to retrieve all the 
information I need. I use one browser to view course names and credits, another to view my grades and the section 
averages, another to view past averages for courses, etc. But this project allows me to get all that information 
in one place. It would be helpful in planning my courses. It also keeps a record of my academic information.

## User Stories
- As a user, I want to be able to add a course to my course-taken list.
- As a user, I want to be able to add a course to my course wishlist. 
- As a user, I want to be able to remove a course from my course wishlist.
- As a user, I want to be able to view the list of courses that I have taken.
- As a user, I want to be able to view the list of courses that I wish to take.
- As a user, I want to be able to see the total number of credits I have earned.
- As a user, I want to be able to see the total number of credits in the course wishlist.
- As a user, I want to be able to see statistics on my average compared to the course section average.
- As a user, I want to be able to save my course-taken list.
- As a user, when I select the quit option from the menu, I want to be able to save my course-taken list and course 
  wishlist to file and have the option to do so or not.
- As a user, when I start the application, I want to be able to load my course-taken list and course wishlist from file.

# Instructions for Grader

- You can locate the panel in which all the Xs that have already been added to Y are displayed by first clicking the 
  button labelled "View information about courses that you have taken", then clicking the button labelled
  "View the list of courses that you have taken".
- You can generate the first required action related to adding Xs to a Y by first clicking the button labelled "View 
  information about courses that you have taken", then clicking the button labelled "Add a new course you have taken".
- You can generate the second required action related to adding Xs to a Y by first clicking the button labelled
  "View information about courses that you have taken", then clicking the button labelled "Calculate section average".
- You can locate my visual component by first clicking the button labelled "View information about courses that you have 
  taken", then clicking the button labelled "Compare your average with the section average".
- You can save the state of my application by first clicking the button labelled
  "View information about courses that you have taken", then clicking the button labelled "Save".
- You can reload the state of my application by clicking the button labelled "Load".

# Phase 4: Task 2
- Added a new course: MATH221 to the course-taken list.
- Added a new course: CPSC110 to the course-taken list.
- Added a new course: PHYS119 to the course-taken list.
- Added a new course: CPSC213 to the course wishlist.
- Added a new course: MATH200 to the course wishlist.
- Added a new course: SCIE113 to the course-taken list.
- Calculated the section average of courses in course-taken list.
- Calculated the total credits the student has earned.

# Phase 4: Task 3
If I had more time to work on the project, I might improve my design by refactoring the CourseTaken and CourseWish 
classes. I might have made the CourseTaken class extend the CourseWish class because they both have fields such as 
course name, course credit, and section average, except that the CourseTaken class has one more filed: student's grade. 
The same is true for the CourseTakenTab and CourseWishTab. I can have the CourseTakenTab extend CourseWishTab because
they share some common behaviours, such as adding a new course, calculating the section average, and calculating the 
total credits, whereas the CourseTakenTab has more behaviours, such as calculating the student's weighted average and 
comparing that average to the section average.