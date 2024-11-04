package com.crmpoject.repository;

import org.springframework.data.repository.CrudRepository;

import com.crmpoject.crm.entities.Attachment.Attachment;

public interface AttachmentRepository extends CrudRepository<Attachment, Long>{

}
