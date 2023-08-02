package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.ProjectType;

@Repository
public interface ProjectTypeRepository extends JpaRepository<ProjectType, String> {
	ProjectType findByDisplayName(String displayName);

}
