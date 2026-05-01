package org.example.lab7.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab7.Api.ApiResponse;
import org.example.lab7.Model.Student;
import org.example.lab7.Service.StudentService;
import org.example.lab7.Service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final SubmissionService submissionService;


    @GetMapping("/get")
    public ResponseEntity<?> getStudent() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()){
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added Successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id,@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()){
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        if (!studentService.updateStudent(id, student)){
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        }
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated Successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        if (!studentService.deleteStudent(id)){
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        }
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted Successfully"));
    }


    @GetMapping("/get-courses/{studentID}")
    public ResponseEntity<?> getCoursesOfStudent(@PathVariable String studentID) {
        if (studentService.getCoursesOfStudent(studentID) == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        }
        return ResponseEntity.status(200).body(studentService.getCoursesOfStudent(studentID));
    }

    @DeleteMapping("/drop/{studentID}/{courseID}")
    public ResponseEntity<?> dropCourse(@PathVariable String studentID, @PathVariable String courseID) {
        int result = studentService.dropCourse(studentID, courseID);
        if (result == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found in student"));
        }
        if (result == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Course dropped successfully"));
    }

    @GetMapping("/total-marks/{studentID}")
    public ResponseEntity<?> getTotalMarks(@PathVariable String studentID) {
        double total = studentService.getTotalMarks(studentID, submissionService.getSubmission());
        return ResponseEntity.status(200).body(new ApiResponse("Total marks: " + total));
    }

    @GetMapping("/passed/{studentID}/{passingMark}")
    public ResponseEntity<?> hasPassedAllAssignments(@PathVariable String studentID, @PathVariable double passingMark) {
        boolean passed = studentService.hasPassedAllAssignments(studentID, submissionService.getSubmission(), passingMark);
        if (!passed) {
            return ResponseEntity.status(400).body(new ApiResponse("Student has not passed all assignments"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student has passed all assignments"));
    }
}
