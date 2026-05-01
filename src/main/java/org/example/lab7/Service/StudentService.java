package org.example.lab7.Service;

import org.example.lab7.Model.Course;
import org.example.lab7.Model.Student;
import org.example.lab7.Model.Submission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public boolean updateStudent(String id, Student student){
        for (int i=0;i< students.size();i++){
            if (students.get(i).getID().equalsIgnoreCase(id)){
                student.setID(id);
                students.set(i, student);
                return true;
            }
        }
        return false;
    }


    public boolean deleteStudent(String id){
        for (int i=0;i< students.size();i++){
            if (students.get(i).getID().equalsIgnoreCase(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }




    public ArrayList<Course> getCoursesOfStudent(String studentID) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equalsIgnoreCase(studentID)) {
                return students.get(i).getCourses();
            }
        }
        return null;
    }



    public int dropCourse(String studentID, String courseID) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equalsIgnoreCase(studentID)) {
                for (int j = 0; j < students.get(i).getCourses().size(); j++) {
                    if (students.get(i).getCourses().get(j).getID().equalsIgnoreCase(courseID)) {
                        students.get(i).getCourses().remove(j);
                        return 0; // dropped successfully
                    }
                }
                return 1; // course not found in student
            }
        }
        return 2; // student not found
    }

    public double getTotalMarks(String studentID, ArrayList<Submission> submissions) {
        double total = 0;
        for (int i = 0; i < submissions.size(); i++) {
            if (submissions.get(i).getStudentID().equalsIgnoreCase(studentID)) {
                total += submissions.get(i).getGrade();
            }
        }
        return total;
    }

    public boolean hasPassedAllAssignments(String studentID, ArrayList<Submission> submissions, double passingMark) {
        for (int i = 0; i < submissions.size(); i++) {
            if (submissions.get(i).getStudentID().equalsIgnoreCase(studentID)) {
                if (submissions.get(i).getGrade() < passingMark) {
                    return false;
                }
            }
        }
        return true;
    }






}
