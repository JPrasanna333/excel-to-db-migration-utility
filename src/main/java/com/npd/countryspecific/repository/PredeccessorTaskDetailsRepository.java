package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.TasksDetailsPredecessorTaskTemplate;

@Repository
public interface PredeccessorTaskDetailsRepository extends JpaRepository<TasksDetailsPredecessorTaskTemplate, Integer> {

}
