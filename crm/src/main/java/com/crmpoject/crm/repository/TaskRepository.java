package com.crmpoject.repository;

import org.springframework.data.repository.CrudRepository;

import com.crmpoject.crm.entities.Task.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{

    

}
