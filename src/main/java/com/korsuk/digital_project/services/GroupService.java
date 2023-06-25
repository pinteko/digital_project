package com.korsuk.digital_project.services;

import com.korsuk.digital_project.dtos.GroupDto;
import com.korsuk.digital_project.dtos.GroupToSave;
import com.korsuk.digital_project.entities.Group;
import com.korsuk.digital_project.exceptions.ExistEntityException;
import com.korsuk.digital_project.exceptions.ResourceNotFoundException;
import com.korsuk.digital_project.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group getGroupById(Long id) {
       return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group not found"));
    }

    public Group getGroupByTitle(String title) {
        return groupRepository.findByTitle(title);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public Group updateGroup(Group group) {
        Group groupToUpdate = groupRepository.findById(group.getId()).orElseThrow(() -> new ResourceNotFoundException("Group not found"));
        groupToUpdate.setTitle(group.getTitle());
        return groupRepository.saveAndFlush(groupToUpdate);
    }

    public Group saveGroup(GroupToSave groupToSave) {
        if (groupRepository.existsByTitle(groupToSave.getTitle()))
            throw new ExistEntityException("Group already exist");
        else {
            Group group = new Group();
            group.setTitle(groupToSave.getTitle());
            return groupRepository.saveAndFlush(group);
        }
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
