package com.example.coursemanagement.service;

import com.example.coursemanagement.model.Instructor;
import com.example.coursemanagement.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.create(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructor) {
        return instructorRepository.update(id, instructor);
    }

    public Instructor deleteInstructorById(Long id) {
        return instructorRepository.deleteById(id);
    }
}