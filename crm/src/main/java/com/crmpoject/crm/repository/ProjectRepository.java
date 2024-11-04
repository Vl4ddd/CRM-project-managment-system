package com.crmpoject.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crmpoject.crm.entities.Project.Project;

@RepositoryRestResource(path = "project")
public interface ProjectRepository extends CrudRepository<Project, Long>{

}
