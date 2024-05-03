package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student testStudent;
    private List<CourseTaken> courseTakenList;
    private List<CourseWish> courseWishList;
    private CourseTaken ct1;
    private CourseTaken ct2;
    private CourseTaken ct3;
    private CourseTaken ct4;
    private CourseWish cw1;
    private CourseWish cw2;
    private CourseWish cw3;
    private CourseWish cw4;

    @BeforeEach
    void runBefore() {
        testStudent = new Student("Test Student");
        courseTakenList = testStudent.getCourseTakenList();
        courseWishList = testStudent.getCourseWishList();
        ct1 = new CourseTaken("MATH221", 3, 76, 97);
        ct2 = new CourseTaken("CPSC110", 4, 79, 92);
        ct3 = new CourseTaken("PHYS119", 1, 80, 91);
        ct4 = new CourseTaken("SCIE113", 3, 82, 91);
        cw1 = new CourseWish("CPSC213", 4, 70);
        cw2 = new CourseWish("MATH200", 3, 66);
        cw3 = new CourseWish("STAT200", 3, 75);
        cw4 = new CourseWish("MATH302", 3, 75);
    }

    @Test
    void testConstructor() {
        assertEquals("Test Student", testStudent.getStudentName());
        assertEquals(0, testStudent.getCourseTakenList().size());
        assertEquals(0, testStudent.getCourseWishList().size());
    }

    @Test
    // Case1: empty courseTakenList; add a CourseTaken to the list
    void testAddCourseToCourseTakenList1() {
        testStudent.addCourseToCourseTakenList(ct1);
        assertTrue(courseTakenList.contains(ct1));
        assertEquals(1, courseTakenList.size());
    }

    @Test
    // Case2: non-empty courseTakenList; add a non-existing CourseTaken to the list
    void testAddCourseToCourseTakenList2() {
        testStudent.addCourseToCourseTakenList(ct1);

        testStudent.addCourseToCourseTakenList(ct2);
        assertTrue(courseTakenList.contains(ct1));
        assertTrue(courseTakenList.contains(ct2));
        assertEquals(2, courseTakenList.size());
    }

    @Test
    // Case3: non-empty courseTakenList; add an existing CourseTaken to the list; list unchanged
    void testAddCourseToCourseTakenList3() {
        testStudent.addCourseToCourseTakenList(ct1);
        assertTrue(courseTakenList.contains(ct1));

        testStudent.addCourseToCourseTakenList(ct1);
        assertTrue(courseTakenList.contains(ct1));
        assertFalse(courseTakenList.contains(ct2));
        assertFalse(courseTakenList.contains(ct3));
        assertEquals(1, courseTakenList.size());
    }

    @Test
    // Case1: empty courseWishList; add a CourseWish to the list
    void testAddCourseToCourseWishList1() {
        testStudent.addCourseToCourseWishList(cw1);
        assertTrue(courseWishList.contains(cw1));
        assertEquals(1, courseWishList.size());
    }

    @Test
    // Case2: non-empty courseWishList; add a non-existing CourseWish to the list
    void testAddCourseToCourseWishList2() {
        testStudent.addCourseToCourseWishList(cw1);

        testStudent.addCourseToCourseWishList(cw2);
        assertTrue(courseWishList.contains(cw1));
        assertTrue(courseWishList.contains(cw2));
        assertEquals(2, courseWishList.size());
    }

    @Test
    // Case3: non-empty courseWishList; add an existing CourseWish to the list; list unchanged
    void testAddCourseToCourseWishList3() {
        testStudent.addCourseToCourseWishList(cw1);
        assertTrue(courseWishList.contains(cw1));

        testStudent.addCourseToCourseWishList(cw1);
        assertTrue(courseWishList.contains(cw1));
        assertFalse(courseWishList.contains(cw2));
        assertFalse(courseWishList.contains(cw3));
        assertEquals(1, courseWishList.size());
    }

    @Test
    // case1: given CourseWish is not in the courseWishList, so remove nothing
    void testRemoveCourseFromCourseWishList1() {
        testStudent.addCourseToCourseWishList(cw1);
        testStudent.addCourseToCourseWishList(cw2);
        testStudent.removeCourseFromCourseWishList(cw3);

        assertTrue(courseWishList.contains(cw1));
        assertTrue(courseWishList.contains(cw2));
        assertFalse(courseWishList.contains(cw3));
        assertEquals(2, courseWishList.size());
    }

    @Test
    // case2: given CourseWish is in the courseWishList, so remove it
    void testRemoveCourseFromCourseWishList2() {
        testStudent.addCourseToCourseWishList(cw1);
        testStudent.addCourseToCourseWishList(cw2);
        testStudent.removeCourseFromCourseWishList(cw2);

        assertTrue(courseWishList.contains(cw1));
        assertFalse(courseWishList.contains(cw2));
        assertEquals(1, courseWishList.size());
    }

    @Test
    // case3: removes all the courses in the list, so list becomes empty
    void testRemoveCourseFromCourseWishList3() {
        testStudent.addCourseToCourseWishList(cw1);
        testStudent.addCourseToCourseWishList(cw2);
        testStudent.addCourseToCourseWishList(cw3);
        testStudent.removeCourseFromCourseWishList(cw2);
        testStudent.removeCourseFromCourseWishList(cw3);
        testStudent.removeCourseFromCourseWishList(cw1);

        assertFalse(courseWishList.contains(cw1));
        assertFalse(courseWishList.contains(cw2));
        assertFalse(courseWishList.contains(cw3));
        assertEquals(0, courseWishList.size());
    }

    @Test
    // case1: courseTakenList is empty
    void testIsCourseInTakenList1() {
        assertFalse(testStudent.isCourseInTakenList(ct1));
    }

    @Test
    // case2: courseTakenList is non-empty
    void testIsCourseInTakenList2() {
        testStudent.addCourseToCourseTakenList(ct1);
        assertTrue(testStudent.isCourseInTakenList(ct1));
        assertFalse(testStudent.isCourseInTakenList(ct2));
    }

    @Test
    // case4: more complicated one
    void testIsCourseInTakenList3() {
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct3);
        assertTrue(testStudent.isCourseInTakenList(ct1));
        assertTrue(testStudent.isCourseInTakenList(ct2));
        assertTrue(testStudent.isCourseInTakenList(ct3));
        assertFalse(testStudent.isCourseInTakenList(ct4));
    }

    @Test
    // case1: courseWishList is empty
    void testIsCourseInWishList1() {
        assertFalse(testStudent.isCourseInWishList(cw1));
    }

    @Test
    // case2: courseWishList is non-empty, given CourseWish is not in list
    void testIsCourseInWishList2() {
        testStudent.addCourseToCourseWishList(cw1);
        assertTrue(testStudent.isCourseInWishList(cw1));
        assertFalse(testStudent.isCourseInWishList(cw2));
    }

    @Test
    // case 3: more complicated one
    void testIsCourseInWishList3() {
        testStudent.addCourseToCourseWishList(cw1);
        testStudent.addCourseToCourseWishList(cw2);
        assertTrue(testStudent.isCourseInWishList(cw1));
        assertTrue(testStudent.isCourseInWishList(cw2));
        assertFalse(testStudent.isCourseInWishList(cw3));
    }

    @Test
    // case1: courseTakenList only has one CourseTaken
    void testGetHighestCourse1() {
        testStudent.addCourseToCourseTakenList(ct1);
        List<CourseTaken> highestList = new ArrayList<>(0);
        highestList.add(ct1);
        assertEquals(highestList, testStudent.getHighestCourse());
    }

    @Test
    // case2: courseTakenList has 3 CourseTaken, the 1st one is the highest
    void testGetHighestCourse2() {
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct3);
        List<CourseTaken> highestList = new ArrayList<>();
        highestList.add(ct1);
        assertEquals(highestList, testStudent.getHighestCourse());
    }

    @Test
    // case3: courseTakenList has 3 CourseTaken, the 2nd one is the highest
    void testGetHighestCourse3() {
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct3);
        List<CourseTaken> highestList = new ArrayList<>();
        highestList.add(ct1);
        assertEquals(highestList, testStudent.getHighestCourse());
    }

    @Test
    // case4: courseTakenList has 3 CourseTaken, the 3rd one is the highest
    void testGetHighestCourse4() {
        testStudent.addCourseToCourseTakenList(ct3);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct1);
        List<CourseTaken> highestList = new ArrayList<>();
        highestList.add(ct1);
        assertEquals(highestList, testStudent.getHighestCourse());
    }

    @Test
    // case5: 2 CourseTakens in the list have the same highest student grade
    void testGetHighestCourse5() {
        testStudent.addCourseToCourseTakenList(ct3);
        testStudent.addCourseToCourseTakenList(ct4);
        List<CourseTaken> highestList = new ArrayList<>();
        highestList.add(ct3);
        highestList.add(ct4);
        assertEquals(highestList, testStudent.getHighestCourse());
    }

    @Test
    // case1: courseTakenList only has one CourseTaken
    void testGetLowestCourse1() {
        testStudent.addCourseToCourseTakenList(ct1);
        List<CourseTaken> lowestList = new ArrayList<>(0);
        lowestList.add(ct1);
        assertEquals(lowestList, testStudent.getLowestCourse());
    }

    @Test
    // case2: courseTakenList has 3 CourseTaken, the 1st one is the lowest
    void testGetLowestCourse2() {
        testStudent.addCourseToCourseTakenList(ct3);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct1);
        List<CourseTaken> lowestList = new ArrayList<>(0);
        lowestList.add(ct3);
        assertEquals(lowestList, testStudent.getLowestCourse());
    }

    @Test
    // case3: courseTakenList has 3 CourseTaken, the 2nd one is the lowest
    void testGetLowestCourse3() {
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct3);
        testStudent.addCourseToCourseTakenList(ct1);
        List<CourseTaken> lowestList = new ArrayList<>(0);
        lowestList.add(ct3);
        assertEquals(lowestList, testStudent.getLowestCourse());
    }

    @Test
    // case4: courseTakenList has 3 CourseTaken, the 3rd one is the lowest
    void testGetLowestCourse4() {
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct3);
        List<CourseTaken> lowestList = new ArrayList<>(0);
        lowestList.add(ct3);
        assertEquals(lowestList, testStudent.getLowestCourse());
    }

    @Test
    // case5: 2 CourseTakens in the list have the same lowest student grade
    void testGetLowestCourse5() {
        testStudent.addCourseToCourseTakenList(ct4);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct3);
        List<CourseTaken> lowestList = new ArrayList<>(0);
        lowestList.add(ct4);
        lowestList.add(ct3);
        assertEquals(lowestList, testStudent.getLowestCourse());
    }

    @Test
        // case1: courseTakenList only has one CourseTaken
    void testCalculateStudentWeightedAverage1() {
        testStudent.addCourseToCourseTakenList(ct1);
        double stAvg = testStudent.calculateStudentWeightedAverage();
        assertEquals(97, stAvg);
    }

    @Test
    // case2: courseTakenList has more than one CourseTaken, each weighted differently
    void testCalculateStudentWeightedAverage2() {
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct3);
        double stAvg = testStudent.calculateStudentWeightedAverage();
        assertEquals(93.75, stAvg);
    }

    @Test
    // case3: courseTakenList has more than one CourseTaken, some are weighted the same
    void testCalculateStudentWeightedAverage3() {
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct4);
        testStudent.addCourseToCourseTakenList(ct2);
        double stAvg = testStudent.calculateStudentWeightedAverage();
        assertEquals(93.2, stAvg);
    }

    @Test
    // case1: courseTakenList only has one CourseTaken
    void testCalculateStudentSectionedAverage1() {
        testStudent.addCourseToCourseTakenList(ct1);
        double scAvg = testStudent.calculateStudentSectionedAverage();
        assertEquals(76, scAvg);
    }

    @Test
    // case2: courseTakenList has more than one CourseTaken, each weighted differently
    void testCalculateStudentSectionedAverage2() {
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct3);
        double scAvg = testStudent.calculateStudentSectionedAverage();
        assertEquals(78, scAvg);
    }

    @Test
    // case3: courseTakenList has more than one CourseTaken, some are weighted the same
    void testCalculateStudentSectionedAverage3() {
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct4);
        testStudent.addCourseToCourseTakenList(ct2);
        double scAvg = testStudent.calculateStudentSectionedAverage();
        assertEquals(79, scAvg);
    }

    @Test
    // case1: courseWishList only has one CourseTaken
    void testCalculateWishListPastAverage1() {
        testStudent.addCourseToCourseWishList(cw1);
        double pAvg = testStudent.calculateWishListPastAverage();
        assertEquals(70, pAvg);
    }

    @Test
    // case2: courseWishList has more than one CourseTaken, each weighted differently
    void testCalculateWishListPastAverage2() {
        testStudent.addCourseToCourseWishList(cw1);
        testStudent.addCourseToCourseWishList(cw2);
        testStudent.addCourseToCourseWishList(cw3);
        testStudent.addCourseToCourseWishList(cw4);
        double pAvg = testStudent.calculateWishListPastAverage();
        assertEquals((double) 928/13, pAvg);
    }

    @Test
    // case1: courseTakenList is empty
    void testTotalCreditsEarned1() {
        int cre = testStudent.totalCreditsEarned();
        assertEquals(0, cre);
    }

    @Test
    // case2: courseTakenList only has one CourseTaken
    void testTotalCreditsEarned2() {
        testStudent.addCourseToCourseTakenList(ct1);
        int cre = testStudent.totalCreditsEarned();
        assertEquals(3, cre);
    }

    @Test
    // case3: courseTakenList has more than one CourseTaken
    void testTotalCreditsEarned3() {
        testStudent.addCourseToCourseTakenList(ct1);
        testStudent.addCourseToCourseTakenList(ct2);
        testStudent.addCourseToCourseTakenList(ct3);
        testStudent.addCourseToCourseTakenList(ct4);
        int cre = testStudent.totalCreditsEarned();
        assertEquals(11, cre);
    }

    @Test
        // case1: courseWishList is empty
    void testTotalCreditsInWishList1() {
        int cre = testStudent.totalCreditsInWishList();
        assertEquals(0, cre);
    }

    @Test
    // case2: courseWishList only has one CourseWish
    void testTotalCreditsInWishList2() {
        testStudent.addCourseToCourseWishList(cw1);
        int cre = testStudent.totalCreditsInWishList();
        assertEquals(4, cre);
    }

    @Test
    // case3: courseWishList has more than one CourseWish
    void testTotalCreditsInWishList3() {
        testStudent.addCourseToCourseWishList(cw1);
        testStudent.addCourseToCourseWishList(cw2);
        testStudent.addCourseToCourseWishList(cw3);
        int cre = testStudent.totalCreditsInWishList();
        assertEquals(10, cre);
    }
}
