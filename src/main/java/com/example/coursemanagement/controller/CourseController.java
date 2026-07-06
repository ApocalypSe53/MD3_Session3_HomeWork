package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        List<Course> list = courseService.getAllCourses();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách khóa học thành công", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(ApiResponse.success("Tìm thấy khóa học phù hợp", course));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy khóa học với ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        if (course.getTitle() == null || course.getInstructorId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Dữ liệu không hợp lệ (Thiếu Title hoặc Instructor ID)"));
        }
        Course created = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Khởi tạo khóa học thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable Long id, @RequestBody Course course) {
        Course updated = courseService.updateCourse(id, course);
        if (updated != null) {
            return ResponseEntity.ok(ApiResponse.success("Cập nhật khóa học thành công", updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy khóa học để cập nhật"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable Long id) {
        Course deleted = courseService.deleteCourseById(id);
        if (deleted != null) {
            return ResponseEntity.ok(ApiResponse.success("Xóa khóa học thành công", deleted));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy khóa học cần xóa"));
    }
}