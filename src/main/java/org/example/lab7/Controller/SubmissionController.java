package org.example.lab7.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab7.Api.ApiResponse;
import org.example.lab7.Model.Submission;
import org.example.lab7.Service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/submission")
@RequiredArgsConstructor
public class SubmissionController {


    private final SubmissionService submissionService;


    @GetMapping("/get")
    public ResponseEntity<?> getSubmission() {
        return ResponseEntity.status(200).body(submissionService.getSubmission());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubmission(@RequestBody @Valid Submission submission, Errors errors) {
        if (errors.hasErrors()) {
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        submissionService.addSubmission(submission);
        return ResponseEntity.status(200).body(new ApiResponse("Submission added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSubmission(@PathVariable String id, @RequestBody @Valid Submission submission, Errors errors) {
        if (errors.hasErrors()) {
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        if (!submissionService.updateSubmission(id, submission)) {
            return ResponseEntity.status(400).body(new ApiResponse("Submission not found"));
        }
        submissionService.updateSubmission(id, submission);
        return ResponseEntity.status(200).body(new ApiResponse("Submission updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubmission(@PathVariable String id) {
        if (!submissionService.deleteSubmission(id)) {
            return ResponseEntity.status(400).body(new ApiResponse("Submission not found"));
        }
        submissionService.deleteSubmission(id);
        return ResponseEntity.status(200).body(new ApiResponse("Submission deleted Successfully"));
    }


    @GetMapping("/search-sub-by-student/{studentID}")
    public ResponseEntity<?> getSubmissionsByStudent(@PathVariable String studentID) {
        if (submissionService.getSubmissionsByStudent(studentID).isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("Submissions not found"));
        }
        return ResponseEntity.status(200).body(submissionService.getSubmissionsByStudent(studentID));
    }



    @GetMapping("/search-sub-by-assignment/{assignmentID}")
    public ResponseEntity<?> getSubmissionsByAssignment(@PathVariable String assignmentID){
        if (submissionService.getSubmissionsByAssignment(assignmentID).isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("Submissions not found"));
        }
        return ResponseEntity.status(200).body(submissionService.getSubmissionsByAssignment(assignmentID));
    }


    @PutMapping("/set-grade/{id}/{grade}")
    public ResponseEntity<?> gradeSubmission(@PathVariable String id,@PathVariable double grade){

        if (!submissionService.gradeSubmission(id, grade)) {
            return ResponseEntity.status(400).body(new ApiResponse("Submission not found"));
        }
        submissionService.gradeSubmission(id, grade);
        return ResponseEntity.status(200).body(new ApiResponse("Submission graded Successfully"));
    }



    @GetMapping("/student-submitted/{studentID}/{assignmentID}")
    public ResponseEntity<?> hasStudentSubmitted(@PathVariable String studentID,@PathVariable String assignmentID){
        if (!submissionService.hasStudentSubmitted(studentID, assignmentID)) {
            return ResponseEntity.status(400).body(new ApiResponse("Student didn't submit yet"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student have submitted Successfully"));
    }

}