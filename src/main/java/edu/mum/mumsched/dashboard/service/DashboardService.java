package edu.mum.mumsched.dashboard.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DashboardService {
    Map<String, Long> getSectionChartData();

    Map<String, Long> getEntryChartData();
}
