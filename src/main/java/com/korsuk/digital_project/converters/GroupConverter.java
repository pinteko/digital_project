package com.korsuk.digital_project.converters;

import com.korsuk.digital_project.dtos.GroupDto;
import com.korsuk.digital_project.entities.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter {
    public Group dtoToEntity(GroupDto groupDto) {
        return new Group(groupDto.getId(), groupDto.getTitle());
    }

    public GroupDto entityToDto(Group group) {
        return new GroupDto(group.getId(), group.getTitle());
    }
}
