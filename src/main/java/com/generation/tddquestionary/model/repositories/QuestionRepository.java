package com.generation.tddquestionary.model.repositories;

import com.generation.tddquestionary.model.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
}
