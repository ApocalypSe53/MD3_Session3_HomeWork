package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Instructor;
import com.example.coursemanagement.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getById(@PathVariable Long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor != null) {
            return ResponseEntity.ok(instructor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
        if (instructor.getName() == null || instructor.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Instructor createdInstructor = instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInstructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> update(@PathVariable Long id, @RequestBody Instructor instructor) {
        Instructor updatedInstructor = instructorService.updateInstructor(id, instructor);
        if (updatedInstructor != null) {
            return ResponseEntity.ok(updatedInstructor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instructor> delete(@PathVariable Long id) {
        Instructor deletedInstructor = instructorService.deleteInstructorById(id);
        if (deletedInstructor != null) {
            return ResponseEntity.ok(deletedInstructor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}