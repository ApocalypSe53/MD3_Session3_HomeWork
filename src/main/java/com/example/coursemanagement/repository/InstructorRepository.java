package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>();
    private Long currentId = 3L;

    public InstructorRepository() {
        instructors.add(new Instructor(1L, "Nguyen Van A", "nva@example.com"));
        instructors.add(new Instructor(2L, "Tran Thi B", "ttb@example.com"));
    }

    public List<Instructor> findAll() {
        return instructors;
    }

    public Instructor findById(Long id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Instructor create(Instructor instructor) {
        instructor.setId(currentId++);
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(Long id, Instructor updatedInstructor) {
        Instructor existing = findById(id);
        if (existing != null) {
            existing.setName(updatedInstructor.getName());
            existing.setEmail(updatedInstructor.getEmail());
        }
        return existing;
    }

    public Instructor deleteById(Long id) {
        Instructor existing = findById(id);
        if (existing != null) {
            instructors.remove(existing);
        }
        return existing;
    }
}