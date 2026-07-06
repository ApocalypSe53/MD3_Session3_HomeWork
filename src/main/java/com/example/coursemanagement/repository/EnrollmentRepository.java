package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private Long currentId = 3L;

    public EnrollmentRepository() {
        // courseId 1 và 2 là id của các Course mẫu ở trên
        enrollments.add(new Enrollment(1L, "Sinh vien X", 1L));
        enrollments.add(new Enrollment(2L, "Sinh vien Y", 2L));
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Enrollment findById(Long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Enrollment create(Enrollment enrollment) {
        enrollment.setId(currentId++);
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(Long id, Enrollment updatedEnrollment) {
        Enrollment existing = findById(id);
        if (existing != null) {
            existing.setStudentName(updatedEnrollment.getStudentName());
            existing.setCourseId(updatedEnrollment.getCourseId());
        }
        return existing;
    }

    public Enrollment deleteById(Long id) {
        Enrollment existing = findById(id);
        if (existing != null) {
            enrollments.remove(existing);
        }
        return existing;
    }
}