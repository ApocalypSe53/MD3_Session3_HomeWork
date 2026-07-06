package com.example.coursemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Course {
    private Long id;
    private String title;
    private String status;
    private Long instructorId;
}
