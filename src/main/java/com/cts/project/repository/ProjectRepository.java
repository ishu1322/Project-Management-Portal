package com.cts.project.repository;

import org.springframework.stereotype.Repository;
import com.cts.project.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	  
}
