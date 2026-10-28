package com.newlearn.stud_man_sys;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public Student(Integer id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

}
