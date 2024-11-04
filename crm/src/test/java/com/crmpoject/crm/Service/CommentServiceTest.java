package com.crmpoject.crm.Service;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crmpoject.crm.entities.Comment.Comment;
import com.crmpoject.crm.entities.Task.Task;
import com.crmpoject.crm.repository.CommentRepository;
import com.crmpoject.crm.repository.TaskRepository;
import com.crmpoject.crm.service.CommentServiceImpl;

@SpringBootTest
public class CommentServiceTest {

    private final CommentServiceImpl commentService;
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public CommentServiceTest(CommentServiceImpl commentService,
            CommentRepository commentRepository,
            TaskRepository taskRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @Test
    void testDeleteComment() {
     
        Task task = new Task();
        task.setTitle(UUID.randomUUID().toString());
        taskRepository.save(task);
       
        Comment comment1 = new Comment();
        comment1.setTitle(UUID.randomUUID().toString());
        comment1.setTask(task);
        commentRepository.save(comment1);
        
        Comment comment2 = new Comment();
        comment2.setTitle(UUID.randomUUID().toString());
        comment2.setTask(task);
        commentRepository.save(comment2);
     
        commentService.deleteComment(task.getTitle());

        Optional<Task> foundTask = taskRepository.findById(task.getId());
        Assertions.assertFalse(foundTask.isEmpty());
        
        Optional<Comment> foundComment1 = commentRepository.findById(comment1.getId());
        Assertions.assertTrue(foundComment1.isEmpty());
      
        Optional<Comment> foundComment2 = commentRepository.findById(comment2.getId());
        Assertions.assertTrue(foundComment2.isEmpty());
    }

}
