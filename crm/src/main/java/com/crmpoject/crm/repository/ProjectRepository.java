package com.crmpoject.crm.repository;

import org.springframework.data.repository.CrudRepository;

import com.crmpoject.crm.entities.Project.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

}
