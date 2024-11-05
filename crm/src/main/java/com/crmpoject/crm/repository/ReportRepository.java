package com.crmpoject.crm.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;


import com.crmpoject.crm.entities.Report.Report;

@RepositoryRestResource(path = "report")
public interface ReportRepository extends CrudRepository<Report, Long>{

}
