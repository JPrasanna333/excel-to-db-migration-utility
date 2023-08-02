package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.ProjectClassification;

@Repository
public interface ProjectClassifcationRepository extends JpaRepository<ProjectClassification, Integer>{

}
