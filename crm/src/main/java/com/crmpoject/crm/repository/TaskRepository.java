package com.crmpoject.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crmpoject.crm.entities.Task.Task;

@RepositoryRestResource(path = "task")
public interface TaskRepository extends CrudRepository<Task, Long>{

    @Query("SELECT t FROM Task t WHERE t.title = :title")
    List<Task> findByTitle(@Param("title") String title);


}
