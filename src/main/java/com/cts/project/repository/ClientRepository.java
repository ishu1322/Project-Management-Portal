package com.cts.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.project.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
