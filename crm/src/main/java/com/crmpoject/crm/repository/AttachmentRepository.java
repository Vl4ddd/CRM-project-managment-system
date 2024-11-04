package com.crmpoject.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crmpoject.crm.entities.Attachment.Attachment;

@RepositoryRestResource(path = "Attachment")
public interface AttachmentRepository extends CrudRepository<Attachment, Long>{

}
