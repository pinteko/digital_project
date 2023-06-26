package com.korsuk.digital_project.converters;

import com.korsuk.digital_project.dtos.GroupDto;
import com.korsuk.digital_project.dtos.GroupToSave;
import com.korsuk.digital_project.entities.Group;
import com.korsuk.digital_project.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GroupConverter {
    private final GroupService groupService;
    public Group dtoToEntity(GroupToSave groupToSave) {
        Group returnGroup = groupService.getGroupByTitle(groupToSave.getTitle());
        return Objects.requireNonNullElseGet(returnGroup, () -> groupService.saveGroup(groupToSave));
    }

    public Group dtoToEntity(GroupDto groupDto) {
        return groupService.getGroupById(groupDto.getId());
    }

    public GroupDto entityToDto(Group group) {
        return new GroupDto(group.getId(), group.getTitle());
    }
}
