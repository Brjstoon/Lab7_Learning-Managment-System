package org.example.lab7.Service;

import org.example.lab7.Model.Course;
import org.example.lab7.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourse(){
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public boolean updateCourse(String id, Course course){
        for (int i=0;i< courses.size();i++){
            if (courses.get(i).getID().equalsIgnoreCase(id)){
                course.setID(id);
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }


    public boolean deleteCourse(String id){
        for (int i=0;i< courses.size();i++){
            if (courses.get(i).getID().equalsIgnoreCase(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }




    public int addStudentToCourse(String courseID, Student student) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equalsIgnoreCase(courseID)) {
                if (courses.get(i).getStudents().size() >= courses.get(i).getCapacity()) {
                    return 1;
                }
                courses.get(i).getStudents().add(student);
                return 0;
            }
        }
        return 2;
    }

    public boolean removeStudentFromCourse(String courseID, String studentID) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equalsIgnoreCase(courseID)) {
                for (int j = 0; j < courses.get(i).getStudents().size(); j++) {
                    if (courses.get(i).getStudents().get(j).getID().equalsIgnoreCase(studentID)) {
                        courses.get(i).getStudents().remove(j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isStudentEnrolled(String courseID, String studentID) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equalsIgnoreCase(courseID)) {
                for (int j = 0; j < courses.get(i).getStudents().size(); j++) {
                    if (courses.get(i).getStudents().get(j).getID().equalsIgnoreCase(studentID)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int transferStudent(String fromCourseID, String toCourseID, String studentID) {
        Student transferStudent = null;

        // find and remove student from old course
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equalsIgnoreCase(fromCourseID)) {
                for (int j = 0; j < courses.get(i).getStudents().size(); j++) {
                    if (courses.get(i).getStudents().get(j).getID().equalsIgnoreCase(studentID)) {
                        transferStudent = courses.get(i).getStudents().get(j);
                        courses.get(i).getStudents().remove(j);
                        break;
                    }
                }
            }
        }

        // student not found in old course
        if (transferStudent == null) {
            return 1; //Student not found in course
        }

        // add student to new course
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equalsIgnoreCase(toCourseID)) {
                if (courses.get(i).getStudents().size() >= courses.get(i).getCapacity()) {
                    return 2; //New course is full
                }
                courses.get(i).getStudents().add(transferStudent);
                return 0; //Student transferred successfully
            }
        }

        return 3; //New course not found
    }




}
