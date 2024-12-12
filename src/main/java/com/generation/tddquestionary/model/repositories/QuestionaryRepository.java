package com.generation.tddquestionary.model.repositories;

import com.generation.tddquestionary.model.entities.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionaryRepository extends JpaRepository<Questionary, Long>
{
}
