package com.korsuk.digital_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentToSave {

    private String name;

    private Integer age;

    private GroupToSave group;
}
