package com.newlearn.stud_man_sys;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    @NotBlank(message="Student name required")
    @Size(max=50,message="Name cannot' exceed 50 chracters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value=16,message="Age must be over 16")
    @Max(value=80,message="Age cannot exceed 80")
    private Integer age;

    @NotBlank(message="Course cannot be empty")
    @Size(max=50,message="Course name cannot exceeed 50 char")
    private String course;


    @NotNull(message = "Marks are required")
    @Min(value=0,message="Must be more than 0")
    @Max(value=100,message="Must not exceed 100")
    private Double marks;
}
