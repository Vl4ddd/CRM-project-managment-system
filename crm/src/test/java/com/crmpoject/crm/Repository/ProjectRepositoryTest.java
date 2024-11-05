package com.crmpoject.crm.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crmpoject.crm.entities.Project.Project;
import com.crmpoject.crm.entities.User.User;
import com.crmpoject.crm.repository.ProjectRepository;

@SpringBootTest
public class ProjectRepositoryTest {

    private final ProjectRepository projectRepository;

    @Autowired
    ProjectRepositoryTest(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Test
    void createTestUser() {
        Project project = new Project();
        project.setTitle("NaumenCRM");
        projectRepository.save(project);

        Project project1 = new Project();
        project1.setTitle("Telebot");
        projectRepository.save(project1);

        Project project2 = new Project();
        project2.setTitle("Catalog");
        projectRepository.save(project2);

        Project project3 = new Project();
        project3.setTitle("WeatherInfo");
        projectRepository.save(project3);

    }



}
