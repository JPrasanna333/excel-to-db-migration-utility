package com.npd.countryspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npd.countryspecific.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, String>{
	Region findByDisplayName(String displayName);


}
