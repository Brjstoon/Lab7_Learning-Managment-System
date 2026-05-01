package org.example.lab7.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab7.Api.ApiResponse;
import org.example.lab7.Model.Course;
import org.example.lab7.Model.Student;
import org.example.lab7.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity<?> getCourse() {
        return ResponseEntity.status(200).body(courseService.getCourse());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()){
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()){
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        if (!courseService.updateCourse(id, course)){
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        if (!courseService.deleteCourse(id)){
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted Successfully"));
    }



    @PostMapping("/add-student/{courseID}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable String courseID, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        int result = courseService.addStudentToCourse(courseID, student);
        if (result == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("Course is full"));
        }
        if (result == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student added to course successfully"));
    }


    @DeleteMapping("/remove-student/{courseID}/{studentID}")
    public ResponseEntity<?> removeStudentFromCourse(@PathVariable String courseID, @PathVariable String studentID) {
        if (!courseService.removeStudentFromCourse(courseID, studentID)) {
            return ResponseEntity.status(400).body(new ApiResponse("Student or course not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student removed from course successfully"));
    }

    @GetMapping("/is-enrolled/{courseID}/{studentID}")
    public ResponseEntity<?> isStudentEnrolled(@PathVariable String courseID, @PathVariable String studentID) {
        if (!courseService.isStudentEnrolled(courseID, studentID)) {
            return ResponseEntity.status(400).body(new ApiResponse("Student is not enrolled in this course"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student is enrolled in this course"));
    }

    @PutMapping("/transfer/{fromCourseID}/{toCourseID}/{studentID}")
    public ResponseEntity<?> transferStudent(@PathVariable String fromCourseID, @PathVariable String toCourseID, @PathVariable String studentID) {
        int result = courseService.transferStudent(fromCourseID, toCourseID, studentID);
        if (result == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("Student not found in course"));
        }
        if (result == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("New course is full"));
        }
        if (result == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("New course not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student transferred successfully"));
    }

}
