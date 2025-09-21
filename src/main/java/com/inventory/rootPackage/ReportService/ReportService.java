package com.inventory.rootPackage.ReportService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.ShoperPaid;
import com.inventory.rootPackage.repository.ReportRepo;

@Service
public class ReportService {
	@Autowired
	private ReportRepo reportRepo;

	public List<ShoperPaid> getInventoryReport() {
		List<ShoperPaid> reports = reportRepo.findAll();
		
//		reports.add(new Report("SUKOVIA-17", 100, 20));
//		reports.add(new Report("AIRCRAFT", 50, 5));
//		reports.add(new Report("PANZER", 200, 40));
//		reports.add(new Report("FF-22", 200, 40));
//		reports.add(new Report("FAT MAN", 200, 40));
		return reports;
	}

}
