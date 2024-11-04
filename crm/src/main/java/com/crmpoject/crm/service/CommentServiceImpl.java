package com.crmpoject.crm.service;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.crmpoject.crm.entities.Comment.Comment;
import com.crmpoject.crm.entities.Task.Task;
import com.crmpoject.crm.repository.CommentRepository;
import com.crmpoject.crm.repository.TaskRepository;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository,
            PlatformTransactionManager transactionManager) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteComment(String title) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {

            List<Task> tasks = taskRepository.findByTitle(title);

            for (Task task : tasks) {
                // Удаляем все комментарии, связанные с задачей
                for (Comment comment : task.getComments()) {
                    commentRepository.delete(comment);
                }
            }

            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            // Откатить транзакцию в случае ошибки
            transactionManager.rollback(status);
            throw ex;
        }
    }

}
