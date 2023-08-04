package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.ProjectClassification;

@Repository
public interface ProjectClassificationRepository extends JpaRepository<ProjectClassification, Integer> {
	@Query("SELECT MAX(projectClassification.id) FROM ProjectClassification projectClassification")
	Integer getMaxProjectId();

}
