package com.cts.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.project.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String>{

	@Query("SELECT COUNT(*) from Resource where project_code= :projectCode and role='Tester'")
	public int getNumberOfTesters(@Param("projectCode") int projectCode);
	
	@Query("SELECT COUNT(*) from Resource where project_code= :projectCode and role='Developer'")
	public int getNumberOfDevelopers(@Param("projectCode") int projectCode);
}
