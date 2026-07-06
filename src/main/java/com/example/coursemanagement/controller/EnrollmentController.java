package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAll() {
        List<Enrollment> list = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách lượt đăng ký thành công", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment != null) {
            return ResponseEntity.ok(ApiResponse.success("Tìm thấy thông tin đăng ký", enrollment));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy lượt đăng ký với ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(@RequestBody Enrollment enrollment) {
        if (enrollment.getStudentName() == null || enrollment.getCourseId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Dữ liệu không hợp lệ (Thiếu tên học viên hoặc Course ID)"));
        }
        Enrollment created = enrollmentService.createEnrollment(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Đăng ký khóa học thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        Enrollment updated = enrollmentService.updateEnrollment(id, enrollment);
        if (updated != null) {
            return ResponseEntity.ok(ApiResponse.success("Cập nhật thông tin đăng ký thành công", updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy thông tin đăng ký để cập nhật"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> delete(@PathVariable Long id) {
        Enrollment deleted = enrollmentService.deleteEnrollmentById(id);
        if (deleted != null) {
            return ResponseEntity.ok(ApiResponse.success("Hủy lượt đăng ký học thành công", deleted));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy thông tin lượt đăng ký cần xóa"));
    }
}