package org.example.lab7.Service;

import org.example.lab7.Model.Assignment;
import org.example.lab7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService {

    ArrayList<Assignment> assignments = new ArrayList<>();


    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

    public boolean updateAssignment(String id, Assignment assignment){
        for (int i=0;i< assignments.size();i++){
            if (assignments.get(i).getID().equalsIgnoreCase(id)){
                assignment.setID(id);
                assignments.set(i, assignment);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAssignment(String id){
        for (int i=0;i< assignments.size();i++){
            if (assignments.get(i).getID().equalsIgnoreCase(id)){
                assignments.remove(i);
                return true;
            }
        }
        return false;
    }



    public ArrayList<Assignment> getAssignmentsByCourse(String courseID) {
        ArrayList<Assignment> result = new ArrayList<>();
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getCourseID().equalsIgnoreCase(courseID)) {
                result.add(assignments.get(i));
            }
        }
        return result;
    }

    public Assignment getAssignmentByTitle(String title) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getTitle().equalsIgnoreCase(title)) {
                return assignments.get(i);
            }
        }
        return null;
    }

    public boolean extendDeadline(String id, String newDueDate) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getID().equalsIgnoreCase(id)) {
                assignments.get(i).setDueDate(newDueDate);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Assignment> getAssignmentsByMarks(double marks) {
        ArrayList<Assignment> result = new ArrayList<>();
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getMarks() >= marks) {
                result.add(assignments.get(i));
            }
        }
        return result;
    }

}
