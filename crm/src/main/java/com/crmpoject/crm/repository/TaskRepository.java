package com.crmpoject.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.crmpoject.crm.entities.Task.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{

    @Query("SELECT t FROM Task t WHERE t.title = :title")
    List<Task> findByTitle(@Param("title") String title);


}
