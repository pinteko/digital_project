package com.korsuk.digital_project.converters;

import com.korsuk.digital_project.dtos.StudentDto;
import com.korsuk.digital_project.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentConverter {

    private final GroupConverter groupConverter;

    public Student dtoToEntity(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getName(), studentDto.getAge(), groupConverter.dtoToEntity(studentDto.getGroup()));
    }

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getAge(), groupConverter.entityToDto(student.getGroup()));
    }
}
