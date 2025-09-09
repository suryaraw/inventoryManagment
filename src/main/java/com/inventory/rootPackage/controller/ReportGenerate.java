package com.inventory.rootPackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.rootPackage.ReportModel.Report;
import com.inventory.rootPackage.ReportService.ReportService;

@Controller
public class ReportGenerate {

	@Autowired
	ReportService reportService;

	@GetMapping("/report")
	public String showReport(Model model) {
		List<Report> reports = reportService.getInventoryReport();
		model.addAttribute("reports", reports);
		return "report";
	}

}
