package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.TasksDetailsPrimaryTaskTemplate;

@Repository
public interface PrimaryTaskDetailsRepository extends JpaRepository<TasksDetailsPrimaryTaskTemplate, Integer> {

}
