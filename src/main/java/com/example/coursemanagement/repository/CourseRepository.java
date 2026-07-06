package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();
    private Long currentId = 3L;

    public CourseRepository() {
        // Giả sử instructorId 1 và 2 là của các giảng viên đã tạo ở bài trước
        courses.add(new Course(1L, "Lập trình Java cơ bản", "Active", 1L));
        courses.add(new Course(2L, "Thiết kế Web với React", "Draft", 2L));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Course create(Course course) {
        course.setId(currentId++);
        courses.add(course);
        return course;
    }

    public Course update(Long id, Course updatedCourse) {
        Course existing = findById(id);
        if (existing != null) {
            existing.setTitle(updatedCourse.getTitle());
            existing.setStatus(updatedCourse.getStatus());
            existing.setInstructorId(updatedCourse.getInstructorId());
        }
        return existing;
    }

    public Course deleteById(Long id) {
        Course existing = findById(id);
        if (existing != null) {
            courses.remove(existing);
        }
        return existing;
    }
}