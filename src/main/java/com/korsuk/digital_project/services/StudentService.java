package com.korsuk.digital_project.services;

import com.korsuk.digital_project.converters.GroupConverter;
import com.korsuk.digital_project.converters.StudentConverter;
import com.korsuk.digital_project.dtos.StudentDto;
import com.korsuk.digital_project.dtos.StudentToSave;
import com.korsuk.digital_project.entities.Student;
import com.korsuk.digital_project.exceptions.ExistEntityException;
import com.korsuk.digital_project.exceptions.ResourceNotFoundException;
import com.korsuk.digital_project.repositories.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentConverter studentConverter;

    private final GroupConverter groupConverter;

    public StudentDto getStudentById(Long id) {
        Student student = repository.findStudentById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return studentConverter.entityToDto(student);
    }

    public List<StudentDto> getAllStudents(){
        List<Student> students = repository.findAll();
        return students.stream().map(studentConverter::entityToDto).collect(Collectors.toList());
    }

    public StudentDto getStudentByName(String name) {
        Student student = repository.findStudentByName(name).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return studentConverter.entityToDto(student);
    }


    @Transactional
    public StudentDto saveStudent (StudentToSave studentDto) {
        if (repository.existsByName(studentDto.getName()))
            throw new ExistEntityException("Student already exist");
        else {
            Student studentToSave = new Student();
            studentToSave.setName(studentDto.getName());
            studentToSave.setAge(studentDto.getAge());
            studentToSave.setGroup(groupConverter.dtoToEntity(studentDto.getGroup()));
            Student savedStudent = repository.saveAndFlush(studentToSave);
            return studentConverter.entityToDto(savedStudent);
        }
    }

    @Transactional
    public StudentDto editStudent (StudentDto studentDto) {
        Student studentToUpdate = repository.findStudentById(studentDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        studentToUpdate.setName(studentDto.getName());
        studentToUpdate.setAge(studentDto.getAge());
        studentToUpdate.setGroup(groupConverter.dtoToEntity(studentDto.getGroup()));
        Student updatedStudent = repository.saveAndFlush(studentToUpdate);
        return studentConverter.entityToDto(updatedStudent);
    }

    public void deleteStudent(Long id) {
        repository.deleteStudentById(id);
    }
}
