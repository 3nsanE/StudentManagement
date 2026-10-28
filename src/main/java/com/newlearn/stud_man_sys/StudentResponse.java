package com.newlearn.stud_man_sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class StudentResponse {
    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public StudentResponse(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }
}
