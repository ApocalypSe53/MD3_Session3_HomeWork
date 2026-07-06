package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Enrollment;
import com.example.coursemanagement.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        return (enrollment != null) ? ResponseEntity.ok(enrollment) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Enrollment> create(@RequestBody Enrollment enrollment) {
        if (enrollment.getStudentName() == null || enrollment.getCourseId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.createEnrollment(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        Enrollment updatedEnrollment = enrollmentService.updateEnrollment(id, enrollment);
        return (updatedEnrollment != null) ? ResponseEntity.ok(updatedEnrollment) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Enrollment> delete(@PathVariable Long id) {
        Enrollment deletedEnrollment = enrollmentService.deleteEnrollmentById(id);
        return (deletedEnrollment != null) ? ResponseEntity.ok(deletedEnrollment) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}