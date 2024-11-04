package com.crmpoject.crm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crmpoject.crm.entities.Task.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{

    List<Task> findByTitle(String title);


}
