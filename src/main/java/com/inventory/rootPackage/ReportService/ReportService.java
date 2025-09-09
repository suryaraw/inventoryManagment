package com.inventory.rootPackage.ReportService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.rootPackage.ReportModel.Report;

@Service
public class ReportService {

	public List<Report> getInventoryReport() {
		List<Report> reports = new ArrayList<>();
		reports.add(new Report("SUKOVIA", 100, 20));
		reports.add(new Report("AIRCRAFT", 50, 5));
		reports.add(new Report("BANZER", 200, 40));
		return reports;
	}

}
