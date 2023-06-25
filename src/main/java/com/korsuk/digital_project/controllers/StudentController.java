package com.korsuk.digital_project.controllers;

import com.korsuk.digital_project.dtos.StudentDto;
import com.korsuk.digital_project.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    public List<StudentDto> findAll() {
        return studentService.getAllStudents();
    }


    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
       return studentService.saveStudent(studentDto);
    }

    @PutMapping
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        return studentService.editStudent(studentDto);
    }

    @DeleteMapping
    public void deleteStudent(Long id) {
        studentService.deleteStudent(id);
    }


}
