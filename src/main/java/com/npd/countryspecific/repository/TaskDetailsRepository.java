package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.TasksDetails;

@Repository
public interface TaskDetailsRepository extends JpaRepository<TasksDetails, Integer> {
	@Query("SELECT MAX(taskDetails.id) FROM TasksDetails taskDetails")
	Integer getMaxTaskId();

}
