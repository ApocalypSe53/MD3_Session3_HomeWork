package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EnrollmentRepository {
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1L, "Nguyễn Văn A", 1L));
        enrollments.add(new Enrollment(2L, "Trần Thị B", 1L));
        enrollments.add(new Enrollment(3L, "Lê Văn C", 2L));
        enrollments.add(new Enrollment(4L, "Phạm Minh D", 3L));
    }

    public ArrayList<Enrollment> findAll() {
        return enrollments;
    }
}
