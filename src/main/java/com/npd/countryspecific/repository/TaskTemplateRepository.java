package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.TaskTemplate;

@Repository
public interface TaskTemplateRepository extends JpaRepository<TaskTemplate, Integer> {

}
