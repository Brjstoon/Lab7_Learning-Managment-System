package org.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {


    @NotEmpty
    private String ID;

    @NotEmpty
    private String title;

    @NotNull
    private int capacity;

    private ArrayList<Assignment> assignments;

    private ArrayList<Student> students;

}
