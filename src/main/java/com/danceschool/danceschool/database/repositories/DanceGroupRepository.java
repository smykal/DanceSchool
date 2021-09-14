package com.danceschool.danceschool.database.repositories;

import com.danceschool.danceschool.database.DanceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanceGroupRepository extends JpaRepository<DanceGroup, Integer> {
}
