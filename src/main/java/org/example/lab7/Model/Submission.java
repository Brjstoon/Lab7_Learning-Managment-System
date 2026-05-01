package org.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Submission {

    @NotEmpty
    private String ID;

    @NotEmpty
    private String studentID;

    @NotEmpty
    private String assignmentID;

    private double grade;

    @NotEmpty
    private String submittedAt;

}
