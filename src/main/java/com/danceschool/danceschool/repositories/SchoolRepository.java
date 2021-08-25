package com.danceschool.danceschool.repositories;

import com.danceschool.danceschool.schools.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolRepository extends JpaRepository<School, UUID> {
}