package org.example.lab7.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty
    private String ID;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @NotNull
    private int age;

    private ArrayList<Course> courses;

}
