package com.danceschool.danceschool.database.services;


import com.danceschool.danceschool.database.DanceGroup;
import com.danceschool.danceschool.database.repositories.DanceGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DanceGroupService {

    @Autowired
    public DanceGroupRepository danceGroupRepository;

    public DanceGroup readDanceGroup(int danceGroupId) {
        System.out.println("read Dance Group by Id: " + danceGroupId);
        DanceGroup danceGroup = danceGroupRepository.getOne(danceGroupId);
        return danceGroup;
    }
}
