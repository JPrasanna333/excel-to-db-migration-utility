package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.ProjectClassificationTasksDetails;

@Repository
public interface ProjectClassificationTaskRepository extends JpaRepository<ProjectClassificationTasksDetails, Integer>{

}
