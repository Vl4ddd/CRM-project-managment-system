package com.crmpoject.crm.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crmpoject.crm.service.ReportService;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {


    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<Long> createReport() {
        Long reportId = reportService.createReport(); 
        reportService.generateReport(reportId); 
        return ResponseEntity.ok(reportId); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getReportContent(@PathVariable Long id) {
        String reportContent = reportService.getReportContent(id); 
        return ResponseEntity.ok(reportContent); 
    }

}
