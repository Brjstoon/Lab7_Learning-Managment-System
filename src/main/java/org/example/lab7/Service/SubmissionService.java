package org.example.lab7.Service;


import org.example.lab7.Model.Course;
import org.example.lab7.Model.Submission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubmissionService {

    ArrayList<Submission> submissions = new ArrayList<>();


    public ArrayList<Submission> getSubmission(){
        return submissions;
    }

    public void addSubmission(Submission submission){
        submissions.add(submission);
    }

    public boolean updateSubmission(String id, Submission submission){
        for (int i=0;i< submissions.size();i++){
            if (submissions.get(i).getID().equalsIgnoreCase(id)){
                submission.setID(id);
                submissions.set(i, submission);
                return true;
            }
        }
        return false;
    }


    public boolean deleteSubmission(String id){
        for (int i=0;i< submissions.size();i++){
            if (submissions.get(i).getID().equalsIgnoreCase(id)){
                submissions.remove(i);
                return true;
            }
        }
        return false;
    }



    public ArrayList<Submission> getSubmissionsByStudent(String studentID){
        ArrayList<Submission> result = new ArrayList<>();
        for (int i=0;i<submissions.size();i++){
            if (submissions.get(i).getStudentID().equalsIgnoreCase(studentID)){
                result.add(submissions.get(i));
            }
        }
        return result;
    }


    public ArrayList<Submission> getSubmissionsByAssignment(String assignmentID){
        ArrayList<Submission> result = new ArrayList<>();
        for (int i=0;i<submissions.size();i++){
            if (submissions.get(i).getAssignmentID().equalsIgnoreCase(assignmentID)){
                result.add(submissions.get(i));
            }
        }
        return result;
    }


    public boolean gradeSubmission(String id, double grade){
        for (int i=0;i<submissions.size();i++){
            if (submissions.get(i).getID().equalsIgnoreCase(id)){
                submissions.get(i).setGrade(grade);
                return true;
            }
        }
        return false;
    }


    public boolean hasStudentSubmitted(String studentID, String assignmentID){
        for (int i=0;i<submissions.size();i++){
            if (submissions.get(i).getStudentID().equalsIgnoreCase(studentID) &&
            submissions.get(i).getAssignmentID().equalsIgnoreCase(assignmentID)){
                return true;
            }
        }
        return false;
    }




}
