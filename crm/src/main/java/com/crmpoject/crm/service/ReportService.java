package com.crmpoject.crm.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.crmpoject.crm.entities.Project.Project;
import com.crmpoject.crm.entities.Report.Report;
import com.crmpoject.crm.entities.Report.ReportStatus;
import com.crmpoject.crm.repository.ProjectRepository;
import com.crmpoject.crm.repository.ReportRepository;
import com.crmpoject.crm.repository.UserRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        reportRepository.save(report);
        return report.getId();
    }

    public String getReportContent(Long reportId) {
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
            return "Отчет не найден.";
        }
        if (report.getStatus() == ReportStatus.CREATED) {
            return "Отчет еще не сформирован.";
        }
        if (report.getStatus() == ReportStatus.ERROR) {
            return "При формировании отчета произошла ошибка.";
        }
        return report.getContent();
    }

    @Async
    public CompletableFuture<Void> generateReport(Long reportId) {
        long startTime = System.currentTimeMillis();
        Report report = reportRepository.findById(reportId).orElseThrow();

        try {
            long userCountStartTime = System.currentTimeMillis();
            CompletableFuture<Integer> userCountFuture = CompletableFuture.supplyAsync(() -> {
                return getUserCount();
            });
            long userCountDuration = (System.currentTimeMillis() - userCountStartTime);

            long projectCountStartTime = System.currentTimeMillis();
            CompletableFuture<List<Project>> projectsFuture = CompletableFuture.supplyAsync(() -> {
                return getObjectList();
            });
            long projectCountDuration = System.currentTimeMillis() - projectCountStartTime;

            Integer userCount = userCountFuture.join();
            List<Project> projectList = projectsFuture.join();

            StringBuilder reportContent = new StringBuilder();
            reportContent.append("Количество пользователей: ").append(userCount).append("\n")
                    .append("Время на подсчет пользователей: ").append(userCountDuration).append(" мс\n")
                    .append("Проекты:\n");

            for (Project project : projectList) {
                reportContent.append(" - ").append(project.getTitle()).append("\n");
            }

            reportContent.append("Время на получение проектов: ").append(projectCountDuration).append(" мс\n")
                    .append("Общее время формирования отчета: ").append(System.currentTimeMillis() - startTime)
                    .append(" мс");

            report.setContent(reportContent.toString());
            report.setStatus(ReportStatus.COMPLETED);
            reportRepository.save(report);

        } catch (Exception e) {
            report.setStatus(ReportStatus.ERROR);
            report.setContent("Произошла ошибка при формировании отчета: " + e.getMessage());
            reportRepository.save(report);
        }

        return CompletableFuture.completedFuture(null);
    }

    private int getUserCount() {
        // Возвращаем количество пользователей
        return (int) userRepository.count(); // Используем метод count() репозитория
    }

    // Метод для получения списка проектов
    private List<Project> getObjectList() {
        // Возвращаем список проектов
        return (List<Project>) projectRepository.findAll(); // Используем метод findAll() репозитория
    }

}
