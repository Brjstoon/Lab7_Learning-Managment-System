package org.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Assignment {



    @NotEmpty
    private String ID;

    @NotEmpty
    private String title;

    @NotEmpty
    private String dueDate;

    @NotNull
    private double marks;

    @NotEmpty
    private String courseID;

    private ArrayList<Submission> submissions;



}
