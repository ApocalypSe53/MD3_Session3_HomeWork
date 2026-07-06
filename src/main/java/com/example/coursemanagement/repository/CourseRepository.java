package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> course = new ArrayList<>();

    public CourseRepository() {
        course.add(new Course(1L, "Lập trình Java Spring Boot", "ACTIVE", 101L));
        course.add(new Course(2L, "Thiết kế Web với ReactJS", "ACTIVE", 102L));
        course.add(new Course(3L, "Cấu trúc dữ liệu và Giải thuật", "PENDING", 101L));
        course.add(new Course(4L, "Trí tuệ nhân tạo cơ bản", "CLOSED", 103L));
    }

    public List<Course> findAll() {
        return course;
    }
}