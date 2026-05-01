package org.example.lab7.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab7.Api.ApiResponse;
import org.example.lab7.Model.Assignment;
import org.example.lab7.Service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assinments")
@RequiredArgsConstructor
public class AssignmentController {


    private final AssignmentService assignmentService;



    @GetMapping("/get")
    public ResponseEntity<?> getAssignment() {
        return ResponseEntity.status(200).body(assignmentService.getAssignments());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAssignment(@RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors()){
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        assignmentService.addAssignment(assignment);
        return ResponseEntity.status(200).body(new ApiResponse("Assignment added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable String id, @RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors()){
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        if (!assignmentService.updateAssignment(id, assignment)){
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
        assignmentService.updateAssignment(id, assignment);
        return ResponseEntity.status(200).body(new ApiResponse("Assignment updated Successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable String id) {
        if (!assignmentService.deleteAssignment(id)){
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
        assignmentService.deleteAssignment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Assignment deleted Successfully"));
    }




    @GetMapping("/get-by-course/{courseID}")
    public ResponseEntity<?> getAssignmentsByCourse(@PathVariable String courseID) {
        if (assignmentService.getAssignmentsByCourse(courseID).isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No assignments found for this course"));
        }
        return ResponseEntity.status(200).body(assignmentService.getAssignmentsByCourse(courseID));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity<?> getAssignmentByTitle(@PathVariable String title) {
        if (assignmentService.getAssignmentByTitle(title) == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
        return ResponseEntity.status(200).body(assignmentService.getAssignmentByTitle(title));
    }

    @PutMapping("/extend-deadline/{id}/{newDueDate}")
    public ResponseEntity<?> extendDeadline(@PathVariable String id, @PathVariable String newDueDate) {
        if (!assignmentService.extendDeadline(id, newDueDate)) {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Deadline extended successfully"));
    }

    @GetMapping("/get-by-marks/{marks}")
    public ResponseEntity<?> getAssignmentsByMarks(@PathVariable double marks) {
        if (assignmentService.getAssignmentsByMarks(marks).isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No assignments found"));
        }
        return ResponseEntity.status(200).body(assignmentService.getAssignmentsByMarks(marks));
    }


}
