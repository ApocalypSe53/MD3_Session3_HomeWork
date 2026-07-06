package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        List<Instructor> list = instructorService.getAllInstructors();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách giảng viên thành công", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getById(@PathVariable Long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor != null) {
            return ResponseEntity.ok(ApiResponse.success("Tìm thấy giảng viên phù hợp", instructor));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy giảng viên với ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(@RequestBody Instructor instructor) {
        if (instructor.getName() == null || instructor.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Dữ liệu đầu vào không hợp lệ (Thiếu Name hoặc Email)"));
        }
        Instructor created = instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Thêm mới giảng viên thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(@PathVariable Long id, @RequestBody Instructor instructor) {
        Instructor updated = instructorService.updateInstructor(id, instructor);
        if (updated != null) {
            return ResponseEntity.ok(ApiResponse.success("Cập nhật thông tin giảng viên thành công", updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy giảng viên để cập nhật (ID: " + id + ")"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> delete(@PathVariable Long id) {
        Instructor deleted = instructorService.deleteInstructorById(id);
        if (deleted != null) {
            return ResponseEntity.ok(ApiResponse.success("Xóa giảng viên thành công", deleted));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy giảng viên để xóa (ID: " + id + ")"));
    }
}